package com.clownswhocode.tsuro.model;

import com.clownswhocode.tsuro.Util.Position;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class DumbStrategy implements iStrategy {
    private Position firstTilePosition;

    public DumbStrategy() {
    }

    // On intermediate placements just chooses the first tile with no rotations
    @Override
    public ArrayList<Integer> chooseTilePlacement(ArrayList<ArrayList<Tile>> board, int turnNumber, ArrayList<Tile> hand,
                                                  Position pos, int portNumber, iPlayer player, iRuleChecker ruleChecker) throws RemoteException {
        ArrayList<Integer> result = new ArrayList<>();
        int tileIndex;
        if (turnNumber == 1) {
            tileIndex = 2;
            Tile thirdTile = hand.get(tileIndex);
            Position initialPosition = this.initialTilePosition(board, thirdTile, hand, player, ruleChecker, turnNumber);
            this.firstTilePosition = initialPosition;
            result.add(tileIndex);
            result.add(initialPosition.get_x());
            result.add(initialPosition.get_y());
        } else {
            tileIndex = 0;
            Position newPosition;
            switch (portNumber) {
                case 1:
                case 2:
                    newPosition = new Position(pos.get_x(), pos.get_y() - 1);
                    break;
                case 3:
                case 4:
                    newPosition = new Position(pos.get_x() + 1, pos.get_y());
                    break;
                case 5:
                case 6:
                    newPosition = new Position(pos.get_x(), pos.get_y() + 1);
                    break;
                case 7:
                case 8:
                    newPosition = new Position(pos.get_x() - 1, pos.get_y());
                    break;
                default:
                    newPosition = new Position(0, 0);
            }
            result.add(tileIndex);
            result.add(newPosition.get_x());
            result.add(newPosition.get_y());
        }
        result.add(0);
        return result;
    }

    /**
     * In a clockwise spiral manner, determine initial tile position
     *
     * @param t The tile that is attempting to be placed
     * @return The first Position on the border that is valid
     */
    private Position initialTilePosition(ArrayList<ArrayList<Tile>> board, Tile t, ArrayList<Tile> hand, iPlayer player,
                                         iRuleChecker ruleChecker, int turnNumber) throws RemoteException {
        // top border
        for (int i = 0; i < 10; i++) {
            if (ruleChecker.checkTilePlacement(board, t, new Position(i, 0), player, player, turnNumber, hand)) {
                return new Position(i, 0);
            }
        }
        // right border
        for (int j = 1; j < 10; j++) {
            if (ruleChecker.checkTilePlacement(board, t, new Position(9, j), player, player, turnNumber, hand)) {
                return new Position(9, j);
            }
        }
        // bottom border
        for (int i = 8; i > -1; i--) {
            if (ruleChecker.checkTilePlacement(board, t, new Position(i, 9), player, player, turnNumber, hand)) {
                return new Position(i, 9);
            }
        }
        // left border
        for (int j = 8; j > 0; j--) {
            if (ruleChecker.checkTilePlacement(board, t, new Position(0, j), player, player, turnNumber, hand)) {
                return new Position(0, j);
            }
        }
        return new Position(0, 0);
    }

    @Override
    public int chooseTokenPlacement(ArrayList<ArrayList<Tile>> board, iPlayer player,
                                    iRuleChecker ruleChecker, int turnNumber) throws RemoteException {
        for (int i = 0; i < 7; i++) {
            if (ruleChecker.checkTokenPlacement(board, this.firstTilePosition, i + 1, player, player, turnNumber)) {
                return i + 1;
            }
        }
        return -1;
    }
}
