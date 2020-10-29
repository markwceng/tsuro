package com.clownswhocode.tsuro.model;

import com.clownswhocode.tsuro.Util.GameState;
import com.clownswhocode.tsuro.Util.Position;
import com.clownswhocode.tsuro.Util.TileCreation;
import com.clownswhocode.tsuro.Util.TokenStatus;
import com.clownswhocode.tsuro.View.Observer;

import java.rmi.RemoteException;
import java.util.ArrayList;

import static com.clownswhocode.tsuro.Util.GameState.ENDED;

public class Referee implements iReferee {

  private ArrayList<ArrayList<Tile>> board;
  private iRuleChecker ruleChecker;
  private ArrayList<iPlayer> players;
  private ArrayList<Tile> deck;
  private GameState gameState;

  private iPlayer currentPlayer;
  private ArrayList<Tile> currentHand;
  private int turnNumber;
  private Tile selectedTile;
  private Position selectedPosition;
  private int deckIndex;
  private ArrayList<String> availableColors;

  public Referee() {
    // initialize game board
    this.board = new ArrayList<ArrayList<Tile>>();
    for (int i = 0; i < 10; i++) {
      ArrayList<Tile> row = new ArrayList<Tile>();
      for (int j = 0; j < 10; j++) {
        row.add(new Tile());
      }
      this.board.add(row);
    }

    // Initialize RuleChecker
    this.ruleChecker = new RuleChecker();

    // initialise players to empty
    this.players = new ArrayList<iPlayer>();

    // initialise the deck
    TileCreation tileCreation = new TileCreation();
    this.deck = tileCreation.getDeck();

    // initialize current player to null
    this.currentPlayer = null;

    // initialize current hand to empty list
    this.currentHand = new ArrayList<Tile>();

    // initialise gamestate to waiting
    this.gameState = GameState.WAITING;

    // initialize turn number to 0
    this.turnNumber = 0;

    // initialize deck index to 0
    this.deckIndex = 34;

    // Used to assign colors to players
    this.availableColors = new ArrayList<String>();
    availableColors.add("white");
    availableColors.add("black");
    availableColors.add("red");
    availableColors.add("green");
    availableColors.add("blue");
  }

  @Override
  public boolean addPlayer(iPlayer player) throws RemoteException {
    for (iPlayer p : this.players) {
      if (p.getName().equals(player.getName())) {
        return false;
      }
    }
    this.players.add(player);
    return true;
  }

  @Override
  public void runGame() throws RemoteException {
    if (this.gameState.equals(GameState.WAITING)) {
      this.gameState = GameState.STARTED;
      this.turnNumber = 1;

      while (!this.isGameOver()) {
        for (iPlayer p : this.players) {
          if (p.getToken().getTokenStatus() == TokenStatus.ALIVE) {
            this.currentPlayer = p;
            this.currentHand = currentHandForPlayer();
            try {
              ArrayList<Integer> response = p
                      .placeTile(this.board, this.turnNumber, this.currentHand);
              if (response.size() != 4) {
                this.terminatePlayer(p);
                continue;
              }
              Position pos = new Position(response.get(1), response.get(2));
              Tile tilePlaced = this.currentHand.get(response.get(0));
              int degree = response.get(3);
              tilePlaced.rotate(degree);

              this.placeTile(p, pos, tilePlaced);
              if (this.turnNumber == 1) {
                int portNumber = p.placeToken(this.board, this.turnNumber);
                this.placeToken(p, pos, portNumber);
              }
            } catch (Exception e) {
              e.printStackTrace();
              this.terminatePlayer(p);
            }
            if (this.isGameOver()) {
              break;
            }
          }
        }
        this.turnNumber++;
      }
      this.gameState = ENDED;
    }
  }

  @Override
  public boolean isValidTilePlacement(iPlayer player, Position pos, Tile t) throws RemoteException {
    return this.ruleChecker
            .checkTilePlacement(this.board, t, pos, this.currentPlayer, player, this.turnNumber,
                    this.currentHand);
  }

  @Override
  public void placeTile(iPlayer player, Position pos, Tile t) throws RemoteException {
    this.selectedTile = t;
    this.selectedPosition = pos;
//    Observer.run(this.board, this.currentHand, this.currentPlayer, this.selectedTile, this.selectedPosition, this.turnNumber);
    if (this.isValidTilePlacement(player, pos, t)) {
      this.board.get(pos.get_x()).set(pos.get_y(), t);
      this.updateConnections(pos);
      this.moveAllTokens(pos);
      this.deathUpdater();
      t.setPlacedBy(player.getColor());
    } else {
      this.terminatePlayer(player);
    }
  }

