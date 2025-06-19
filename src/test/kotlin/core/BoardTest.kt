package core

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe
import me.amol.core.Bishop
import me.amol.core.Board
import me.amol.core.Column
import me.amol.core.King
import me.amol.core.Knight
import me.amol.core.Pawn
import me.amol.core.Queen
import me.amol.core.Rook
import me.amol.core.Row
import me.amol.core.Square

class BoardTest : FreeSpec(
    {
        "Board" - {
            "get and set operations" - {
                "should get and set pieces using column and row" {
                    val board = Board()

                    // Initially all squares should be empty
                    board[Column.A, Row.R1].shouldBeNull()

                    // Set and get a piece
                    board[Column.A, Row.R1] = Pawn.white
                    board[Column.A, Row.R1] shouldBe Pawn.white

                    // Set null to clear a square
                    board[Column.A, Row.R1] = null
                    board[Column.A, Row.R1].shouldBeNull()

                    // Edge case: corner squares
                    board[Column.H, Row.R8] = King.black
                    board[Column.H, Row.R8] shouldBe King.black

                    board[Column.A, Row.R8] = Rook.black
                    board[Column.A, Row.R8] shouldBe Rook.black

                    board[Column.H, Row.R1] = Rook.white
                    board[Column.H, Row.R1] shouldBe Rook.white
                }

                "should get and set pieces using Square enum" {
                    val board = Board()

                    // Initially all squares should be empty
                    board[Square.A1].shouldBeNull()

                    // Set and get a piece
                    board[Square.A1] = Pawn.white
                    board[Square.A1] shouldBe Pawn.white

                    // Set null to clear a square
                    board[Square.A1] = null
                    board[Square.A1].shouldBeNull()

                    // Edge case: corner squares
                    board[Square.H8] = King.black
                    board[Square.H8] shouldBe King.black

                    board[Square.A8] = Rook.black
                    board[Square.A8] shouldBe Rook.black

                    board[Square.H1] = Rook.white
                    board[Square.H1] shouldBe Rook.white

                    // Middle squares
                    board[Square.D4] = Knight.white
                    board[Square.D4] shouldBe Knight.white

                    board[Square.E5] = Queen.black
                    board[Square.E5] shouldBe Queen.black
                }

                "column,row access should be equivalent to Square enum access" {
                    val board = Board()

                    // Set via column,row and get via Square
                    board[Column.D, Row.R4] = Bishop.white
                    board[Square.D4] shouldBe Bishop.white

                    // Set via Square and get via column,row
                    board[Square.E5] = Queen.black
                    board[Column.E, Row.R5] shouldBe Queen.black
                }
            }

            "reset function" - {
                "should clear all pieces from the board" {
                    val board = Board()

                    // Place some pieces
                    board[Square.A1] = Rook.white
                    board[Square.D4] = Knight.white
                    board[Square.H8] = King.black

                    // Reset the board
                    board.reset()

                    // All squares should be empty
                    board[Square.A1].shouldBeNull()
                    board[Square.D4].shouldBeNull()
                    board[Square.H8].shouldBeNull()

                    // Check that all squares are null after reset
                    Square.allSquares.forEach { square ->
                        board[square].shouldBeNull()
                    }
                }
            }

            "initialize function" - {
                "should set up a standard chess board position" {
                    val board = Board()
                    board.initialize()

                    // Check white pieces on first rank
                    board[Square.A1] shouldBe Rook.white
                    board[Square.B1] shouldBe Knight.white
                    board[Square.C1] shouldBe Bishop.white
                    board[Square.D1] shouldBe Queen.white
                    board[Square.E1] shouldBe King.white
                    board[Square.F1] shouldBe Bishop.white
                    board[Square.G1] shouldBe Knight.white
                    board[Square.H1] shouldBe Rook.white

                    // Check white pawns on second rank
                    Column.allColumns.forEach { col ->
                        board[col, Row.R2] shouldBe Pawn.white
                    }

                    // Check empty squares in the middle
                    for (row in listOf(Row.R3, Row.R4, Row.R5, Row.R6)) {
                        Column.allColumns.forEach { col ->
                            board[col, row].shouldBeNull()
                        }
                    }

                    // Check black pawns on seventh rank
                    Column.allColumns.forEach { col ->
                        board[col, Row.R7] shouldBe Pawn.black
                    }

                    // Check black pieces on eighth rank
                    board[Square.A8] shouldBe Rook.black
                    board[Square.B8] shouldBe Knight.black
                    board[Square.C8] shouldBe Bishop.black
                    board[Square.D8] shouldBe Queen.black
                    board[Square.E8] shouldBe King.black
                    board[Square.F8] shouldBe Bishop.black
                    board[Square.G8] shouldBe Knight.black
                    board[Square.H8] shouldBe Rook.black
                }
            }

            "iterator function" - {
                "should iterate through all board squares" {
                    val board = Board()

                    // Initially all squares should be null
                    val pieces = board.iterator().asSequence().toList()
                    pieces.size shouldBe 64 // 8x8 board
                    pieces.all { it == null } shouldBe true

                    // Place some pieces and check iterator
                    board[Square.A1] = Rook.white
                    board[Square.H8] = King.black

                    val updatedPieces = board.iterator().asSequence().toList()
                    updatedPieces.count { it != null } shouldBe 2

                    // Full board
                    board.initialize()
                    val fullBoardPieces = board.iterator().asSequence().toList()
                    fullBoardPieces.count { it != null } shouldBe 32 // 16 pieces per side
                }
            }

            "render function" - {
                "should correctly render an initialized board" {
                    val board = Board()
                    board.initialize()

                    val renderedBoard = board.render()
                    renderedBoard shouldBe
                        """
                             A     B     C     D     E     F     G     H
                          ┌─────┬─────┬─────┬─────┬─────┬─────┬─────┬─────┐
                        8 │  ♜  │  ♞  │  ♝  │  ♛  │  ♚  │  ♝  │  ♞  │  ♜  │ 8
                          ├─────┼─────┼─────┼─────┼─────┼─────┼─────┼─────┤
                        7 │  ♟  │  ♟  │  ♟  │  ♟  │  ♟  │  ♟  │  ♟  │  ♟  │ 7
                          ├─────┼─────┼─────┼─────┼─────┼─────┼─────┼─────┤
                        6 │     │     │     │     │     │     │     │     │ 6
                          ├─────┼─────┼─────┼─────┼─────┼─────┼─────┼─────┤
                        5 │     │     │     │     │     │     │     │     │ 5
                          ├─────┼─────┼─────┼─────┼─────┼─────┼─────┼─────┤
                        4 │     │     │     │     │     │     │     │     │ 4
                          ├─────┼─────┼─────┼─────┼─────┼─────┼─────┼─────┤
                        3 │     │     │     │     │     │     │     │     │ 3
                          ├─────┼─────┼─────┼─────┼─────┼─────┼─────┼─────┤
                        2 │  ♙  │  ♙  │  ♙  │  ♙  │  ♙  │  ♙  │  ♙  │  ♙  │ 2
                          ├─────┼─────┼─────┼─────┼─────┼─────┼─────┼─────┤
                        1 │  ♖  │  ♘  │  ♗  │  ♕  │  ♔  │  ♗  │  ♘  │  ♖  │ 1
                          └─────┴─────┴─────┴─────┴─────┴─────┴─────┴─────┘
                             A     B     C     D     E     F     G     H

                        """.trimIndent()
                }
            }
        }
    },
)
