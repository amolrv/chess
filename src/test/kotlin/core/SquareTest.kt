package core

import io.kotest.assertions.throwables.shouldThrow
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

        "fromNotation function should work correctly" - {
            "for corner squares" - {
                "A1 (bottom-left corner)" {
                    Square.fromNotation("A1") shouldBe Square.A1
                }

                "H1 (bottom-right corner)" {
                    Square.fromNotation("H1") shouldBe Square.H1
                }

                "A8 (top-left corner)" {
                    Square.fromNotation("A8") shouldBe Square.A8
                }

                "H8 (top-right corner)" {
                    Square.fromNotation("H8") shouldBe Square.H8
                }
            }

            "for middle squares" - {
                "D4 (center square)" {
                    Square.fromNotation("D4") shouldBe Square.D4
                }

                "E5 (center square)" {
                    Square.fromNotation("E5") shouldBe Square.E5
                }

                "C6 (arbitrary middle square)" {
                    Square.fromNotation("C6") shouldBe Square.C6
                }

                "F3 (arbitrary middle square)" {
                    Square.fromNotation("F3") shouldBe Square.F3
                }
            }

            "with case insensitivity" - {
                "lowercase a1 should work" {
                    Square.fromNotation("a1") shouldBe Square.A1
                }

                "mixed case e5 should work" {
                    Square.fromNotation("e5") shouldBe Square.E5
                }

                "mixed case h8 should work" {
                    Square.fromNotation("h8") shouldBe Square.H8
                }
            }

            "should throw exceptions for invalid inputs" - {
                "non-existent column I5" {
                    shouldThrow<IllegalArgumentException> {
                        Square.fromNotation("I5")
                    }
                }

                "non-existent row A9" {
                    shouldThrow<IllegalArgumentException> {
                        Square.fromNotation("A9")
                    }
                }

                "empty string" {
                    shouldThrow<IllegalArgumentException> {
                        Square.fromNotation("")
                    }
                }

                "invalid format 'A'" {
                    shouldThrow<IllegalArgumentException> {
                        Square.fromNotation("A")
                    }
                }

                "invalid format '5'" {
                    shouldThrow<IllegalArgumentException> {
                        Square.fromNotation("5")
                    }
                }

                "invalid format with space 'A 1'" {
                    shouldThrow<IllegalArgumentException> {
                        Square.fromNotation("A 1")
                    }
                }
            }
        }
    }
})
