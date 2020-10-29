package com.clownswhocode.tsuro.model;

import com.clownswhocode.tsuro.Util.Position;
import com.clownswhocode.tsuro.Util.TileAndPort;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Represent a port There are 8 ports on each tile, port number 1- 8
 */
public class Port implements Serializable {

  private int number;
  private ColorToken token;
  private Port next;

  /**
   * Construct a normal Port for a tile
   *
   * @param number initial port number
   */
  public Port(int number) {
    this.number = number;
    this.token = null;
    this.next = null;
  }

  /**
   * Used for filling the game board with null Tiles
   */
  public Port() {
    this.number = 0;
    this.token = null;
    this.next = null;
  }

  /**
   * Get the port number of this port
   */
  public int getPortNumber() {
    return this.number;
  }

  /**
   * Get the token currently on this port
   *
   * @return ColorToken or null
   */
  public ColorToken getToken() {
    return this.token;
  }

  /**
   * Change the port number of this port (After a rotation)
   */
  public void changePortNumber() {
    this.number = (this.number + 2) % 8;
    if (this.number == 0) {
      this.number = 8;
    }
  }

  // Traverses the path from a port until it reaches an empty square, or the edge of the board
  public TileAndPort lastPort(Position pos) {
    // Used to track where you are as you're traversing the board
    TileAndPort tileAndPort = new TileAndPort(pos, this);
    // As long as there is a next port
    if (tileAndPort.port.next != null) {
      // The tile you go to corresponds to the port number you're on. i.e. if in port 1 you would go to the tile above
      Position newPosition;
      switch (tileAndPort.port.getPortNumber()) {
        // Move up
        case 1:
        case 2:
          newPosition = new Position(pos.get_x(), pos.get_y() - 1);
          break;
        // Move left
        case 3:
        case 4:
          newPosition = new Position(pos.get_x() + 1, pos.get_y());
          break;
        // Move down
        case 5:
        case 6:
          newPosition = new Position(pos.get_x(), pos.get_y() + 1);
          break;
        // Move left
        case 7:
        case 8:
          newPosition = new Position(pos.get_x() - 1, pos.get_y());
          break;
        default:
          newPosition = new Position(0, 0);
      }
      // Uses recursion here, finds the lastport of the next and the next and so on
      return tileAndPort.port.next.lastPort(newPosition);
    }
    // When there is no next port, return the final position and port
    TileAndPort finalTileAndPort = new TileAndPort(pos, this);
    return finalTileAndPort;
  }

  // Returns the ColorToken that the given ColorToken collided with, if any. Returns null if it does not collide with anything
  public ColorToken collidedWith(Position pos, ArrayList<ArrayList<Tile>> board,
                                 ColorToken collidedWith) {
    // Used to track where you are as you're traversing the board
    TileAndPort tileAndPort = new TileAndPort(pos, this);
    // As long as there is a next port
    if (tileAndPort.port.next != null) {
      // The tile you go to corresponds to the port number you're on. i.e. if in port 1 you would go to the tile above
      Position newPosition;
      // The port that is shared between two tiles
      int adjacentPortNumber;
      switch (tileAndPort.port.getPortNumber()) {
        case 1:
          newPosition = new Position(pos.get_x(), pos.get_y() - 1);
          adjacentPortNumber = 6;
          break;
        case 2:
          newPosition = new Position(pos.get_x(), pos.get_y() - 1);
          adjacentPortNumber = 5;
          break;
        case 3:
          newPosition = new Position(pos.get_x() + 1, pos.get_y());
          adjacentPortNumber = 8;
          break;
        case 4:
          newPosition = new Position(pos.get_x() + 1, pos.get_y());
          adjacentPortNumber = 7;
          break;
        case 5:
          newPosition = new Position(pos.get_x(), pos.get_y() + 1);
          adjacentPortNumber = 2;
          break;
        case 6:
          newPosition = new Position(pos.get_x(), pos.get_y() + 1);
          adjacentPortNumber = 1;
          break;
        case 7:
          newPosition = new Position(pos.get_x() - 1, pos.get_y());
          adjacentPortNumber = 4;
          break;
        case 8:
          newPosition = new Position(pos.get_x() - 1, pos.get_y());
          adjacentPortNumber = 3;
          break;
        default:
          newPosition = new Position(0, 0);
          adjacentPortNumber = 0;
      }
      // Check the port that is shared between two tiles for existence of another ColorToken
      Tile nextTile = board.get(newPosition.get_x()).get(newPosition.get_y());
      ColorToken adjacentPortToken = nextTile.getPorts().get(adjacentPortNumber - 1).getToken();
      if (adjacentPortToken != null) {
        collidedWith = adjacentPortToken;
      }
      // Uses recursion here, finds the token collided with of the next and the next and so on
      return tileAndPort.port.next.collidedWith(newPosition, board, collidedWith);
    }
    // Return the token collided with, if any
    return collidedWith;
  }

  /**
   * Get the port number of the adjacent port of the given port number
   */
  public int getAdjacentPort(int number) {
    switch (number) {
      case 1:
        return 6;
      case 2:
        return 5;
      case 3:
        return 8;
      case 4:
        return 7;
      case 5:
        return 2;
      case 6:
        return 1;
      case 7:
        return 4;
      case 8:
        return 3;
      default:
        return 0;
    }
  }

  /**
   * Set the next Port of this port to the given port
   */
  public void setNext(Port port) {
    this.next = port;
  }

  /**
   * Set the given token to be on this Port
   */
  public void setToken(ColorToken token) {
    this.token = token;
  }


}
