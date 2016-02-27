package org.grimrose.tddbc.scala

import org.scalatest.prop.TableDrivenPropertyChecks
import org.scalatest.{DiagrammedAssertions, FunSpec}

class GridPointSpec extends FunSpec with DiagrammedAssertions with TableDrivenPropertyChecks {

  describe("格子点") {

    describe("課題1-1") {
      val fixtures = Table(
        ("x座標", "y座標", "expected"),
        (X(4), Y(7), (4, 7)),
        (X(3), Y(8), (3, 8))
      )

      it("x座標とy座標を取得できること") {

        forAll(fixtures) { case (x, y, (a, b)) =>
          val actual = GridPoint(x, y)
          assert(actual.x == X(a))
          assert(actual.x != X(b))
          assert(actual.y == Y(b))
        }
      }

      it("notationを取得できること") {
        forAll(fixtures) { case (x, y, (a, b)) =>
          val actual = GridPoint(x, y)
          assert(actual.notation() == s"($a, $b)")
        }
      }
    }

    describe("課題1-2") {
      it("2つの座標が同じ座標(coordinates)を持つことが判定できること") {
        val fixtures = Table(
          ("座標1", "座標2", "expected"),
          ((X(4), Y(7)), (X(4), Y(7)), true),
          ((X(4), Y(7)), (X(3), Y(8)), false)
        )
        forAll(fixtures) { case ((x1, y1), (x2, y2), expected) =>
          val gp1 = GridPoint(x1, y1)
          val gp2 = GridPoint(x2, y2)
          assert(gp1.hasSameCoordinatesWith(gp2) == expected)
        }
      }
    }

    describe("課題2") {
      val fixtures = Table(
        ("自分", "相手", "expected"),
        ((X(1), Y(1)), (X(0), Y(1)), Neighbor),
        ((X(1), Y(1)), (X(2), Y(1)), Neighbor),
        ((X(1), Y(1)), (X(1), Y(0)), Neighbor),
        ((X(1), Y(1)), (X(1), Y(2)), Neighbor),
        ((X(4), Y(7)), (X(3), Y(7)), Neighbor),
        ((X(4), Y(7)), (X(3), Y(8)), NotNeighbor),
        ((X(4), Y(7)), (X(4), Y(7)), NotNeighbor)
      )

      it("隣り合っているか判定できること") {
        forAll(fixtures) { case ((x1, y1), (x2, y2), expected) =>
          val gp1 = GridPoint(x1, y1)
          val gp2 = GridPoint(x2, y2)
          assert(gp1.neighborOf(gp2) == expected)
        }
      }
    }
  }

}
