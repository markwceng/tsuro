package com.clownswhocode.tsuro.model;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.clownswhocode.tsuro.Util.Position;

import com.clownswhocode.tsuro.Util.TokenStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.rmi.RemoteException;
import java.util.ArrayList;

// Tests for various aspects of our model
public class ModelJUnitTests {
    Tile emptyTile;
    iReferee referee;
    ColorToken redToken;
    iPlayer dumbPlayer1;
    ArrayList<ArrayList<Tile>> board;
    Tile firstTileOnBoard;
    iPlayer dumbPlayer2;
    ColorToken blueToken;
    iStrategy dumbStrategy;
    iPlayer dumbPlayer3;
    ColorToken greenToken;
    iPlayer smartPlayer1;
    iPlayer smartPlayer2;
    iPlayer smartPlayer3;

    @BeforeEach
    public void setup() throws RemoteException {

        referee = new Referee();
        redToken = new ColorToken("White");
        blueToken = new ColorToken("Black");
        greenToken = new ColorToken("Red");
        dumbPlayer1 = new Player(redToken,"Ansh","dumb");
        dumbPlayer2 = new Player(blueToken,"allen","dumb");
        dumbPlayer3 = new Player(greenToken,"mark","dumb");
        board = referee.getBoard();
        firstTileOnBoard = board.get(0).get(0);
        dumbStrategy = new DumbStrategy();
        smartPlayer1 = new Player(redToken,"Kad","smart");
        smartPlayer2 = new Player(blueToken,"Ansh","smart");
        smartPlayer3 = new Player(greenToken,"David","smart");
    }


    @Test
    public void addPlayerToGame() throws RemoteException {

        ArrayList<iPlayer> currentPlayerBeforeJoining = referee.getPlayers();
        assertEquals(0,currentPlayerBeforeJoining.size());

        referee.addPlayer(dumbPlayer1);
        ArrayList<iPlayer> currentPlayerAfterJoining = referee.getPlayers();


        assertEquals(1,currentPlayerAfterJoining.size());

    }

    // Check if a Tile was placed on (0,0)
    @Test
    public void checkPlaceTile() throws RemoteException {
        referee.addPlayer(dumbPlayer1);
        referee.addPlayer(dumbPlayer2);

        referee.runGame();


        assertEquals(board.get(0).get(0).getConnections().size(),4);
    }

    @Test
    public void checkPlayerPositionAtEnd() throws RemoteException{
        referee.addPlayer(dumbPlayer1);
        referee.addPlayer(dumbPlayer2);
        boolean hasToken = false;


        referee.runGame();
        Position redTokenPos = redToken.getPosition();
        Position blueTokenPos = blueToken.getPosition();
        Tile tile = board.get(0).get(0);


       assertEquals(blueTokenPos.get_x(),3);
       assertEquals(blueTokenPos.get_y(),1);
    }


    @Test
    public void testRotateTile() throws RemoteException {
        referee.addPlayer(dumbPlayer1);
        referee.addPlayer(dumbPlayer2);
        boolean hasToken = false;


        referee.runGame();

        Tile t = Tile.deepCopy(referee.getBoard().get(0).get(0),Tile.class);

        assertEquals(t.getRotation(),0);

        t.rotate(90);
        Tile tileOriginal = referee.getBoard().get(0).get(0);

        ArrayList<ArrayList<Port>> connectionsOriginal = tileOriginal.getConnections();
        ArrayList<ArrayList<Port>> connectionsAfterRotate = t.getConnections();

        for (int i = 0; i < connectionsOriginal.size(); i++) {
            for (int j = 0; j < connectionsOriginal.get(i).size(); j++) {
                if ((connectionsOriginal.get(i).get(j).getPortNumber() + 2) % 8 != 0){
                    assertEquals(connectionsAfterRotate.get(i).get(j).getPortNumber(),(connectionsOriginal.get(i).get(j).getPortNumber() + 2) % 8);
                } else{
                    assertEquals(connectionsAfterRotate.get(i).get(j).getPortNumber(),8);
                }
            }
        }

        assertEquals(connectionsAfterRotate.get(0).get(0).getPortNumber(),(connectionsOriginal.get(0).get(0).getPortNumber() + 2) % 8);


        assertEquals(t.getRotation(),90);
    }

    @Test
    public void testGetAdjacentPort() throws RemoteException {
        referee.addPlayer(dumbPlayer1);
        referee.addPlayer(dumbPlayer2);
        referee.runGame();

        Tile t = referee.getBoard().get(0).get(0);

        assertEquals(t.getPorts().get(0).getAdjacentPort(5), 2);
        assertEquals(t.getPorts().get(0).getAdjacentPort(1), 6);
        assertEquals(t.getPorts().get(0).getAdjacentPort(8), 3);
    }

    @Test
    public void testDeepCopy()  throws RemoteException {
        referee.addPlayer(dumbPlayer1);
        referee.addPlayer(dumbPlayer2);
        boolean hasToken = false;


        referee.runGame();

        Tile t = Tile.deepCopy(referee.getBoard().get(0).get(0),Tile.class);

        assertEquals(t,referee.getBoard().get(0).get(0));
    }



    @Test
    public void testGameWith3DumbPlayers() throws RemoteException {
        referee.addPlayer(dumbPlayer1);
        referee.addPlayer(dumbPlayer2);
        referee.addPlayer(dumbPlayer3);

        referee.runGame();

        assertEquals(dumbPlayer3.getToken().getTokenStatus(), TokenStatus.ALIVE);
        assertEquals(dumbPlayer2.getToken().getTokenStatus(),TokenStatus.DEAD);
        assertEquals(dumbPlayer1.getToken().getTokenStatus(),TokenStatus.TERMINATED);

    }

    @Test
    public void testGameWith3SmartPlayers() throws RemoteException {
        referee.addPlayer(smartPlayer1);
        referee.addPlayer(smartPlayer2);
        referee.addPlayer(smartPlayer3);

        referee.runGame();

        assertEquals(smartPlayer3.getToken().getTokenStatus(), TokenStatus.DEAD);
        assertEquals(smartPlayer2.getToken().getTokenStatus(),TokenStatus.DEAD);
        assertEquals(smartPlayer1.getToken().getTokenStatus(),TokenStatus.DEAD);

    }

    @Test
    public void testGetColorOfOpponents() throws RemoteException {
        referee.addPlayer(smartPlayer1);
        referee.addPlayer(smartPlayer2);
        referee.addPlayer(smartPlayer3);

        ArrayList<String> colors = new ArrayList<>();


        assertEquals(2,referee.getColorOfOpponents(smartPlayer3).size());
    }








}
