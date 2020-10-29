package com.clownswhocode.tsuro.model;

import com.clownswhocode.tsuro.Util.Position;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Represents a rule checker which check if players' moves follow the game's rule
 */
public class RuleChecker implements iRuleChecker, Serializable {

  @Override
  public boolean checkTilePlacement(ArrayList<ArrayList<Tile>> board, Tile t, Position pos,
                                    iPlayer currentPlayer, iPlayer playerAttemptingMove, int currentTurn, ArrayList<Tile> currentHand) throws RemoteException {
    if (currentPlayer == playerAttemptingMove) {
      // Check if the selected tile is one of the tiles in the current hand (with rotations)
      ArrayList<Tile> allPossibleMoves = new ArrayList<Tile>();
      for (Tile tile : currentHand) {
        for (int i = 0; i < 4; i++) {
          Tile copy = Tile.deepCopy(tile, Tile.class);
          copy.rotate(90 * i);
          allPossibleMoves.add(copy);
        }
      }

      // If not, return false
      if (!allPossibleMoves.contains(t)) {
        return false;
      }

      // check first turn
      if (currentTurn == 1) {
        if (board.get(pos.get_x()).get(pos.get_y()).getPlacedBy() == null) {
          // check if neighbours are occupied
          if (pos.get_x() == 0) {

            // top left corner
            if (pos.get_y() == 0) {
              if (board.get(0).get(1).getConnections() != null || board.get(1).get(0).getConnections() != null) {
                return false;
              }
            }

            // bottom left corner
            else if (pos.get_y() == 9) {
              if (board.get(0).get(8).getConnections() != null || board.get(1).get(9).getConnections() != null) {
                return false;
              }
            }

            // rest of left border cases
            else if (board.get(0).get(pos.get_y() + 1).getConnections() != null
                    || board.get(0).get(pos.get_y() - 1).getConnections() != null) {
              return false;
            }
          }
          // top, right, bottom border cases
          else if (pos.get_y() == 0) {

            if (pos.get_x() == 9) {
              if (board.get(8).get(0).getConnections() != null || board.get(9).get(1).getConnections() != null) {
                return false;
              }
            } else if (board.get(pos.get_x() + 1).get(0).getConnections() != null || board.get(pos.get_x() - 1).get(0).getConnections() != null) {
              return false;
            }
          } else if (pos.get_x() == 9) {

            if (pos.get_y() == 9) {
              if (board.get(8).get(9).getConnections() != null || board.get(9).get(8).getConnections() != null) {
                return false;
              }
            } else if (board.get(9).get(pos.get_y() + 1).getConnections() != null || board.get(9).get(pos.get_y() - 1).getConnections() != null) {
              return false;
            }
          } else if (pos.get_y() == 9) {
            if (board.get(pos.get_x() + 1).get(9).getConnections() != null || board.get(pos.get_x() - 1).get(9).getConnections() != null) {
              return false;
            }
          }

          // check if a future valid token placement exists for the tile placement
          boolean isThereValidTokenPlacement = false;
          for (int i = 0; i < 8; i++) {
            if (checkTokenPlacementHelper(t, pos, i + 1, currentPlayer, playerAttemptingMove, currentTurn)) {
              isThereValidTokenPlacement = true;
            }
          }

          // allow placement if the tile will be on the border of the board and a valid token placement exists for it
          return isThereValidTokenPlacement && (pos.get_x() == 0 || pos.get_y() == 0 || pos.get_x() == 9 || pos.get_y() == 9);
        }
      }
      // check other turns after 1
      else if (currentTurn > 1) {
        // get valid tile placements for this turn
        ArrayList<Tile> allValidTiles = this.validMoves(board, currentHand, pos, playerAttemptingMove);

        // check if player's tile is valid or if they have no choice but to suicide
        if ((allValidTiles.isEmpty() || allValidTiles.contains(t))) {

          ColorToken token = currentPlayer.getToken();
          Position currentPos = token.getPosition();
          Tile currentTile = board.get(currentPos.get_x()).get(currentPos.get_y());

          // ensure player's token borders their desired tile placement
          if (pos.get_x() == currentPos.get_x() + 1 && pos.get_y() == currentPos.get_y()) {
            return (currentTile.getPorts().get(2).getToken() != null
                    || currentTile.getPorts().get(3).getToken() != null);
          }
          if (pos.get_x() == currentPos.get_x() - 1 && pos.get_y() == currentPos.get_y()) {
            return (currentTile.getPorts().get(6).getToken() != null
                    || currentTile.getPorts().get(7).getToken() != null);
          }
          if (pos.get_x() == currentPos.get_x() && pos.get_y() == currentPos.get_y() + 1) {
            return (currentTile.getPorts().get(4).getToken() != null
                    || currentTile.getPorts().get(5).getToken() != null);
          }
          if (pos.get_x() == currentPos.get_x() && pos.get_y() == currentPos.get_y() - 1) {
            return (currentTile.getPorts().get(0).getToken() != null
                    || currentTile.getPorts().get(1).getToken() != null);
          }
        }
      }
    }
    return false;
  }

