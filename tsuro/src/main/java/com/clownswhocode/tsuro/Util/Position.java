package com.clownswhocode.tsuro.Util;


import java.io.Serializable;

/**
 * Represent a coordinate on the game board
 */
public class Position implements Serializable {

  private int x;
  private int y;

  public Position(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int get_x() {
    return this.x;
  }

  public int get_y() {
    return this.y;
  }

}
