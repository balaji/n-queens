package com.balaji.nqueens;

import com.balaji.nqueens.ui.GuiFrame;

public class Main {

  public static void main(String args[]) {
    MovesCalculator calculator = new MovesCalculator();
    calculator.process();
    new GuiFrame(calculator.getLists());
  }
}
