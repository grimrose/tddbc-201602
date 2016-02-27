package org.grimrose.tddbc.scala

import org.scalatest.prop.TableDrivenPropertyChecks
import org.scalatest.{DiagrammedAssertions, FunSpec}

class GridPointSpec extends FunSpec with DiagrammedAssertions with TableDrivenPropertyChecks {

  describe("格子点") {

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
}
