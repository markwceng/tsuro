package com.clownswhocode.tsuro.model;

import com.clownswhocode.tsuro.Util.Position;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface iRuleChecker {

  /**
   * Check if a tile placement attempt by a player is valid
   *
   * @param board                game board
   * @param t                    tile to be placed
   * @param pos                  position on the board
   * @param currentPlayer        current player of the game
   * @param playerAttemptingMove player trying to place tile
   * @param currentTurn          turn number of the game
   * @param currentHand          current hand of the current player
   * @return true if valid, false if not
   */
  boolean checkTilePlacement(ArrayList<ArrayList<Tile>> board, Tile t, Position pos,
                             iPlayer currentPlayer, iPlayer playerAttemptingMove, int currentTurn,
                             ArrayList<Tile> currentHand) throws RemoteException;


  /**
   * Check if a avatar placement attempt by a player is valid
   *
   * @param board                game board
   * @param pos                  position on the board to be placed
   * @param portNumber           port number the player wants to place the avatar at
   * @param currentPlayer        current player of the game
   * @param playerAttemptingMove player trying to place avatar
   * @param currentTurn          turn number of the game
   * @return true if valid, false if not
   */
  boolean checkTokenPlacement(ArrayList<ArrayList<Tile>> board, Position pos, int portNumber,
                              iPlayer currentPlayer, iPlayer playerAttemptingMove, int currentTurn) throws RemoteException;
}
