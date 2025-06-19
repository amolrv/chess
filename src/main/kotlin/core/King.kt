package me.amol.core

@ConsistentCopyVisibility
data class King private constructor(override val side: Side) : Piece {
    override val symbol: String by lazy { if (side == Side.WHITE) "♔" else "♚" }

    override fun passibleMoves(
        board: Board,
        square: Square,
    ): List<Square> =
        directions.mapNotNull { (colOffset, rowOffset) ->
            val nextSquare = square.move(colOffset, rowOffset) ?: return@mapNotNull null
            val pieceAtNextSquare = board[nextSquare]
            nextSquare.takeIf { pieceAtNextSquare == null || pieceAtNextSquare.side != side }
        }

    companion object {
        val white = King(Side.WHITE)
        val black = King(Side.BLACK)

        private val directions: List<Pair<Int, Int>> =
            listOf(
                Pair(0, 1),
                Pair(0, -1),
                Pair(1, 0),
                Pair(-1, 0),
                Pair(1, 1),
                Pair(1, -1),
                Pair(-1, 1),
                Pair(-1, -1),
            )
    }
}
