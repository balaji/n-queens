package com.balaji.nqueens;

import com.balaji.nqueens.ui.GuiFrame;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class Main {

  public static void main(String args[]) {
    new GuiFrame(new MovesCalculator(new ArrayDeque<Position>(), new ArrayList<List<Position>>()).process());
  }
}