  @Override
  public boolean isValidTokenPlacement(iPlayer player, Position pos, int portNumber) throws RemoteException {
    return this.ruleChecker
            .checkTokenPlacement(this.board, pos, portNumber, this.currentPlayer, player,
                    this.turnNumber);
  }

  @Override
  public void placeToken(iPlayer player, Position pos, int portNumber) throws RemoteException {
    if (this.isValidTokenPlacement(player, pos, portNumber)) {
      Tile chosenTile = this.board.get(pos.get_x()).get(pos.get_y());
      Port chosenPort = chosenTile.getPorts().get(portNumber - 1);
      player.setTokenPortNumber(chosenPort.getPortNumber());
      player.setTokenPosition(pos);
      chosenPort.setToken(player.getToken());
    } else {
      this.terminatePlayer(player);
    }

  }

  @Override
  public ColorToken constructToken() {
    String color = this.getAvailableColor(this.players);
    return new ColorToken(color);
  }

  @Override
  public String availableColor() {
    return this.getAvailableColor(this.players);
  }

  @Override
  public boolean isGameOver() throws RemoteException {
    ArrayList<iPlayer> playerList = this.getPlayers();

    int playersAlive = 0;

    for (iPlayer player : playerList) {
      if (player.getToken().getTokenStatus() == TokenStatus.ALIVE) {
        playersAlive += 1;
      }
    }
    return (playersAlive == 0 || playersAlive == 1);
  }

  @Override
  public ArrayList<iPlayer> getPlayers() {
    return this.players;
  }

  // never used
  @Override
  public ArrayList<String> getColorOfOpponents(iPlayer player) throws RemoteException {
    ArrayList<iPlayer> listOfPlayers = (ArrayList<iPlayer>) this.players.clone();
    listOfPlayers.remove(player);
    ArrayList<String> colorOfOpponents = new ArrayList<String>();
    for (int i = 0; i < listOfPlayers.size(); i++) {
      colorOfOpponents.add(listOfPlayers.get(i).getColor());
    }
    return colorOfOpponents;
  }


  @Override
  public String getAvailableColor(ArrayList<iPlayer> players) {
    return this.availableColors.remove(0);
  }

  // view
  @Override
  public Tile getSelectedTile() {
    return selectedTile;
  }

  // view
  @Override
  public Position getSelectedPosition() {
    return this.selectedPosition;
  }

  @Override
  public GameState getGameState() {
    return this.gameState;
  }


  /**
   * Prepare for the next player's move by setting properties of this ref
   */
  private void prepareForNextTurn() throws RemoteException {
    // index of next player
    int index = this.players.indexOf(this.currentPlayer) + 1;
    if (index >= this.players.size()) {
      System.out.println(this.turnNumber);
      this.turnNumber++;
      index = index % this.players.size();
    }

    // while next player is not alive
    while (this.players.get(index).getToken().getTokenStatus() != TokenStatus.ALIVE) {
      // go to next player
      index++;
      if (index >= this.players.size()) {
        this.turnNumber++;
        index = index % this.players.size();
      }
    }
    this.currentPlayer = this.players.get(index);
    this.currentHand = currentHandForPlayer();
  }

