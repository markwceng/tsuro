package com.clownswhocode.tsuro.Util;

import com.clownswhocode.tsuro.model.Port;

/**
 * A data structure to represent a pair of Tile and Port
 */
public class TileAndPort {

  public Position pos;
  public Port port;

  public TileAndPort(Position pos, Port port) {
    this.pos = pos;
    this.port = port;
  }
}
