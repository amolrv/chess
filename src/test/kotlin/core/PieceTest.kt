package core

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import me.amol.core.Piece
import me.amol.core.Side

class PieceTest : FreeSpec({
    "Piece.make function" - {
        "should create white pieces correctly" - {
            "PAWN" {
                val piece = Piece.make("PAWN", Side.WHITE)
                piece.side shouldBe Side.WHITE
                piece.symbol shouldBe "♙"
            }

            "KNIGHT" {
                val piece = Piece.make("KNIGHT", Side.WHITE)
                piece.side shouldBe Side.WHITE
                piece.symbol shouldBe "♘"
            }

            "BISHOP" {
                val piece = Piece.make("BISHOP", Side.WHITE)
                piece.side shouldBe Side.WHITE
                piece.symbol shouldBe "♗"
            }

            "ROOK" {
                val piece = Piece.make("ROOK", Side.WHITE)
                piece.side shouldBe Side.WHITE
                piece.symbol shouldBe "♖"
            }

            "QUEEN" {
                val piece = Piece.make("QUEEN", Side.WHITE)
                piece.side shouldBe Side.WHITE
                piece.symbol shouldBe "♕"
            }

            "KING" {
                val piece = Piece.make("KING", Side.WHITE)
                piece.side shouldBe Side.WHITE
                piece.symbol shouldBe "♔"
            }
        }

        "should create black pieces correctly" - {
            "PAWN" {
                val piece = Piece.make("PAWN", Side.BLACK)
                piece.side shouldBe Side.BLACK
                piece.symbol shouldBe "♟"
            }

            "KNIGHT" {
                val piece = Piece.make("KNIGHT", Side.BLACK)
                piece.side shouldBe Side.BLACK
                piece.symbol shouldBe "♞"
            }

            "BISHOP" {
                val piece = Piece.make("BISHOP", Side.BLACK)
                piece.side shouldBe Side.BLACK
                piece.symbol shouldBe "♝"
            }

            "ROOK" {
                val piece = Piece.make("ROOK", Side.BLACK)
                piece.side shouldBe Side.BLACK
                piece.symbol shouldBe "♜"
            }

            "QUEEN" {
                val piece = Piece.make("QUEEN", Side.BLACK)
                piece.side shouldBe Side.BLACK
                piece.symbol shouldBe "♛"
            }

            "KING" {
                val piece = Piece.make("KING", Side.BLACK)
                piece.side shouldBe Side.BLACK
                piece.symbol shouldBe "♚"
            }
        }

        "should handle case insensitivity" - {
            "lowercase 'pawn'" {
                val piece = Piece.make("pawn", Side.WHITE)
                piece.symbol shouldBe "♙"
            }

            "mixed case 'KnIgHt'" {
                val piece = Piece.make("KnIgHt", Side.BLACK)
                piece.symbol shouldBe "♞"
            }

            "uppercase 'BISHOP'" {
                val piece = Piece.make("BISHOP", Side.WHITE)
                piece.symbol shouldBe "♗"
            }
        }

        "should throw exception for invalid inputs" - {
            "non-existent piece name" {
                shouldThrow<IllegalArgumentException> {
                    Piece.make("UNICORN", Side.WHITE)
                }
            }

            "empty string as piece name" {
                shouldThrow<IllegalArgumentException> {
                    Piece.make("", Side.BLACK)
                }
            }
        }
    }
})
