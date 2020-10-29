package com.clownswhocode.tsuro.model;

import com.clownswhocode.tsuro.Util.Position;
import com.clownswhocode.tsuro.Util.TokenStatus;
import java.io.Serializable;

/**
 * Represents a token that will be assigned to a player once they join
 */
public class ColorToken implements Serializable {

  // Color of the token
  private String color;
  // Port number the token is currently on
  private int portNumber;
  // Current position of the token on the game board
  private Position pos;

  // Status of token. (Alive, Dead, Terminated...)
  private TokenStatus tokenStatus;

  public ColorToken(String color) {
    this.color = color;
    this.tokenStatus = TokenStatus.ALIVE;
  }

  /**
   * Gets the current port number the color token is on currently. If the avatar isn't placed,
   * return -1.
   *
   * @return port number
   */
  public int getPortNumber() {
    return this.portNumber;
  }

  /**
   * Setter to set the current port number of the token
   */
  public void setPortNumber(int portNumber) {
    this.portNumber = portNumber;
  }

  /**
   * Getter for the color of the token
   *
   * @return String which represents color
   */
  public String getColor() {
    return this.color;
  }

  /**
   * Get the current status of the token
   *
   * @return Enum TokenStatus
   */
  public TokenStatus getTokenStatus() {
    return this.tokenStatus;
  }

  /**
   * Set the current status of the token
   *
   * @param tokenStatus Enum TokenStatus
   */
  public void setTokenStatus(TokenStatus tokenStatus) {
    this.tokenStatus = tokenStatus;
  }

  /**
   * Get current position of the token on the board
   *
   * @return Position
   */
  public Position getPosition() {
    return this.pos;
  }

  /**
   * Set current position of the token on the board
   *
   * @param pos position to be set
   */
  public void setPosition(Position pos) {
    this.pos = pos;
  }
}
