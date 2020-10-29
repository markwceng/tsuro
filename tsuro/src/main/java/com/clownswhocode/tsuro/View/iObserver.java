package com.clownswhocode.tsuro.View;

import com.clownswhocode.tsuro.model.iReferee;

import java.awt.*;
import java.rmi.RemoteException;
import java.util.ArrayList;

// interface for a spectator who can watch the progress of a game
public interface iObserver {

  // renders the current game state. This includes the current board state, which player's turn it is, what
  // their requested placement is, and what tiles the player had in their hand.
  void paintComponent(Graphics g);


  // leaves the game
  void leaveGame();
}
