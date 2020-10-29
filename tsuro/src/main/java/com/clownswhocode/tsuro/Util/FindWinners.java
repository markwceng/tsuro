package com.clownswhocode.tsuro.Util;

import com.clownswhocode.tsuro.model.iPlayer;
import com.clownswhocode.tsuro.model.iReferee;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static com.clownswhocode.tsuro.Util.TokenStatus.TERMINATED;

public class FindWinners {

  // Sorts winner list in descending order according to the turn eliminated
  private static void sortWinners(ArrayList<iPlayer> winnerList) throws RemoteException {
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

  public static String calculateWinners(iReferee referee) throws RemoteException {
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
    sortWinners(allWinners);

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

    return output;
  }


}
