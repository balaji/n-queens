package com.balaji.eightqueens.main;

import com.balaji.eightqueens.controller.EightQueens;
import com.balaji.eightqueens.ui.QueensFrame;

public class Main {

  public static void main(String args[]) {
    EightQueens queens = new EightQueens();
    queens.process();
    new QueensFrame(queens.getLists());
  }
}
