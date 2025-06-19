package me.amol.core

@ConsistentCopyVisibility
data class Bishop private constructor(override val side: Side) : Piece {
    override val symbol: String = if (side == Side.WHITE) "♗" else "♝"

    companion object {
        val white = Bishop(Side.WHITE)
        val black = Bishop(Side.BLACK)
    }
}