  /**
   * Update the path (port.next) of the newly placed tile and its neighbour tiles
   *
   * @param pos position of the newly placed tile
   */
  private void updateConnections(Position pos) {
    int x = pos.get_x();
    int y = pos.get_y();
    // newly placed tile : current
    Tile current = this.board.get(x).get(y);
    // tile below current tile
    if (y + 1 <= 9) {
      // if there is a tile with conncections
      if (this.board.get(x).get(y + 1).getConnections() != null) {
        Tile below = this.board.get(x).get(y + 1);
        // update current
        current.getPorts().get(4).setNext(below.getPorts().get(below.checkRoutes(2) - 1));
        current.getPorts().get(5).setNext(below.getPorts().get(below.checkRoutes(1) - 1));
        // update below
        below.getPorts().get(0).setNext(current.getPorts().get(current.checkRoutes(6) - 1));
        below.getPorts().get(1).setNext(current.getPorts().get(current.checkRoutes(5) - 1));
      }
    }
    //tile above current tile
    if (y - 1 >= 0) {
      // if there is a tile with conncections
      if (this.board.get(x).get(y - 1).getConnections() != null) {
        Tile above = this.board.get(x).get(y - 1);
        // update current
        current.getPorts().get(0).setNext(above.getPorts().get(above.checkRoutes(6) - 1));
        current.getPorts().get(1).setNext(above.getPorts().get(above.checkRoutes(5) - 1));
        // update above
        above.getPorts().get(4).setNext(current.getPorts().get(current.checkRoutes(2) - 1));
        above.getPorts().get(5).setNext(current.getPorts().get(current.checkRoutes(1) - 1));
      }
    }
    //tile right to the current tile
    if (x + 1 <= 9) {
      // if there is a tile with conncections
      if (this.board.get(x + 1).get(y).getConnections() != null) {
        Tile right = this.board.get(x + 1).get(y);
        // update current
        current.getPorts().get(2).setNext(right.getPorts().get(right.checkRoutes(8) - 1));
        current.getPorts().get(3).setNext(right.getPorts().get(right.checkRoutes(7) - 1));
        // update right
        right.getPorts().get(6).setNext(current.getPorts().get(current.checkRoutes(4) - 1));
        right.getPorts().get(7).setNext(current.getPorts().get(current.checkRoutes(3) - 1));
      }
    }
    //tile left to the current tile
    if (x - 1 >= 0) {
      // if there is a tile with conncections
      if (this.board.get(x - 1).get(y).getConnections() != null) {
        Tile left = this.board.get(x - 1).get(y);
        //update current
        current.getPorts().get(6).setNext(left.getPorts().get(left.checkRoutes(4) - 1));
        current.getPorts().get(7).setNext(left.getPorts().get(left.checkRoutes(3) - 1));
        // update left
        left.getPorts().get(2).setNext(current.getPorts().get(current.checkRoutes(8) - 1));
        left.getPorts().get(3).setNext(current.getPorts().get(current.checkRoutes(7) - 1));
      }
    }
  }

  /**
   * Move all tokens next to the tile after a tile is placed at a position
   *
   * @param pos position of the newly placed tile
   */
  private void moveAllTokens(Position pos) throws RemoteException {
    // four possible neighbour tiles
    Tile above, below, left, right;
    //the tile above the newly placed tile
    if (pos.get_y() > 0) {
      above = board.get(pos.get_x()).get(pos.get_y() - 1);
      // above tile has connections (i.e. the tile was placed by a player)
      if (above.getPorts() != null) {
        // check port5
        if (above.getPorts().get(4).getToken() != null) {
          // move the token in port5
          this.moveToken(above.getPorts().get(4).getToken());
        }
        // check port6
        if (above.getPorts().get(5).getToken() != null) {
          // move the token in port6
          this.moveToken(above.getPorts().get(5).getToken());
        }
      }
    }

    //the tile below the newly placed tile
    if (pos.get_y() < 9) {
      below = board.get(pos.get_x()).get(pos.get_y() + 1);
      if (below.getPorts() != null) {
        if (below.getPorts().get(0).getToken() != null) {
          this.moveToken(below.getPorts().get(0).getToken());
        }
        if (below.getPorts().get(1).getToken() != null) {
          this.moveToken(below.getPorts().get(1).getToken());
        }
      }
    }

    //the tile right to the newly placed tile
    if (pos.get_x() < 9) {
      right = board.get(pos.get_x() + 1).get(pos.get_y());
      if (right.getPorts() != null) {
        if (right.getPorts().get(6).getToken() != null) {
          this.moveToken(right.getPorts().get(6).getToken());
        }
        if (right.getPorts().get(7).getToken() != null) {
          this.moveToken(right.getPorts().get(7).getToken());
        }
      }
    }

    //the tile left to the newly placed tile
    if (pos.get_x() > 0) {
      left = board.get(pos.get_x() - 1).get(pos.get_y());
      if (left.getPorts() != null) {
        if (left.getPorts().get(2).getToken() != null) {
          this.moveToken(left.getPorts().get(2).getToken());
        }
        if (left.getPorts().get(3).getToken() != null) {
          this.moveToken(left.getPorts().get(3).getToken());
        }
      }
    }
  }

