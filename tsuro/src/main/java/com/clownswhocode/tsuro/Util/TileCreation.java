package com.clownswhocode.tsuro.Util;

import com.clownswhocode.tsuro.model.Port;

import java.util.ArrayList;

import com.clownswhocode.tsuro.model.Tile;

class TileConnections {

  private ArrayList<ArrayList<ArrayList<Port>>> connections;

  // Hard coded list of all 35 Tiles
  public TileConnections() {
    ArrayList<ArrayList<ArrayList<Port>>> temp = new ArrayList<ArrayList<ArrayList<Port>>>();
    ArrayList<ArrayList<Port>> conn_1 = new ArrayList<ArrayList<Port>>();
    //Pair 1
    ArrayList<Port> conn_1_pair_1 = new ArrayList<Port>();
    conn_1_pair_1.add(new Port(1));
    conn_1_pair_1.add(new Port(5));
    conn_1.add(conn_1_pair_1);
    //Pair 2
    ArrayList<Port> conn_1_pair_2 = new ArrayList<Port>();
    conn_1_pair_2.add(new Port(2));
    conn_1_pair_2.add(new Port(6));
    conn_1.add(conn_1_pair_2);
    //Pair 3
    ArrayList<Port> conn_1_pair_3 = new ArrayList<Port>();
    conn_1_pair_3.add(new Port(3));
    conn_1_pair_3.add(new Port(8));
    conn_1.add(conn_1_pair_3);
    //Pair 4
    ArrayList<Port> conn_1_pair_4 = new ArrayList<Port>();
    conn_1_pair_4.add(new Port(4));
    conn_1_pair_4.add(new Port(7));
    conn_1.add(conn_1_pair_4);

    temp.add(conn_1);

    ArrayList<ArrayList<Port>> conn_2 = new ArrayList<ArrayList<Port>>();
    ArrayList<Port> conn_2_pair_1 = new ArrayList<Port>();
    conn_2_pair_1.add(new Port(1));
    conn_2_pair_1.add(new Port(5));
    conn_2.add(conn_2_pair_1);

    ArrayList<Port> conn_2_pair_2 = new ArrayList<Port>();
    conn_2_pair_2.add(new Port(2));
    conn_2_pair_2.add(new Port(6));
    conn_2.add(conn_2_pair_2);

    ArrayList<Port> conn_2_pair_3 = new ArrayList<Port>();
    conn_2_pair_3.add(new Port(3));
    conn_2_pair_3.add(new Port(7));
    conn_2.add(conn_2_pair_3);

    ArrayList<Port> conn_2_pair_4 = new ArrayList<Port>();
    conn_2_pair_4.add(new Port(4));
    conn_2_pair_4.add(new Port(8));
    conn_2.add(conn_2_pair_4);

    temp.add(conn_2);

    ArrayList<ArrayList<Port>> conn_3 = new ArrayList<ArrayList<Port>>();
    ArrayList<Port> conn_3_pair_1 = new ArrayList<Port>();
    conn_3_pair_1.add(new Port(1));
    conn_3_pair_1.add(new Port(6));
    conn_3.add(conn_3_pair_1);

    ArrayList<Port> conn_3_pair_2 = new ArrayList<Port>();
    conn_3_pair_2.add(new Port(2));
    conn_3_pair_2.add(new Port(5));
    conn_3.add(conn_3_pair_2);

    ArrayList<Port> conn_3_pair_3 = new ArrayList<Port>();
    conn_3_pair_3.add(new Port(3));
    conn_3_pair_3.add(new Port(8));
    conn_3.add(conn_3_pair_3);

    ArrayList<Port> conn_3_pair_4 = new ArrayList<Port>();
    conn_3_pair_4.add(new Port(4));
    conn_3_pair_4.add(new Port(7));
    conn_3.add(conn_3_pair_4);

    temp.add(conn_3);

    ArrayList<ArrayList<Port>> conn_4 = new ArrayList<ArrayList<Port>>();
    ArrayList<Port> conn_4_pair_1 = new ArrayList<Port>();
    conn_4_pair_1.add(new Port(1));
    conn_4_pair_1.add(new Port(5));
    conn_4.add(conn_4_pair_1);

    ArrayList<Port> conn_4_pair_2 = new ArrayList<Port>();
    conn_4_pair_2.add(new Port(2));
    conn_4_pair_2.add(new Port(4));
    conn_4.add(conn_1_pair_2);

    ArrayList<Port> conn_4_pair_3 = new ArrayList<Port>();
    conn_4_pair_3.add(new Port(3));
    conn_4_pair_3.add(new Port(7));
    conn_4.add(conn_1_pair_3);

    ArrayList<Port> conn_4_pair_4 = new ArrayList<Port>();
    conn_4_pair_4.add(new Port(6));
    conn_4_pair_4.add(new Port(8));
    conn_4.add(conn_1_pair_4);

    temp.add(conn_4);

    ArrayList<ArrayList<Port>> conn_5 = new ArrayList<ArrayList<Port>>();
    ArrayList<Port> conn_5_pair_1 = new ArrayList<Port>();
    conn_5_pair_1.add(new Port(1));
    conn_5_pair_1.add(new Port(8));
    conn_5.add(conn_5_pair_1);

    ArrayList<Port> conn_5_pair_2 = new ArrayList<Port>();
    conn_5_pair_2.add(new Port(2));
    conn_5_pair_2.add(new Port(3));
    conn_5.add(conn_5_pair_2);

    ArrayList<Port> conn_5_pair_3 = new ArrayList<Port>();
    conn_5_pair_3.add(new Port(4));
    conn_5_pair_3.add(new Port(5));
    conn_5.add(conn_5_pair_3);

    ArrayList<Port> conn_5_pair_4 = new ArrayList<Port>();
    conn_5_pair_4.add(new Port(6));
    conn_5_pair_4.add(new Port(7));
    conn_5.add(conn_5_pair_4);

    temp.add(conn_5);

    ArrayList<ArrayList<Port>> conn_6 = new ArrayList<ArrayList<Port>>();
    ArrayList<Port> conn_6_pair_1 = new ArrayList<Port>();
    conn_6_pair_1.add(new Port(1));
    conn_6_pair_1.add(new Port(5));
    conn_6.add(conn_6_pair_1);

    ArrayList<Port> conn_6_pair_2 = new ArrayList<Port>();
    conn_6_pair_2.add(new Port(2));
    conn_6_pair_2.add(new Port(3));
    conn_6.add(conn_6_pair_2);

    ArrayList<Port> conn_6_pair_3 = new ArrayList<Port>();
    conn_6_pair_3.add(new Port(4));
    conn_6_pair_3.add(new Port(8));
    conn_6.add(conn_6_pair_3);

    ArrayList<Port> conn_6_pair_4 = new ArrayList<Port>();
    conn_6_pair_4.add(new Port(6));
    conn_6_pair_4.add(new Port(7));
    conn_6.add(conn_6_pair_4);

    temp.add(conn_6);

    ArrayList<ArrayList<Port>> conn_7 = new ArrayList<ArrayList<Port>>();
    ArrayList<Port> conn_7_pair_1 = new ArrayList<Port>();
    conn_7_pair_1.add(new Port(1));
    conn_7_pair_1.add(new Port(5));
    conn_7.add(conn_7_pair_1);

    ArrayList<Port> conn_7_pair_2 = new ArrayList<Port>();
    conn_7_pair_2.add(new Port(2));
    conn_7_pair_2.add(new Port(3));
    conn_7.add(conn_7_pair_2);

    ArrayList<Port> conn_7_pair_3 = new ArrayList<Port>();
    conn_7_pair_3.add(new Port(4));
    conn_7_pair_3.add(new Port(7));
    conn_7.add(conn_7_pair_3);

    ArrayList<Port> conn_7_pair_4 = new ArrayList<Port>();
    conn_7_pair_4.add(new Port(6));
    conn_7_pair_4.add(new Port(8));
    conn_7.add(conn_7_pair_4);

    temp.add(conn_7);

    ArrayList<ArrayList<Port>> conn_8 = new ArrayList<ArrayList<Port>>();
    ArrayList<Port> conn_8_pair_1 = new ArrayList<Port>();
    conn_8_pair_1.add(new Port(1));
    conn_8_pair_1.add(new Port(4));
    conn_8.add(conn_8_pair_1);

    ArrayList<Port> conn_8_pair_2 = new ArrayList<Port>();
    conn_8_pair_2.add(new Port(2));
    conn_8_pair_2.add(new Port(7));
    conn_8.add(conn_8_pair_2);

    ArrayList<Port> conn_8_pair_3 = new ArrayList<Port>();
    conn_8_pair_3.add(new Port(3));
    conn_8_pair_3.add(new Port(6));
    conn_8.add(conn_8_pair_3);

    ArrayList<Port> conn_8_pair_4 = new ArrayList<Port>();
    conn_8_pair_4.add(new Port(5));
    conn_8_pair_4.add(new Port(8));
    conn_8.add(conn_8_pair_4);

    temp.add(conn_8);

    ArrayList<ArrayList<Port>> conn_9 = new ArrayList<ArrayList<Port>>();
    ArrayList<Port> conn_9_pair_1 = new ArrayList<Port>();
    conn_9_pair_1.add(new Port(1));
    conn_9_pair_1.add(new Port(4));
    conn_9.add(conn_9_pair_1);

    ArrayList<Port> conn_9_pair_2 = new ArrayList<Port>();
    conn_9_pair_2.add(new Port(2));
    conn_9_pair_2.add(new Port(6));
    conn_9.add(conn_9_pair_2);

    ArrayList<Port> conn_9_pair_3 = new ArrayList<Port>();
    conn_9_pair_3.add(new Port(3));
    conn_9_pair_3.add(new Port(7));
    conn_9.add(conn_9_pair_3);

    ArrayList<Port> conn_9_pair_4 = new ArrayList<Port>();
    conn_9_pair_4.add(new Port(5));
    conn_9_pair_4.add(new Port(8));
    conn_9.add(conn_9_pair_4);

    temp.add(conn_9);

    ArrayList<ArrayList<Port>> conn_10 = new ArrayList<ArrayList<Port>>();
    ArrayList<Port> conn_10_pair_1 = new ArrayList<Port>();
    conn_10_pair_1.add(new Port(1));
    conn_10_pair_1.add(new Port(4));
    conn_10.add(conn_10_pair_1);

    ArrayList<Port> conn_10_pair_2 = new ArrayList<Port>();
    conn_10_pair_2.add(new Port(2));
    conn_10_pair_2.add(new Port(5));
    conn_10.add(conn_10_pair_2);

    ArrayList<Port> conn_10_pair_3 = new ArrayList<Port>();
    conn_10_pair_3.add(new Port(3));
    conn_10_pair_3.add(new Port(8));
    conn_10.add(conn_10_pair_3);

    ArrayList<Port> conn_10_pair_4 = new ArrayList<Port>();
    conn_10_pair_4.add(new Port(6));
    conn_10_pair_4.add(new Port(7));
    conn_10.add(conn_10_pair_4);

    temp.add(conn_10);

    ArrayList<ArrayList<Port>> conn_11 = new ArrayList<ArrayList<Port>>();
    ArrayList<Port> conn_11_pair_1 = new ArrayList<Port>();
    conn_11_pair_1.add(new Port(1));
    conn_11_pair_1.add(new Port(4));
    conn_11.add(conn_11_pair_1);

    ArrayList<Port> conn_11_pair_2 = new ArrayList<Port>();
    conn_11_pair_2.add(new Port(2));
    conn_11_pair_2.add(new Port(5));
    conn_11.add(conn_11_pair_2);

    ArrayList<Port> conn_11_pair_3 = new ArrayList<Port>();
    conn_11_pair_3.add(new Port(3));
    conn_11_pair_3.add(new Port(7));
    conn_11.add(conn_11_pair_3);

    ArrayList<Port> conn_11_pair_4 = new ArrayList<Port>();
    conn_11_pair_4.add(new Port(6));
    conn_11_pair_4.add(new Port(8));
    conn_11.add(conn_11_pair_4);

    temp.add(conn_11);

    ArrayList<ArrayList<Port>> conn_12 = new ArrayList<ArrayList<Port>>();
    ArrayList<Port> conn_12_pair_1 = new ArrayList<Port>();
    conn_12_pair_1.add(new Port(1));
    conn_12_pair_1.add(new Port(4));
    conn_12.add(conn_12_pair_1);

    ArrayList<Port> conn_12_pair_2 = new ArrayList<Port>();
    conn_12_pair_2.add(new Port(2));
    conn_12_pair_2.add(new Port(3));
    conn_12.add(conn_12_pair_2);

    ArrayList<Port> conn_12_pair_3 = new ArrayList<Port>();
    conn_12_pair_3.add(new Port(5));
    conn_12_pair_3.add(new Port(8));
    conn_12.add(conn_12_pair_3);

    ArrayList<Port> conn_12_pair_4 = new ArrayList<Port>();
    conn_12_pair_4.add(new Port(6));
    conn_12_pair_4.add(new Port(7));
    conn_12.add(conn_12_pair_4);

    temp.add(conn_12);

    ArrayList<ArrayList<Port>> conn_13 = new ArrayList<ArrayList<Port>>();
    ArrayList<Port> conn_13_pair_1 = new ArrayList<Port>();
    conn_13_pair_1.add(new Port(1));
    conn_13_pair_1.add(new Port(3));
    conn_13.add(conn_13_pair_1);

    ArrayList<Port> conn_13_pair_2 = new ArrayList<Port>();
    conn_13_pair_2.add(new Port(2));
    conn_13_pair_2.add(new Port(8));
    conn_13.add(conn_13_pair_2);

    ArrayList<Port> conn_13_pair_3 = new ArrayList<Port>();
    conn_13_pair_3.add(new Port(4));
    conn_13_pair_3.add(new Port(6));
    conn_13.add(conn_13_pair_3);

    ArrayList<Port> conn_13_pair_4 = new ArrayList<Port>();
    conn_13_pair_4.add(new Port(5));
    conn_13_pair_4.add(new Port(7));
    conn_13.add(conn_13_pair_4);

    temp.add(conn_13);

    ArrayList<ArrayList<Port>> conn_14 = new ArrayList<ArrayList<Port>>();
    ArrayList<Port> conn_14_pair_1 = new ArrayList<Port>();
    conn_14_pair_1.add(new Port(1));
    conn_14_pair_1.add(new Port(3));
    conn_14.add(conn_14_pair_1);

    ArrayList<Port> conn_14_pair_2 = new ArrayList<Port>();
    conn_14_pair_2.add(new Port(2));
    conn_14_pair_2.add(new Port(8));
    conn_14.add(conn_14_pair_2);

    ArrayList<Port> conn_14_pair_3 = new ArrayList<Port>();
    conn_14_pair_3.add(new Port(4));
    conn_14_pair_3.add(new Port(5));
    conn_14.add(conn_14_pair_3);

    ArrayList<Port> conn_14_pair_4 = new ArrayList<Port>();
    conn_14_pair_4.add(new Port(6));
    conn_14_pair_4.add(new Port(7));
    conn_14.add(conn_14_pair_4);

    temp.add(conn_14);

    ArrayList<ArrayList<Port>> conn_15 = new ArrayList<ArrayList<Port>>();
    ArrayList<Port> conn_15_pair_1 = new ArrayList<Port>();
    conn_15_pair_1.add(new Port(1));
    conn_15_pair_1.add(new Port(3));
    conn_15.add(conn_15_pair_1);

    ArrayList<Port> conn_15_pair_2 = new ArrayList<Port>();
    conn_15_pair_2.add(new Port(2));
    conn_15_pair_2.add(new Port(7));
    conn_15.add(conn_15_pair_2);

    ArrayList<Port> conn_15_pair_3 = new ArrayList<Port>();
    conn_15_pair_3.add(new Port(4));
    conn_15_pair_3.add(new Port(6));
    conn_15.add(conn_15_pair_3);

    ArrayList<Port> conn_15_pair_4 = new ArrayList<Port>();
    conn_15_pair_4.add(new Port(5));
    conn_15_pair_4.add(new Port(8));
    conn_15.add(conn_15_pair_4);

    temp.add(conn_15);

    ArrayList<ArrayList<Port>> conn_16 = new ArrayList<ArrayList<Port>>();
    ArrayList<Port> conn_16_pair_1 = new ArrayList<Port>();
    conn_16_pair_1.add(new Port(1));
    conn_16_pair_1.add(new Port(3));
    conn_16.add(conn_16_pair_1);

    ArrayList<Port> conn_16_pair_2 = new ArrayList<Port>();
    conn_16_pair_2.add(new Port(2));
    conn_16_pair_2.add(new Port(7));
    conn_16.add(conn_16_pair_2);

    ArrayList<Port> conn_16_pair_3 = new ArrayList<Port>();
    conn_16_pair_3.add(new Port(4));
    conn_16_pair_3.add(new Port(5));
    conn_16.add(conn_16_pair_3);

    ArrayList<Port> conn_16_pair_4 = new ArrayList<Port>();
    conn_16_pair_4.add(new Port(6));
    conn_16_pair_4.add(new Port(8));
    conn_16.add(conn_16_pair_4);

    temp.add(conn_16);

    ArrayList<ArrayList<Port>> conn_17 = new ArrayList<ArrayList<Port>>();
    ArrayList<Port> conn_17_pair_1 = new ArrayList<Port>();
    conn_17_pair_1.add(new Port(1));
    conn_17_pair_1.add(new Port(3));
    conn_17.add(conn_17_pair_1);

    ArrayList<Port> conn_17_pair_2 = new ArrayList<Port>();
    conn_17_pair_2.add(new Port(2));
    conn_17_pair_2.add(new Port(6));
    conn_17.add(conn_17_pair_2);

    ArrayList<Port> conn_17_pair_3 = new ArrayList<Port>();
    conn_17_pair_3.add(new Port(4));
    conn_17_pair_3.add(new Port(8));
    conn_17.add(conn_17_pair_3);

    ArrayList<Port> conn_17_pair_4 = new ArrayList<Port>();
    conn_17_pair_4.add(new Port(5));
    conn_17_pair_4.add(new Port(7));
    conn_17.add(conn_17_pair_4);

    temp.add(conn_17);

    ArrayList<ArrayList<Port>> conn_18 = new ArrayList<ArrayList<Port>>();
    ArrayList<Port> conn_18_pair_1 = new ArrayList<Port>();
    conn_18_pair_1.add(new Port(1));
    conn_18_pair_1.add(new Port(3));
    conn_18.add(conn_18_pair_1);

    ArrayList<Port> conn_18_pair_2 = new ArrayList<Port>();
    conn_18_pair_2.add(new Port(2));
    conn_18_pair_2.add(new Port(6));
    conn_18.add(conn_18_pair_2);

    ArrayList<Port> conn_18_pair_3 = new ArrayList<Port>();
    conn_18_pair_3.add(new Port(4));
    conn_18_pair_3.add(new Port(7));
    conn_18.add(conn_18_pair_3);

    ArrayList<Port> conn_18_pair_4 = new ArrayList<Port>();
    conn_18_pair_4.add(new Port(5));
    conn_18_pair_4.add(new Port(8));
    conn_18.add(conn_18_pair_4);

    temp.add(conn_18);

    ArrayList<ArrayList<Port>> conn_19 = new ArrayList<ArrayList<Port>>();
    ArrayList<Port> conn_19_pair_1 = new ArrayList<Port>();
    conn_19_pair_1.add(new Port(1));
    conn_19_pair_1.add(new Port(3));
    conn_19.add(conn_19_pair_1);

    ArrayList<Port> conn_19_pair_2 = new ArrayList<Port>();
    conn_19_pair_2.add(new Port(2));
    conn_19_pair_2.add(new Port(5));
    conn_19.add(conn_19_pair_2);

    ArrayList<Port> conn_19_pair_3 = new ArrayList<Port>();
    conn_19_pair_3.add(new Port(4));
    conn_19_pair_3.add(new Port(8));
    conn_19.add(conn_19_pair_3);

    ArrayList<Port> conn_19_pair_4 = new ArrayList<Port>();
    conn_19_pair_4.add(new Port(6));
    conn_19_pair_4.add(new Port(7));
    conn_19.add(conn_19_pair_4);

    temp.add(conn_19);

    ArrayList<ArrayList<Port>> conn_20 = new ArrayList<ArrayList<Port>>();
    ArrayList<Port> conn_20_pair_1 = new ArrayList<Port>();
    conn_20_pair_1.add(new Port(1));
    conn_20_pair_1.add(new Port(3));
    conn_20.add(conn_20_pair_1);

    ArrayList<Port> conn_20_pair_2 = new ArrayList<Port>();
    conn_20_pair_2.add(new Port(2));
    conn_20_pair_2.add(new Port(5));
    conn_20.add(conn_20_pair_2);

    ArrayList<Port> conn_20_pair_3 = new ArrayList<Port>();
    conn_20_pair_3.add(new Port(4));
    conn_20_pair_3.add(new Port(7));
    conn_20.add(conn_20_pair_3);

    ArrayList<Port> conn_20_pair_4 = new ArrayList<Port>();
    conn_20_pair_4.add(new Port(6));
    conn_20_pair_4.add(new Port(8));
    conn_20.add(conn_20_pair_4);

    temp.add(conn_20);

    ArrayList<ArrayList<Port>> conn_21 = new ArrayList<ArrayList<Port>>();
    ArrayList<Port> conn_21_pair_1 = new ArrayList<Port>();
    conn_21_pair_1.add(new Port(1));
    conn_21_pair_1.add(new Port(3));
    conn_21.add(conn_21_pair_1);

    ArrayList<Port> conn_21_pair_2 = new ArrayList<Port>();
    conn_21_pair_2.add(new Port(2));
    conn_21_pair_2.add(new Port(4));
    conn_21.add(conn_21_pair_2);

    ArrayList<Port> conn_21_pair_3 = new ArrayList<Port>();
    conn_21_pair_3.add(new Port(5));
    conn_21_pair_3.add(new Port(8));
    conn_21.add(conn_21_pair_3);

    ArrayList<Port> conn_21_pair_4 = new ArrayList<Port>();
    conn_21_pair_4.add(new Port(6));
    conn_21_pair_4.add(new Port(7));
    conn_21.add(conn_21_pair_4);

    temp.add(conn_21);

    ArrayList<ArrayList<Port>> conn_22 = new ArrayList<ArrayList<Port>>();
    ArrayList<Port> conn_22_pair_1 = new ArrayList<Port>();
    conn_22_pair_1.add(new Port(1));
    conn_22_pair_1.add(new Port(3));
    conn_22.add(conn_22_pair_1);

    ArrayList<Port> conn_22_pair_2 = new ArrayList<Port>();
    conn_22_pair_2.add(new Port(2));
    conn_22_pair_2.add(new Port(4));
    conn_22.add(conn_22_pair_2);

    ArrayList<Port> conn_22_pair_3 = new ArrayList<Port>();
    conn_22_pair_3.add(new Port(5));
    conn_22_pair_3.add(new Port(7));
    conn_22.add(conn_22_pair_3);

    ArrayList<Port> conn_22_pair_4 = new ArrayList<Port>();
    conn_22_pair_4.add(new Port(6));
    conn_22_pair_4.add(new Port(8));
    conn_22.add(conn_22_pair_4);

    temp.add(conn_22);

    ArrayList<ArrayList<Port>> conn_23 = new ArrayList<ArrayList<Port>>();
    ArrayList<Port> conn_23_pair_1 = new ArrayList<Port>();
    conn_23_pair_1.add(new Port(1));
    conn_23_pair_1.add(new Port(2));
    conn_23.add(conn_23_pair_1);

    ArrayList<Port> conn_23_pair_2 = new ArrayList<Port>();
    conn_23_pair_2.add(new Port(3));
    conn_23_pair_2.add(new Port(8));
    conn_23.add(conn_23_pair_2);

    ArrayList<Port> conn_23_pair_3 = new ArrayList<Port>();
    conn_23_pair_3.add(new Port(4));
    conn_23_pair_3.add(new Port(7));
    conn_23.add(conn_23_pair_3);

    ArrayList<Port> conn_23_pair_4 = new ArrayList<Port>();
    conn_23_pair_4.add(new Port(5));
    conn_23_pair_4.add(new Port(6));
    conn_23.add(conn_23_pair_4);

    temp.add(conn_23);

    ArrayList<ArrayList<Port>> conn_24 = new ArrayList<ArrayList<Port>>();
    ArrayList<Port> conn_24_pair_1 = new ArrayList<Port>();
    conn_24_pair_1.add(new Port(1));
    conn_24_pair_1.add(new Port(2));
    conn_24.add(conn_24_pair_1);

    ArrayList<Port> conn_24_pair_2 = new ArrayList<Port>();
    conn_24_pair_2.add(new Port(3));
    conn_24_pair_2.add(new Port(8));
    conn_24.add(conn_24_pair_2);

    ArrayList<Port> conn_24_pair_3 = new ArrayList<Port>();
    conn_24_pair_3.add(new Port(4));
    conn_24_pair_3.add(new Port(6));
    conn_24.add(conn_24_pair_3);

    ArrayList<Port> conn_24_pair_4 = new ArrayList<Port>();
    conn_24_pair_4.add(new Port(5));
    conn_24_pair_4.add(new Port(7));
    conn_24.add(conn_24_pair_4);

    temp.add(conn_24);

    ArrayList<ArrayList<Port>> conn_25 = new ArrayList<ArrayList<Port>>();
    ArrayList<Port> conn_25_pair_1 = new ArrayList<Port>();
    conn_25_pair_1.add(new Port(1));
    conn_25_pair_1.add(new Port(2));
    conn_25.add(conn_25_pair_1);

    ArrayList<Port> conn_25_pair_2 = new ArrayList<Port>();
    conn_25_pair_2.add(new Port(3));
    conn_25_pair_2.add(new Port(8));
    conn_25.add(conn_25_pair_2);

    ArrayList<Port> conn_25_pair_3 = new ArrayList<Port>();
    conn_25_pair_3.add(new Port(4));
    conn_25_pair_3.add(new Port(5));
    conn_25.add(conn_25_pair_3);

    ArrayList<Port> conn_25_pair_4 = new ArrayList<Port>();
    conn_25_pair_4.add(new Port(6));
    conn_25_pair_4.add(new Port(7));
    conn_25.add(conn_25_pair_4);

    temp.add(conn_25);

    ArrayList<ArrayList<Port>> conn_26 = new ArrayList<ArrayList<Port>>();
    ArrayList<Port> conn_26_pair_1 = new ArrayList<Port>();
    conn_26_pair_1.add(new Port(1));
    conn_26_pair_1.add(new Port(2));
    conn_26.add(conn_26_pair_1);

    ArrayList<Port> conn_26_pair_2 = new ArrayList<Port>();
    conn_26_pair_2.add(new Port(3));
    conn_26_pair_2.add(new Port(7));
    conn_26.add(conn_26_pair_2);

    ArrayList<Port> conn_26_pair_3 = new ArrayList<Port>();
    conn_26_pair_3.add(new Port(4));
    conn_26_pair_3.add(new Port(8));
    conn_26.add(conn_26_pair_3);

    ArrayList<Port> conn_26_pair_4 = new ArrayList<Port>();
    conn_26_pair_4.add(new Port(5));
    conn_26_pair_4.add(new Port(6));
    conn_26.add(conn_26_pair_4);

    temp.add(conn_26);

    ArrayList<ArrayList<Port>> conn_27 = new ArrayList<ArrayList<Port>>();
    ArrayList<Port> conn_27_pair_1 = new ArrayList<Port>();
    conn_27_pair_1.add(new Port(1));
    conn_27_pair_1.add(new Port(2));
    conn_27.add(conn_27_pair_1);

    ArrayList<Port> conn_27_pair_2 = new ArrayList<Port>();
    conn_27_pair_2.add(new Port(3));
    conn_27_pair_2.add(new Port(7));
    conn_27.add(conn_27_pair_2);

    ArrayList<Port> conn_27_pair_3 = new ArrayList<Port>();
    conn_27_pair_3.add(new Port(4));
    conn_27_pair_3.add(new Port(6));
    conn_27.add(conn_27_pair_3);

    ArrayList<Port> conn_27_pair_4 = new ArrayList<Port>();
    conn_27_pair_4.add(new Port(5));
    conn_27_pair_4.add(new Port(8));
    conn_27.add(conn_27_pair_4);

    temp.add(conn_27);

    ArrayList<ArrayList<Port>> conn_28 = new ArrayList<ArrayList<Port>>();
    ArrayList<Port> conn_28_pair_1 = new ArrayList<Port>();
    conn_28_pair_1.add(new Port(1));
    conn_28_pair_1.add(new Port(2));
    conn_28.add(conn_28_pair_1);

    ArrayList<Port> conn_28_pair_2 = new ArrayList<Port>();
    conn_28_pair_2.add(new Port(3));
    conn_28_pair_2.add(new Port(7));
    conn_28.add(conn_28_pair_2);

    ArrayList<Port> conn_28_pair_3 = new ArrayList<Port>();
    conn_28_pair_3.add(new Port(4));
    conn_28_pair_3.add(new Port(5));
    conn_28.add(conn_28_pair_3);

    ArrayList<Port> conn_28_pair_4 = new ArrayList<Port>();
    conn_28_pair_4.add(new Port(6));
    conn_28_pair_4.add(new Port(8));
    conn_28.add(conn_28_pair_4);

    temp.add(conn_28);

    ArrayList<ArrayList<Port>> conn_29 = new ArrayList<ArrayList<Port>>();
    ArrayList<Port> conn_29_pair_1 = new ArrayList<Port>();
    conn_29_pair_1.add(new Port(1));
    conn_29_pair_1.add(new Port(2));
    conn_29.add(conn_29_pair_1);

    ArrayList<Port> conn_29_pair_2 = new ArrayList<Port>();
    conn_29_pair_2.add(new Port(3));
    conn_29_pair_2.add(new Port(6));
    conn_29.add(conn_29_pair_2);

    ArrayList<Port> conn_29_pair_3 = new ArrayList<Port>();
    conn_29_pair_3.add(new Port(4));
    conn_29_pair_3.add(new Port(8));
    conn_29.add(conn_29_pair_3);

    ArrayList<Port> conn_29_pair_4 = new ArrayList<Port>();
    conn_29_pair_4.add(new Port(5));
    conn_29_pair_4.add(new Port(7));
    conn_29.add(conn_29_pair_4);

    temp.add(conn_29);

    ArrayList<ArrayList<Port>> conn_30 = new ArrayList<ArrayList<Port>>();
    ArrayList<Port> conn_30_pair_1 = new ArrayList<Port>();
    conn_30_pair_1.add(new Port(1));
    conn_30_pair_1.add(new Port(2));
    conn_30.add(conn_30_pair_1);

    ArrayList<Port> conn_30_pair_2 = new ArrayList<Port>();
    conn_30_pair_2.add(new Port(3));
    conn_30_pair_2.add(new Port(6));
    conn_30.add(conn_30_pair_2);

    ArrayList<Port> conn_30_pair_3 = new ArrayList<Port>();
    conn_30_pair_3.add(new Port(4));
    conn_30_pair_3.add(new Port(7));
    conn_30.add(conn_30_pair_3);

    ArrayList<Port> conn_30_pair_4 = new ArrayList<Port>();
    conn_30_pair_4.add(new Port(5));
    conn_30_pair_4.add(new Port(8));
    conn_30.add(conn_30_pair_4);

    temp.add(conn_30);

    ArrayList<ArrayList<Port>> conn_31 = new ArrayList<ArrayList<Port>>();
    ArrayList<Port> conn_31_pair_1 = new ArrayList<Port>();
    conn_31_pair_1.add(new Port(1));
    conn_31_pair_1.add(new Port(2));
    conn_31.add(conn_31_pair_1);

    ArrayList<Port> conn_31_pair_2 = new ArrayList<Port>();
    conn_31_pair_2.add(new Port(3));
    conn_31_pair_2.add(new Port(5));
    conn_31.add(conn_31_pair_2);

    ArrayList<Port> conn_31_pair_3 = new ArrayList<Port>();
    conn_31_pair_3.add(new Port(4));
    conn_31_pair_3.add(new Port(8));
    conn_31.add(conn_31_pair_3);

    ArrayList<Port> conn_31_pair_4 = new ArrayList<Port>();
    conn_31_pair_4.add(new Port(6));
    conn_31_pair_4.add(new Port(7));
    conn_31.add(conn_31_pair_4);

    temp.add(conn_31);

    ArrayList<ArrayList<Port>> conn_32 = new ArrayList<ArrayList<Port>>();
    ArrayList<Port> conn_32_pair_1 = new ArrayList<Port>();
    conn_32_pair_1.add(new Port(1));
    conn_32_pair_1.add(new Port(2));
    conn_32.add(conn_32_pair_1);

    ArrayList<Port> conn_32_pair_2 = new ArrayList<Port>();
    conn_32_pair_2.add(new Port(3));
    conn_32_pair_2.add(new Port(5));
    conn_32.add(conn_32_pair_2);

    ArrayList<Port> conn_32_pair_3 = new ArrayList<Port>();
    conn_32_pair_3.add(new Port(4));
    conn_32_pair_3.add(new Port(7));
    conn_32.add(conn_32_pair_3);

    ArrayList<Port> conn_32_pair_4 = new ArrayList<Port>();
    conn_32_pair_4.add(new Port(6));
    conn_32_pair_4.add(new Port(8));
    conn_32.add(conn_32_pair_4);

    temp.add(conn_32);

    ArrayList<ArrayList<Port>> conn_33 = new ArrayList<ArrayList<Port>>();
    ArrayList<Port> conn_33_pair_1 = new ArrayList<Port>();
    conn_33_pair_1.add(new Port(1));
    conn_33_pair_1.add(new Port(2));
    conn_33.add(conn_33_pair_1);

    ArrayList<Port> conn_33_pair_2 = new ArrayList<Port>();
    conn_33_pair_2.add(new Port(3));
    conn_33_pair_2.add(new Port(4));
    conn_33.add(conn_33_pair_2);

    ArrayList<Port> conn_33_pair_3 = new ArrayList<Port>();
    conn_33_pair_3.add(new Port(5));
    conn_33_pair_3.add(new Port(8));
    conn_33.add(conn_33_pair_3);

    ArrayList<Port> conn_33_pair_4 = new ArrayList<Port>();
    conn_33_pair_4.add(new Port(6));
    conn_33_pair_4.add(new Port(7));
    conn_33.add(conn_33_pair_4);

    temp.add(conn_33);

    ArrayList<ArrayList<Port>> conn_34 = new ArrayList<ArrayList<Port>>();
    ArrayList<Port> conn_34_pair_1 = new ArrayList<Port>();
    conn_34_pair_1.add(new Port(1));
    conn_34_pair_1.add(new Port(2));
    conn_34.add(conn_34_pair_1);

    ArrayList<Port> conn_34_pair_2 = new ArrayList<Port>();
    conn_34_pair_2.add(new Port(3));
    conn_34_pair_2.add(new Port(4));
    conn_34.add(conn_34_pair_2);

    ArrayList<Port> conn_34_pair_3 = new ArrayList<Port>();
    conn_34_pair_3.add(new Port(5));
    conn_34_pair_3.add(new Port(7));
    conn_34.add(conn_34_pair_3);

    ArrayList<Port> conn_34_pair_4 = new ArrayList<Port>();
    conn_34_pair_4.add(new Port(6));
    conn_34_pair_4.add(new Port(8));
    conn_34.add(conn_34_pair_4);

    temp.add(conn_34);

    ArrayList<ArrayList<Port>> conn_35 = new ArrayList<ArrayList<Port>>();
    ArrayList<Port> conn_35_pair_1 = new ArrayList<Port>();
    conn_35_pair_1.add(new Port(1));
    conn_35_pair_1.add(new Port(2));
    conn_35.add(conn_35_pair_1);

    ArrayList<Port> conn_35_pair_2 = new ArrayList<Port>();
    conn_35_pair_2.add(new Port(3));
    conn_35_pair_2.add(new Port(4));
    conn_35.add(conn_35_pair_2);

    ArrayList<Port> conn_35_pair_3 = new ArrayList<Port>();
    conn_35_pair_3.add(new Port(5));
    conn_35_pair_3.add(new Port(6));
    conn_35.add(conn_35_pair_3);

    ArrayList<Port> conn_35_pair_4 = new ArrayList<Port>();
    conn_35_pair_4.add(new Port(7));
    conn_35_pair_4.add(new Port(8));
    conn_35.add(conn_35_pair_4);

    temp.add(conn_35);

    this.connections = temp;

  }

