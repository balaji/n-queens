package com.balaji.nqueens;

import java.util.Deque;


public class Rules {

  public static int LIMIT = 8;

  public static boolean ruleDiagonal(Position p, Deque<Position> moves) {
    boolean flag = true;
    for (Position Position : moves) {
      if (Math.abs(Position.getX() - p.getX()) == Math.abs(Position.getY() - p.getY())) {
        flag = false;
        break;
      }
    }
    return flag;
  }

  public static boolean ruleHorizontal(Position p, Deque<Position> moves) {
    boolean flag = true;
    for (Position Position : moves) {
      if (Position.getX() == p.getX()) {
        flag = false;
        break;
      }
    }
    return flag;
  }

  public static boolean ruleVertical(Position p, Deque<Position> moves) {
    boolean flag = true;
    for (Position Position : moves) {
      if (Position.getY() == p.getY()) {
        flag = false;
        break;
      }
    }
    return flag;
  }
}
