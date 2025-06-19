package me.amol.core

sealed interface Piece {
    val side: Side
    val symbol: String

    fun passibleMoves(
        board: Board,
        square: Square,
    ): List<Square>

    companion object {
        private val allPiece: Map<String, Array<Piece>> =
            buildMap {
                put("PAWN", arrayOf(Pawn.white, Pawn.black))
                put("KNIGHT", arrayOf(Knight.white, Knight.black))
                put("BISHOP", arrayOf(Bishop.white, Bishop.black))
                put("ROOK", arrayOf(Rook.white, Rook.black))
                put("QUEEN", arrayOf(Queen.white, Queen.black))
                put("KING", arrayOf(King.white, King.black))
            }

        fun make(
            name: String,
            side: Side,
        ): Piece =
            name.uppercase().let { allPiece[it] }?.get(side.ordinal)
                ?: throw IllegalArgumentException("No piece found with name $name and side $side")
    }
}