  public ArrayList<ArrayList<ArrayList<Port>>> getConnections() {
    return this.connections;
  }

}

/**
 * A util class which generates the deck when a game is created
 */
public class TileCreation {

  private ArrayList<Tile> deck;

  // Hard coded creation of all 35 tiles.
  public TileCreation() {
    this.deck = new ArrayList<>();

    TileConnections connectionsClass = new TileConnections();
    ArrayList<ArrayList<ArrayList<Port>>> connections = connectionsClass.getConnections();

    //for (int i = 0; i < 3; i++) {
    Tile tile_1 = new Tile(connections.get(0));
    this.deck.add(tile_1);

    Tile tile_2 = new Tile(connections.get(1));
    this.deck.add(tile_2);

    Tile tile_3 = new Tile(connections.get(2));
    this.deck.add(tile_3);

    Tile tile_4 = new Tile(connections.get(3));
    this.deck.add(tile_4);

    Tile tile_5 = new Tile(connections.get(4));
    this.deck.add(tile_5);

    Tile tile_6 = new Tile(connections.get(5));
    this.deck.add(tile_6);

    Tile tile_7 = new Tile(connections.get(6));
    this.deck.add(tile_7);

    Tile tile_8 = new Tile(connections.get(7));
    this.deck.add(tile_8);

    Tile tile_9 = new Tile(connections.get(8));
    this.deck.add(tile_9);

    Tile tile_10 = new Tile(connections.get(9));
    this.deck.add(tile_10);

    Tile tile_11 = new Tile(connections.get(10));
    this.deck.add(tile_11);

    Tile tile_12 = new Tile(connections.get(11));
    this.deck.add(tile_12);

    Tile tile_13 = new Tile(connections.get(12));
    this.deck.add(tile_13);

    Tile tile_14 = new Tile(connections.get(13));
    this.deck.add(tile_14);

    Tile tile_15 = new Tile(connections.get(14));
    this.deck.add(tile_15);

    Tile tile_16 = new Tile(connections.get(15));
    this.deck.add(tile_16);

    Tile tile_17 = new Tile(connections.get(16));
    this.deck.add(tile_17);

    Tile tile_18 = new Tile(connections.get(17));
    this.deck.add(tile_18);

    Tile tile_19 = new Tile(connections.get(18));
    this.deck.add(tile_19);

    Tile tile_20 = new Tile(connections.get(19));
    this.deck.add(tile_20);

    Tile tile_21 = new Tile(connections.get(20));
    this.deck.add(tile_21);

    Tile tile_22 = new Tile(connections.get(21));
    this.deck.add(tile_22);

    Tile tile_23 = new Tile(connections.get(22));
    this.deck.add(tile_23);

    Tile tile_24 = new Tile(connections.get(23));
    this.deck.add(tile_24);

    Tile tile_25 = new Tile(connections.get(24));
    this.deck.add(tile_25);

    Tile tile_26 = new Tile(connections.get(25));
    this.deck.add(tile_26);

    Tile tile_27 = new Tile(connections.get(26));
    this.deck.add(tile_27);

    Tile tile_28 = new Tile(connections.get(27));
    this.deck.add(tile_28);

    Tile tile_29 = new Tile(connections.get(28));
    this.deck.add(tile_29);

    Tile tile_30 = new Tile(connections.get(29));
    this.deck.add(tile_30);

    Tile tile_31 = new Tile(connections.get(30));
    this.deck.add(tile_31);

    Tile tile_32 = new Tile(connections.get(31));
    this.deck.add(tile_32);

    Tile tile_33 = new Tile(connections.get(32));
    this.deck.add(tile_33);

    Tile tile_34 = new Tile(connections.get(33));
    this.deck.add(tile_34);

    Tile tile_35 = new Tile(connections.get(34));
    this.deck.add(tile_35);
  }

  public ArrayList<Tile> getDeck() {
    return this.deck;
  }

}

