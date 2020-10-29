package com.clownswhocode.tsuro.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.awt.geom.Path2D;
import java.io.Serializable;
import java.util.ArrayList;

import com.clownswhocode.tsuro.Util.TileCreation;

/**
 * Represents tile which are the cards to be placed by the player. If a tile's connection is null,
 * the it is a default piece on the board
 */
public class Tile implements Serializable {
  private ArrayList<Port> ports;
  private ArrayList<ArrayList<Port>> connections;
  private int rotation;
  private String placedBy;

  public Tile() {
    this.ports = null;
    this.connections = null;
    this.rotation = 0;
  }

  public Tile(ArrayList<ArrayList<Port>> connections) {
    this.ports = new ArrayList<>();
    for (int i = 1; i < 9; i++) {
      Port newPort = new Port(i);
      this.ports.add(newPort);
    }
    this.connections = connections;
    this.rotation = 0;
  }

  @Override
  // Checks if this Tile is equal to the given object
  // Rotations of the same foundational tile are NOT considered equal
  public boolean equals(Object o) {
    boolean equivalent = true;

    if (o instanceof Tile) {
      Tile otherTile = (Tile) o;

      // Check all other Tile's connection pairs against this Tile
      for (ArrayList<Port> connectionPair : otherTile.getConnections()) {
        if (!containsPair(connectionPair, this.getConnections())) {
          return false;
        }
      }
    } else {
      equivalent = false;
    }
    return equivalent;
  }

  /**
   * Checks whether a connection pair equivalent to connectionPair exists in connectionList (set of
   * connections in a Tile)
   */
  private boolean containsPair(ArrayList<Port> connectionPair, ArrayList<ArrayList<Port>> connectionList) {
    // Check if given connection pair exists in given connectionList
    for (ArrayList<Port> otherConnectionPair : connectionList) {
      if ((connectionPair.get(0).getPortNumber() == otherConnectionPair.get(0).getPortNumber()
              && connectionPair.get(1).getPortNumber() == otherConnectionPair.get(1).getPortNumber())
              || (connectionPair.get(0).getPortNumber() == otherConnectionPair.get(1).getPortNumber()
              && connectionPair.get(1).getPortNumber() == otherConnectionPair.get(0).getPortNumber())) {
        return true;
      }
    }
    // If no equivalent connection pair is found, return false
    return false;
  }

  /**
   * Rotate a tile clockwise
   *
   * @param degrees (0, 90, 180, 270)
   */
  public void rotate(int degrees) {
    degrees = degrees % 360;
    if (degrees == 0 || degrees == 90 || degrees == 180 || degrees == 270) {
      this.rotation = (this.rotation + degrees) % 360;
      int numberOfRotations = this.rotation / 90;
      for (int i = 0; i < numberOfRotations; i++) {
        this.rotateHelper();
      }
    } else {
      throw new IllegalArgumentException("Please enter a degree value that is a multiple of 90.");
    }
  }

  /**
   * Check which port a given port number leads to in this tile and return the port number
   */
  public int checkRoutes(int portNumber) {
    for (int i = 0; i < this.connections.size(); i++) {
      for (int j = 0; j < 2; j++) {
        if (this.connections.get(i).get(j).getPortNumber() == portNumber) {
          if (j == 0) {
            return this.connections.get(i).get(1).getPortNumber();
          } else {
            return this.connections.get(i).get(0).getPortNumber();
          }
        }
      }
    }
    return -1;
  }

  /**
   * Create a deep copy of this tile using Gson
   */
  public static <Tile> Tile deepCopy(Tile tile, Class<Tile> classInfo) {
    Gson gson = new GsonBuilder().create();
    String text = gson.toJson(tile);
    Tile newObject = gson.fromJson(text, classInfo);
    return newObject;
  }

  /**
   * Get the color of the color token which placed this tile on the board
   */
  public String getPlacedBy() {
    return this.placedBy;
  }

  /**
   * Set the color of the color token which placed this tile to the given color
   */
  public void setPlacedBy(String color) {
    this.placedBy = color;
  }

