package com.clownswhocode.tsuro.View;

import com.clownswhocode.tsuro.Util.Position;
import com.clownswhocode.tsuro.model.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import static com.clownswhocode.tsuro.Util.GameState.STARTED;

public class Observer extends JPanel implements iObserver {

    private static final int rows = 10;
    private static final int columns = 10;
    // Used to keep track of when to save image
    private int counter = 0;
    private ArrayList<ArrayList<Tile>> board;
    private ArrayList<Tile> hand;
    private iPlayer player;
    private Tile tile;
    private Position pos;
    private int turnNumber;

    public static final int viewSize = 180;

    public Observer(ArrayList<ArrayList<Tile>> board, ArrayList<Tile> hand, iPlayer player, Tile tile, Position pos, int turnNumber) {
        this.board = board;
        this.hand = hand;
        this.player = player;
        this.tile = tile;
        this.pos = pos;
        this.turnNumber = turnNumber;
        int preferredWidth = columns * viewSize;
        int preferredHeight = rows * 100;
        // Set window size
        setPreferredSize(new Dimension(preferredWidth, preferredHeight));

    }


    /**
     * Return awt.Color value from a string
     *
     * @param col String representation of a color name
     * @return java.awt.Color value for the given color
     */
    static Color getColor(String col) {
        Color color = null;
        switch (col.toLowerCase()) {
            case "black":
                color = Color.BLACK;
                break;
            case "blue":
                color = Color.BLUE;
                break;
            case "green":
                color = Color.GREEN;
                break;
            case "red":
                color = Color.RED;
                break;
            case "white":
                color = Color.WHITE;
                break;
        }
        return color;
    }


    /**
     * Draw boxes to show Ports on a Tile
     *
     * @param x          x-coordinate
     * @param y          y-coordinate
     * @param rectWidth  width
     * @param rectHeight height
     * @param g2         Graphics2D object
     */
    private void drawPorts(int x, int y, int rectWidth, int rectHeight, Graphics2D g2) {

        g2.drawRect(x, y, rectWidth, rectHeight);
        // Port 1 and 2
        g2.fillRect(x + rectWidth * 2 / 3, y, 5, 5);
        g2.fillRect(x + rectWidth * 1 / 3, y, 5, 5);

        // Port 3 and 4
        g2.fillRect(x + rectWidth - 5, y + rectWidth * 2 / 3, 5, 5);
        g2.fillRect(x + rectWidth - 5, y + rectWidth * 1 / 3, 5, 5);

        // Port 5 and 6
        g2.fillRect(x + rectWidth * 2 / 3, y + rectHeight - 5, 5, 5);
        g2.fillRect(x + rectWidth * 1 / 3, y + rectHeight - 5, 5, 5);

        // Port 7 and 8
        g2.fillRect(x, y + rectWidth * 2 / 3, 5, 5);
        g2.fillRect(x, y + rectWidth * 1 / 3, 5, 5);
    }

    /**
     * Draw string labels on the screen
     *
     * @param g2 Graphics2D object
     */
    private void placeText(Graphics2D g2) throws RemoteException {
        // Display current player
        g2.setFont(new Font("SansSerif", Font.BOLD + Font.ITALIC, 40));
        g2.setPaint(Color.BLACK);
        g2.drawString("Current Player", 1000, 50);

        g2.setFont(new Font("SansSerif", Font.PLAIN, 40));
        g2.drawString(player.getName(), 1000, 100);

        // Draw current hand string
        g2.setFont(new Font("SansSerif", Font.BOLD + Font.ITALIC, 40));
        g2.setPaint(Color.BLACK);
        g2.drawString("Current Hand", 1000, 200);

        // Display selected tile string
        g2.setFont(new Font("SansSerif", Font.BOLD + Font.ITALIC, 40));
        g2.setPaint(Color.BLACK);
        g2.drawString("Selected Tile", 1000, 450);

        // Display selected placement string
        g2.setFont(new Font("SansSerif", Font.BOLD + Font.ITALIC, 30));
        g2.drawString("Selected Placement", 1000, 650);
        g2.drawString(
                "(" + pos.get_x() + "," + pos.get_y() + ")", 1000, 700);
    }

    /**
     * Draw paths between Ports on a Tile
     *
     * @param paths List of Pth2D objects that are drawn to the screen
     * @param g2    Graphics2D object
     */
    private void drawPaths(ArrayList<Path2D> paths, Graphics2D g2) {
        if (paths.size() != 0) {

            for (Path2D path :
                    paths) {
                g2.setColor(Color.BLACK);
                g2.draw(path);
            }
        }
    }

