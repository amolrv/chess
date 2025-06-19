package core

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.shouldBe
import me.amol.core.Board
import me.amol.core.Pawn
import me.amol.core.Queen
import me.amol.core.Square

class QueenTest : FreeSpec(
    {
        "A white queen" - {
            "in the middle of an empty board" - {
                "should be able to move horizontally, vertically, and diagonally" {
                    val board = Board()

                    val possibleMoves = Queen.white.passibleMoves(board, Square.D4)

                    // Should be able to move in all eight directions to the edge of the board
                    // Vertical moves (up, down), Horizontal moves (right, left), Diagonal moves (in four directions)
                    val expectedMoves =
                        listOf(
                            // Vertical moves up
                            Square.D5, Square.D6, Square.D7, Square.D8,
                            // Vertical moves down
                            Square.D3, Square.D2, Square.D1,
                            // Horizontal moves right
                            Square.E4, Square.F4, Square.G4, Square.H4,
                            // Horizontal moves left
                            Square.C4, Square.B4, Square.A4,
                            // Diagonal moves up-right
                            Square.E5, Square.F6, Square.G7, Square.H8,
                            // Diagonal moves down-right
                            Square.E3, Square.F2, Square.G1,
                            // Diagonal moves up-left
                            Square.C5, Square.B6, Square.A7,
                            // Diagonal moves down-left
                            Square.C3, Square.B2, Square.A1,
                        )

                    possibleMoves shouldContainExactlyInAnyOrder expectedMoves
                }
            }

            "with pieces blocking its path" - {
                "should only move up to the blocking pieces" {
                    val board = Board()

                    // Place blocking pieces
                    board[Square.D6] = Pawn.white // Friendly piece blocking upward
                    board[Square.D2] = Pawn.black // Enemy piece that can be captured
                    board[Square.F4] = Pawn.white // Friendly piece blocking rightward
                    board[Square.B4] = Queen.black // Enemy piece that can be captured
                    board[Square.F6] = Pawn.white // Friendly piece blocking diagonal up-right
                    board[Square.B2] = Pawn.black // Enemy piece that can be captured on diagonal down-left

                    val possibleMoves = Queen.white.passibleMoves(board, Square.D4)

                    // Different types of moves with blocking pieces
                    val expectedMoves =
                        listOf(
                            // Vertical moves up - up to but not including D6
                            Square.D5,
                            // Vertical moves down - up to and including D2 (capture)
                            Square.D3, Square.D2,
                            // Horizontal moves right - up to but not including F4
                            Square.E4,
                            // Horizontal moves left - up to and including B4 (capture)
                            Square.C4, Square.B4,
                            // Diagonal moves up-right - up to but not including F6
                            Square.E5,
                            // Diagonal moves down-right - all available
                            Square.E3, Square.F2, Square.G1,
                            // Diagonal moves up-left - all available
                            Square.C5, Square.B6, Square.A7,
                            // Diagonal moves down-left - up to and including B2 (capture)
                            Square.C3, Square.B2,
                        )

                    possibleMoves shouldContainExactlyInAnyOrder expectedMoves
                }
            }

            "in a corner of the board" - {
                "should have restricted movement options" {
                    val board = Board()

                    val possibleMoves = Queen.white.passibleMoves(board, Square.A1)

                    // Movement is restricted to only three directions from the corner
                    val expectedMoves =
                        listOf(
                            // Vertical moves up only from A1
                            Square.A2, Square.A3, Square.A4, Square.A5, Square.A6, Square.A7, Square.A8,
                            // Horizontal moves right only from A1
                            Square.B1, Square.C1, Square.D1, Square.E1, Square.F1, Square.G1, Square.H1,
                            // Diagonal moves up-right only from A1
                            Square.B2, Square.C3, Square.D4, Square.E5, Square.F6, Square.G7, Square.H8,
                        )

                    possibleMoves shouldContainExactlyInAnyOrder expectedMoves
                }
            }

            "completely surrounded by pieces" - {
                "should not be able to move" {
                    val board = Board()

                    // Surround the queen with pieces
                    board[Square.D5] = Pawn.white // Above
                    board[Square.D3] = Pawn.white // Below
                    board[Square.E4] = Pawn.white // Right
                    board[Square.C4] = Pawn.white // Left
                    board[Square.E5] = Pawn.white // Up-right
                    board[Square.E3] = Pawn.white // Down-right
                    board[Square.C5] = Pawn.white // Up-left
                    board[Square.C3] = Pawn.white // Down-left

                    val possibleMoves = Queen.white.passibleMoves(board, Square.D4)

                    possibleMoves shouldBe emptyList()
                }
            }

            "surrounded by capturable enemy pieces" - {
                "should be able to capture in all directions" {
                    val board = Board()

                    // Surround the queen with enemy pieces
                    board[Square.D5] = Pawn.black
                    board[Square.D3] = Pawn.black
                    board[Square.E4] = Pawn.black
                    board[Square.C4] = Pawn.black
                    board[Square.E5] = Pawn.black
                    board[Square.E3] = Pawn.black
                    board[Square.C5] = Pawn.black
                    board[Square.C3] = Pawn.black

                    val possibleMoves = Queen.white.passibleMoves(board, Square.D4)

                    // Can capture in all 8 directions
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

        "A black queen" - {
            "in the middle of an empty board" - {
                "should have the same movement as a white queen" {
                    val board = Board()

                    val whiteQueenMoves = Queen.white.passibleMoves(board, Square.D4)
                    val blackQueenMoves = Queen.black.passibleMoves(board, Square.D4)

                    // The color shouldn't affect movement pattern on an empty board
                    blackQueenMoves shouldContainExactlyInAnyOrder whiteQueenMoves
                }
            }

            "with pieces blocking its path" - {
                "should only move up to the blocking pieces" {
                    val board = Board()

                    // Place blocking pieces (opposite colors from white queen test)
                    board[Square.D6] = Pawn.black
                    board[Square.D2] = Pawn.white
                    board[Square.F4] = Pawn.black
                    board[Square.B4] = Queen.white

                    val possibleMoves = Queen.black.passibleMoves(board, Square.D4)

                    // Different types of moves with various blocking pieces
                    val expectedMoves =
                        listOf(
                            // Vertical moves up - up to but not including D6
                            Square.D5,
                            // Vertical moves down - up to and including D2 (capture)
                            Square.D3, Square.D2,
                            // Horizontal moves right - up to but not including F4
                            Square.E4,
                            // Horizontal moves left - up to and including B4 (capture)
                            Square.C4, Square.B4,
                            // Diagonal moves up-right
                            Square.E5, Square.F6, Square.G7, Square.H8,
                            // Diagonal moves down-right
                            Square.E3, Square.F2, Square.G1,
                            // Diagonal moves up-left
                            Square.C5, Square.B6, Square.A7,
                            // Diagonal moves down-left
                            Square.C3, Square.B2, Square.A1,
                        )

                    possibleMoves shouldContainExactlyInAnyOrder expectedMoves
                }
            }
        }
    },
)
