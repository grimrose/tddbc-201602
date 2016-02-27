package org.grimrose.tddbc.scala

trait Coordinate {
  def value: Int
}

case class X(value: Int) extends Coordinate

case class Y(value: Int) extends Coordinate

case class GridPoint(x: X, y: Y) {
  def notation() = s"(${x.value}, ${y.value})"

  def hasSameCoordinatesWith(other: GridPoint): Boolean = {
    this.x == other.x && this.y == other.y
  }

  def neighborOf(other: GridPoint): Neighbor = {
    // (x-1,y),(x+1,y),(x,y-1),(x,y+1)
    val neighbors = Seq(
      (X(x.value - 1), y),
      (X(x.value + 1), y),
      (x, Y(y.value - 1)),
      (x, Y(y.value + 1))
    )
    if (neighbors.contains(GridPoint.unapply(other).get)) Neighbor else NotNeighbor
  }

}

trait Neighbor

case object Neighbor extends Neighbor

case object NotNeighbor extends Neighbor