  @Override
  public boolean checkTokenPlacement(ArrayList<ArrayList<Tile>> board, Position pos,
                                     int portNumber, iPlayer currentPlayer, iPlayer playerAttemptingMove, int currentTurn) throws RemoteException {
    // can only place avatar on first turn
    if (currentTurn != 1) {
      return false;
    }

    // has to be the current player's turn
    if (currentPlayer == playerAttemptingMove) {
      Tile first = board.get(pos.get_x()).get(pos.get_y());

      //
      if (first.getPlacedBy().equals(currentPlayer.getColor())) {
        boolean result = false;
        // left border
        if (pos.get_x() == 0) {
          // the port that port 7 leads to
          int port_1 = first.checkRoutes(7);
          // the port that port 8 leads to
          int port_2 = first.checkRoutes(8);

          // top left corner
          if (pos.get_y() == 0) {
            int port_3 = first.checkRoutes(1);
            int port_4 = first.checkRoutes(2);
            if (((portNumber == port_1) && (port_1 != 8 && port_1 != 1 && port_1 != 2)) ||
                    ((portNumber == port_2) && (port_2 != 7 && port_2 != 1 && port_2 != 2)) ||
                    ((portNumber == port_3) && (port_3 != 7 && port_3 != 8 && port_3 != 2)) ||
                    ((portNumber == port_4) && (port_4 != 7 && port_4 != 8 && port_4 != 1))) {
              result = true;
            }
          }
          // bottom left corner
          if (pos.get_y() == 9) {
            int port_3 = first.checkRoutes(5);
            int port_4 = first.checkRoutes(6);
            if (((portNumber == port_1) && (port_1 != 8 && port_1 != 5 && port_1 != 6)) ||
                    ((portNumber == port_2) && (port_2 != 7 && port_2 != 5 && port_2 != 6)) ||
                    ((portNumber == port_3) && (port_3 != 7 && port_3 != 8 && port_3 != 6)) ||
                    ((portNumber == port_4) && (port_4 != 7 && port_4 != 8 && port_4 != 5))) {
              result = true;
            }
          }

          // all other left border tiles
          else if ((portNumber == port_1 && port_1 != 8) || (portNumber == port_2 && port_2 != 7)) {
            result = true;
          }
        }
        // right border
        else if (pos.get_x() == 9) {
          int port_1 = first.checkRoutes(3);
          int port_2 = first.checkRoutes(4);
          // top right corner
          if (pos.get_y() == 0) {
            int port_3 = first.checkRoutes(1);
            int port_4 = first.checkRoutes(2);
            if (((portNumber == port_1) && (port_1 != 4 && port_1 != 1 && port_1 != 2)) ||
                    ((portNumber == port_2) && (port_2 != 3 && port_2 != 1 && port_2 != 2)) ||
                    ((portNumber == port_3) && (port_3 != 3 && port_3 != 4 && port_3 != 2)) ||
                    ((portNumber == port_4) && (port_4 != 3 && port_4 != 4 && port_4 != 1))) {
              result = true;
            }
          }
          // bottom right corner
          if (pos.get_y() == 9) {
            int port_3 = first.checkRoutes(5);
            int port_4 = first.checkRoutes(6);
            if (((portNumber == port_1) && (port_1 != 4 && port_1 != 5 && port_1 != 6)) ||
                    ((portNumber == port_2) && (port_2 != 3 && port_2 != 5 && port_2 != 6)) ||
                    ((portNumber == port_3) && (port_3 != 3 && port_3 != 4 && port_3 != 6)) ||
                    ((portNumber == port_4) && (port_4 != 3 && port_4 != 4 && port_4 != 5))) {
              result = true;
            }
          } else if ((portNumber == port_1 && port_1 != 4) || (portNumber == port_2 && port_2 != 3)) {
            result = true;
          }
        }

        // top border
        else if (pos.get_y() == 0) {
          int port_1 = first.checkRoutes(1);
          int port_2 = first.checkRoutes(2);
          if ((portNumber == port_1 && port_1 != 2) || (portNumber == port_2 && port_2 != 1)) {
            result = true;
          }
        }

        // bottom border
        else if (pos.get_y() == 9) {
          int port_1 = first.checkRoutes(5);
          int port_2 = first.checkRoutes(6);
          if ((portNumber == port_1 && port_1 != 5) || (portNumber == port_2 && port_2 != 6)) {
            result = true;
          }
        }
        return result;
      }
    }
    return false;
  }

