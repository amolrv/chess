package me.amol.core

@ConsistentCopyVisibility
data class King private constructor(override val side: Side) : Piece {
    override val symbol: String by lazy { if (side == Side.WHITE) "♔" else "♚" }

    companion object {
        val white = King(Side.WHITE)
        val black = King(Side.BLACK)
    }
}
