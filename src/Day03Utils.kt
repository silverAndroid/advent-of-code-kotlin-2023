fun findAllSymbols(input: List<String>): List<SymbolCoordinate> {
    val symbols = mutableListOf<SymbolCoordinate>()

    input.forEachIndexed RowIndex@{ row, line ->
        line.forEachIndexed ColumnIndex@{ column, c ->
            if (c.isDigit() || c == '.') return@ColumnIndex

            symbols.add(SymbolCoordinate(symbol = c, coordinate = Coordinate(row, column)))
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

fun getAdjacentNumbers(
    input: List<String>,
    visited: MutableSet<Coordinate>,
    coordinate: Coordinate,
): List<Int> {
    val surroundingCells = getSurroundingCells(coordinate)
    val cellsWithDigits = surroundingCells.filter { (row, column) -> input[row][column].isDigit() }
    return cellsWithDigits.mapNotNull {
        if (visited.contains(it)) {
            return@mapNotNull null
        }

        buildNumber(input, visited, coordinate = it)?.toInt()
    }
}

fun getSurroundingCells(coordinate: Coordinate): List<Coordinate> {
    val (row, column) = coordinate
    return List(8) {
        if (it < 3) {
            Coordinate(row - 1, column = it + column - 1)
        } else if (it in 5..7) {
            Coordinate(row + 1, column = it % 3 + column - 1)
        } else if (it == 3) {
            Coordinate(row, column - 1)
        } else if (it == 4) {
            Coordinate(row, it % 3 + column)
        } else {
            throw Exception("Unknown coordinate $row $column $it")
        }
    }
}

data class Coordinate(val row: Int, val column: Int)

data class SymbolCoordinate(val symbol: Char, val coordinate: Coordinate)