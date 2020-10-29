package com.clownswhocode.tsuro.model;
import com.clownswhocode.tsuro.Util.Position;
import com.clownswhocode.tsuro.Util.TokenStatus;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface iPlayer extends Remote {

  /**
   * Place a tile at a position. Either in first or later turns.
   *
   * @param board game board, necessary to figure out tile placement
   * @param turnNumber turn number, necessary to figure out tile placement
   * @param hand current hand, necessary to figure out tile placement
   * @return Arraylist of integers representing the tile's placement on the board
   */
  ArrayList<Integer> placeTile(ArrayList<ArrayList<Tile>> board, int turnNumber, ArrayList<Tile> hand) throws RemoteException;


  /**
   * Place a token at a position in first turn after placing the first tile
   *
   * @param board game board, necessary to figure out token placement
   * @param turnNumber turn number, necessary to figure out token placement
   * @return port number which to place the token
   */
  int placeToken(ArrayList<ArrayList<Tile>> board, int turnNumber) throws RemoteException;

  /**
   * Check if a tile placement is valid.
   *
   * @param board game board
   * @param pos position to be placed
   * @param t tile to be placed
   * @param turnNumber current turn number
   * @param hand current hand
   * @return true if valid, false if not.
   */
  boolean isValidTilePlacement(ArrayList<ArrayList<Tile>> board, Position pos, Tile t, int turnNumber, ArrayList<Tile> hand) throws RemoteException;

  /**
   * Check if a token placement is valid.
   *
   * @param board game board
   * @param pos position to place token
   * @param portNumber port number to place token
   * @param turnNumber current turn number
   * @return true if valid, false if not.
   */
  boolean isValidTokenPlacement(ArrayList<ArrayList<Tile>> board, Position pos, int portNumber, int turnNumber) throws RemoteException;

  /**
   * Get the color token of the player.
   *
   * @return ColorToken of the player
   */
  ColorToken getToken() throws RemoteException;

  /**
   * Get the color of the player's color token
   *
   * @return String color of player's color token
   */
  String getColor() throws RemoteException;

  /**
   * Set the turn number in which the player is eliminated. Used to rank players
   *
   * @param elim turn number
   */
  void setTurnEliminated(int elim) throws RemoteException;

  /**
   * Get the turn number where the player was terminated
   *
   * @return turn number where player was terminated
   */
  int getTurnEliminated() throws RemoteException;

  /**
   * Get the name of a player
   *
   * @return name of player
   */
  String getName() throws RemoteException;

  void setTokenStatus(TokenStatus status) throws RemoteException;

  void setTokenPortNumber(int portNumber) throws RemoteException;

  void setTokenPosition(Position pos) throws RemoteException;

}