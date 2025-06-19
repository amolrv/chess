package me.amol.core

data class Rook(override val side: Side) : Piece {
    override val symbol: String by lazy { if (side == Side.WHITE) "♖" else "♜" }

    override fun passibleMoves(
        board: Board,
        square: Square,
    ): List<Square> {
        TODO("Not yet implemented")
    }

    companion object {
        val white = Rook(Side.WHITE)
        val black = Rook(Side.BLACK)
    }
}
