package org.grimrose.tddbc.scala

trait Coordinate

case class X(value: Int) extends Coordinate

case class Y(value: Int) extends Coordinate

case class GridPoint(x: X, y: Y) {
  def notation() = s"(${x.value}, ${y.value})"
}
