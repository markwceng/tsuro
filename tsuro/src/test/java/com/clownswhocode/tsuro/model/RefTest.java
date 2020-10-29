package com.clownswhocode.tsuro.model;


import com.clownswhocode.tsuro.model.ColorToken;

import com.google.gson.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static com.clownswhocode.tsuro.Util.TokenStatus.*;

public class RefTest {

  ArrayList<JsonElement> tokenize(BufferedReader reader) throws IOException {
    Gson gson = new Gson();
    ArrayList<String> tokens = new ArrayList<String>();
    int next;
    boolean seenQuote = false;
    StringBuilder result = new StringBuilder();

    while ((next = reader.read()) != -1) {
      char currentChar = (char) next;

      switch (currentChar) {

        case '"':
          if (seenQuote) {
            result.append(currentChar);
            System.out.println(result);
            tokens.add(result.toString());
            result = new StringBuilder();
            seenQuote = false;
          } else {
            result.append(currentChar);
            seenQuote = true;
          }

          break;

        case '[':

          //result.append(currentChar);

          break;

        case '{':
          result.append(currentChar);

          break;

        case ']':
        case '}':
//                    result.append(currentChar);
//                    System.out.println(result);
//                    tokens.add(result.toString());
          break;
        case ',':

          //result.append(currentChar);

          break;
        case ' ':
        case '\r':
        case '\n':
          // don't want to include whitespace, return carriages, and newlines in our tokens
          break;

        default:
          if (seenQuote == false){
            result.append(currentChar);
            System.out.println(result);
            tokens.add(result.toString());
            result = new StringBuilder();
          } else{
            result.append(currentChar);
          }

      }
    }
    ArrayList<JsonElement> jsonTokens = new ArrayList<>();

    for (String token : tokens) {
      jsonTokens.add(gson.fromJson(token, JsonElement.class));
    }
    System.out.println(jsonTokens);
    return jsonTokens;
  }


  // Sorts winner list in descending order according to the turn eliminated
  public void sortWinners(ArrayList<iPlayer> winnerList) throws RemoteException {
    boolean winnersSorted = false;
    iPlayer temp;

    while (!winnersSorted) {
      winnersSorted = true;

      for (int i = 0; i < winnerList.size() - 1; i++) {
        if (winnerList.get(i).getTurnEliminated() < winnerList.get(i + 1).getTurnEliminated()) {
          temp = winnerList.get(i);
          winnerList.set(i, winnerList.get(i + 1));
          winnerList.set(i + 1, temp);
          winnersSorted = false;
        }
      }
    }
  }

  public static void main(String[] args) throws IOException {

    // Create a class object
    RefTest refTest = new RefTest();

    // Read from System.in
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    // Transfer input into ArrayList of (what should be) JSON arrays that each represent a placement
    ArrayList<JsonElement> jsonTokens = refTest.tokenize(reader);

    ArrayList<String> playerNames = new ArrayList<>();

    // Add each player's name to an ArrayList
    for (JsonElement j : jsonTokens) {
      // Attempt to convert tokenized input to JSON array
      String playerName;
      try {
        playerName = j.getAsString();
        playerNames.add(playerName);
      } catch (Exception e) {
        throw new IllegalArgumentException("Cannot convert move to JSON array");
      }
    }

    // Create referee
    iReferee referee = new Referee();

    // Add each DumbPlayer to the referee's list, colors will be assigned automatically
    for (String playerName : playerNames) {
      ColorToken token = referee.constructToken();
      referee.addPlayer(new Player(token, playerName, "dumb"));
    }

    referee.runGame();

    ArrayList<iPlayer> allWinners = new ArrayList<>();
    ArrayList<iPlayer> losers = new ArrayList<>();

    ArrayList<String> loserNames = new ArrayList<>();
    ArrayList<ArrayList<String>> winnerLists = new ArrayList<>();
    winnerLists.add(new ArrayList<>());
    winnerLists.add(new ArrayList<>());
    winnerLists.add(new ArrayList<>());
    winnerLists.add(new ArrayList<>());
    winnerLists.add(new ArrayList<>());

    // Separate winners and losers
    for (iPlayer player : referee.getPlayers()) {
      if (player.getToken().getTokenStatus() != TERMINATED) {
        allWinners.add(player);
      } else {
        losers.add(player);
      }
    }

    // Create list of loser names only
    for (iPlayer loser : losers) {
      loserNames.add("\"" + loser.getName() + "\"");
    }

    // Sort loser names
    Collections.sort(loserNames);

    // Sorts winner list in descending order
    refTest.sortWinners(allWinners);

    int winnersAllocated = 0;
    int listsAllocated = 0;
    int compare = allWinners.get(0).getTurnEliminated();

    while (winnersAllocated < allWinners.size()) {
      for (int i = winnersAllocated; i < allWinners.size(); i++) {
        if (allWinners.get(i).getTurnEliminated() == compare) {
          // Add players who lost on the same turn to one list
          String outputString = "\"" + allWinners.get(i).getName() + "\"";

          winnerLists.get(listsAllocated).add(outputString);

          winnersAllocated++;
        } else {
          // Set compare to the next highest finisher's turn number
          compare = allWinners.get(i).getTurnEliminated();

          // Reached a new turn number, so we must have completed a list allocation
          listsAllocated++;

          // Don't want to keep looping or else we will skip a winner
          break;
        }
      }
    }

    // Sort winner lists alphabetically and remove any hanging lists
    ArrayList<ArrayList<String>> toRemove = new ArrayList<>();

    for (int i = 0; i < winnerLists.size(); i++) {
      if (winnerLists.get(i).size() == 0) {
        toRemove.add(winnerLists.get(i));
      } else {
        Collections.sort(winnerLists.get(i));
      }
    }

    winnerLists.removeAll(toRemove);

    // Get arrays of winner and loser names only
    String[][] winnerArrays = new String[winnerLists.size()][];

    for (int i = 0; i < winnerLists.size(); i++) {
      ArrayList<String> winnerList = winnerLists.get(i);
      winnerArrays[i] = winnerList.toArray(new String[0]);
    }

    String[] loserArray = loserNames.toArray(new String[0]);

    String output = "{\"winners\" : " + Arrays.deepToString(winnerArrays) +
        ",\n" + "\"losers\" : " + Arrays.toString(loserArray) + "}";

    System.out.println(output);
  }
}
