package me.amol.core

class Board {
    private val squares = Array(8) { Array<Piece?>(8) { null } }

    operator fun get(
        column: Column,
        row: Row,
    ): Piece? = squares[row.index][column.index]

    operator fun set(
        column: Column,
        row: Row,
        piece: Piece?,
    ) {
        squares[row.index][column.index] = piece
    }

    operator fun get(square: Square): Piece? = squares[square.row.index][square.column.index]

    operator fun set(
        square: Square,
        piece: Piece?,
    ) {
        squares[square.row.index][square.column.index] = piece
    }

    operator fun iterator(): Iterator<Piece?> = squares.flatten().iterator()

    fun reset() = Square.allSquares.forEach { square -> this[square] = null }

    fun initialize() {
        val pieces = arrayOf("rook", "knight", "bishop", "queen", "king", "bishop", "knight", "rook")
        val emptyRows = listOf(Row.R3, Row.R4, Row.R5, Row.R6)
        Column.allColumns.forEach { col ->
            this[col, Row.R2] = Pawn.white
            this[col, Row.R1] = Piece.make(pieces[col.index], Side.WHITE)
            this[col, Row.R7] = Pawn.black
            emptyRows.forEach { row -> this[col, row] = null }
            this[col, Row.R8] = Piece.make(pieces[col.index], Side.BLACK)
        }
    }

    fun render(): String {
        val rows = Row.allRows.reversed()
        val last = rows.last()
        val columnLabels = Column.allColumns.joinToString(separator = "     ", prefix = "     ") { "${it.name}" }
        val topBorder = Column.allColumns.drop(1).joinToString(separator = "─────", prefix = "┌─────", "─────┐") { "┬" }
        val bottomBorder = Column.allColumns.drop(1).joinToString(separator = "─────", prefix = "└─────", "─────┘") { "┴" }
        val middleBorder = Column.allColumns.drop(1).joinToString(separator = "─────", prefix = "├─────", "─────┤") { "┼" }
        return buildString {
            appendLine(columnLabels)
            appendLine("  $topBorder")

            rows.forEach { row ->
                append("${row.notation} │")
                Column.allColumns.forEach { column -> append("  ${this@Board[column, row]?.symbol ?: " "}  │") }
                appendLine(" ${row.notation}")
                if (row != last) appendLine("  $middleBorder")
            }

            appendLine("  $bottomBorder")
            appendLine(columnLabels)
        }
    }

    override fun toString(): String = render()
}
