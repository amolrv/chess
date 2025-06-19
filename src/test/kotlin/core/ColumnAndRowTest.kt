package core

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import me.amol.core.Column
import me.amol.core.Row

class ColumnAndRowTest : FreeSpec({
    "Column" - {
        "should have correct notations and indices" {
            Column.A.notation shouldBe "A"
            Column.A.index shouldBe 0

            Column.H.notation shouldBe "H"
            Column.H.index shouldBe 7

            // Check all columns have the expected index
            Column.entries.forEachIndexed { index, column ->
                column.index shouldBe index
            }
        }

        "allColumns should contain all columns in order" {
            Column.allColumns.size shouldBe 8
            Column.allColumns[0] shouldBe Column.A
            Column.allColumns[7] shouldBe Column.H
        }

        "fromNotation function" - {
            "should convert valid notation to column" {
                Column.fromNotation("A") shouldBe Column.A
                Column.fromNotation("B") shouldBe Column.B
                Column.fromNotation("C") shouldBe Column.C
                Column.fromNotation("D") shouldBe Column.D
                Column.fromNotation("E") shouldBe Column.E
                Column.fromNotation("F") shouldBe Column.F
                Column.fromNotation("G") shouldBe Column.G
                Column.fromNotation("H") shouldBe Column.H
            }

            "should handle case sensitivity correctly" {
                // The current implementation appears to be case-sensitive
                shouldThrow<IllegalArgumentException> {
                    Column.fromNotation("a")
                }

                shouldThrow<IllegalArgumentException> {
                    Column.fromNotation("h")
                }
            }

            "should throw exception for invalid notations" {
                shouldThrow<IllegalArgumentException> {
                    Column.fromNotation("I") // Out of range
                }

                shouldThrow<IllegalArgumentException> {
                    Column.fromNotation("1") // Number instead of letter
                }

                shouldThrow<IllegalArgumentException> {
                    Column.fromNotation("") // Empty string
                }

                shouldThrow<IllegalArgumentException> {
                    Column.fromNotation("AA") // Too long
                }
            }
        }
    }

    "Row" - {
        "should have correct notations and indices" {
            Row.R1.notation shouldBe 1
            Row.R1.index shouldBe 0

            Row.R8.notation shouldBe 8
            Row.R8.index shouldBe 7

            // Check all rows have the expected index
            Row.entries.forEachIndexed { index, row ->
                row.index shouldBe index
            }
        }

        "allRows should contain all rows in order" {
            Row.allRows.size shouldBe 8
            Row.allRows[0] shouldBe Row.R1
            Row.allRows[7] shouldBe Row.R8
        }

        "fromNotation function" - {
            "should convert valid notation to row" {
                Row.fromNotation(1) shouldBe Row.R1
                Row.fromNotation(2) shouldBe Row.R2
                Row.fromNotation(3) shouldBe Row.R3
                Row.fromNotation(4) shouldBe Row.R4
                Row.fromNotation(5) shouldBe Row.R5
                Row.fromNotation(6) shouldBe Row.R6
                Row.fromNotation(7) shouldBe Row.R7
                Row.fromNotation(8) shouldBe Row.R8
            }

            "should throw exception for invalid notations" {
                shouldThrow<IllegalArgumentException> {
                    Row.fromNotation(0) // Out of range (too low)
                }

                shouldThrow<IllegalArgumentException> {
                    Row.fromNotation(9) // Out of range (too high)
                }

                shouldThrow<IllegalArgumentException> {
                    Row.fromNotation(-1) // Negative number
                }
            }
        }
    }
})
