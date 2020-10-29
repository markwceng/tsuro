//package com.clownswhocode.tsuro.model;
//
//import com.google.gson.*;
//import com.clownswhocode.tsuro.Util.TileCreation;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//
//public class TileTest {
//
//  //    Analyse from the buffer reader, first store each Json element (in string) as an Array list of strings
//  //    Then transform the array list of string into array list of Json elements
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
//  private ArrayList<String> testExitPort(int index, int degrees, String port) {
//    ArrayList<String> output = new ArrayList<String>();
//    output.add("if ");
//    output.add(port);
//    output.add(" is the entrance ");
//    TileCreation allTiles = new TileCreation();
//    Tile tile = Tile.deepCopy(allTiles.getDeck().get(index), Tile.class);
//    tile.rotate(degrees);
//    ArrayList<ArrayList<Port>> connections = tile.getConnections();
//    for (int i = 0; i < connections.size(); i++) {
//      for (int j = 0; j < connections.get(i).size(); j++) {
//        if (connections.get(i).get(j).getPortNumber() == convertPortToNumber(port)) {
//          if (j == 0) {
//            output.add(convertNumberToPort(connections.get(i).get(j + 1).getPortNumber()));
//          } else if (j == 1) {
//            output.add(convertNumberToPort(connections.get(i).get(j - 1).getPortNumber()));
//          }
//        }
//      }
//    }
//    output.add(" is the exit.");
//    return output;
//  }
//
//  public static void main(String[] args) throws IOException {
//    ArrayList<ArrayList<String>> output = new ArrayList<ArrayList<String>>();
////      Create a class object
//    TileTest tileTest = new TileTest();
////      Read from System.in
//    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
////      Transfer string into ArrayList of Json elements
//    ArrayList<JsonElement> jsonTokens = tileTest.tokenize(reader);
//    System.out.println(reader);
////      If validation passes, sort the array list
//    System.out.println(jsonTokens.size());
////      If validation passes, sort the array list
//    for (JsonElement e : jsonTokens) {
//      if (e.isJsonArray()) {
//        JsonArray input = (JsonArray) e;
//        int index = input.get(0).getAsInt();
//        int degrees = input.get(1).getAsInt();
//        String port = input.get(2).getAsString();
//        output.add(tileTest.testExitPort(index, degrees, port));
//      }
//    }
//    for (ArrayList<String> sentence : output) {
//      for (String word : sentence) {
//        System.out.print(word);
//      }
//      System.out.println();
//    }
//  }
//}