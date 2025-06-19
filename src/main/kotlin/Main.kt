package me.amol

import me.amol.core.Board
import me.amol.core.Piece
import me.amol.core.Side
import me.amol.core.Square

fun main(args: Array<String>) {
    println("Args: " + args.joinToString(", "))

    if (args.size < 2) {
        println("Usage: Please provide piece type and position (e.g., 'Pawn G1')")
        return
    }
    val defaultSide = Side.WHITE
    val piece = args[0].let { Piece.make(it, defaultSide) }
    val position = args[1].let { Square.fromNotation(it) }
    val board = Board()
    val possibleMoves = piece.passibleMoves(board, position)
        println("Possible moves for $piece at $position: $possibleMoves")
}
