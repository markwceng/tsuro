//package com.clownswhocode.tsuro.model;
//
//import com.clownswhocode.tsuro.Util.Position;
//import com.clownswhocode.tsuro.Util.TokenStatus;
//import com.google.gson.*;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.rmi.RemoteException;
//import java.sql.Ref;
//import java.util.ArrayList;
//
//
//public class GameTest {
//
//
//  // Tokenizer to get Json objects from STDin
//  ArrayList<JsonElement> tokenize(BufferedReader reader) throws IOException {
//    Gson gson = new Gson();
//    ArrayList<String> tokens = new ArrayList<String>();
//    int next;
//    StringBuilder result = new StringBuilder();
//    boolean seenOpenBracketOnce = false;
//    boolean seenOpenBracketTwice = false;
//    boolean seenClosedBracket = false;
//    boolean seenBrace = false;
//    boolean seenQuotation = false;
//    while ((next = reader.read()) != -1) {
//      char currentChar = (char) next;
//
//      switch (currentChar) {
//        case '"':
//          result.append(currentChar);
//
//                    /* we need to make sure the end quotation is not nested inside of an object or array
//                    that we are currently building before we push the token, i.e. we only push to the list
//                    of tokens if we have just completed building a standalone string */
//          if (seenQuotation && !seenBrace && !seenOpenBracketOnce) {
//            tokens.add(result.toString()); // push completed token to list of tokens
//            result = new StringBuilder(); // reset contents of our StringBuilder
//            seenQuotation = false; // reset quotation flag
//            seenBrace = false; // reset brace flag
//            seenOpenBracketOnce = false; // reset bracket flag
//          } else {
//            seenQuotation = true;
//          }
//          break;
//
//        case '[':
//          //result.append(currentChar);
//          if (seenOpenBracketOnce) {
//            seenOpenBracketTwice = true;
//            result.append(currentChar);
//
//            seenClosedBracket = false;
//          } else {
//            seenOpenBracketOnce = true;
//          }
//
//          break;
//
//        case '{':
//          result.append(currentChar);
//          seenBrace = true;
//          break;
//
//        case ']':
//        case '}':
//          if (!seenClosedBracket) {
//            result.append(currentChar);
//            tokens.add(result.toString());
//
//            result = new StringBuilder();
//            System.out.println(result);
//
//            seenBrace = false;
//            seenQuotation = false;
//            seenClosedBracket = true;
//
//          }
//
//          break;
//        case ',':
//          if (seenOpenBracketOnce && !seenClosedBracket) {
//            result.append(currentChar);
//          }
//          break;
//        case ' ':
//        case '\r':
//        case '\n':
//          // don't want to include whitespace, return carriages, and newlines in our tokens
//          break;
//
//        default:
//          result.append(currentChar);
//
//
//      }
//    }
//    ArrayList<JsonElement> jsonTokens = new ArrayList<>();
//
//    for (String token : tokens) {
//      jsonTokens.add(gson.fromJson(token, JsonElement.class));
//    }
//    return jsonTokens;
//  }
//
//  // Convert Port index from a String to a number
//  private int convertPortToNumber(String port) {
//    return port.toLowerCase().charAt(0) - 'a' + 1;
//  }
//
//  // Convert Port index from a number to a String
//  private String convertNumberToPort(int number) {
//    int portNumber = number + 96;
//    char port = (char) portNumber;
//    return Character.toString(port).toUpperCase();
//  }
//
//  // Testing initial placements
//  public void testInitial(Referee r, int index, int degrees, String color, String port, int x,
//      int y) throws RemoteException {
////    ArrayList<String> output = new ArrayList<String>();
//
//    // turn number 1 to force correct rule checking
//    r.setTurnNumber(1);
//
//    // Deck
//    ArrayList<Tile> deck = r.getDeck();
//
//    // Tile
//    Tile t = deck.get(index);
//
//    // Rotate the tile
//    t.rotate(degrees);
//
//    // Staring hand of first player (in a normal game this would be generated and contain 3 tiles)
//    ArrayList<Tile> playerTiles = new ArrayList<Tile>();
//    playerTiles.add(t);
//
//    // Create player avatar
//    ColorToken c = new ColorToken(color);
//
//    // Create the player
//    iPlayer player = new Player(c);
//
//    // Adds Player to the Game's Player list
//    r.addPlayer(player);
//    r.setCurrentPlayer(player);
//    r.setCurrentHand(playerTiles);
//
//    // Place the first tile for the player
//    r.placeTile(player, new Position(x, y), t);
//
//    // Place Player on the board
//    r.placeToken(player, new Position(x, y), convertPortToNumber(port));
//  }
//
//  public void testIntermediate(Referee r, int index, int degrees, String color, int x, int y) throws RemoteException {
//    ArrayList<String> output = new ArrayList<String>();
//    ArrayList<String> notPlayedOutput = new ArrayList<String>();
//
//    // arbitrary turn number greater than 1 to force correct rule checking
//    r.setTurnNumber(3);
//    //iPlayer currentPlayer = g.getCurrentPlayer();
//    for (int i = 0; i < r.getPlayers().size(); i++) {
//      iPlayer p = r.getPlayers().get(i);
//      if (p.getToken().getColor().equals(color)) {
//        r.setCurrentPlayer(p);
//        // Deck
//        ArrayList<Tile> deck = r.getDeck();
//
//        // Tile
//        Tile t = deck.get(index);
//        // Rotate the tile
//        t.rotate(degrees);
//
//        // Starting hand of first player (in a normal game this would be generated and contain 2 tiles)
//        ArrayList<Tile> playerTiles = new ArrayList<>();
//        playerTiles.add(t);
//
//        Tile tTemp = deck.get(14);
//        playerTiles.add(tTemp);
//
//        r.setCurrentHand(playerTiles);
//
//        r.placeTile(p, new Position(x, y), t);
//      }
//    }
//  }
//
//  // Determine final state and according output for a Player
//  public String determineOutput(Referee r, String color) throws RemoteException {
//    StringBuilder outputLine = new StringBuilder();
//    iPlayer correspondingPlayer = getPlayerFromColor(r, color);
//
//    // Avatar was never placed initially
//    if (correspondingPlayer == null) {
//      outputLine.append("[\"").append(color).append("\", ").append(" \"never played\"]");
//      return outputLine.toString(); // prevents null pointers later on
//    }
//    // Player exited the board during the series of tests
//    else if (correspondingPlayer.getToken().getTokenStatus() == TokenStatus.DEAD) {
//      outputLine.append("[\"").append(color).append("\", ").append("\" exited\"]");
//    }
//    // Player collided with another Player during the series of tests
//    else if (correspondingPlayer.getToken().getTokenStatus() == TokenStatus.COLLIDED) {
//      outputLine.append("[\"").append(color).append("\", ").append("\" collided\"]");
//    }
//    // Player was alive at the end of the tests and finished somewhere on the board
//    else {
//      Tile playerEndingTile = r.getBoard().get(correspondingPlayer.getToken().getPosition().get_x())
//          .get(correspondingPlayer.getToken().getPosition().get_y());
//
//      Position playerEndingPosition = correspondingPlayer.getToken().getPosition();
//
//      // Building output in case of valid input
//      outputLine.append("[\"").append(color)
//          .append("\", ").append(fetchTileIndex(r, playerEndingTile))
//          .append(", ").append(playerEndingTile.getRotation())
//          .append(", \"")
//          .append(convertNumberToPort(correspondingPlayer.getToken().getPortNumber()))
//          .append("\", ").append(playerEndingPosition.get_x())
//          .append(", ").append(playerEndingPosition.get_y())
//          .append("]");
//    }
//
//    return outputLine.toString();
//  }
//
//  // Gets the Player whose ColorToken is of the specified color
//  public iPlayer getPlayerFromColor(Referee r, String color) {
//    for (iPlayer p : r.getPlayers()) {
//      if (p.getToken().getColor().toLowerCase().equals(color.toLowerCase())) {
//        return p;
//      }
//    }
//    return null;
//  }
//
//  // Get index of a Tile from the deck
//  public int fetchTileIndex(Referee r, Tile t) {
//    ArrayList<Tile> deck = r.getDeck();
//
//    for (int i = 0; i < deck.size(); i++) {
//      if (deck.get(i).equals(t)) {
//        return i;
//      }
//    }
//    // Tile not found in the deck (should never happen)
//    return -1;
//  }
//
//
//  public static void main(String[] args) throws IOException {
//    Referee ref = new Referee();
//    ArrayList<String> output = new ArrayList<String>();
//
//    ArrayList<String> allColors = new ArrayList<String>();
//    allColors.add("white");
//    allColors.add("black");
//    allColors.add("red");
//    allColors.add("green");
//    allColors.add("blue");
//
//    // Create a class object
//    GameTest gameTest = new GameTest();
//
//    // Read from System.in
//    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//
//    // Transfer input into ArrayList of (what should be) JSON arrays that each represent a placement
//    ArrayList<JsonElement> jsonTokens = gameTest.tokenize(reader);
//
//    // Iterate over each placement until all are complete
//    for (JsonElement j : jsonTokens) {
//      // Attempt to convert tokenized input to JSON array
//      JsonArray command;
//      try {
//        command = (JsonArray) j;
//      } catch (Exception e) {
//        throw new IllegalArgumentException("Cannot convert move to JSON array");
//      }
//
//      // Call appropriate test method according to format of each JSON array
//      // Initial placement handling
//      if (command.size() == 6) {
//        int index = command.get(0).getAsInt();
//        int degrees = command.get(1).getAsInt();
//        String color = command.get(2).getAsString();
//        String port = command.get(3).getAsString();
//        int x = command.get(4).getAsInt();
//        int y = command.get(5).getAsInt();
//
//        gameTest.testInitial(ref, index, degrees, color, port, x, y);
//
//      }
//      // Intermediate placement handling
//      else if (command.size() == 5) {
//        String color = command.get(0).getAsString();
//        int index = command.get(1).getAsInt();
//        int degrees = command.get(2).getAsInt();
//        int x = command.get(3).getAsInt();
//        int y = command.get(4).getAsInt();
//
//        gameTest.testIntermediate(ref, index, degrees, color, x, y);
//      }
//      // Token is JSON array, but not of the correct format
//      else {
//        throw new IllegalArgumentException(
//            "JSON array is of invalid format, no placement can be completed");
//      }
//    }
//
//    // Leading JSON array bracket
//    System.out.println("[");
//
//    // Display corresponding output for each Player's ColorToken's final position
//    for (String color : allColors) {
//      System.out.println(gameTest.determineOutput(ref, color) + ",");
//    }
//    System.out.println("]");
//  }
//}
