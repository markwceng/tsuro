package com.clownswhocode.tsuro.model;

import com.clownswhocode.tsuro.Util.Position;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface iStrategy {
    /**
     * Places a tile on the board. Covers both initial and intermediate placements.
     * @param board game board
     * @param turnNumber current turn number
     * @param hand current hand
     * @param pos position to place the tile
     * @param portNumber port number of the player's token
     * @param player the player making the move
     * @param ruleChecker the rule checker
     * @return Arraylist of ints representing where the player wnats to place their tile
     * @throws RemoteException
     */
    ArrayList<Integer> chooseTilePlacement(ArrayList<ArrayList<Tile>> board, int turnNumber, ArrayList<Tile> hand,
                                           Position pos, int portNumber, iPlayer player, iRuleChecker ruleChecker) throws RemoteException;

    /**
     * Places a token on the board. Only allowed on turn 1.
     * @param board game board
     * @param player player making the move
     * @param ruleChecker rule checker
     * @param turnNumber current turn number
     * @return Port number to place the token
     * @throws RemoteException
     */
    int chooseTokenPlacement(ArrayList<ArrayList<Tile>> board, iPlayer player, iRuleChecker ruleChecker, int turnNumber) throws RemoteException;
}
