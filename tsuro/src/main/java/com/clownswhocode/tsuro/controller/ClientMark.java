package com.clownswhocode.tsuro.controller;

import com.clownswhocode.tsuro.model.ColorToken;
import com.clownswhocode.tsuro.model.Player;
import com.clownswhocode.tsuro.model.iPlayer;

import java.net.*;
import java.io.*;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class ClientMark {
  private static final String HOST = "localhost";
  private static final int PORT = 8083;

  private Socket clientSocket;
  private PrintWriter out;
  private BufferedReader in;

  public ClientMark(String address, int port) {
    try {
      this.clientSocket = new Socket(address, port);
      System.out.println("Connected to socket.");
      this.out = new PrintWriter(clientSocket.getOutputStream(), true);
      this.in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void sendMessage(String msg) {
    out.println(msg);
  }

  public String readMessage() throws IOException {
    return in.readLine();
  }

  public static void main(String args[]) throws IOException {

    String ip = "localhost";
    int port = 8000;
    ClientMark client1 = new ClientMark("localhost", 8000);
    String color = client1.in.readLine();
    System.out.println("Your color: " + color);

    try {
      // create token with the color sent back from server
      ColorToken token = new ColorToken(color);
      // create a player
      iPlayer smartPlayer = new Player(token, "mark", "smart");

      // bind the player created to a registry for the server to use
      // port 8080, ip localhost
      LocateRegistry.createRegistry(PORT);
      Naming.bind("rmi://" + HOST + ":" + PORT + "/mark", smartPlayer);
      System.out.println("Remote player successfully bonded！");

      // send the join player request
      client1.sendMessage("join mark " + HOST + " " + PORT);

      // continue to wait for any server response
      String line;
      while ((line = client1.in.readLine()) != null) {
        System.out.println("Server: " + line);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

