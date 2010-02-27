package com.balaji.nqueens;

public class Position {

  public Position(int x, int y) {
    this.x = x;
    this.y = y;
  }

  private int x;
  private int y;

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public boolean checkPosition(int x, int y) {
    return (this.x == x && this.y == y);
  }
}