  /**
   * Returns a list of all valid moves for the current player with the given current hand
   *
   * @return List of all valid Tiles
   */
  private ArrayList<Tile> validMoves(ArrayList<ArrayList<Tile>> board, ArrayList<Tile> currentHand, Position pos, iPlayer playerAttemptingMove) throws RemoteException {
    ArrayList<Tile> result = new ArrayList<>();
    for (Tile tile : currentHand) {
      for (int i = 0; i < 4; i++) {
        Tile copy = Tile.deepCopy(tile, Tile.class);
        copy.rotate(90 * i);
        Port currentPort = copy.getPorts().get(playerAttemptingMove.getToken().getPortNumber() - 1);
        int adjPortNumber = currentPort.getAdjacentPort(currentPort.getPortNumber());
        int endNumber = copy.checkRoutes(adjPortNumber);
        Port port = copy.getPorts().get(endNumber - 1);

        if ((endNumber == 1 || endNumber == 2) && pos.get_y() > 0) {
          Tile above = board.get(pos.get_x()).get(pos.get_y() - 1);
          if (above.getConnections() == null) {
            result.add(copy);
            continue;
          }
          int nextPortNumber = port.getAdjacentPort(endNumber);
          int finalNumber = above.checkRoutes(nextPortNumber);

          Port endPort = above.getPorts().get(finalNumber - 1).lastPort(new Position(pos.get_x(), pos.get_y() - 1)).port;
          Position endPos = above.getPorts().get(finalNumber - 1).lastPort(new Position(pos.get_x(), pos.get_y() - 1)).pos;

          if (!this.isLeavingBoard(endPos, endPort.getPortNumber())) {
            result.add(copy);
          }
        } else if ((endNumber == 3 || endNumber == 4) && pos.get_x() < 9) {
          Tile right = board.get(pos.get_x() + 1).get(pos.get_y());
          if (right.getConnections() == null) {
            result.add(copy);
            continue;
          }

          int nextPortNumber = port.getAdjacentPort(endNumber);
          int finalNumber = right.checkRoutes(nextPortNumber);

          Port endPort = right.getPorts().get(finalNumber - 1).lastPort(new Position(pos.get_x() + 1, pos.get_y())).port;
          Position endPos = right.getPorts().get(finalNumber - 1).lastPort(new Position(pos.get_x() + 1, pos.get_y())).pos;

          if (!this.isLeavingBoard(endPos, endPort.getPortNumber())) {
            result.add(copy);
          }

        } else if ((endNumber == 5 || endNumber == 6) && pos.get_y() < 9) {
          Tile below = board.get(pos.get_x()).get(pos.get_y() + 1);
          if (below.getConnections() == null) {
            result.add(copy);
            continue;
          }
          int nextPortNumber = port.getAdjacentPort(endNumber);
          int finalNumber = below.checkRoutes(nextPortNumber);

          Port endPort = below.getPorts().get(finalNumber - 1).lastPort(new Position(pos.get_x(), pos.get_y() + 1)).port;
          Position endPos = below.getPorts().get(finalNumber - 1).lastPort(new Position(pos.get_x(), pos.get_y() + 1)).pos;

          if (!this.isLeavingBoard(endPos, endPort.getPortNumber())) {
            result.add(copy);
          }
        } else if ((endNumber == 7 || endNumber == 8) && pos.get_x() > 0) {
          Tile left = board.get(pos.get_x() - 1).get(pos.get_y());
          if (left.getConnections() == null) {
            result.add(copy);
            continue;
          }
          int nextPortNumber = port.getAdjacentPort(endNumber);
          int finalNumber = left.checkRoutes(nextPortNumber);

          Port endPort = left.getPorts().get(finalNumber - 1).lastPort(new Position(pos.get_x() - 1, pos.get_y())).port;
          Position endPos = left.getPorts().get(finalNumber - 1).lastPort(new Position(pos.get_x() - 1, pos.get_y())).pos;

          if (!this.isLeavingBoard(endPos, endPort.getPortNumber())) {
            result.add(copy);
          }
        }
      }
    }
    return result;
  }

