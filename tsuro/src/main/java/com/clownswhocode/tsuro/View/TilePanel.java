//<<<<<<< HEAD
////package com.clownswhocode.tsuro.View;
////
////import com.clownswhocode.tsuro.model.*;
////
////import javax.swing.*;
////import java.awt.*;
////import java.awt.geom.Line2D;
////import java.awt.geom.Path2D;
////import java.util.ArrayList;
////import java.util.Random;
////public class TilePanel extends JPanel{
////  public static final int NUM_ROWS = 10;
////  public static final int NUM_COLS = 10;
////  iReferee referee;
////
////  public static final int PREFERRED_GRID_SIZE_PIXELS = 180;
////
////  public Tile[][] board;
////
////  public TilePanel(iReferee referee){
////    this.referee = referee;
////    this.board = new Tile[NUM_ROWS][NUM_COLS];
////    for (int i = 0; i < NUM_ROWS; i++) {
////      for (int j = 0; j < NUM_COLS; j++) {
////        ArrayList<ArrayList<Tile>> gameBoard = referee.getBoard();
////
////        this.board[i][j] = gameBoard.get(i).get(j);
////      }
////    }
////    int preferredWidth = NUM_COLS * PREFERRED_GRID_SIZE_PIXELS;
////    int preferredHeight = NUM_ROWS * PREFERRED_GRID_SIZE_PIXELS;
////    setPreferredSize(new Dimension(preferredWidth, preferredHeight));
////  }
////
////
////  @Override
////  public void paintComponent(Graphics g) {
////    // Important to call super class method
////    Graphics2D g2 = (Graphics2D) g;
////    super.paintComponent(g2);
////    // Clear the board
////    g2.clearRect(0, 0, getWidth(), getHeight());
////
////    // Draw the grid
////    int rectWidth = 100;
////    int rectHeight = 100;
////
////    for (int i = 0; i < NUM_ROWS; i++) {
////      for (int j = 0; j < NUM_COLS; j++) {
////        // Upper left corner of this terrain rect
////        int x = i * rectWidth;
////        int y = j * rectHeight;
////        g2.setStroke(new BasicStroke(2));
////        g2.drawRect(x,y,rectWidth,rectHeight);
////
////        // Port 1 and 2
////        g2.fillRect(x + 70, y, 5, 5);
////        g2.fillRect(x + 30, y, 5, 5);
////
////
////        /*
////            Coordinates
////            Port1 = (x + 30, y)
////            Port2 = (x + 70, y)
////
////            Port3 = (x + rectWidth - 5, y + 30)
////            Port4 = (x + rectWidth - 5, y + 70)
////
////            Port5 = (x + 70, y + rectHeight - 5)
////            Port6 = (x + 30, y + rectHeight - 5)
////
////            Port7 = (x, y + 70)
////            Port8 = (x, y + 30)
////        */
////
////
////        // Curve from Port 1 to 2
////        Path2D.Double path_1_to_2 = new Path2D.Double();
////        path_1_to_2.moveTo(x + 30, y);
////        path_1_to_2.curveTo(x + 40, y + 50,
////                x +  60, y + 50,
////                x + 70, y );
////
////        // Curve from Port 1 to 3
////        Path2D.Double path_1_to_3 = new Path2D.Double();
////        path_1_to_3.moveTo(x + 30, y);
////        path_1_to_3.curveTo(x + 40, y + 15,
////                x +  25, y + 15,
////                x + rectWidth - 5, y + 30);
////        // Curve from Port 1 to 4
////        Path2D.Double path_1_to_4 = new Path2D.Double();
////        path_1_to_4.moveTo(x + 30, y);
////        path_1_to_4.curveTo(x + 40, y + 15,
////                x +  25, y + 30,
////                x + rectWidth - 5, y + 70);
////        // Curve from Port 1 to 5
////        Path2D.Double path_1_to_5 = new Path2D.Double();
////        path_1_to_5.moveTo(x + 30, y);
////        path_1_to_5.curveTo(x + 70, y + rectHeight - 5,
////                x + 70, y + rectHeight - 5,
////                x + 70, y + rectHeight - 5);
////
////        // Curve from Port 1 to 6
////        Path2D.Double path_1_to_6 = new Path2D.Double();
////        path_1_to_6.moveTo(x + 30, y);
////        path_1_to_6.lineTo(x + 30, y + rectHeight - 5);
////
////        // Curve from Port 1 to 5
////        Path2D.Double path_1_to_7 = new Path2D.Double();
////        path_1_to_7.moveTo(x + 30, y);
////        path_1_to_7.curveTo(x + 30, y + 40,
////                x + 20, y + 50,
////                x , y + 70);
////
////        // Curve from Port 1 to Port 8
////        Path2D.Double path_1_to_8 = new Path2D.Double();
////        path_1_to_8.moveTo(x + 30, y);
////        path_1_to_8.curveTo(x + 25, y + 25,
////                x +  25, y + 25,
////                x, y + 30);
////
////
////        // Curve from Port 2 to Port 1
////        Path2D.Double path_2_to_1 = path_1_to_2;
////
////        // Curve from Port 2 to Port 3
////        Path2D.Double path_2_to_3 = new Path2D.Double();
////        path_2_to_3.moveTo(x + 70, y);
////        path_2_to_3.curveTo(x + 80, y + 20,
////                x +  75, y + 25,
////                x + rectWidth - 5, y + 30);
////        // Curve from Port 2 to Port 4
////        Path2D.Double path_2_to_4 = new Path2D.Double();
////        path_2_to_4.moveTo(x + 70, y);
////        path_2_to_4.curveTo(x + 70, y + 40,
////                x +  85, y + 55,
////                x + rectWidth - 5, y + 70);
////
////        // Curve from Port 2 to Port 5
////        Path2D.Double path_2_to_5 = new Path2D.Double();
////        path_2_to_5.moveTo(x + 70, y);
////        path_2_to_5.lineTo(  x + 70, y + rectHeight - 5);
////
////        // Curve from Port 2 to Port 6
////        Path2D.Double path_2_to_6 = new Path2D.Double();
////        path_2_to_6.moveTo(x + 70, y);
////        path_2_to_6.curveTo(x + 30, y + rectHeight - 5,
////                x + 30, y + rectHeight - 5,
////                x + 30, y + rectHeight - 5);
////        // Curve from Port 2 to Port 7
////        Path2D.Double path_2_to_7 = new Path2D.Double();
////        path_2_to_7.moveTo(x + 70, y);
////        path_2_to_7.curveTo(x + 65, y + 45,
////                x + 30, y + 65,
////                x , y + 70);
////
////        // Curve from Port 2 to Port 8
////        Path2D.Double path_2_to_8 = new Path2D.Double();
////        path_2_to_8.moveTo(x + 70, y);
////        path_2_to_8.curveTo(x + 65, y + 15,
////                x + 30, y + 25,
////                x , y + 30);
////
////
////        // Curve from Port 3 to Port 1
////        Path2D.Double path_3_to_1 = path_1_to_3;
////
////        // Curve from Port 3 to Port 2
////        Path2D.Double path_3_to_2 = path_2_to_3;
////
////        // Curve from Port 3 to 4
////        Path2D.Double path_3_to_4 = new Path2D.Double();
////        path_3_to_4.moveTo(x + rectWidth - 5, y + 30);
////        path_3_to_4.curveTo(x + 60, y + 50,
////                x +  60, y + 50,
////                x + rectWidth - 5, y + 70);
////
////        // Curve from Port 3 to 5
////        Path2D.Double path_3_to_5 = new Path2D.Double();
////        path_3_to_5.moveTo(x + rectWidth - 5, y + 30);
////        path_3_to_5.curveTo(x + 55, y + 55,
////                x +  55, y + 55,
////                x + 30, y + rectHeight - 5);
////
////        // Curve from Port 3 to 6
////        Path2D.Double path_3_to_6 = new Path2D.Double();
////        path_3_to_6.moveTo(x + rectWidth - 5, y + 30);
////        path_3_to_6.curveTo(x + 70, y + 70,
////                x + 75, y + 70,
////                x + 70, y + rectHeight - 5);
////
////        // Curve from Port 3 to 7
////        Path2D.Double path_3_to_7 = new Path2D.Double();
////        path_3_to_7.moveTo(x + rectWidth - 5, y + 30);
////        path_3_to_7.curveTo(x + 50, y + 50,
////                x +  50, y + 50,
////                x, y + 70);
////
////        // Curve from Port 3 to 8
////        Path2D.Double path_3_to_8 = new Path2D.Double();
////        path_3_to_8.moveTo(x + rectWidth - 5, y + 30);
////        path_3_to_8.lineTo(x, y + 30);
////
////        // Curve from Port 4 to Port 1
////        Path2D.Double path_4_to_1 = path_1_to_4;
////        // Curve from Port 4 to Port 2
////        Path2D.Double path_4_to_2 = path_2_to_4;
////        // Curve from Port 4 to Port 3
////        //Path2D.Double path_4_to_3 = path_3_to_4;
////        // Curve from Port 4 to Port 5
////        Path2D.Double path_4_to_5 = new Path2D.Double();
////        path_4_to_5.moveTo(x + rectWidth - 5, y + 70);
////        path_4_to_5.curveTo(x + 70, y + 80,
////                x +  75, y + 90,
////                x + 70, y + rectHeight - 5);
////
////        Path2D.Double path_4_to_6 = new Path2D.Double();
////        path_4_to_6.moveTo(x + rectWidth - 5, y + 70);
////        path_4_to_6.curveTo(x + 65, y + 75,
////                x +  55, y + 80,
////                x + 30, y + rectHeight - 5);
////        Path2D.Double path_4_to_7 = new Path2D.Double();
////        path_4_to_7.moveTo(x + rectWidth - 5, y + 70);
////        path_4_to_7.lineTo(x, y + 70);
////
////        Path2D.Double path_4_to_8 = new Path2D.Double();
////        path_4_to_8.moveTo(x + rectWidth - 5, y + 70);
////        path_4_to_8.lineTo(x, y + 30);
////
////
////        // Curve from Port 5 to Port 1
////        Path2D.Double path_5_to_1 = new Path2D.Double();
////        path_5_to_1.moveTo(x + 70, y + rectHeight - 5);
////        path_5_to_1.lineTo(x + 30, y);
////
////        // Curve from Port 5 to Port 2
////        Path2D.Double path_5_to_2 = new Path2D.Double();
////        path_5_to_2.moveTo(x + 70, y + rectHeight - 5);
////        path_5_to_2.curveTo(x + 70, y + rectHeight - 5,
////                x + 70, y + rectHeight - 5,
////                x + 70, y);
////
////        // Curve from Port 5 to Port 3
////        Path2D.Double path_5_to_3 = new Path2D.Double();
////        path_5_to_3.moveTo(x + 70, y + rectHeight - 5);
////        path_5_to_3.curveTo(x + 75, y + 55,
////                x + 70, y + 65,
////                x + rectWidth - 5, y + 30);
////
////
////        // Curve from Port 5 to Port 4
////        Path2D.Double path_5_to_4 = new Path2D.Double();
////        path_5_to_4.moveTo(x + 70, y + rectHeight - 5);
////        path_5_to_4.curveTo(x + rectWidth - 20, y + 70,
////                x + rectWidth - 10, y + 70 ,
////                x + rectWidth - 5, y + 70);
////
////        // Curve from Port 5 to Port 6
////        Path2D.Double path_5_to_6 = new Path2D.Double();
////        path_5_to_6.moveTo(x + 70, y + rectHeight - 5);
////        path_5_to_6.curveTo(x + 60, y + 50,
////                x + 40, y + 50,
////                x + 30, y + rectHeight - 5);
////
////        // Curve from Port 5 to Port 7
////        Path2D.Double path_5_to_7 = new Path2D.Double();
////        path_5_to_7.moveTo(x + 70, y + rectHeight - 5);
////        path_5_to_7.curveTo(x + 60, y + rectHeight - 20,
////                x + 35, y + rectHeight - 30,
////                x, y + 70);
////
////        // Curve from Port 5 to Port 8
////        Path2D.Double path_5_to_8 = new Path2D.Double();
////        path_5_to_8.moveTo(x + 70, y + rectHeight - 5);
////        path_5_to_8.curveTo(x + 60, y + 50,
////                x + 25, y + 40,
////                x, y + 30);
////        // Curve from Port 7 to Port 1
////        Path2D.Double path_6_to_1 = path_1_to_6;
////        // Curve from Port 7 to Port 2
////        Path2D.Double path_6_to_2 = path_2_to_6;
////        // Curve from Port 7 to Port 3
////        Path2D.Double path_6_to_3 = path_3_to_6;
////        // Curve from Port 7 to Port 4
////        Path2D.Double path_6_to_4 = path_4_to_6;
////        // Curve from Port 7 to Port 5
////        Path2D.Double path_6_to_5 = path_5_to_6;
////
////        Path2D.Double path_6_to_7 = new Path2D.Double();
////        path_6_to_7.moveTo(x + 30, y  + rectHeight - 5);
////        path_6_to_7.curveTo(x + 30, y + 90,
////                x +  35, y + 80,
////                x, y + 70);
////        Path2D.Double path_6_to_8 = new Path2D.Double();
////        path_6_to_8.moveTo(x + 30, y  + rectHeight - 5);
////        path_6_to_8.curveTo(x + 30, y + 85,
////                x +  35, y + 70,
////                x, y + 30);
////
////        // Curve from Port 7 to Port 1
////        Path2D.Double path_7_to_1 = path_1_to_7;
////        // Curve from Port 7 to Port 2
////        Path2D.Double path_7_to_2 = path_2_to_7;
////        // Curve from Port 7 to Port 3
////        Path2D.Double path_7_to_3 = path_3_to_7;
////        // Curve from Port 7 to Port 4
////        Path2D.Double path_7_to_4 = path_4_to_7;
////        // Curve from Port 7 to Port 5
////        Path2D.Double path_7_to_5 = path_5_to_7;
////        // Curve from Port 7 to Port 6
////        Path2D.Double path_7_to_6 = path_6_to_7;
////         //Curve from Port 7 to Port 8
////        Path2D.Double path_7_to_8 = new Path2D.Double();
////        path_7_to_8.moveTo(x, y + 70);
////        path_7_to_8.curveTo(x + 35, y + 50,
////                x + 50, y + 55,
////                x , y + 30);
////        //        // Curve from Port 8 to Port 1
////        Path2D.Double path_8_to_1 = path_1_to_8;
////        // Curve from Port 8 to Port 2
////        Path2D.Double path_8_to_2 = path_2_to_8;
////        // Curve from Port 8 to Port 3
////        Path2D.Double path_8_to_3 = path_3_to_8;
////        // Curve from Port 8 to Port 4
////        Path2D.Double path_8_to_4 = path_4_to_8;
////        // Curve from Port 8 to Port 5
////        Path2D.Double path_8_to_5 = path_5_to_8;
////        // Curve from Port 8 to Port 6
////        Path2D.Double path_8_to_6 = path_6_to_8;
////        // Curve from Port 8 to Port 7
////        Path2D.Double path_8_to_7 = path_7_to_8;
////
////
////
////
////
////
////        g2.draw(path_6_to_8);
////
////
////
//////
//////
//////
//////
//////
//////
//////
//////        g2.draw(path_5_to_1);
//////        g2.draw(path_5_to_2);
//////        g2.draw(path_5_to_3);
//////        g2.draw(path_5_to_4);
//////        g2.draw(path_5_to_6);
//////        g2.draw(path_5_to_7);
//////        g2.draw(path_5_to_8);
////
////        // Port 1 and 2
////        g2.fillRect(x + 70, y, 5, 5);
////        g2.fillRect(x + 30, y, 5, 5);
////
////        // Port 5 and 6
////        g2.fillRect(x + 70, y + rectHeight - 5, 5, 5);
////        g2.fillRect(x + 30, y + rectHeight - 5, 5, 5);
////
////        // Port 7 and 8
////        g2.fillRect(x, y + 70, 5, 5);
////        g2.fillRect(x, y + 30, 5, 5);
////
////        // Port 3 and 4
////        g2.fillRect(x + rectWidth - 5, y + 70, 5, 5);
////        g2.fillRect(x + rectWidth - 5, y + 30, 5, 5);
////
////      }
////    }
////  }
////  public static void main(String[] args) {
////    // http://docs.oracle.com/javase/tutorial/uiswing/concurrency/initial.html
////    Referee ref = new Referee();
////    SwingUtilities.invokeLater(new Runnable() {
////      public void run() {
////        JFrame frame = new JFrame("TsuroBoard");
////        TilePanel tilePanel = new TilePanel(ref);
////        frame.add(tilePanel);
////        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
////        frame.pack();
////        frame.setVisible(true);
////      }
////    });
////  }
////}
//=======
//package com.clownswhocode.tsuro.View;
//
//import com.clownswhocode.tsuro.model.*;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.geom.Line2D;
//import java.awt.geom.Path2D;
//import java.util.ArrayList;
//import java.util.Random;
//public class TilePanel extends JPanel{
//  public static final int NUM_ROWS = 10;
//  public static final int NUM_COLS = 10;
//  Referee referee;
//
//  public static final int PREFERRED_GRID_SIZE_PIXELS = 180;
//
//  public Tile[][] board;
//
//  public TilePanel(Referee referee){
//    this.referee = referee;
//    this.board = new Tile[NUM_ROWS][NUM_COLS];
//    for (int i = 0; i < NUM_ROWS; i++) {
//      for (int j = 0; j < NUM_COLS; j++) {
//        ArrayList<ArrayList<Tile>> gameBoard = referee.getBoard();
//
//        this.board[i][j] = gameBoard.get(i).get(j);
//      }
//    }
//    int preferredWidth = NUM_COLS * PREFERRED_GRID_SIZE_PIXELS;
//    int preferredHeight = NUM_ROWS * PREFERRED_GRID_SIZE_PIXELS;
//    setPreferredSize(new Dimension(preferredWidth, preferredHeight));
//  }
//  @Override
//  public void paintComponent(Graphics g) {
//    // Important to call super class method
//    Graphics2D g2 = (Graphics2D) g;
//    super.paintComponent(g2);
//    // Clear the board
//    g2.clearRect(0, 0, getWidth(), getHeight());
//
//    // Draw the grid
//    int rectWidth = 100;
//    int rectHeight = 100;
//
//    for (int i = 0; i < NUM_ROWS; i++) {
//      for (int j = 0; j < NUM_COLS; j++) {
//        // Upper left corner of this terrain rect
//        int x = i * rectWidth;
//        int y = j * rectHeight;
//        g2.setStroke(new BasicStroke(2));
//        g2.drawRect(x,y,rectWidth,rectHeight);
//
//
//        // Curve from Port 1 to 2
//        Path2D.Double path_1_to_2 = new Path2D.Double();
//        path_1_to_2.moveTo(x + 30, y);
//        path_1_to_2.curveTo(x + 40, y + 50,
//                x +  60, y + 50,
//                x + 70, y );
//
//        // Curve from Port 1 to 3
//        Path2D.Double path_1_to_3 = new Path2D.Double();
//        path_1_to_3.moveTo(x + 30, y);
//        path_1_to_3.curveTo(x + 40, y + 15,
//                x +  25, y + 15,
//                x + rectWidth - 5, y + 30);
//        // Curve from Port 1 to 4
//        Path2D.Double path_1_to_4 = new Path2D.Double();
//        path_1_to_4.moveTo(x + 30, y);
//        path_1_to_4.curveTo(x + 40, y + 15,
//                x +  25, y + 30,
//                x + rectWidth - 5, y + 70);
//        // Curve from Port 1 to 5
//        Path2D.Double path_1_to_5 = new Path2D.Double();
//        path_1_to_5.moveTo(x + 30, y);
//        path_1_to_5.curveTo(x + 70, y + rectHeight - 5,
//                x + 70, y + rectHeight - 5,
//                x + 70, y + rectHeight - 5);
//
//        // Curve from Port 1 to 6
//        Path2D.Double path_1_to_6 = new Path2D.Double();
//        path_1_to_6.moveTo(x + 30, y);
//        path_1_to_6.lineTo(x + 30, y + rectHeight - 5);
//
//        // Curve from Port 1 to 5
//        Path2D.Double path_1_to_7 = new Path2D.Double();
//        path_1_to_7.moveTo(x + 30, y);
//        path_1_to_7.curveTo(x + 30, y + 40,
//                x + 20, y + 50,
//                x , y + 70);
//
//        // Curve from Port 1 to Port 8
//        Path2D.Double path_1_to_8 = new Path2D.Double();
//        path_1_to_8.moveTo(x + 30, y);
//        path_1_to_8.curveTo(x + 25, y + 25,
//                x +  25, y + 25,
//                x, y + 30);
//
//
//        // Curve from Port 2 to Port 1
//        Path2D.Double path_2_to_1 = path_1_to_2;
//
//        // Curve from Port 2 to Port 3
//        Path2D.Double path_2_to_3 = new Path2D.Double();
//        path_2_to_3.moveTo(x + 70, y);
//        path_2_to_3.curveTo(x + 80, y + 20,
//                x +  75, y + 25,
//                x + rectWidth - 5, y + 30);
//        // Curve from Port 2 to Port 4
//        Path2D.Double path_2_to_4 = new Path2D.Double();
//        path_2_to_4.moveTo(x + 70, y);
//        path_2_to_4.curveTo(x + 70, y + 40,
//                x +  85, y + 55,
//                x + rectWidth - 5, y + 70);
//
//        // Curve from Port 2 to Port 5
//        Path2D.Double path_2_to_5 = new Path2D.Double();
//        path_2_to_5.moveTo(x + 70, y);
//        path_2_to_5.lineTo(  x + 70, y + rectHeight - 5);
//
//        // Curve from Port 2 to Port 6
//        Path2D.Double path_2_to_6 = new Path2D.Double();
//        path_2_to_6.moveTo(x + 70, y);
//        path_2_to_6.curveTo(x + 30, y + rectHeight - 5,
//                x + 30, y + rectHeight - 5,
//                x + 30, y + rectHeight - 5);
//        // Curve from Port 2 to Port 7
//        Path2D.Double path_2_to_7 = new Path2D.Double();
//        path_2_to_7.moveTo(x + 70, y);
//        path_2_to_7.curveTo(x + 65, y + 45,
//                x + 30, y + 65,
//                x , y + 70);
//
//        // Curve from Port 2 to Port 8
//        Path2D.Double path_2_to_8 = new Path2D.Double();
//        path_2_to_8.moveTo(x + 70, y);
//        path_2_to_8.curveTo(x + 65, y + 15,
//                x + 30, y + 25,
//                x , y + 30);
//
//
//        // Curve from Port 3 to Port 1
//        Path2D.Double path_3_to_1 = path_1_to_3;
//
//        // Curve from Port 3 to Port 2
//        Path2D.Double path_3_to_2 = path_2_to_3;
//
//        // Curve from Port 3 to 4
//        Path2D.Double path_3_to_4 = new Path2D.Double();
//        path_3_to_4.moveTo(x + rectWidth - 5, y + 30);
//        path_3_to_4.curveTo(x + 60, y + 50,
//                x +  60, y + 50,
//                x + rectWidth - 5, y + 70);
//
//        // Curve from Port 3 to 5
//        Path2D.Double path_3_to_5 = new Path2D.Double();
//        path_3_to_5.moveTo(x + rectWidth - 5, y + 30);
//        path_3_to_5.curveTo(x + 55, y + 55,
//                x +  55, y + 55,
//                x + 30, y + rectHeight - 5);
//
//        // Curve from Port 3 to 6
//        Path2D.Double path_3_to_6 = new Path2D.Double();
//        path_3_to_6.moveTo(x + rectWidth - 5, y + 30);
//        path_3_to_6.curveTo(x + 70, y + 70,
//                x + 75, y + 70,
//                x + 70, y + rectHeight - 5);
//
//        // Curve from Port 3 to 7
//        Path2D.Double path_3_to_7 = new Path2D.Double();
//        path_3_to_7.moveTo(x + rectWidth - 5, y + 30);
//        path_3_to_7.curveTo(x + 50, y + 50,
//                x +  50, y + 50,
//                x, y + 70);
//
//        // Curve from Port 3 to 8
//        Path2D.Double path_3_to_8 = new Path2D.Double();
//        path_3_to_8.moveTo(x + rectWidth - 5, y + 30);
//        path_3_to_8.lineTo(x, y + 30);
//
//        // Curve from Port 4 to Port 1
//        Path2D.Double path_4_to_1 = path_1_to_4;
//        // Curve from Port 4 to Port 2
//        Path2D.Double path_4_to_2 = path_2_to_4;
//        // Curve from Port 4 to Port 3
//        //Path2D.Double path_4_to_3 = path_3_to_4;
//        // Curve from Port 4 to Port 5
//        Path2D.Double path_4_to_5 = new Path2D.Double();
//        path_4_to_5.moveTo(x + rectWidth - 5, y + 70);
//        path_4_to_5.curveTo(x + 70, y + 80,
//                x +  75, y + 90,
//                x + 70, y + rectHeight - 5);
//
//        Path2D.Double path_4_to_6 = new Path2D.Double();
//        path_4_to_6.moveTo(x + rectWidth - 5, y + 70);
//        path_4_to_6.curveTo(x + 65, y + 75,
//                x +  55, y + 80,
//                x + 30, y + rectHeight - 5);
//        Path2D.Double path_4_to_7 = new Path2D.Double();
//        path_4_to_7.moveTo(x + rectWidth - 5, y + 70);
//        path_4_to_7.lineTo(x, y + 70);
//
//        Path2D.Double path_4_to_8 = new Path2D.Double();
//        path_4_to_8.moveTo(x + rectWidth - 5, y + 70);
//        path_4_to_8.lineTo(x, y + 30);
//
//
//        // Curve from Port 5 to Port 1
//        Path2D.Double path_5_to_1 = new Path2D.Double();
//        path_5_to_1.moveTo(x + 70, y + rectHeight - 5);
//        path_5_to_1.lineTo(x + 30, y);
//
//        // Curve from Port 5 to Port 2
//        Path2D.Double path_5_to_2 = new Path2D.Double();
//        path_5_to_2.moveTo(x + 70, y + rectHeight - 5);
//        path_5_to_2.curveTo(x + 70, y + rectHeight - 5,
//                x + 70, y + rectHeight - 5,
//                x + 70, y);
//
//        // Curve from Port 5 to Port 3
//        Path2D.Double path_5_to_3 = new Path2D.Double();
//        path_5_to_3.moveTo(x + 70, y + rectHeight - 5);
//        path_5_to_3.curveTo(x + 75, y + 55,
//                x + 70, y + 65,
//                x + rectWidth - 5, y + 30);
//
//
//        // Curve from Port 5 to Port 4
//        Path2D.Double path_5_to_4 = new Path2D.Double();
//        path_5_to_4.moveTo(x + 70, y + rectHeight - 5);
//        path_5_to_4.curveTo(x + rectWidth - 20, y + 70,
//                x + rectWidth - 10, y + 70 ,
//                x + rectWidth - 5, y + 70);
//
//        // Curve from Port 5 to Port 6
//        Path2D.Double path_5_to_6 = new Path2D.Double();
//        path_5_to_6.moveTo(x + 70, y + rectHeight - 5);
//        path_5_to_6.curveTo(x + 60, y + 50,
//                x + 40, y + 50,
//                x + 30, y + rectHeight - 5);
//
//        // Curve from Port 5 to Port 7
//        Path2D.Double path_5_to_7 = new Path2D.Double();
//        path_5_to_7.moveTo(x + 70, y + rectHeight - 5);
//        path_5_to_7.curveTo(x + 60, y + rectHeight - 20,
//                x + 35, y + rectHeight - 30,
//                x, y + 70);
//
//        // Curve from Port 5 to Port 8
//        Path2D.Double path_5_to_8 = new Path2D.Double();
//        path_5_to_8.moveTo(x + 70, y + rectHeight - 5);
//        path_5_to_8.curveTo(x + 60, y + 50,
//                x + 25, y + 40,
//                x, y + 30);
//        // Curve from Port 7 to Port 1
//        Path2D.Double path_6_to_1 = path_1_to_6;
//        // Curve from Port 7 to Port 2
//        Path2D.Double path_6_to_2 = path_2_to_6;
//        // Curve from Port 7 to Port 3
//        Path2D.Double path_6_to_3 = path_3_to_6;
//        // Curve from Port 7 to Port 4
//        Path2D.Double path_6_to_4 = path_4_to_6;
//        // Curve from Port 7 to Port 5
//        Path2D.Double path_6_to_5 = path_5_to_6;
//
//        Path2D.Double path_6_to_7 = new Path2D.Double();
//        path_6_to_7.moveTo(x + 30, y  + rectHeight - 5);
//        path_6_to_7.curveTo(x + 30, y + 90,
//                x +  35, y + 80,
//                x, y + 70);
//        Path2D.Double path_6_to_8 = new Path2D.Double();
//        path_6_to_8.moveTo(x + 30, y  + rectHeight - 5);
//        path_6_to_8.curveTo(x + 30, y + 85,
//                x +  35, y + 70,
//                x, y + 30);
//
//        // Curve from Port 7 to Port 1
//        Path2D.Double path_7_to_1 = path_1_to_7;
//        // Curve from Port 7 to Port 2
//        Path2D.Double path_7_to_2 = path_2_to_7;
//        // Curve from Port 7 to Port 3
//        Path2D.Double path_7_to_3 = path_3_to_7;
//        // Curve from Port 7 to Port 4
//        Path2D.Double path_7_to_4 = path_4_to_7;
//        // Curve from Port 7 to Port 5
//        Path2D.Double path_7_to_5 = path_5_to_7;
//        // Curve from Port 7 to Port 6
//        Path2D.Double path_7_to_6 = path_6_to_7;
//         //Curve from Port 7 to Port 8
//        Path2D.Double path_7_to_8 = new Path2D.Double();
//        path_7_to_8.moveTo(x, y + 70);
//        path_7_to_8.curveTo(x + 35, y + 50,
//                x + 50, y + 55,
//                x , y + 30);
//        //        // Curve from Port 8 to Port 1
//        Path2D.Double path_8_to_1 = path_1_to_8;
//        // Curve from Port 8 to Port 2
//        Path2D.Double path_8_to_2 = path_2_to_8;
//        // Curve from Port 8 to Port 3
//        Path2D.Double path_8_to_3 = path_3_to_8;
//        // Curve from Port 8 to Port 4
//        Path2D.Double path_8_to_4 = path_4_to_8;
//        // Curve from Port 8 to Port 5
//        Path2D.Double path_8_to_5 = path_5_to_8;
//        // Curve from Port 8 to Port 6
//        Path2D.Double path_8_to_6 = path_6_to_8;
//        // Curve from Port 8 to Port 7
//        Path2D.Double path_8_to_7 = path_7_to_8;
//
//
//
//
//
//
//        g2.draw(path_6_to_8);
//
//
//
////
////
////
////
////
////
////
////        g2.draw(path_5_to_1);
////        g2.draw(path_5_to_2);
////        g2.draw(path_5_to_3);
////        g2.draw(path_5_to_4);
////        g2.draw(path_5_to_6);
////        g2.draw(path_5_to_7);
////        g2.draw(path_5_to_8);
//
//        // Port 1 and 2
//        g2.fillRect(x + 70, y, 5, 5);
//        g2.fillRect(x + 30, y, 5, 5);
//
//        // Port 5 and 6
//        g2.fillRect(x + 70, y + rectHeight - 5, 5, 5);
//        g2.fillRect(x + 30, y + rectHeight - 5, 5, 5);
//
//        // Port 7 and 8
//        g2.fillRect(x, y + 70, 5, 5);
//        g2.fillRect(x, y + 30, 5, 5);
//
//        // Port 3 and 4
//        g2.fillRect(x + rectWidth - 5, y + 70, 5, 5);
//        g2.fillRect(x + rectWidth - 5, y + 30, 5, 5);
//
//      }
//    }
//  }
//  public static void main(String[] args) {
//    // http://docs.oracle.com/javase/tutorial/uiswing/concurrency/initial.html
//    Referee ref = new Referee();
//    SwingUtilities.invokeLater(new Runnable() {
//      public void run() {
//        JFrame frame = new JFrame("TsuroBoard");
//        TilePanel tilePanel = new TilePanel(ref);
//        frame.add(tilePanel);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.pack();
//        frame.setVisible(true);
//      }
//    });
//  }
//}
//>>>>>>> 649ad3b1cceb7c346ebbc08c2cd7a81511a92247
