package me.amol.core

@ConsistentCopyVisibility
data class Knight private constructor(override val side: Side) : Piece {
    override val symbol: String by lazy { if (side == Side.WHITE) "♘" else "♞" }

    override fun passibleMoves(
        board: Board,
        square: Square,
    ): List<Square> {
        TODO("Not yet implemented")
    }

    companion object {
        val white = Knight(Side.WHITE)
        val black = Knight(Side.BLACK)
    }
}
