package com.clownswhocode.tsuro.model;

import com.clownswhocode.tsuro.Util.Position;
import com.clownswhocode.tsuro.Util.TokenStatus;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Player extends UnicastRemoteObject implements iPlayer {

    private ColorToken token;
    private int turnEliminated;
    private String name;
    private iRuleChecker ruleChecker;
    private iStrategy strategy;

    // new constructor
    public Player(ColorToken token, String name, String strategy) throws RemoteException, IllegalArgumentException {
        this.token = token;
        this.turnEliminated = 9999;
        this.name = name;
        this.ruleChecker = new RuleChecker();
        if (strategy.toLowerCase().equals("dumb")) {
          this.strategy = new DumbStrategy();
      }
        else if (strategy.toLowerCase().equals("smart")) {
          this.strategy = new SmartStrategy();
        }
        else {
          throw new IllegalArgumentException("Invalid strategy assigned to player");
        }
    }

    // return [tileIndex, x, y, degree]
    @Override
    public ArrayList<Integer> placeTile(ArrayList<ArrayList<Tile>> board, int turnNumber, ArrayList<Tile> hand) throws RemoteException {
        return strategy.chooseTilePlacement(board, turnNumber, hand, this.token.getPosition(), this.token.getPortNumber(), this, this.ruleChecker);
    }

    @Override
    public int placeToken(ArrayList<ArrayList<Tile>> board, int turnNumber) throws RemoteException {
        return this.strategy.chooseTokenPlacement(board, this, this.ruleChecker, turnNumber);
    }

    @Override
    public boolean isValidTilePlacement(ArrayList<ArrayList<Tile>> board, Position pos, Tile t, int turnNumber, ArrayList<Tile> hand) throws RemoteException {
        return this.ruleChecker
                .checkTilePlacement(board, t, pos, this, this, turnNumber,
                        hand);
    }

    @Override
    public boolean isValidTokenPlacement(ArrayList<ArrayList<Tile>> board, Position pos, int portNumber, int turnNumber) throws RemoteException {
        return this.ruleChecker
                .checkTokenPlacement(board, pos, portNumber, this, this, turnNumber);
    }

    @Override
    public ColorToken getToken() {
        return this.token;
    }

    @Override
    public String getColor() {
        return this.token.getColor();
    }

    @Override
    public void setTurnEliminated(int elim) {
        this.turnEliminated = elim;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getTurnEliminated() {
        return this.turnEliminated;
    }

    @Override
    public void setTokenStatus(TokenStatus status) {
        this.token.setTokenStatus(status);
    }

    @Override
    public void setTokenPortNumber(int portNumber) {
        this.token.setPortNumber(portNumber);
    }

    @Override
    public void setTokenPosition(Position pos) {
        this.token.setPosition(pos);
    }
}