  /**
   * Get all ports of this tile
   */
  public ArrayList<Port> getPorts() {
    return this.ports;
  }

  /**
   * Get all connections of this tile
   */
  public ArrayList<ArrayList<Port>> getConnections() {
    return this.connections;
  }

  /**
   * Get the degree of rotation of this tile
   */
  public int getRotation() {
    return this.rotation;
  }

  /**
   * Transform connection info into info used to draw this tile
   */
  public ArrayList<Path2D> pathsToDraw(int x, int y) {

    ArrayList<Path2D> paths = new ArrayList<>();
    int rectWidth = 80;
    int rectHeight = 80;
    // Curve from Port 1 to 2
    Path2D.Double path_1_to_2 = new Path2D.Double();
    path_1_to_2.moveTo(x + rectWidth / 3, y);
    path_1_to_2.curveTo(x + rectWidth * .4, y + rectHeight * .5,
            x + rectWidth * .60, y + rectHeight * .5,
            x + rectWidth * 2 / 3, y);

    // Curve from Port 1 to 3
    Path2D.Double path_1_to_3 = new Path2D.Double();
    path_1_to_3.moveTo(x + rectWidth / 3, y);
    path_1_to_3.curveTo(x + rectWidth * .4, y + rectHeight * .15,
            x + rectWidth * .25, y + rectHeight * .15,
            x + rectWidth - 5, y + rectHeight / 3);
    // Curve from Port 1 to 4
    Path2D.Double path_1_to_4 = new Path2D.Double();
    path_1_to_4.moveTo(x + rectWidth / 3, y);
    path_1_to_4.curveTo(x + rectWidth * .4, y + rectHeight * .15,
            x + rectWidth * .25, y + rectHeight / 3,
            x + rectWidth - 5, y + rectHeight * 2 / 3);
    // Curve from Port 1 to 5
    Path2D.Double path_1_to_5 = new Path2D.Double();
    path_1_to_5.moveTo(x + rectWidth * 1 / 3, y);
    path_1_to_5.curveTo(x + rectWidth * 2 / 3, y + rectHeight - 5,
            x + rectWidth * 2 / 3, y + rectHeight - 5,
            x + rectWidth * 2 / 3, y + rectHeight - 5);

    // Curve from Port 1 to 6
    Path2D.Double path_1_to_6 = new Path2D.Double();
    path_1_to_6.moveTo(x + rectWidth * 1 / 3, y);
    path_1_to_6.lineTo(x + rectWidth * 1 / 3, y + rectHeight - 5);

    // Curve from Port 1 to 5
    Path2D.Double path_1_to_7 = new Path2D.Double();
    path_1_to_7.moveTo(x + rectWidth * 1 / 3, y);
    path_1_to_7.curveTo(x + rectWidth * 1 / 3, y + rectHeight * .4,
            x + rectWidth * .20, y + rectHeight * .5,
            x, y + rectHeight * 2 / 3);

    // Curve from Port 1 to Port 8
    Path2D.Double path_1_to_8 = new Path2D.Double();
    path_1_to_8.moveTo(x + rectWidth * 1 / 3, y);
    path_1_to_8.curveTo(x + rectWidth * .25, y + rectHeight * .25,
            x + rectWidth * .25, y + rectHeight * .25,
            x, y + rectHeight / 3);


    // Curve from Port 2 to Port 1
    Path2D.Double path_2_to_1 = path_1_to_2;

    // Curve from Port 2 to Port 3
    Path2D.Double path_2_to_3 = new Path2D.Double();
    path_2_to_3.moveTo(x + rectWidth * 2 / 3, y);
    path_2_to_3.curveTo(x + rectWidth * .8, y + rectHeight * .20,
            x + rectWidth * .75, y + rectHeight * .25,
            x + rectWidth - 5, y + rectHeight * 1 / 3);
    // Curve from Port 2 to Port 4
    Path2D.Double path_2_to_4 = new Path2D.Double();
    path_2_to_4.moveTo(x + rectWidth * 2 / 3, y);
    path_2_to_4.curveTo(x + rectWidth * 2 / 3, y + rectHeight * .4,
            x + rectWidth * .85, y + rectHeight * .55,
            x + rectWidth - 5, y + rectHeight * 2 / 3);

    // Curve from Port 2 to Port 5
    Path2D.Double path_2_to_5 = new Path2D.Double();
    path_2_to_5.moveTo(x + rectWidth * 2 / 3, y);
    path_2_to_5.lineTo(x + rectWidth * 2 / 3, y + rectHeight - 5);

    // Curve from Port 2 to Port 6
    Path2D.Double path_2_to_6 = new Path2D.Double();
    path_2_to_6.moveTo(x + rectWidth * 2 / 3, y);
    path_2_to_6.curveTo(x + rectWidth * 1 / 3, y + rectHeight - 5,
            x + rectWidth * 1 / 3, y + rectHeight - 5,
            x + rectWidth * 1 / 3, y + rectHeight - 5);
    // Curve from Port 2 to Port 7
    Path2D.Double path_2_to_7 = new Path2D.Double();
    path_2_to_7.moveTo(x + rectWidth * 2 / 3, y);
    path_2_to_7.curveTo(x + rectWidth * .65, y + rectHeight * .45,
            x + rectWidth * 1 / 3, y + rectHeight * .60,
            x, y + rectHeight * 2 / 3);

    // Curve from Port 2 to Port 8
    Path2D.Double path_2_to_8 = new Path2D.Double();
    path_2_to_8.moveTo(x + rectWidth * 2 / 3, y);
    path_2_to_8.curveTo(x + rectWidth * .65, y + rectHeight * .15,
            x + rectWidth * 1 / 3, y + rectHeight * .25,
            x, y + rectHeight * 1 / 3);


    // Curve from Port 3 to Port 1
    Path2D.Double path_3_to_1 = path_1_to_3;

    // Curve from Port 3 to Port 2
    Path2D.Double path_3_to_2 = path_2_to_3;

    // Curve from Port 3 to 4
    Path2D.Double path_3_to_4 = new Path2D.Double();
    path_3_to_4.moveTo(x + rectWidth - 5, y + rectHeight * 1 / 3);
    path_3_to_4.curveTo(x + rectWidth * .6, y + rectHeight * .5,
            x + rectWidth * .60, y + rectHeight * .5,
            x + rectWidth - 5, y + rectHeight * 2 / 3);

    // Curve from Port 3 to 5
    Path2D.Double path_3_to_5 = new Path2D.Double();
    path_3_to_5.moveTo(x + rectWidth - 5, y + rectHeight * 1 / 3);
    path_3_to_5.curveTo(x + rectWidth * .55, y + rectHeight * .45,
            x + rectWidth * .55, y + rectHeight * .45,
            x + rectWidth * 2 / 3, y + rectHeight - 5);

    // Curve from Port 3 to 6
    Path2D.Double path_3_to_6 = new Path2D.Double();
    path_3_to_6.moveTo(x + rectWidth - 5, y + rectHeight * 1 / 3);
    path_3_to_6.curveTo(x + rectWidth * 2 / 3, y + rectHeight * 2 / 3,
            x + rectWidth * .75, y + rectHeight * 2 / 3,
            x + rectWidth * 1 / 3, y + rectHeight - 5);

    // Curve from Port 3 to 7
    Path2D.Double path_3_to_7 = new Path2D.Double();
    path_3_to_7.moveTo(x + rectWidth - 5, y + rectHeight * 1 / 3);
    path_3_to_7.curveTo(x + rectWidth * .5, y + rectHeight * .5,
            x + rectWidth * .50, y + rectHeight * .5,
            x, y + rectHeight * 2 / 3);

    // Curve from Port 3 to 8
    Path2D.Double path_3_to_8 = new Path2D.Double();
    path_3_to_8.moveTo(x + rectWidth - 5, y + rectHeight * 1 / 3);
    path_3_to_8.lineTo(x, y + rectHeight * 1 / 3);

    // Curve from Port 4 to Port 1
    Path2D.Double path_4_to_1 = path_1_to_4;
    // Curve from Port 4 to Port 2
    Path2D.Double path_4_to_2 = path_2_to_4;
    // Curve from Port 4 to Port 3
    Path2D.Double path_4_to_3 = path_3_to_4;
    // Curve from Port 4 to Port 5
    Path2D.Double path_4_to_5 = new Path2D.Double();
    path_4_to_5.moveTo(x + rectWidth - 5, y + rectHeight * 2 / 3);
    path_4_to_5.curveTo(x + rectWidth * 2 / 3, y + rectHeight * .80,
            x + rectWidth * .75, y + rectHeight * .90,
            x + rectWidth * 2 / 3, y + rectHeight - 5);

    Path2D.Double path_4_to_6 = new Path2D.Double();
    path_4_to_6.moveTo(x + rectWidth - 5, y + rectHeight * 2 / 3);
    path_4_to_6.curveTo(x + rectWidth * .65, y + rectHeight * .75,
            x + rectWidth * .55, y + rectHeight * .80,
            x + rectWidth * 1 / 3, y + rectHeight - 5);
    Path2D.Double path_4_to_7 = new Path2D.Double();
    path_4_to_7.moveTo(x + rectWidth - 5, y + rectHeight * 2 / 3);
    path_4_to_7.lineTo(x, y + rectHeight * 2 / 3);

    Path2D.Double path_4_to_8 = new Path2D.Double();
    path_4_to_8.moveTo(x + rectWidth - 5, y + rectHeight * 2 / 3);
    path_4_to_8.lineTo(x, y + rectHeight * 1 / 3);


    // Curve from Port 5 to Port 1
    Path2D.Double path_5_to_1 = new Path2D.Double();
    path_5_to_1.moveTo(x + rectWidth * 2 / 3, y + rectHeight - 5);
    path_5_to_1.lineTo(x + rectWidth * 1 / 3, y);

    // Curve from Port 5 to Port 2
    Path2D.Double path_5_to_2 = new Path2D.Double();
    path_5_to_2.moveTo(x + rectWidth * 2 / 3, y + rectHeight - 5);
    path_5_to_2.curveTo(x + rectWidth * 2 / 3, y + rectHeight - 5,
            x + rectWidth * 2 / 3, y + rectHeight - 5,
            x + rectWidth * 2 / 3, y);

    // Curve from Port 5 to Port 3
    Path2D.Double path_5_to_3 = new Path2D.Double();
    path_5_to_3.moveTo(x + rectWidth * 2 / 3, y + rectHeight - 5);
    path_5_to_3.curveTo(x + rectWidth * .75, y + rectHeight * .55,
            x + rectWidth * 2 / 3, y + rectHeight * .60,
            x + rectWidth - 5, y + rectHeight * 1 / 3);


    // Curve from Port 5 to Port 4
    Path2D.Double path_5_to_4 = new Path2D.Double();
    path_5_to_4.moveTo(x + rectWidth * 2 / 3, y + rectHeight - 5);
    path_5_to_4.curveTo(x + rectWidth - 20, y + rectHeight * 2 / 3,
            x + rectWidth - 10, y + rectHeight * 2 / 3,
            x + rectWidth - 5, y + rectHeight * 2 / 3);

    // Curve from Port 5 to Port 6
    Path2D.Double path_5_to_6 = new Path2D.Double();
    path_5_to_6.moveTo(x + rectWidth * 2 / 3, y + rectHeight - 5);
    path_5_to_6.curveTo(x + rectWidth * .6, y + rectHeight * .5,
            x + rectWidth * .4, y + rectHeight * .5,
            x + rectWidth * 1 / 3, y + rectHeight - 5);

    // Curve from Port 5 to Port 7
    Path2D.Double path_5_to_7 = new Path2D.Double();
    path_5_to_7.moveTo(x + rectWidth * 2 / 3, y + rectHeight - 5);
    path_5_to_7.curveTo(x + rectWidth * .6, y + rectHeight - 20,
            x + rectWidth * .35, y + rectHeight - 30,
            x, y + rectHeight * 2 / 3);

    // Curve from Port 5 to Port 8
    Path2D.Double path_5_to_8 = new Path2D.Double();
    path_5_to_8.moveTo(x + rectWidth * 2 / 3, y + rectHeight - 5);
    path_5_to_8.curveTo(x + rectWidth * .6, y + rectHeight * .5,
            x + rectWidth * .25, y + rectHeight * .4,
            x, y + rectHeight * 1 / 3);
    // Curve from Port 7 to Port 1
    Path2D.Double path_6_to_1 = path_1_to_6;
    // Curve from Port 7 to Port 2
    Path2D.Double path_6_to_2 = path_2_to_6;
    // Curve from Port 7 to Port 3
    Path2D.Double path_6_to_3 = path_3_to_6;
    // Curve from Port 7 to Port 4
    Path2D.Double path_6_to_4 = path_4_to_6;
    // Curve from Port 7 to Port 5
    Path2D.Double path_6_to_5 = path_5_to_6;

    Path2D.Double path_6_to_7 = new Path2D.Double();
    path_6_to_7.moveTo(x + rectWidth * 1 / 3, y + rectHeight - 5);
    path_6_to_7.curveTo(x + rectWidth * 1 / 3, y + rectHeight * .90,
            x + rectWidth * .35, y + rectHeight * .80,
            x, y + rectHeight * 2 / 3);
    Path2D.Double path_6_to_8 = new Path2D.Double();
    path_6_to_8.moveTo(x + rectWidth * 1 / 3, y + rectHeight - 5);
    path_6_to_8.curveTo(x + rectWidth * 1 / 3, y + rectHeight * .85,
            x + rectWidth * .35, y + rectHeight * 2 / 3,
            x, y + rectHeight * 1 / 3);

    // Curve from Port 7 to Port 1
    Path2D.Double path_7_to_1 = path_1_to_7;
    // Curve from Port 7 to Port 2
    Path2D.Double path_7_to_2 = path_2_to_7;
    // Curve from Port 7 to Port 3
    Path2D.Double path_7_to_3 = path_3_to_7;
    // Curve from Port 7 to Port 4
    Path2D.Double path_7_to_4 = path_4_to_7;
    // Curve from Port 7 to Port 5
    Path2D.Double path_7_to_5 = path_5_to_7;
    // Curve from Port 7 to Port 6
    Path2D.Double path_7_to_6 = path_6_to_7;
    //Curve from Port 7 to Port 8
    Path2D.Double path_7_to_8 = new Path2D.Double();
    path_7_to_8.moveTo(x, y + rectHeight * 2 / 3);
    path_7_to_8.curveTo(x + rectWidth * .35, y + rectHeight * .5,
            x + rectWidth * .5, y + rectHeight * .55,
            x, y + rectHeight * 1 / 3);
    //        // Curve from Port 8 to Port 1
    Path2D.Double path_8_to_1 = path_1_to_8;
    // Curve from Port 8 to Port 2
    Path2D.Double path_8_to_2 = path_2_to_8;
    // Curve from Port 8 to Port 3
    Path2D.Double path_8_to_3 = path_3_to_8;
    // Curve from Port 8 to Port 4
    Path2D.Double path_8_to_4 = path_4_to_8;
    // Curve from Port 8 to Port 5
    Path2D.Double path_8_to_5 = path_5_to_8;
    // Curve from Port 8 to Port 6
    Path2D.Double path_8_to_6 = path_6_to_8;
    // Curve from Port 8 to Port 7
    Path2D.Double path_8_to_7 = path_7_to_8;

    if (this.connections == null) {
      return paths;
    } else {


      for (ArrayList<Port> pair :
              this.connections) {
        //Port 1
        if (pair.get(0).getPortNumber() == 1 && pair.get(1).getPortNumber() == 2) {
          paths.add(path_1_to_2);
        } else if (pair.get(0).getPortNumber() == 1 && pair.get(1).getPortNumber() == 3) {
          paths.add(path_1_to_3);
        } else if (pair.get(0).getPortNumber() == 1 && pair.get(1).getPortNumber() == 4) {
          paths.add(path_1_to_4);
        } else if (pair.get(0).getPortNumber() == 1 && pair.get(1).getPortNumber() == 5) {
          paths.add(path_1_to_5);
        } else if (pair.get(0).getPortNumber() == 1 && pair.get(1).getPortNumber() == 6) {
          paths.add(path_1_to_6);
        } else if (pair.get(0).getPortNumber() == 1 && pair.get(1).getPortNumber() == 7) {
          paths.add(path_1_to_7);
        } else if (pair.get(0).getPortNumber() == 1 && pair.get(1).getPortNumber() == 8) {
          paths.add(path_1_to_8);
        }
        //Port 2
        else if (pair.get(0).getPortNumber() == 2 && pair.get(1).getPortNumber() == 1) {
          paths.add(path_2_to_1);
        } else if (pair.get(0).getPortNumber() == 2 && pair.get(1).getPortNumber() == 3) {
          paths.add(path_2_to_3);
        } else if (pair.get(0).getPortNumber() == 2 && pair.get(1).getPortNumber() == 4) {
          paths.add(path_2_to_4);
        } else if (pair.get(0).getPortNumber() == 2 && pair.get(1).getPortNumber() == 5) {
          paths.add(path_2_to_5);
        } else if (pair.get(0).getPortNumber() == 2 && pair.get(1).getPortNumber() == 6) {
          paths.add(path_2_to_6);
        } else if (pair.get(0).getPortNumber() == 2 && pair.get(1).getPortNumber() == 7) {
          paths.add(path_2_to_7);
        } else if (pair.get(0).getPortNumber() == 2 && pair.get(1).getPortNumber() == 8) {
          paths.add(path_2_to_8);
        }
        // Port 3
        else if (pair.get(0).getPortNumber() == 3 && pair.get(1).getPortNumber() == 1) {
          paths.add(path_3_to_1);
        } else if (pair.get(0).getPortNumber() == 3 && pair.get(1).getPortNumber() == 2) {
          paths.add(path_3_to_2);
        } else if (pair.get(0).getPortNumber() == 3 && pair.get(1).getPortNumber() == 4) {
          paths.add(path_3_to_4);
        } else if (pair.get(0).getPortNumber() == 3 && pair.get(1).getPortNumber() == 5) {
          paths.add(path_3_to_5);
        } else if (pair.get(0).getPortNumber() == 3 && pair.get(1).getPortNumber() == 6) {
          paths.add(path_3_to_6);
        } else if (pair.get(0).getPortNumber() == 3 && pair.get(1).getPortNumber() == 7) {
          paths.add(path_3_to_7);
        } else if (pair.get(0).getPortNumber() == 3 && pair.get(1).getPortNumber() == 8) {
          paths.add(path_3_to_8);
        }
        // Port 4
        else if (pair.get(0).getPortNumber() == 4 && pair.get(1).getPortNumber() == 1) {
          paths.add(path_4_to_1);
        } else if (pair.get(0).getPortNumber() == 4 && pair.get(1).getPortNumber() == 2) {
          paths.add(path_4_to_2);
        } else if (pair.get(0).getPortNumber() == 4 && pair.get(1).getPortNumber() == 3) {
          paths.add(path_4_to_3);
        } else if (pair.get(0).getPortNumber() == 4 && pair.get(1).getPortNumber() == 5) {
          paths.add(path_4_to_5);
        } else if (pair.get(0).getPortNumber() == 4 && pair.get(1).getPortNumber() == 6) {
          paths.add(path_4_to_6);
        } else if (pair.get(0).getPortNumber() == 4 && pair.get(1).getPortNumber() == 7) {
          paths.add(path_4_to_7);
        } else if (pair.get(0).getPortNumber() == 4 && pair.get(1).getPortNumber() == 8) {
          paths.add(path_4_to_8);
        }
        // Port 5
        else if (pair.get(0).getPortNumber() == 5 && pair.get(1).getPortNumber() == 1) {
          paths.add(path_5_to_1);
        } else if (pair.get(0).getPortNumber() == 5 && pair.get(1).getPortNumber() == 2) {
          paths.add(path_5_to_2);
        } else if (pair.get(0).getPortNumber() == 5 && pair.get(1).getPortNumber() == 3) {
          paths.add(path_5_to_3);
        } else if (pair.get(0).getPortNumber() == 5 && pair.get(1).getPortNumber() == 4) {
          paths.add(path_5_to_4);
        } else if (pair.get(0).getPortNumber() == 5 && pair.get(1).getPortNumber() == 6) {
          paths.add(path_5_to_6);
        } else if (pair.get(0).getPortNumber() == 5 && pair.get(1).getPortNumber() == 7) {
          paths.add(path_5_to_7);
        } else if (pair.get(0).getPortNumber() == 5 && pair.get(1).getPortNumber() == 8) {
          paths.add(path_5_to_8);
        }
        // Port 6
        else if (pair.get(0).getPortNumber() == 6 && pair.get(1).getPortNumber() == 1) {
          paths.add(path_6_to_1);
        } else if (pair.get(0).getPortNumber() == 6 && pair.get(1).getPortNumber() == 2) {
          paths.add(path_6_to_2);
        } else if (pair.get(0).getPortNumber() == 6 && pair.get(1).getPortNumber() == 3) {
          paths.add(path_6_to_3);
        } else if (pair.get(0).getPortNumber() == 6 && pair.get(1).getPortNumber() == 4) {
          paths.add(path_6_to_4);
        } else if (pair.get(0).getPortNumber() == 6 && pair.get(1).getPortNumber() == 5) {
          paths.add(path_6_to_5);
        } else if (pair.get(0).getPortNumber() == 6 && pair.get(1).getPortNumber() == 7) {
          paths.add(path_6_to_7);
        } else if (pair.get(0).getPortNumber() == 6 && pair.get(1).getPortNumber() == 8) {
          paths.add(path_6_to_8);
        }
        // Port 7
        else if (pair.get(0).getPortNumber() == 7 && pair.get(1).getPortNumber() == 1) {
          paths.add(path_7_to_1);
        } else if (pair.get(0).getPortNumber() == 7 && pair.get(1).getPortNumber() == 2) {
          paths.add(path_7_to_2);
        } else if (pair.get(0).getPortNumber() == 7 && pair.get(1).getPortNumber() == 3) {
          paths.add(path_7_to_3);
        } else if (pair.get(0).getPortNumber() == 7 && pair.get(1).getPortNumber() == 4) {
          paths.add(path_7_to_4);
        } else if (pair.get(0).getPortNumber() == 7 && pair.get(1).getPortNumber() == 5) {
          paths.add(path_7_to_5);
        } else if (pair.get(0).getPortNumber() == 7 && pair.get(1).getPortNumber() == 6) {
          paths.add(path_7_to_6);
        } else if (pair.get(0).getPortNumber() == 7 && pair.get(1).getPortNumber() == 8) {
          paths.add(path_7_to_8);
        }
        // Port 8
        else if (pair.get(0).getPortNumber() == 8 && pair.get(1).getPortNumber() == 1) {
          paths.add(path_8_to_1);
        } else if (pair.get(0).getPortNumber() == 8 && pair.get(1).getPortNumber() == 2) {
          paths.add(path_8_to_2);
        } else if (pair.get(0).getPortNumber() == 8 && pair.get(1).getPortNumber() == 3) {
          paths.add(path_8_to_3);
        } else if (pair.get(0).getPortNumber() == 8 && pair.get(1).getPortNumber() == 4) {
          paths.add(path_8_to_4);
        } else if (pair.get(0).getPortNumber() == 8 && pair.get(1).getPortNumber() == 5) {
          paths.add(path_8_to_5);
        } else if (pair.get(0).getPortNumber() == 8 && pair.get(1).getPortNumber() == 6) {
          paths.add(path_8_to_6);
        } else if (pair.get(0).getPortNumber() == 8 && pair.get(1).getPortNumber() == 7) {
          paths.add(path_8_to_7);
        }
      }
    }

    return paths;
  }


  /**
   * update port number for each of the port in the connection
   */
  private void rotateHelper() {
    for (int i = 0; i < this.connections.size(); i++) {
      for (int j = 0; j < this.connections.get(i).size(); j++) {
        this.connections.get(i).get(j).changePortNumber();
      }
    }
  }


}


