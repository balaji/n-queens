package com.balaji.eightqueens.utils;

import com.balaji.eightqueens.controller.EightQueens;


public class EightQueensRules {

  public static int LIMIT = 8;

  public static boolean ruleDiagonal(Position p) {
    boolean flag = true;
    for (Position position : EightQueens.getMoves()) {
      if (Math.abs(position.getX() - p.getX()) == Math.abs(position.getY() - p.getY())) {
        flag = false;
        break;
      }
    }
    return flag;
  }

  public static boolean ruleHorizontal(Position p) {
    boolean flag = true;
    for (Position position : EightQueens.getMoves()) {
      if (position.getX() == p.getX()) {
        flag = false;
        break;
      }
    }
    return flag;
  }

  public static boolean ruleVertical(Position p) {
    boolean flag = true;
    for (Position position : EightQueens.getMoves()) {
      if (position.getY() == p.getY()) {
        flag = false;
        break;
      }
    }
    return flag;
  }
}
