package com.balaji.nqueens;

import com.balaji.nqueens.MovesCalculator;
import com.balaji.nqueens.Position;


public class Rules {

  public static int LIMIT = 8;

  public static boolean ruleDiagonal(Position p) {
    boolean flag = true;
    for (Position Position : MovesCalculator.getMoves()) {
      if (Math.abs(Position.getX() - p.getX()) == Math.abs(Position.getY() - p.getY())) {
        flag = false;
        break;
      }
    }
    return flag;
  }

  public static boolean ruleHorizontal(Position p) {
    boolean flag = true;
    for (Position Position : MovesCalculator.getMoves()) {
      if (Position.getX() == p.getX()) {
        flag = false;
        break;
      }
    }
    return flag;
  }

  public static boolean ruleVertical(Position p) {
    boolean flag = true;
    for (Position Position : MovesCalculator.getMoves()) {
      if (Position.getY() == p.getY()) {
        flag = false;
        break;
      }
    }
    return flag;
  }
}
