package core

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.shouldBe
import me.amol.core.Board
import me.amol.core.Pawn
import me.amol.core.Square

class PawnTest : FreeSpec(
    {
        "A white pawn" - {
            "on its starting position (R2)" - {
                "should be able to move one or two squares forward if unblocked" {
                    val board = Board()

                    val possibleMoves = Pawn.white.passibleMoves(board, Square.E2)

                    possibleMoves shouldContainExactlyInAnyOrder listOf(Square.E3, Square.E4)
                }

                "should only move one square if second square is blocked" {
                    val board = Board()
                    board[Square.E4] = Pawn.black // Blocking the two-square move

                    val possibleMoves = Pawn.white.passibleMoves(board, Square.E2)

                    possibleMoves shouldContainExactlyInAnyOrder listOf(Square.E3)
                }

                "should not move if first square is blocked" {
                    val board = Board()
                    board[Square.E3] = Pawn.black // Blocking the one-square move

                    val possibleMoves = Pawn.white.passibleMoves(board, Square.E2)

                    possibleMoves shouldBe emptyList()
                }

                "should be able to capture diagonally" {
                    val board = Board()
                    board[Square.D3] = Pawn.black // Can be captured
                    board[Square.F3] = Pawn.black // Can be captured

                    val possibleMoves = Pawn.white.passibleMoves(board, Square.E2)

                    possibleMoves shouldContainExactlyInAnyOrder listOf(Square.E3, Square.E4, Square.D3, Square.F3)
                }

                "should not be able to capture friendly pieces" {
                    val board = Board()
                    board[Square.D3] = Pawn.white // Friendly piece, can't capture
                    board[Square.F3] = Pawn.white // Friendly piece, can't capture

                    val possibleMoves = Pawn.white.passibleMoves(board, Square.E2)

                    possibleMoves shouldContainExactlyInAnyOrder listOf(Square.E3, Square.E4)
                }
            }

            "on a non-starting position" - {
                "should only move one square forward" {
                    val board = Board()

                    val possibleMoves = Pawn.white.passibleMoves(board, Square.E3)

                    possibleMoves shouldContainExactlyInAnyOrder listOf(Square.E4)
                }

                "should be able to capture diagonally" {
                    val board = Board()
                    board[Square.D4] = Pawn.black // Can be captured
                    board[Square.F4] = Pawn.black // Can be captured

                    val possibleMoves = Pawn.white.passibleMoves(board, Square.E3)

                    possibleMoves shouldContainExactlyInAnyOrder listOf(Square.E4, Square.D4, Square.F4)
                }
            }

            "on the edge of the board" - {
                "should not move off the board horizontally" {
                    val board = Board()
                    board[Square.B3] = Pawn.black // Can be captured

                    val possibleMoves = Pawn.white.passibleMoves(board, Square.A2)

                    possibleMoves shouldContainExactlyInAnyOrder listOf(Square.A3, Square.A4, Square.B3)
                }

                "should handle the last row correctly" {
                    val board = Board()

                    val possibleMoves = Pawn.white.passibleMoves(board, Square.E7)

                    possibleMoves shouldContainExactlyInAnyOrder listOf(Square.E8)
                }
            }
        }

        "A black pawn" - {
            "on its starting position (R7)" - {
                "should be able to move one or two squares forward if unblocked" {
                    val board = Board()

                    val possibleMoves = Pawn.black.passibleMoves(board, Square.E7)

                    possibleMoves shouldContainExactlyInAnyOrder listOf(Square.E6, Square.E5)
                }

                "should only move one square if second square is blocked" {
                    val board = Board()
                    board[Square.E5] = Pawn.white // Blocking the two-square move

                    val possibleMoves = Pawn.black.passibleMoves(board, Square.E7)

                    possibleMoves shouldContainExactlyInAnyOrder listOf(Square.E6)
                }

                "should not move if first square is blocked" {
                    val board = Board()
                    board[Square.E6] = Pawn.white // Blocking the one-square move

                    val possibleMoves = Pawn.black.passibleMoves(board, Square.E7)

                    possibleMoves shouldBe emptyList()
                }

                "should be able to capture diagonally" {
                    val board = Board()
                    board[Square.D6] = Pawn.white // Can be captured
                    board[Square.F6] = Pawn.white // Can be captured

                    val possibleMoves = Pawn.black.passibleMoves(board, Square.E7)

                    possibleMoves shouldContainExactlyInAnyOrder listOf(Square.E6, Square.E5, Square.D6, Square.F6)
                }

                "should not be able to capture friendly pieces" {
                    val board = Board()
                    board[Square.D6] = Pawn.black // Friendly piece, can't capture
                    board[Square.F6] = Pawn.black // Friendly piece, can't capture

                    val possibleMoves = Pawn.black.passibleMoves(board, Square.E7)

                    possibleMoves shouldContainExactlyInAnyOrder listOf(Square.E6, Square.E5)
                }
            }

            "on a non-starting position" - {
                "should only move one square forward" {
                    val board = Board()

                    val possibleMoves = Pawn.black.passibleMoves(board, Square.E6)

                    possibleMoves shouldContainExactlyInAnyOrder listOf(Square.E5)
                }

                "should be able to capture diagonally" {
                    val board = Board()
                    board[Square.D5] = Pawn.white // Can be captured
                    board[Square.F5] = Pawn.white // Can be captured

                    val possibleMoves = Pawn.black.passibleMoves(board, Square.E6)

                    possibleMoves shouldContainExactlyInAnyOrder listOf(Square.E5, Square.D5, Square.F5)
                }
            }

            "on the edge of the board" - {
                "should not move off the board horizontally" {
                    val board = Board()
                    board[Square.G6] = Pawn.white // Can be captured

                    val possibleMoves = Pawn.black.passibleMoves(board, Square.H7)

                    possibleMoves shouldContainExactlyInAnyOrder listOf(Square.H6, Square.H5, Square.G6)
                }

                "should handle the first row correctly" {
                    val board = Board()

                    val possibleMoves = Pawn.black.passibleMoves(board, Square.E2)

                    possibleMoves shouldContainExactlyInAnyOrder listOf(Square.E1)
                }
            }
        }
    },
)
