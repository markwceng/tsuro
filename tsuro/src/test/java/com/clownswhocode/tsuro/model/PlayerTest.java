//package com.clownswhocode.tsuro.model;
//
//import com.clownswhocode.tsuro.Util.Position;
//import com.clownswhocode.tsuro.Util.TokenStatus;
//import com.clownswhocode.tsuro.modelClient.ColorToken;
//
//import com.google.gson.Gson;
//import com.google.gson.JsonArray;
//import com.google.gson.JsonElement;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.rmi.RemoteException;
//import java.util.ArrayList;
//
//public class PlayerTest {
//
//  ArrayList<JsonElement> tokenize(BufferedReader reader) throws IOException {
//    Gson gson = new Gson();
//    ArrayList<String> tokens = new ArrayList<String>();
//    int next;
//    StringBuilder result = new StringBuilder();
//    boolean seenBracketOnce = false;
//    boolean seenBracketTwice = false;
//    boolean seenClosedBracket = false;
//    boolean seenClosedBracketTwice = false;
//    boolean seenFirstClosingBracket = false;
//    int openBrackets = 0;
//    char prevChar = '.';
//    boolean isNewInput = false;
//    boolean addComma = false;
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
//          if (seenQuotation && !seenBrace && !seenBracketOnce) {
//            tokens.add(result.toString()); // push completed token to list of tokens
//            result = new StringBuilder(); // reset contents of our StringBuilder
//            seenQuotation = false; // reset quotation flag
//            seenBrace = false; // reset brace flag
//            seenBracketOnce = false; // reset bracket flag
//          } else {
//            seenQuotation = true;
//          }
//          break;
//
//        case '[':
//
//          prevChar = currentChar;
//
//          openBrackets += 1;
//          if (openBrackets != 1) {
//            if (prevChar == '[') {
//              isNewInput = true;
//              //System.out.println(openBrackets);
//            }
//          }
//
//          addComma = true;
//          //result.append(currentChar);
//          if (seenFirstClosingBracket) {
//            if (seenBracketOnce) {
//              seenBracketTwice = true;
//              result.append(currentChar);
//
//              seenClosedBracket = false;
//            } else {
//              seenBracketOnce = true;
//            }
//          } else {
//            if (seenBracketOnce) {
//              seenBracketTwice = true;
//              result.append(currentChar);
//              seenClosedBracket = false;
//            } else {
//              seenBracketOnce = true;
//            }
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
//          prevChar = currentChar;
//
//          if (openBrackets == 1) {
//            break;
//          }
//          openBrackets -= 1;
//
//          if (openBrackets == 2) {
//            if (isNewInput) {
//
//              if (!seenClosedBracket && !seenClosedBracketTwice) {
//                result.append(currentChar);
//                seenClosedBracket = true;
//                break;
//              }
//              if (!seenClosedBracketTwice && seenClosedBracket) {
//                result.append(currentChar);
//                //System.out.println(result);
//                tokens.add(result.toString());
//                addComma = false;
//                result = new StringBuilder();
//                seenClosedBracketTwice = true;
//                seenBrace = false;
//                seenQuotation = false;
//                seenClosedBracket = true;
//
//                break;
//              }
//            }
//          }
//          if (!seenFirstClosingBracket) {
//            result.append(currentChar);
//
//            //System.out.println(result);
//            addComma = false;
//            tokens.add(result.toString());
//
//            result = new StringBuilder();
//
//            seenBrace = false;
//            seenFirstClosingBracket = true;
//            seenQuotation = false;
//            seenClosedBracket = false;
//            break;
//
//          }
//
//          if (seenBracketOnce) {
//            result.append(currentChar);
//            //System.out.println(result);
//            tokens.add(result.toString());
//            addComma = false;
//
//            result = new StringBuilder();
//
//            seenBrace = false;
//            seenFirstClosingBracket = true;
//            seenClosedBracketTwice = false;
//            seenQuotation = false;
//
//            break;
//          }
//
//          break;
//        case ',':
////                     if (seenBracketOnce && seenBracketTwice && !seenFirstClosingBracket && seenClosedBracket) {
////                        // System.out.println(result + " before comma");
////                        result.append(currentChar);
////                       // System.out.println(result + " after comma");
////                        //System.out.println("comma");
////
////                    } else if (seenBracketTwice && !seenClosedBracketTwice) {
////                         result.append(currentChar);
////                     }
//
//          if (addComma) {
//            result.append(currentChar);
//          }
//
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
//  public void testInitial(Referee g, int index, int degrees, String color, String port, int x,
//      int y) {
////    ArrayList<String> output = new ArrayList<String>();
//
//    // turn number 1 to force correct rule checking
//    g.setTurnNumber(1);
//
//    // Deck
//    ArrayList<Tile> deck = g.getDeck();
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
//    ColorToken playerColor = new ColorToken(color);
//
//        /*
//        // Create the player
//        iPlayer player = new Player(g, playerColor);
//
//
//        // Adds Player to the Referee's Player list
//        g.addPlayer(player);
//        g.setCurrentPlayer(player);
//
//        // Ensure the Referee has the Player's desired Tile in its CurrentHand
//        g.setCurrentHand(playerTiles);
//
//        // Place the first tile for the player
//        g.placeTile(player, new Position(x, y), t);
//
//        // Place Player on the board
//        g.placeToken(player, new Position(x, y), convertPortToNumber(port));
//        */
//  }
//
//  public void testIntermediate(Referee g, int index, int degrees, String color, int x, int y) throws RemoteException {
//    ArrayList<String> output = new ArrayList<String>();
//    ArrayList<String> notPlayedOutput = new ArrayList<String>();
//
//    // arbitrary turn number greater than 1 to force correct rule checking
//    g.setTurnNumber(3);
//    //iPlayer currentPlayer = g.getCurrentPlayer();
//
//    iPlayer p = getPlayerFromColor(g, color);
//
//    g.setCurrentPlayer(p);
//    // Deck
//    ArrayList<Tile> deck = g.getDeck();
//
//    // Tile
//    Tile t = deck.get(index);
//    // Rotate the tile
//    t.rotate(degrees);
//
//    // Starting hand of first player (in a normal game this would be generated and contain 2 tiles)
//    ArrayList<Tile> playerTiles = new ArrayList<>();
//    playerTiles.add(t);
//
//    Tile tTemp = deck.get(14);
//    playerTiles.add(tTemp);
//
//    g.setCurrentHand(playerTiles);
//
//    g.placeTile(p, new Position(x, y), t);
//  }
//
//  // Determine final state and according output for a Player
//  public String determineOutput(Referee g, String color) throws RemoteException {
//    StringBuilder outputLine = new StringBuilder();
//    iPlayer correspondingPlayer = getPlayerFromColor(g, color);
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
//    // Player collided with another Player during the series of tests
//    else if (correspondingPlayer.getToken().getTokenStatus() == TokenStatus.TERMINATED) {
//      outputLine.append("[\"").append(color).append("\", ").append("\"terminated\"]");
//    }
//    // Player was alive at the end of the tests and finished somewhere on the board
//    else {
//      Tile playerEndingTile = g.getBoard().get(correspondingPlayer.getToken().getPosition().get_x())
//          .get(correspondingPlayer.getToken().getPosition().get_y());
//
//      Position playerEndingPosition = correspondingPlayer.getToken().getPosition();
//
//      // Building output in case of valid input
//      outputLine.append("[\"").append(color)
//          .append("\", ").append(fetchTileIndex(g, playerEndingTile))
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
//
//  public boolean determineLegality(Referee g, String color, int chosenTileIndex, int degrees, int x,
//      int y, int tileIndex1, int tileIndex2) throws RemoteException {
//    iPlayer player = getPlayerFromColor(g, color);
//    Position desiredPos = new Position(x, y);
//
//    g.setTurnNumber(3);
//
//    g.setCurrentPlayer(player);
//    // Deck
//    ArrayList<Tile> deck = g.getDeck();
//
//    // Tile
//    Tile chosenTile = deck.get(chosenTileIndex);
//    // Rotate the tile
//    chosenTile.rotate(degrees);
//
//    Tile otherTile1 = deck.get(tileIndex1);
//    Tile otherTile2 = deck.get(tileIndex2);
//
//    ArrayList<Tile> playerTiles = new ArrayList<>();
//    playerTiles.add(otherTile1);
//    playerTiles.add(otherTile2);
//
//    g.setCurrentHand(playerTiles);
//
//    return player.isValidTilePlacement(desiredPos, chosenTile);
//  }
//
//  // Gets the Player whose ColorToken is of the specified color
//  public iPlayer getPlayerFromColor(Referee g, String color) throws RemoteException {
//    for (iPlayer p : g.getPlayers()) {
//      if (p.getToken().getColor().toLowerCase().equals(color.toLowerCase())) {
//        return p;
//      }
//    }
//    return null;
//  }
//
//  // Get index of a Tile from the deck
//  public int fetchTileIndex(Referee g, Tile t) {
//    ArrayList<Tile> deck = g.getDeck();
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
//  public static void main(String[] args) throws IOException {
//    Referee game = new Referee();
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
//    PlayerTest playerTest = new PlayerTest();
//
//    // Read from System.in
//    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//
//    // Transfer input into ArrayList of (what should be) JSON arrays that each represent a placement
//    ArrayList<JsonElement> jsonTokens = playerTest.tokenize(reader);
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
//        playerTest.testInitial(game, index, degrees, color, port, x, y);
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
//        playerTest.testIntermediate(game, index, degrees, color, x, y);
//      } else if (command.size() == 3) {
//
//        JsonArray spec = (JsonArray) command.get(0);
//        String color = spec.get(0).getAsString();
//        int chosenTileIndex = spec.get(1).getAsInt();
//        int degrees = spec.get(2).getAsInt();
//        int x = spec.get(3).getAsInt();
//        int y = spec.get(4).getAsInt();
//
//        int tileIndex1 = command.get(1).getAsInt();
//        int tileIndex2 = command.get(2).getAsInt();
//
//        if (playerTest.determineLegality(game, color, chosenTileIndex, degrees, x, y, tileIndex1,
//            tileIndex2)) {
//          System.out.println("\"legal\"");
//        } else {
//          System.out.println("\"illegal\"");
//        }
//      }
//      // Token is JSON array, but not of the correct format
//      else {
//        throw new IllegalArgumentException(
//            "JSON array is of invalid format, no placement can be completed");
//      }
//    }
//  }
//}
