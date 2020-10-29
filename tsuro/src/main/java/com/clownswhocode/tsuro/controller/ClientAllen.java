package com.clownswhocode.tsuro.controller;

import com.clownswhocode.tsuro.model.ColorToken;
import com.clownswhocode.tsuro.model.iPlayer;
import com.clownswhocode.tsuro.model.Player;

import java.net.*;
import java.io.*;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class ClientAllen {
  private static final String HOST = "localhost";
  private static final int PORT = 8080;

  private Socket clientSocket;
  private PrintWriter out;
  private BufferedReader in;

  public ClientAllen(String address, int port) {
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

  public static void main(String args[]) throws IOException {

    ClientAllen client1 = new ClientAllen("localhost", 8000);
    String color = client1.in.readLine();
    System.out.println("Your color: " + color);

    try {
      // create token with the color sent back from server
      ColorToken token = new ColorToken(color);
      // create a player
      iPlayer player = new Player(token, "allen", "dumb");

      // bind the player created to a registry for the server to use
      // port 8080, ip localhost
      LocateRegistry.createRegistry(PORT);
      Naming.bind("rmi://" + HOST + ":" + PORT + "/allen", player);
      System.out.println("Remote player successfully bonded！");

      // send the join player request
      client1.sendMessage("join allen " + HOST + " " + PORT);

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

