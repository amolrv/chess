package me.amol

fun main(args: Array<String>) {
    println("Args: " + args.joinToString(", "))

    if (args.size < 2) {
        println("Usage: Please provide piece type and position (e.g., 'Pawn G1')")
        return
    }

    val piece = args[0]
    val position = args[1]

    val possibleMoves = "G2" // calculatePossibleMoves(piece, position)
    println("Possible moves for $piece at $position: $possibleMoves")
}
