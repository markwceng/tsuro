package com.clownswhocode.tsuro.model;

import com.clownswhocode.tsuro.Util.Position;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class SmartStrategy implements iStrategy {
    private Position firstTilePosition;

    public SmartStrategy() {
    }

    // On intermediate placements first checks the second tile with rotations from 0 to 270, then the first tile with
    // rotations from 0 to 270. Player uses the first valid tile placement in that respective order.
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
            result.add(0);
        } else {
            ArrayList<Tile> allPossibleMoves = new ArrayList<Tile>();
            for (int tile = 1; tile >= 0; tile--) {
                for (int i = 0; i < 4; i++) {
                    Tile copy = Tile.deepCopy(hand.get(tile), Tile.class);
                    copy.rotate(90 * i);
                    allPossibleMoves.add(copy);
                }
            }
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

            boolean placedTile = false;
            for (int i = 0; i < 8; i++) {
                if (ruleChecker.checkTilePlacement(board, allPossibleMoves.get(i), newPosition, player, player, turnNumber, hand)) {
                    if (i >= 0 && i <= 3) {
                        result.add(1);
                    }
                    else {
                        result.add(0);
                    }
                    result.add(newPosition.get_x());
                    result.add(newPosition.get_y());
                    switch(i) {
                        case 0:
                        case 4:
                            result.add(0);
                            break;
                        case 1:
                        case 5:
                            result.add(90);
                            break;
                        case 2:
                        case 6:
                            result.add(180);
                            break;
                        case 3:
                        case 7:
                            result.add(270);
                            break;
                    }
                    placedTile = true;
                    return result;
                }
            }

            // Default placements if there are no valid placements
            if (placedTile == false) {
                result.add(1);
                result.add(newPosition.get_x());
                result.add(newPosition.get_y());
                result.add(0);
            }
        }
        return result;
    }

    /**
     * In a counterclockwise spiral manner starting from (0,1), determine initial tile position
     *
     * @param t The tile that is attempting to be placed
     * @return The first Position on the border that is valid
     */
    private Position initialTilePosition(ArrayList<ArrayList<Tile>> board, Tile t, ArrayList<Tile> hand, iPlayer player,
                                         iRuleChecker ruleChecker, int turnNumber) throws RemoteException {
        // left border
        for (int j = 1; j < 10; j++) {
            if (ruleChecker.checkTilePlacement(board, t, new Position(0, j), player, player, turnNumber, hand)) {
                return new Position(0, j);
            }
        }
        // bottom border
        for (int i = 1; i < 10; i++) {
            if (ruleChecker.checkTilePlacement(board, t, new Position(i, 9), player, player, turnNumber, hand)) {
                return new Position(i, 9);
            }
        }
        // right border
        for (int j = 8; j > -1; j--) {
            if (ruleChecker.checkTilePlacement(board, t, new Position(9, j), player, player, turnNumber, hand)) {
                return new Position(9, j);
            }
        }
        // top border
        for (int i = 8; i > 1; i--) {
            if (ruleChecker.checkTilePlacement(board, t, new Position(i, 0), player, player, turnNumber, hand)) {
                return new Position(i, 0);
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
