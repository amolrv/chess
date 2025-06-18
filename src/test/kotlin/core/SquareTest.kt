package core

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import me.amol.core.Column
import me.amol.core.Row
import me.amol.core.Square

class SquareTest : FreeSpec({
  "Square" - {
    "should calculate correct column for each square" - {
      // Test first column (A)
      "A1 should have column A" {
        Square.A1.column shouldBe Column.A
      }
      "A8 should have column A" {
        Square.A8.column shouldBe Column.A
      }

      // Test middle columns
      "D4 should have column D" {
        Square.D4.column shouldBe Column.D
      }
      "E5 should have column E" {
        Square.E5.column shouldBe Column.E
      }

      // Test last column (H)
      "H1 should have column H" {
        Square.H1.column shouldBe Column.H
      }
      "H8 should have column H" {
        Square.H8.column shouldBe Column.H
      }
    }

    "should calculate correct row for each square" - {
      // Test first row (1)
      "A1 should have row R1" {
        Square.A1.row shouldBe Row.R1
      }
      "H1 should have row R1" {
        Square.H1.row shouldBe Row.R1
      }

      // Test middle rows
      "D4 should have row R4" {
        Square.D4.row shouldBe Row.R4
      }
      "E5 should have row R5" {
        Square.E5.row shouldBe Row.R5
      }

      // Test last row (8)
      "A8 should have row R8" {
        Square.A8.row shouldBe Row.R8
      }
      "H8 should have row R8" {
        Square.H8.row shouldBe Row.R8
      }
    }

    "should handle edge cases correctly" - {
      // Corner squares should have correct column and row
      "A1 (bottom-left corner)" {
        val square = Square.A1
        square.column shouldBe Column.A
        square.row shouldBe Row.R1
      }

      "H1 (bottom-right corner)" {
        val square = Square.H1
        square.column shouldBe Column.H
        square.row shouldBe Row.R1
      }

      "A8 (top-left corner)" {
        val square = Square.A8
        square.column shouldBe Column.A
        square.row shouldBe Row.R8
      }

      "H8 (top-right corner)" {
        val square = Square.H8
        square.column shouldBe Column.H
        square.row shouldBe Row.R8
      }
    }
  }
})
