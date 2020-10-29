package com.clownswhocode.tsuro.View;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

class painting extends javax.swing.JFrame {

  int myx = 0;
  int myy = 0;
  int mypx = 0;
  int mypy = 0;

  public painting() {
    initComponents();
  }

  private void initComponents() {
    //buttonPanel = new javax.swing.JPanel();//not needed
    mySaveButton = new javax.swing.JButton();
    //myDrawPanel = new javax.swing.JPanel();//see below override
    myDrawPanel = new javax.swing.JPanel() {
      public void paintComponent(Graphics g) {
        super.paintComponent(g);//clears old lines etc
        g.drawLine(mypx, mypy, myx, myy);//<---moved to here
      }
    };
    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    mySaveButton.setText("Save");
    mySaveButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        mySaveButtonActionPerformed(evt);
      }
    });
    myDrawPanel.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mousePressed(java.awt.event.MouseEvent evt) {
        myDrawPanelMousePressed(evt);
      }

      public void mouseReleased(java.awt.event.MouseEvent evt) {
        myDrawPanelMouseReleased(evt);
      }
    });
    getContentPane().add(
        myDrawPanel);//<---these two lines instead of GroupLayout, which has nothing to do with problem
    getContentPane().add(mySaveButton, BorderLayout.SOUTH);
    setSize(600, 400);
    setLocationRelativeTo(null);
  }

  private void myDrawPanelMousePressed(java.awt.event.MouseEvent evt) {
    mypx = evt.getX();
    mypy = evt.getY();
  }

  private void myDrawPanelMouseReleased(java.awt.event.MouseEvent evt) {
    myx = evt.getX();
    myy = evt.getY();
    //Graphics g = myDrawPanel.getGraphics();//never,never,never
    //g.drawLine(mypx, mypy, myx, myy);//transferred to paintComponent
    myDrawPanel
        .repaint();//<---------------------------------------------this triggers a redraw, using new co-ords
  }

  private void mySaveButtonActionPerformed(java.awt.event.ActionEvent evt) {
    int w = myDrawPanel.getWidth(), h = myDrawPanel.getHeight();
    BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
    Graphics g = image.getGraphics();
    myDrawPanel.print(g);
    g.dispose();
    try {
      ImageIO.write(image, "jpeg", new File("myImageFile.jpeg"));
    } catch (IOException e) {
    }
  }

  public static void main(String args[]) {
    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        new painting().setVisible(true);
      }
    });
  }

  private javax.swing.JPanel buttonPanel;
  private javax.swing.JPanel myDrawPanel;
  private javax.swing.JButton mySaveButton;
}