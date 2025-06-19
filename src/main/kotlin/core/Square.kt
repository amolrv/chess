@file:Suppress("ktlint:standard:no-multi-spaces")

package me.amol.core

enum class Column(val notation: String) {
    A("A"),
    B("B"),
    C("C"),
    D("D"),
    E("E"),
    F("F"),
    G("G"),
    H("H"),
    ;

    val index = ordinal

    companion object {
        val allColumns: Array<Column> = entries.toTypedArray()

        fun fromNotation(notation: String): Column = valueOf(notation)
    }
}

enum class Row(val notation: Int) {
    R1(1),
    R2(2),
    R3(3),
    R4(4),
    R5(5),
    R6(6),
    R7(7),
    R8(8),
    ;

    val index = ordinal

    companion object {
        val allRows: Array<Row> = entries.toTypedArray()

        fun fromNotation(notation: Int): Row = valueOf("R$notation")
    }
}

enum class Square {
    A1, B1, C1, D1, E1, F1, G1, H1,
    A2, B2, C2, D2, E2, F2, G2, H2,
    A3, B3, C3, D3, E3, F3, G3, H3,
    A4, B4, C4, D4, E4, F4, G4, H4,
    A5, B5, C5, D5, E5, F5, G5, H5,
    A6, B6, C6, D6, E6, F6, G6, H6,
    A7, B7, C7, D7, E7, F7, G7, H7,
    A8, B8, C8, D8, E8, F8, G8, H8;

    fun move(
        columnOffset: Int,
        rowOffset: Int,
    ): Square? {
        val newColumnIndex = column.index + columnOffset
        val newRowIndex = row.index + rowOffset
        return when {
            newColumnIndex in 0..7 && newRowIndex in 0..7 -> allSquares[newRowIndex * 8 + newColumnIndex]
            else -> null
        }
    }

    val column: Column
        get() = Column.allColumns[ordinal % 8]

    val row: Row
        get() = Row.allRows[ordinal / 8]

    companion object {
        val allSquares: Array<Square> = entries.toTypedArray()

        fun fromNotation(notation: String): Square = valueOf(notation.uppercase())
    }
}
