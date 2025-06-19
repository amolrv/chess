package core

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.shouldBe
import me.amol.core.Board
import me.amol.core.King
import me.amol.core.Pawn
import me.amol.core.Square

class KingTest : FreeSpec(
    {
        "A white king" - {
            "in the middle of an empty board" - {
                "should be able to move one square in any direction" {
                    val board = Board()

                    val possibleMoves = King.white.passibleMoves(board, Square.D4)

                    // Should be able to move one square in all eight directions
                    val expectedMoves =
                        listOf(Square.D5, Square.D3, Square.E4, Square.C4, Square.E5, Square.E3, Square.C5, Square.C3)

                    possibleMoves shouldContainExactlyInAnyOrder expectedMoves
                }
            }

            "with friendly pieces blocking some moves" - {
                "should only be able to move to unblocked squares" {
                    val board = Board()

                    // Place friendly pieces to block some moves
                    board[Square.D5] = Pawn.white
                    board[Square.E4] = Pawn.white
                    board[Square.E5] = Pawn.white

                    val possibleMoves = King.white.passibleMoves(board, Square.D4)

                    val expectedMoves = listOf(Square.D3, Square.C4, Square.E3, Square.C5, Square.C3)

                    possibleMoves shouldContainExactlyInAnyOrder expectedMoves
                }
            }

            "with enemy pieces in capture range" - {
                "should be able to capture them" {
                    val board = Board()

                    // Place enemy pieces that can be captured
                    board[Square.D5] = Pawn.black
                    board[Square.E4] = Pawn.black
                    board[Square.C3] = Pawn.black

                    val possibleMoves = King.white.passibleMoves(board, Square.D4)

                    val expectedMoves =
                        listOf(Square.D5, Square.D3, Square.E4, Square.C4, Square.E5, Square.E3, Square.C5, Square.C3)

                    possibleMoves shouldContainExactlyInAnyOrder expectedMoves
                }
            }

            "in a corner of the board" - {
                "should have restricted movement options" {
                    val board = Board()

                    val possibleMoves = King.white.passibleMoves(board, Square.A1)

                    // Up, Right, Up-right
                    val expectedMoves =
                        listOf(
                            Square.A2,
                            Square.B1,
                            Square.B2,
                        )

                    possibleMoves shouldContainExactlyInAnyOrder expectedMoves
                }
            }

            "on the edge of the board" - {
                "should have restricted movement options" {
                    val board = Board()

                    val possibleMoves = King.white.passibleMoves(board, Square.A4)

                    val expectedMoves = listOf(Square.A5, Square.A3, Square.B4, Square.B5, Square.B3)

                    possibleMoves shouldContainExactlyInAnyOrder expectedMoves
                }
            }

            "completely surrounded by friendly pieces" - {
                "should not be able to move" {
                    val board = Board()

                    // Surround the king with friendly pieces
                    board[Square.D5] = Pawn.white
                    board[Square.D3] = Pawn.white
                    board[Square.E4] = Pawn.white
                    board[Square.C4] = Pawn.white
                    board[Square.E5] = Pawn.white
                    board[Square.E3] = Pawn.white
                    board[Square.C5] = Pawn.white
                    board[Square.C3] = Pawn.white

                    val possibleMoves = King.white.passibleMoves(board, Square.D4)

                    possibleMoves shouldBe emptyList()
                }
            }

            "surrounded by capturable enemy pieces" - {
                "should be able to capture in all directions" {
                    val board = Board()

                    // Surround the king with enemy pieces
                    board[Square.D5] = Pawn.black
                    board[Square.D3] = Pawn.black
                    board[Square.E4] = Pawn.black
                    board[Square.C4] = Pawn.black
                    board[Square.E5] = Pawn.black
                    board[Square.E3] = Pawn.black
                    board[Square.C5] = Pawn.black
                    board[Square.C3] = Pawn.black

                    val possibleMoves = King.white.passibleMoves(board, Square.D4)

                    // Captures in all 8 directions
                    val expectedMoves =
                        listOf(
                            Square.D5,
                            Square.D3,
                            Square.E4,
                            Square.C4,
                            Square.E5,
                            Square.E3,
                            Square.C5,
                            Square.C3,
                        )

                    possibleMoves shouldContainExactlyInAnyOrder expectedMoves
                }
            }
        }

        "A black king" - {
            "in the middle of an empty board" - {
                "should have the same movement as a white king" {
                    val board = Board()

                    val whiteKingMoves = King.white.passibleMoves(board, Square.D4)
                    val blackKingMoves = King.black.passibleMoves(board, Square.D4)

                    // The color shouldn't affect movement pattern on an empty board
                    blackKingMoves shouldContainExactlyInAnyOrder whiteKingMoves
                }
            }

            "with friendly pieces blocking some moves" - {
                "should only be able to move to unblocked squares" {
                    val board = Board()

                    board[Square.D5] = Pawn.black
                    board[Square.E4] = Pawn.black
                    board[Square.E5] = Pawn.black

                    val possibleMoves = King.black.passibleMoves(board, Square.D4)

                    val expectedMoves = listOf(Square.D3, Square.C4, Square.E3, Square.C5, Square.C3)

                    possibleMoves shouldContainExactlyInAnyOrder expectedMoves
                }
            }
        }
    },
)
