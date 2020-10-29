package com.clownswhocode.tsuro.model;

import com.clownswhocode.tsuro.Util.GameState;
import com.clownswhocode.tsuro.Util.Position;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface iReferee {

  /**
   *
   */
  void runGame() throws RemoteException;

  /**
   * Called by a player in request to place a token on a port.
   *
   * @param player     the player requesting to place the token
   * @param pos        position of the tile
   * @param portNumber port number of the tile
   */
  void placeToken(iPlayer player, Position pos, int portNumber) throws RemoteException;

  /**
   * Called by a player in request to place a tile on a port.
   *
   * @param player the player requested to place the tile
   * @param pos    position of the tile
   * @param t      tile to be placed
   */
  void placeTile(iPlayer player, Position pos, Tile t) throws RemoteException;

  /**
   * Called by a player to check if a tile placement is valid
   *
   * @param player the player requesting to check the placement
   * @param pos    position of the tile placement
   * @param t      tile to be placed
   * @return true if valid, false if valid
   */
  boolean isValidTilePlacement(iPlayer player, Position pos, Tile t) throws RemoteException;

  /**
   * Called by a player to check if a token placement is valid
   *
   * @param player     the player requesting to check the placement
   * @param pos        position of the tile that the player wants to place the token on
   * @param portNumber port number on the tile that the player wants to place the token on
   * @return true if valid, false if valid
   */
  boolean isValidTokenPlacement(iPlayer player, Position pos, int portNumber) throws RemoteException;

  /**
   * Check if this game is over
   *
   * @return Boolean
   */
  boolean isGameOver() throws RemoteException;


  /**
   * Requested by the player to be added to the game
   */
  boolean addPlayer(iPlayer player) throws RemoteException;

  String availableColor();

  /**
   * Shows all players in the current game
   *
   * @return ArrayList<iPlayer> all players
   */
  ArrayList<Tile> getCurrentHand();

  /**
   * Get all players in the game.
   *
   * @return ArrayList<iPlayer>
   */
  ArrayList<iPlayer> getPlayers();

  /**
   * Get the current turn of the game
   *
   * @return int the turn number
   */
  int getTurnNumber();

  /**
   * Get the current gamestate of the game
   *
   * @return Enum GameState
   */
  GameState getGameState();

  /**
   * construct a Color Token
   */
  ColorToken constructToken();

  /**
   * Gets the color of all the player's opponents' color tokens
   *
   * @param player the player requesting the colors
   * @return ArrayList<String> the list of all colors of the opponents' color tokens
   */
  ArrayList<String> getColorOfOpponents(iPlayer player) throws RemoteException;

  /**
   * Get the next available avatar color to be assigned to a new player
   *
   * @param players all players
   * @return a String which represents a color
   */
  String getAvailableColor(ArrayList<iPlayer> players);

  /**
   * Get the currently selected tile by the current player
   */
  Tile getSelectedTile();

  /**
   * Get the currently selected position by the current player
   */
  Position getSelectedPosition();

  /*
  Gets the game board
   */
  public ArrayList<ArrayList<Tile>> getBoard();

  public ArrayList<Tile> getDeck();
}