    /**
     * Draw ColorTokens on a Tile's list of Ports
     *
     * @param x          x-coordinate
     * @param y          y-coordinate
     * @param rectWidth  width
     * @param rectHeight height
     * @param ports      List of Ports
     * @param g2         Graphics2D object
     */
    private void drawColorTokens(int x, int y, int rectWidth, int rectHeight, ArrayList<Port> ports,
                                 Graphics2D g2) {
        Position port1 = new Position(x + rectWidth * 1 / 3, y);
        Position port2 = new Position(x + rectWidth * 2 / 3, y);

        Position port3 = new Position(x + rectWidth - 5, y + rectWidth * 1 / 3);
        Position port4 = new Position(x + rectWidth - 5, y + rectWidth * 2 / 3);

        Position port5 = new Position(x + rectWidth * 2 / 3, y + rectHeight - 5);
        Position port6 = new Position(x + rectWidth * 1 / 3, y + rectHeight - 5);

        Position port7 = new Position(x, y + rectWidth * 2 / 3);
        Position port8 = new Position(x, y + rectWidth * 1 / 3);
        if (ports != null) {
            for (Port p : ports) {
                if (p.getToken() != null) {
                    switch (p.getPortNumber()) {
                        case 1:
                            Shape circle = new Ellipse2D.Double(port1.get_x() - 5, port1.get_y() - 5, 20, 20);
                            g2.setColor(getColor(p.getToken().getColor()));
                            g2.fill(circle);
                            g2.setColor(Color.BLACK);
                            break;
                        case 2:
                            Shape circle2 = new Ellipse2D.Double(port2.get_x() - 5, port2.get_y() - 5, 20, 20);
                            g2.setColor(getColor(p.getToken().getColor()));
                            g2.fill(circle2);
                            g2.setColor(Color.BLACK);
                            break;
                        case 3:
                            Shape circle3 = new Ellipse2D.Double(port3.get_x() - 5, port3.get_y() - 5, 20, 20);
                            g2.setStroke(new BasicStroke(1));
                            g2.setColor(getColor(p.getToken().getColor()));
                            g2.fill(circle3);
                            g2.setColor(Color.BLACK);
                            break;
                        case 4:
                            Shape circle4 = new Ellipse2D.Double(port4.get_x() - 5, port4.get_y() - 5, 20, 20);
                            g2.setColor(getColor(p.getToken().getColor()));
                            g2.fill(circle4);
                            g2.setColor(Color.BLACK);
                            break;
                        case 5:
                            Shape circle5 = new Ellipse2D.Double(port5.get_x() - 5, port5.get_y() - 5, 20, 20);
                            g2.setColor(getColor(p.getToken().getColor()));
                            g2.fill(circle5);
                            g2.setColor(Color.BLACK);
                            break;
                        case 6:
                            Shape circle6 = new Ellipse2D.Double(port6.get_x() - 5, port6.get_y() - 5, 20, 20);
                            g2.setColor(getColor(p.getToken().getColor()));
                            g2.fill(circle6);
                            g2.setColor(Color.BLACK);
                            break;
                        case 7:
                            Shape circle7 = new Ellipse2D.Double(port7.get_x() - 5, port7.get_y() - 5, 20, 20);
                            g2.setColor(getColor(p.getToken().getColor()));
                            g2.fill(circle7);
                            g2.setColor(Color.BLACK);

                            break;
                        case 8:
                            Shape circle8 = new Ellipse2D.Double(port8.get_x() - 5, port8.get_y() - 5, 20, 20);
                            g2.setColor(getColor(p.getToken().getColor()));
                            g2.fill(circle8);
                            g2.setColor(Color.BLACK);
                            break;
                    }
                }
            }
        }
    }

    /**
     * Main draw method that renders all components on the screen
     *
     * @param g Graphics object
     */
    @Override
    public void paintComponent(Graphics g) {
        // Cast as Graphics2D
        Graphics2D g2 = (Graphics2D) g;
        // Used to keep track of when to save image
        counter++;
        super.paintComponent(g2);
        g2.setBackground(new Color(166, 164, 164));
        // Clear the board
        g2.clearRect(0, 0, getWidth(), getHeight());

        // Draw text on the panel
        try {
            placeText(g2);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        // Coordinates for selected Tile
        int selected_x = 1000;
        int selected_y = 500;
        int rectWidth = 80;
        int rectHeight = 80;

        // Draw Ports for selected Tile
        drawPorts(selected_x, selected_y, rectWidth, rectHeight, g2);
        // Get Path2D objects that represent path connections between Ports in the selected Tile
        ArrayList<Path2D> paths_selected = tile.pathsToDraw(selected_x, selected_y);
        // Draw paths
        drawPaths(paths_selected, g2);

        // Drawing current hand
        for (int k = 0; k < hand.size(); k++) {
            int x = k * rectWidth + 1000;
            int y = 250;
            // Get Path2D objects that represent path connections between Ports in a Tile
            ArrayList<Path2D> paths = hand.get(k).pathsToDraw(x, y);
            // Draw Ports on a Tile
            drawPorts(x, y, rectWidth, rectHeight, g2);
            // Draw paths between Ports
            drawPaths(paths, g2);

        }

        // Draw the game board
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                // Coordinates for Tiles on the game board
                int x = i * rectWidth;
                int y = j * rectHeight;
                // Grab Tile
                Tile t = board.get(i).get(j);
                // Get Path2D objects that represent path connections between Ports in a Tile
                ArrayList<Path2D> paths = t.pathsToDraw(x, y);
                // Draw Ports in the Tile
                drawPorts(x, y, rectWidth, rectHeight, g2);
                // Draw paths
                drawPaths(paths, g2);
                // Get list of Ports from a Tile
                ArrayList<Port> ports = t.getPorts();
                // Draw any ColorToken if they exist on a Port
                drawColorTokens(x, y, rectWidth, rectHeight, ports, g2);
            }
        }
        // Only save image once
        if (this.counter == 1) {
            this.takeImage();
        }
    }

    /**
     * Saves an image of the current panel to a BufferedImage
     */
    private void takeImage() {
        int w = this.getWidth();
        int h = this.getHeight();
        BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        // Paint the current screen onto the image
        this.print(g);
        g.dispose();
        try {
            ImageIO.write(image, "jpeg", new File(
                    "BoardAt" + "Turn" + turnNumber + player.getName()
                            + ".jpeg"));
        } catch (IOException e) {
        }
    }

    public static void run(ArrayList<ArrayList<Tile>> board, ArrayList<Tile> hand, iPlayer player, Tile tile, Position pos, int turnNumber) {
        JFrame frame = new JFrame("TsuroBoard");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Observer observer = new Observer(board, hand, player, tile, pos, turnNumber);
        frame.add(observer);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();

        frame.setVisible(true);
    }

    @Override
    public void leaveGame() {
    }
}
