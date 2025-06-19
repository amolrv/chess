package me.amol.core

@ConsistentCopyVisibility
data class Pawn private constructor(override val side: Side) : Piece {
    override val symbol: String by lazy { if (side == Side.WHITE) "♙" else "♟" }

    override fun passibleMoves(
        board: Board,
        square: Square,
    ): List<Square> =
        buildList {
            val direction = if (side == Side.WHITE) 1 else -1
            val startRow = if (side == Side.WHITE) Row.R2 else Row.R7

            val forwardSquare = square.move(0, direction)
            if (forwardSquare != null && board[forwardSquare] == null) {
                add(forwardSquare)
                val doubleForwardSquare = square.move(0, direction * 2)
                if (square.row == startRow && doubleForwardSquare != null && board[doubleForwardSquare] == null) {
                    add(doubleForwardSquare)
                }
            }
            captureDirections.forEach { colOffset ->
                val captureSquare = square.move(colOffset, direction)
                captureSquare?.let {
                    val pieceAtCapture = board[it]
                    if (pieceAtCapture != null && pieceAtCapture.side != side) {
                        add(it)
                    }
                }
            }
        }

    companion object {
        val white = Pawn(Side.WHITE)
        val black = Pawn(Side.BLACK)

        private val captureDirections: List<Int> = listOf(-1, 1)
    }
}
