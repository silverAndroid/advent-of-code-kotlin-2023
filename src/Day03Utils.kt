fun findAllSymbols(input: List<String>): List<Coordinate> {
    val symbols = mutableListOf<Coordinate>()

    input.forEachIndexed RowIndex@{ row, line ->
        line.forEachIndexed ColumnIndex@{ column, c ->
            if (c.isDigit() || c == '.') return@ColumnIndex

            symbols.add(Coordinate(row, column))
        }
    }

    return symbols
}

fun buildNumber(
    input: List<String>,
    visited: MutableSet<Coordinate>,
    coordinate: Coordinate,
): String? {
    if (!input[coordinate.row][coordinate.column].isDigit()) {
        return null
    }

    val maxColumn = input[0].length
    var number = "${input[coordinate.row][coordinate.column]}"
    visited.add(coordinate)

    var column = coordinate.column - 1
    while (column >= 0 && input[coordinate.row][column].isDigit()) {
        number = input[coordinate.row][column] + number
        visited.add(coordinate.copy(column = column))
        column -= 1
    }

    column = coordinate.column + 1
    while (column < maxColumn && input[coordinate.row][column].isDigit()) {
        number += input[coordinate.row][column]
        visited.add(coordinate.copy(column = column))
        column += 1
    }

    return number
}

data class Coordinate(val row: Int, val column: Int)