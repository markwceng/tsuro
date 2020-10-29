package com.clownswhocode.tsuro.Util;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import java.io.IOException;
import java.util.ArrayList;

public class ServerJsonParser {

  public static ArrayList<JsonElement> tokenize(String reader) throws IOException {
    Gson gson = new Gson();
    ArrayList<String> tokens = new ArrayList<String>();
    int next;
    boolean seenQuote = false;
    StringBuilder result = new StringBuilder();
    int i = 0;

    while (i < reader.length()) {
      char currentChar = reader.charAt(i);

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
          if (seenQuote == false) {
            result.append(currentChar);
            System.out.println(result);
            tokens.add(result.toString());
            result = new StringBuilder();
          } else {
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

}
