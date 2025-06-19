package me.amol.core

data class Queen(override val side: Side) : Piece {
    override val symbol: String by lazy { if (side == Side.WHITE) "♕" else "♛" }

    companion object {
        val white = Queen(Side.WHITE)
        val black = Queen(Side.BLACK)
    }
}
