package me.amol.core

data class Queen(override val side: Side) : Piece {
    override val symbol: String by lazy { if (side == Side.WHITE) "♕" else "♛" }

    override fun passibleMoves(
        board: Board,
        square: Square,
    ): List<Square> =
        buildList {
            directions.forEach { (colOffset, rowOffset) ->
                var currentSquare = square
                while (true) {
                    val nextSquare = currentSquare.move(colOffset, rowOffset) ?: break
                    val pieceAtNext = board[nextSquare]
                    when {
                        pieceAtNext == null -> {
                            add(nextSquare)
                            currentSquare = nextSquare
                            continue
                        }

                        pieceAtNext.side != side -> {
                            add(nextSquare)
                            break
                        }

                        else -> {
                            break
                        }
                    }
                }
            }
        }

    companion object {
        val white = Queen(Side.WHITE)
        val black = Queen(Side.BLACK)

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
