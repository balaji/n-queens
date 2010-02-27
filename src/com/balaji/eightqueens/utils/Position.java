package com.balaji.eightqueens.utils;

public class Position {

  public Position(int x, int y) {
    this.x = x;
    this.y = y;
  }

  private int x;
  private int y;

  @Override
  public String toString() {
    return "The position points are: X:= " + x + ", Y:= " + y;
  }

  /**
   * @return the x
   */
  public int getX() {
    return x;
  }

  /**
   * @param x the x to set
   */
  public void setX(int x) {
    this.x = x;
  }

  /**
   * @return the y
   */
  public int getY() {
    return y;
  }

  /**
   * @param y the y to set
   */
  public void setY(int y) {
    this.y = y;
  }

  public boolean checkPosition(int x, int y) {
    return (this.x == x && this.y == y);
  }
}