  /**
   * Move one token
   *
   * @param colorToken token to be moved
   */
  private void moveToken(ColorToken colorToken) throws RemoteException {
    // token's current port number
    int portNumber = colorToken.getPortNumber();

    iPlayer player = getPlayerFromColor(colorToken.getColor());

    // token's tile's current position
    Position pos = colorToken.getPosition();

    // the current port that the token is on
    Port initialPort = this.board.get(pos.get_x()).get(pos.get_y()).getPorts().get(portNumber - 1);

    // the token that the token would collide if it moves
    ColorToken collidedWith = this.board.get(pos.get_x()).get(pos.get_y())
            .getPorts().get(portNumber - 1).collidedWith(pos, this.board, null);

    // if the token will collide with other token, set status of both token to COLLIDED
    if (collidedWith != null) {
      player.setTokenStatus(TokenStatus.COLLIDED);
      this.setPlayerTurnEliminated(player);

      iPlayer playerCollided = getPlayerFromColor(collidedWith.getColor());

      playerCollided.setTokenStatus(TokenStatus.COLLIDED);
      this.setPlayerTurnEliminated(player);
    }

    // move the token all the way and get the position of the tile and port
    Position finalPos = this.board.get(pos.get_x()).get(pos.get_y()).getPorts().get(portNumber - 1)
            .lastPort(pos).pos;
    Port lastPort = this.board.get(pos.get_x()).get(pos.get_y()).getPorts().get(portNumber - 1)
            .lastPort(pos).port;

    try {
      // update the token's position and port
      player.setTokenPosition(finalPos);
      player.setTokenPortNumber(lastPort.getPortNumber());

      // remove token from previous port
      initialPort.setToken(null);
      // add token to new port
      lastPort.setToken(colorToken);
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  /**
   * Check if any token moved out of the board and update their status to dead if they did
   */
  private void deathUpdater() throws RemoteException {
    // top row
    for (int i = 0; i < 10; i++) {
      if (this.board.get(i).get(0).getPorts() != null) {
        Port port1 = this.board.get(i).get(0).getPorts().get(0);
        Port port2 = this.board.get(i).get(0).getPorts().get(1);

        if (port1.getToken() != null) {
          if (port1.getToken().getTokenStatus() == TokenStatus.ALIVE) {
            iPlayer player = this.getPlayerFromColor(port1.getToken().getColor());
            player.setTokenStatus(TokenStatus.DEAD);
            this.setPlayerTurnEliminated(player);
          }
        }
        if (port2.getToken() != null) {
          if (port2.getToken().getTokenStatus() == TokenStatus.ALIVE) {
            iPlayer player = this.getPlayerFromColor(port2.getToken().getColor());
            port2.getToken().setTokenStatus(TokenStatus.DEAD);
            this.setPlayerTurnEliminated(player);
          }
        }
      }
    }
    // bottom row
    for (int i = 0; i < 10; i++) {
      if (this.board.get(i).get(9).getPorts() != null) {
        Port port1 = this.board.get(i).get(9).getPorts().get(4);
        Port port2 = this.board.get(i).get(9).getPorts().get(5);
        if (port1.getToken() != null) {
          if (port1.getToken().getTokenStatus() == TokenStatus.ALIVE) {
            iPlayer player = this.getPlayerFromColor(port1.getToken().getColor());
            player.setTokenStatus(TokenStatus.DEAD);
            this.setPlayerTurnEliminated(player);
          }
        }
        if (port2.getToken() != null) {
          if (port2.getToken().getTokenStatus() == TokenStatus.ALIVE) {
            iPlayer player = this.getPlayerFromColor(port2.getToken().getColor());
            player.setTokenStatus(TokenStatus.DEAD);
            this.setPlayerTurnEliminated(player);
          }
        }
      }
    }
    // right column
    for (int i = 0; i < 10; i++) {
      if (this.board.get(9).get(i).getPorts() != null) {
        Port port1 = this.board.get(9).get(i).getPorts().get(2);
        Port port2 = this.board.get(9).get(i).getPorts().get(3);
        if (port1.getToken() != null) {
          if (port1.getToken().getTokenStatus() == TokenStatus.ALIVE) {
            iPlayer player = this.getPlayerFromColor(port1.getToken().getColor());
            player.setTokenStatus(TokenStatus.DEAD);
            this.setPlayerTurnEliminated(player);
          }
        }
        if (port2.getToken() != null) {
          if (port2.getToken().getTokenStatus() == TokenStatus.ALIVE) {
            iPlayer player = this.getPlayerFromColor(port2.getToken().getColor());
            player.setTokenStatus(TokenStatus.DEAD);
            this.setPlayerTurnEliminated(player);
          }
        }
      }
    }
    // left row
    for (int i = 0; i < 10; i++) {
      if (this.board.get(0).get(i).getPorts() != null) {
        Port port1 = this.board.get(0).get(i).getPorts().get(6);
        Port port2 = this.board.get(0).get(i).getPorts().get(7);
        if (port1.getToken() != null) {
          if (port1.getToken().getTokenStatus() == TokenStatus.ALIVE) {
            iPlayer player = this.getPlayerFromColor(port1.getToken().getColor());
            player.setTokenStatus(TokenStatus.DEAD);
            this.setPlayerTurnEliminated(player);
          }
        }
        if (port2.getToken() != null) {
          if (port2.getToken().getTokenStatus() == TokenStatus.ALIVE) {
            iPlayer player = this.getPlayerFromColor(port2.getToken().getColor());
            player.setTokenStatus(TokenStatus.DEAD);
            this.setPlayerTurnEliminated(player);
          }
        }
      }
    }
  }

  /**
   * Set the turn number which a player was eliminated
   */
  private void setPlayerTurnEliminated(iPlayer player) throws RemoteException {
    player.setTurnEliminated(this.turnNumber);
  }

  /**
   * Terminate a player from the game
   */
  private void terminatePlayer(iPlayer player) throws RemoteException {
    if (player.getToken().getPosition() != null) {
      Position playerCurrentPosition = player.getToken().getPosition();
      Tile playerCurrentTile = this.board.get(playerCurrentPosition.get_x())
              .get(playerCurrentPosition.get_y());
      Port playerCurrentPort = playerCurrentTile.getPorts()
              .get(player.getToken().getPortNumber() - 1);
      playerCurrentPort.setToken(null);
    }
    player.setTokenStatus(TokenStatus.TERMINATED);
    player.setTokenPortNumber(0);
    player.setTokenPosition(null);
    this.setPlayerTurnEliminated(player);
  }

  /**
   * Generate the hand for the current player
   */
  private ArrayList<Tile> currentHandForPlayer() {

    ArrayList<Tile> hand = new ArrayList<>();
    Tile firstTile;
    Tile secondTile;
    Tile thirdTile;

    ArrayList<Tile> deck = this.deck;

    int currentDeck = this.deckIndex;

    if (this.turnNumber != 1) {
      // Checking for Index out of bounds
      if (currentDeck == deck.size() - 2) {
        firstTile = Tile.deepCopy(deck.get(currentDeck + 1), Tile.class);

        secondTile = Tile.deepCopy(deck.get(0), Tile.class);
        this.deckIndex = 0;
      } else if (currentDeck == deck.size() - 1) {
        firstTile = Tile.deepCopy(deck.get(0), Tile.class);

        secondTile = Tile.deepCopy(deck.get(1), Tile.class);
        this.deckIndex = 1;
      } else {
        firstTile = Tile.deepCopy(deck.get(currentDeck + 1), Tile.class);
        secondTile = Tile.deepCopy(deck.get(currentDeck + 2), Tile.class);
        this.deckIndex = currentDeck + 2;
      }

      hand.add(firstTile);
      hand.add(secondTile);

      return hand;
    } else {

      if (currentDeck == deck.size() - 3) {
        firstTile = Tile.deepCopy(deck.get(currentDeck + 1), Tile.class);
        secondTile = Tile.deepCopy(deck.get(currentDeck + 2), Tile.class);
        thirdTile = Tile.deepCopy(deck.get(0), Tile.class);
        this.deckIndex = 0;
      } else if (currentDeck == deck.size() - 2) {
        firstTile = Tile.deepCopy(deck.get(currentDeck + 1), Tile.class);

        secondTile = Tile.deepCopy(deck.get(0), Tile.class);
        thirdTile = Tile.deepCopy(deck.get(1), Tile.class);
        this.deckIndex = 1;
      } else if (currentDeck == deck.size() - 1) {
        firstTile = Tile.deepCopy(deck.get(0), Tile.class);

        secondTile = Tile.deepCopy(deck.get(1), Tile.class);
        thirdTile = Tile.deepCopy(deck.get(2), Tile.class);
        this.deckIndex = 2;
      } else {
        firstTile = Tile.deepCopy(deck.get(currentDeck + 1), Tile.class);

        secondTile = Tile.deepCopy(deck.get(currentDeck + 2), Tile.class);
        thirdTile = Tile.deepCopy(deck.get(currentDeck + 3), Tile.class);
        this.deckIndex = currentDeck + 3;
      }

      hand.add(firstTile);
      hand.add(secondTile);
      hand.add(thirdTile);
      return hand;
    }
  }

  /**
   * Given a color string, returns the corresponding iPlayer
   */
  private iPlayer getPlayerFromColor(String color) throws RemoteException {
    for (iPlayer p : this.players) {
      if (p.getToken().getColor().toLowerCase().equals(color.toLowerCase())) {
        return p;
      }
    }
    return null;
  }


  // to be deleted
  public ArrayList<Tile> getCurrentHand() {
    return this.currentHand;
  }


  // to be deleted
  @Override
  public int getTurnNumber() {
    return this.turnNumber;
  }
  @Override
  public ArrayList<ArrayList<Tile>> getBoard() {
    return board;
  }

  @Override
  public ArrayList<Tile> getDeck() {
    return deck;
  }
}
