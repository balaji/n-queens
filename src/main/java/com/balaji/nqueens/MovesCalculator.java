package com.balaji.nqueens;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class MovesCalculator {

  private Deque<Position> stack;
  private List<List<Position>> lists;

  public MovesCalculator(Deque<Position> stack, List<List<Position>> lists) {
    this.stack = stack;
    this.lists = lists;
  }

  private Position pop(Deque<Position> deque) {
    return deque.isEmpty() ? null : deque.removeLast();
  }

  private void empty(Deque<Position> deque) {
    deque.clear();
  }

  public List<List<Position>> process() {
    for (int y = 1; y <= Rules.LIMIT; y++) {
      Position p = new Position(1, y);
      Deque<Position> moves = new ArrayDeque<Position>();
      moves.addLast(p);
      next(2, moves);
    }
    return lists;
  }

  private void replace(Position p, Deque<Position> deque) {
    while (deque.getLast().getX() >= p.getX()) {
      deque.removeLast();
    }
    deque.addLast(p);
  }

  private void collect(Deque<Position> moves) {
    List<Position> list = new ArrayList<Position>();
    for (Position p : moves) {
      list.add(p);
    }
    this.lists.add(list);
  }

  private void backTrack(Deque<Position> moves) {
    Position p = pop(stack);
    if (p != null) {
      replace(p, moves);
      next(p.getX() + 1, moves);
    } else {
      empty(moves);
      empty(stack);
    }
  }

  private void next(int place, Deque<Position> moves) {

    Position nextPos = null;
    Position identified = null;
    boolean flag = false;

    for (int y = 1; y <= Rules.LIMIT; y++) {
      nextPos = new Position(place, y);
      if (Rules.ruleDiagonal(nextPos, moves) && Rules.ruleHorizontal(nextPos, moves) && Rules.ruleVertical(nextPos, moves)) {
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
      if (nextPos.getX() == Rules.LIMIT) {
        collect(moves);
        backTrack(moves);
      } else {
        next(nextPos.getX() + 1, moves);
      }
    } else {
      backTrack(moves);
    }
  }
}
