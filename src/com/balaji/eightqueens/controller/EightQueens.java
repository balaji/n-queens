package com.balaji.eightqueens.controller;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import com.balaji.eightqueens.utils.EightQueensRules;
import com.balaji.eightqueens.utils.Position;

public class EightQueens {

  private static Deque<Position> moves = new ArrayDeque<Position>();
  private Deque<Position> stack = new ArrayDeque<Position>();
  private List<List<Position>> lists = new ArrayList<List<Position>>();
  private static final int DIMENSION = EightQueensRules.LIMIT;

  private Position pop() {
    return stack.isEmpty() ? null : stack.removeLast();
  }

  private void emptyStack() {
    moves.clear();
    stack.clear();
  }

  public void process() {
    for (int y = 1; y <= DIMENSION; y++) {
      Position p = new Position(1, y);
      moves.addLast(p);
      next(2);
    }
  }

  private void replace(Position p) {
    while (moves.getLast().getX() >= p.getX()) {
      moves.removeLast();
    }
    moves.addLast(p);
  }

  private void display() {
    List<Position> list = new ArrayList<Position>();
    for (Position p : moves) {
      list.add(p);
    }
    lists.add(list);
  }

  private void backTrack() {
    Position p = pop();
    if (p != null) {
      replace(p);
      next(p.getX() + 1);
    } else {
      emptyStack();
    }
  }

  private void next(int position) {

    Position nextPos = null;
    Position identified = null;
    boolean flag = false;

    for (int y = 1; y <= DIMENSION; y++) {
      nextPos = new Position(position, y);
      if (EightQueensRules.ruleDiagonal(nextPos)
          && EightQueensRules.ruleHorizontal(nextPos)
          && EightQueensRules.ruleVertical(nextPos)) {

        if (!flag) {
          flag = true;
          identified = nextPos;
        } else {
          stack.addLast(nextPos);
        }
      }
    }

    if (flag) {
      moves.addLast(identified);
      if (nextPos.getX() == DIMENSION) {
        display();
        backTrack();
      } else {
        next(nextPos.getX() + 1);
      }
    } else {
      backTrack();
    }
  }

  public List<List<Position>> getLists() {
    return lists;
  }

  public static Deque<Position> getMoves() {
    return moves;
  }

}
