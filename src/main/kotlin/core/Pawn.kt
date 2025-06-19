package me.amol.core

@ConsistentCopyVisibility
data class Pawn private constructor(override val side: Side) : Piece {
    override val symbol: String by lazy { if (side == Side.WHITE) "♙" else "♟" }

    companion object {
        val white = Pawn(Side.WHITE)
        val black = Pawn(Side.BLACK)
    }
}
