//package com.clownswhocode.tsuro.model;
//
//import com.google.gson.*;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.util.ArrayList;
//
//
//public class MethodTests {
//
//
//  private ArrayList<JsonElement> tokenize(BufferedReader reader) throws IOException {
//    Gson gson = new Gson();
//    ArrayList<String> tokens = new ArrayList<String>();
//    int next;
//    StringBuilder result = new StringBuilder();
//    boolean seenBracket = false;
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
//          if (seenQuotation && !seenBrace && !seenBracket) {
//            tokens.add(result.toString()); // push completed token to list of tokens
//            result = new StringBuilder(); // reset contents of our StringBuilder
//            seenQuotation = false; // reset quotation flag
//            seenBrace = false; // reset brace flag
//            seenBracket = false; // reset bracket flag
//          } else {
//            seenQuotation = true;
//          }
//          break;
//
//        case '[':
//          result.append(currentChar);
//          seenBracket = true;
//          break;
//
//        case '{':
//          result.append(currentChar);
//          seenBrace = true;
//          break;
//
//        case ']':
//        case '}':
//          result.append(currentChar);
//          tokens.add(result.toString());
//          result = new StringBuilder();
//          seenBracket = false;
//          seenBrace = false;
//          seenQuotation = false;
//          break;
//
//        case ' ':
//        case '\r':
//        case '\n':
//          // don't want to include whitespace, return carriages, and newlines in our tokens
//          break;
//
//        default:
//          result.append(currentChar);
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
//  private int convertPortToNumber(String port) {
//    return port.toLowerCase().charAt(0) - 'a' + 1;
//  }
//
//  private String convertNumberToPort(int number) {
//    int portNumber = number + 96;
//    char port = (char) portNumber;
//    return Character.toString(port).toUpperCase();
//  }
//
//  public void testInitial(Game g, int index, int degrees, String color, String port, int x, int y) {
////    ArrayList<String> output = new ArrayList<String>();
//
//
//
//    // Deck
//    ArrayList<Tile> deck = g.getDeck();
//
//    // Tile
//    Tile t = deck.get(index);
//    // Rotate the tile
//    t.rotate(degrees);
//    // Staring hand of first player (in a normal game this would be generated and contain 3 tiles)
//    ArrayList<Tile> playerTiles = new ArrayList<Tile>();
//    playerTiles.add(t);
//    // Get currentHand for the player
//    // ArrayList<Tile> currentHand = g.getCurrentHand();
//    // Create player avatar
//    ColorToken c = new ColorToken(color);
//
//    // Create the player
//    iPlayer player = new Player(g);
//
//    g.addPlayer(player);
//    g.setCurrentHand(playerTiles);
//
//    // PLace the first tile for the player
//    g.placeTile(player,new Position(x,y),t);
//    // Place Player on the board
//    g.placeToken(player,new Position(x,y),convertPortToNumber(port));
//
//    // Build output
////    output.add(((Player) player).getColortoken().getColor());
////    output.add(Integer.toString(index));
////    output.add(Integer.toString(degrees));
////    output.add(port);
////    output.add(Integer.toString(x));
////    output.add(Integer.toString(y));
//
//
//
//
//  }
//
//  public void testIntermediate(Game g, int index, int degrees, String color, int x, int y) {
//    ArrayList<String> output = new ArrayList<String>();
//    ArrayList<String> notPlayedOutput = new ArrayList<String>();
//    //iPlayer currentPlayer = g.getCurrentPlayer();
//    for (iPlayer p:g.getPlayers()) {
//      if (p.getToken().getColor().equals(color)) {
//        g.setCurrentPlayer(p);
//        // Deck
//        ArrayList<Tile> deck = g.getDeck();
//
//        // Tile
//        Tile t = deck.get(index);
//        // Rotate the tile
//        t.rotate(degrees);
//
//        // Staring hand of first player (in a normal game this would be generated and contain 2 tiles)
//        ArrayList<Tile> playerTiles = new ArrayList<>();
//        playerTiles.add(t);
//
//        g.placeTile(p,new Position(x,y),t);
//      }
//    }
//  }
//
//  public static void main(String[] args) throws IOException {
//
//    // Game 1 test
//    Game game = new Game();
//
//    ArrayList<Tile> deck = game.getDeck();
//    if (deck.size() == 35) {
//      System.out.println("Deck size: " + deck.size());
//    }
//
//    iPlayer player = new Player(game);
//    Tile t = deck.get(33);
//    game.setCurrentPlayer(player);
//    game.setTurnNumber(1);
//    player.placeTile(new Position(0,0), t);
//    player.placeToken(new Position(0,0),4);
//
//    game.setTurnNumber(2);
//
//    Tile t2 = deck.get(34);
//    player.placeTile(new Position(1,0), t2);
//
//    // Deep copy test
////    Tile t3 = Tile.deepCopy(deck.get(2), Tile.class);
////    t3.rotate(90);
////    Tile t5 = deck.get(2);
////    t5.rotate(90);
////    Tile t6 = deck.get(2);
//
//    //Game 2 test: collision
////    Game game = new Game();
////
////    ArrayList<Tile> deck = game.getDeck();
////    if (deck.size() == 35) {
////      System.out.println("Deck size: " + deck.size());
////    }
////
////    iPlayer player = new Player(game, new ColorToken("Red"), new ArrayList<>());
////    iPlayer player2 = new Player(game, new ColorToken("Blue"), new ArrayList<>());
////    Tile t = deck.get(1);
////    Tile t2 = deck.get(2);
////    t2.rotate(90);
////    game.setCurrentPlayer(player);
////    game.setTurnNumber(1);
////    player.placeTile(t, new Position(0, 0));
////    player.placeToken(new Position(0, 0), 5);
////    game.setCurrentPlayer(player2);
////    player2.placeTile(t2, new Position(0, 2));
////    player2.placeToken(new Position(0, 2), 2);
////
////    game.setTurnNumber(2);
////    Tile t3 = deck.get(33);
////    game.setCurrentPlayer(player);
////    player.placeTile(t3, new Position(0, 1));
//  }
//}