  /**
   * Check if a token is leaving the board given the position and port number
   *
   * @return Boolean
   */
  private boolean isLeavingBoard(Position endPos, int finalNumber) {
    if (endPos.get_x() == 0) {
      if (finalNumber == 7 || finalNumber == 8) {
        return true;
      }
    }
    if (endPos.get_x() == 9) {
      if (finalNumber == 3 || finalNumber == 4) {
        return true;
      }
    }
    if (endPos.get_y() == 0) {
      if (finalNumber == 1 || finalNumber == 2) {
        return true;
      }
    }
    if (endPos.get_y() == 9) {
      if (finalNumber == 5 || finalNumber == 6) {
        return true;
      }
    }
    return false;
  }

  private boolean checkTokenPlacementHelper(Tile t, Position pos,
                                            int portNumber, iPlayer currentPlayer, iPlayer playerAttemptingMove, int currentTurn) {
    // can only place avatar on first turn
    if (currentTurn != 1) {
      return false;
    }
    // has to be the current player's turn
    if (currentPlayer == playerAttemptingMove) {
      boolean result = false;
      // left border
      if (pos.get_x() == 0) {
        // the port that port 7 leads to
        int port_1 = t.checkRoutes(7);
        // the port that port 8 leads to
        int port_2 = t.checkRoutes(8);
        if (pos.get_y() == 0) {
          int port_3 = t.checkRoutes(1);
          int port_4 = t.checkRoutes(2);
          if ((portNumber == port_1 || portNumber == port_2 || portNumber == port_3 || portNumber == port_4)
                  && (port_1 != 8 && port_1 != 1 && port_1 != 2)
                  && (port_2 != 7 && port_2 != 1 && port_2 != 2)
                  && (port_3 != 7 && port_3 != 8 && port_3 != 2)
                  && (port_4 != 7 && port_4 != 8 && port_4 != 1)) {
            result = true;
          }
        }
        if (pos.get_y() == 9) {
          int port_3 = t.checkRoutes(5);
          int port_4 = t.checkRoutes(6);
          if ((portNumber == port_1 || portNumber == port_2 || portNumber == port_3 || portNumber == port_4)
                  && (port_1 != 8 && port_1 != 5 && port_1 != 6)
                  && (port_2 != 7 && port_2 != 5 && port_2 != 6)
                  && (port_3 != 7 && port_3 != 8 && port_3 != 6)
                  && (port_4 != 7 && port_4 != 8 && port_4 != 5)) {
            result = true;
          }
        } else if (portNumber == port_1 || portNumber == port_2 && port_1 != 8 && port_2 != 7) {
          result = true;
        }
      }
      // right border
      if (pos.get_x() == 9) {
        int port_1 = t.checkRoutes(3);
        int port_2 = t.checkRoutes(4);
        if (pos.get_y() == 0) {
          int port_3 = t.checkRoutes(1);
          int port_4 = t.checkRoutes(2);
          if ((portNumber == port_1 || portNumber == port_2 || portNumber == port_3 || portNumber == port_4)
                  && (port_1 != 4 && port_1 != 1 && port_1 != 2)
                  && (port_2 != 3 && port_2 != 1 && port_2 != 2)
                  && (port_3 != 3 && port_3 != 4 && port_3 != 2)
                  && (port_4 != 3 && port_4 != 4 && port_4 != 1)) {
            result = true;
          }
        }
        if (pos.get_y() == 9) {
          int port_3 = t.checkRoutes(5);
          int port_4 = t.checkRoutes(6);
          if ((portNumber == port_1 || portNumber == port_2 || portNumber == port_3 || portNumber == port_4)
                  && (port_1 != 4 && port_1 != 5 && port_1 != 6)
                  && (port_2 != 3 && port_2 != 5 && port_2 != 6)
                  && (port_3 != 3 && port_3 != 4 && port_3 != 6)
                  && (port_4 != 3 && port_4 != 4 && port_4 != 5)) {
            result = true;
          }
        } else if (portNumber == port_1 || portNumber == port_2 && port_1 != 4 && port_2 != 3) {
          result = true;
        }
      }

      // top border
      else if (pos.get_y() == 0) {
        int port_1 = t.checkRoutes(1);
        int port_2 = t.checkRoutes(2);
        if ((portNumber == port_1 || portNumber == port_2) && (port_1 != 2 && port_2 != 1)) {
          result = true;
        }
      }

      // bottom border
      else if (pos.get_y() == 9) {
        int port_1 = t.checkRoutes(5);
        int port_2 = t.checkRoutes(6);
        if ((portNumber == port_1 || portNumber == port_2) && (port_1 != 5 && port_2 != 6)) {
          result = true;
        }
      }
      return result;
    }
    return false;
  }

}
