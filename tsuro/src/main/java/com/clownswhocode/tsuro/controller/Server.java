package com.clownswhocode.tsuro.controller;

import com.clownswhocode.tsuro.Util.FindWinners;
import com.clownswhocode.tsuro.Util.GameState;
import com.clownswhocode.tsuro.model.Referee;
import com.clownswhocode.tsuro.model.iPlayer;
import com.clownswhocode.tsuro.model.iReferee;

import java.net.*;
import java.io.*;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Server extends Thread {

  private InetAddress ip;
  private int port;
  private ServerSocket server;
  private Socket socket;
  private iReferee ref;
  private ArrayList<TsuroThread> clients;
  private BufferedWriter logger;

  public Server(int port, InetAddress ip) throws IOException {
    this.port = port;
    this.ip = ip;
    try {
      FileWriter fw = new FileWriter("xserver.log", false);
      this.logger = new BufferedWriter(fw);

    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  @Override
  public void run() {
    this.clients = new ArrayList<>();
    this.ref = new Referee();
    try {
      server = new ServerSocket(port, 0, ip);
      System.out.println("Server started on port: " + port);
      System.out.println("Waiting for a client ...");

      // Keep the server running
      while (true) {
        // wait until a client has connected to the server
        socket = server.accept();
        System.out.println("Client accepted");
        // new Thread for each client
        TsuroThread tt = new TsuroThread(socket, ref);
        tt.start();
        // add the client to the list of clients
        this.clients.add(tt);
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  // Performs end game actions
  private void initiateEndGame() throws IOException {
    if (this.ref.getGameState().equals(GameState.ENDED)) {
      System.out.println("The game has ended!");
      for (TsuroThread thread : clients) {
        thread.writeMsg("The game has ended!");
        try {
          thread.writeMsg(FindWinners.calculateWinners(ref));
        } catch (RemoteException e) {
          e.printStackTrace();
        }
      }
    }
  }

  // Serves as a timed background thread for our server's player join logic
  private class TimerThread implements Runnable {

    @Override
    public void run() {
      for (TsuroThread thread : clients) {
        try {
          thread.writeMsg("The game has started and is running...");
        } catch (IOException e) {
          e.printStackTrace();
        }
      }

      try {
        ref.runGame();
        initiateEndGame();
        logger.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  private class TsuroThread extends Thread {

    protected Socket socket;
    protected iReferee ref;
    private PrintWriter writer;
    private ScheduledExecutorService scheduler;
    private iPlayer player;


    public TsuroThread(Socket socket, iReferee ref) {
      this.socket = socket;
      this.ref = ref;
      this.scheduler = Executors.newScheduledThreadPool(1);
    }

    public void writeMsg(String text) throws IOException {
      this.writer.println(text);
      logger.write(player.getColor() + " << " + text + "\n");
    }

    public void run() {
      // input
      InputStream input = null;
      try {
        input = socket.getInputStream();
      } catch (IOException e) {
        e.printStackTrace();
      }
      BufferedReader reader = new BufferedReader(new InputStreamReader(input));

      // output
      OutputStream output = null;
      try {
        output = socket.getOutputStream();
      } catch (IOException e) {
        e.printStackTrace();
      }
      writer = new PrintWriter(output, true);
      String color = ref.availableColor();
      writer.println(color);

      // msg from client
      String text = "";

      boolean gameEnded = false;
      while (!gameEnded) {
        try {
          text = reader.readLine();

          System.out.println("Client: " + text);
          logger.write( "New Client >> " + text + "\n");
          String[] stringArray = text.split(" ");
          switch (stringArray[0]) {
            case "join":
              try {
                iPlayer iplayer;
                iplayer = (iPlayer) Naming
                    .lookup(
                        "rmi://" + stringArray[2] + ":" + stringArray[3] + "/" + stringArray[1]);

                if (!iplayer.getColor().equals(color)) {
                  writer.println(iplayer.getColor()
                      + " is not the color assigned to this client by the server.");
                  break;
                }
                this.player = iplayer;
                System.out.println(iplayer.getName() + " has just joined.");
                this.ref.addPlayer(iplayer);
                writer.println("Welcome to the game, " + iplayer.getName() + "!");

              } catch (Exception e) {
                e.printStackTrace();
              }
              break;

            default:
              writer.println("Unrecognizable command.");
              break;
          }
        } catch (IOException e) {
          e.printStackTrace();
        }

        // player count
        int playerNum = this.ref.getPlayers().size();
        // start a 30 seconds timer
        if (playerNum == 3) {
          // a timer thread which waits for 30 seconds and run the game
          scheduler.schedule(new TimerThread(), 30, TimeUnit.SECONDS);

          System.out.println(
              "30 seconds timer started.");
          for (TsuroThread thread : clients) {
            try {
              thread.writeMsg("3 players have joined, starting game when either 2 more players join" +
                  " or 30 seconds elapses!");
            } catch (IOException e) {
              e.printStackTrace();
            }
          }
          // start the game
        } else if (playerNum == 5) {
          // stop the timed thread from running the game again
          this.scheduler.shutdownNow();
          for (TsuroThread thread : clients) {
            try {
              thread.writeMsg("The game has started and is running...");
            } catch (IOException e) {
              e.printStackTrace();
            }
          }
          try {
            this.ref.runGame();
            initiateEndGame();
            gameEnded = true;
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      }
      try {
        logger.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

  }

  public static void main(String[] args) throws IOException {
    InetAddress ip = InetAddress.getByName("127.0.0.1");
    int port = 8000;
    if (args.length == 1) {
      port = Integer.parseInt(args[0]);
    }
    if (args.length == 2) {
      ip = InetAddress.getByName(args[0]);
      port = Integer.parseInt(args[1]);
    }
    Server server = new Server(port, ip);
    server.start();
  }

}