//package com.clownswhocode.tsuro.model;
//
//import com.clownswhocode.tsuro.controller.*;
//import com.clownswhocode.tsuro.controller.Client;
//import com.google.gson.Gson;
//import com.google.gson.JsonArray;
//import com.google.gson.JsonElement;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.InetAddress;
//import java.util.ArrayList;
//
//public class RunTest {
//
//    Server server;
//    Client client;
//    //    Analyse from the buffer reader, first store each Json element (in string) as an Array list of strings
//    //    Then transform the array list of string into array list of Json elements
//    private ArrayList<JsonElement> tokenize(BufferedReader reader) throws IOException {
//        Gson gson = new Gson();
//        ArrayList<String> tokens = new ArrayList<String>();
//        int next;
//        StringBuilder result = new StringBuilder();
//        boolean seenBracket = false;
//        boolean seenBrace = false;
//        boolean seenQuotation = false;
//        while ((next = reader.read()) != -1) {
//            char currentChar = (char) next;
//
//            switch (currentChar) {
//                case '"':
//                    result.append(currentChar);
//
//                    /* we need to make sure the end quotation is not nested inside of an object or array
//                    that we are currently building before we push the token, i.e. we only push to the list
//                    of tokens if we have just completed building a standalone string */
//                    if (seenQuotation && !seenBrace && !seenBracket) {
//                        //System.out.println(result);
//                        tokens.add(result.toString()); // push completed token to list of tokens
//                        result = new StringBuilder(); // reset contents of our StringBuilder
//                        seenQuotation = false; // reset quotation flag
//                        seenBrace = false; // reset brace flag
//                        seenBracket = false; // reset bracket flag
//                    } else {
//                        seenQuotation = true;
//                    }
//                    break;
//
//                case '[':
////          result.append(currentChar);
////          seenBracket = true;
//                    break;
//
//                case '{':
//                    result.append('[');
//                    seenBrace = true;
//                    break;
//
//                case ']':
//
//                    break;
//                case '}':
//                    result.append(']');
//                    tokens.add(result.toString());
//                    //System.out.println(result);
//                    result = new StringBuilder();
//                    seenBracket = false;
//                    seenBrace = false;
//                    seenQuotation = false;
//                    break;
//
//                case ' ':
//                case '\r':
//                case '\n':
//                    // don't want to include whitespace, return carriages, and newlines in our tokens
//                    break;
//                case ',':
//                    if (seenBrace) {
//                        result.append(currentChar);
//                    }
//                    break;
//                case ':':
//                    result.append(',');
//                    break;
//
//                default:
//                    result.append(currentChar);
//            }
//        }
//        ArrayList<JsonElement> jsonTokens = new ArrayList<>();
//
//        for (String token : tokens) {
//            jsonTokens.add(gson.fromJson(token, JsonElement.class));
//        }
//        System.out.println(tokens);
//        return jsonTokens;
//    }
//
////    private static void createPlayer(String name, String strategy) throws IOException {
////        Client client = new Client("localhost",8000,name,strategy);
////        client.startConnection("localhost",8000);
////    }
//
//
//    public static void main(String[] args) throws IOException {
//        iReferee ref = new Referee();
//        iPlayer player;
//
//
//        ArrayList<ArrayList<String>> output = new ArrayList<ArrayList<String>>();
////      Create a class object
//        RunTest runTest = new RunTest();
////      Read from System.in
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
////      Transfer string into ArrayList of Json elements
//        ArrayList<JsonElement> jsonTokens = runTest.tokenize(reader);
//        Server server = new Server(8000, InetAddress.getByName("127.0.0.1"));
//        server.start();
//        System.out.println("HERE");
//        if (jsonTokens.size() > 5 || jsonTokens.size() < 3) {
//            throw new IllegalArgumentException("Must have between 3 and 5 players");
//        }
//
//        for (JsonElement e : jsonTokens) {
//
//            if (e.isJsonArray()) {
//
//                JsonArray input = (JsonArray) e;
//
//                String name = input.get(1).getAsString();
//
//                String strategy = input.get(3).getAsString();
//                System.out.println(name.toLowerCase());
//                if (strategy.toLowerCase() == "dumb") {
//                    iPlayer dumbPlayer = new DumbPlayer(ref,name);
//                    ref.addPlayer(dumbPlayer);
//
//                    player = dumbPlayer;
//                } else if (strategy.toLowerCase() == "smart") {
//                    iPlayer smartPlayer = new SmartPlayer(ref,name);
//                    ref.addPlayer(smartPlayer);
//
//                    player = smartPlayer;
//                }
//
//
//                Client client = new Client("127.0.0.1","8000",name,strategy.toLowerCase());
//                client.start();
//                //client.sendMessage("join " +name + " " + strategy);
//
//
//
//
//
//
//            }
//        }
//    }
//}