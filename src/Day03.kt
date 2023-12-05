fun main() {
    fun part1(input: List<String>): Int {
        val maxRow = input.size
        val maxColumn = input[0].length

        val symbols = findAllSymbols(input)
        val visitedCells = mutableSetOf(*(symbols.toTypedArray()))
        val partNumbers = mutableListOf<Int>()

        symbols.forEach { (row, column) ->
            val cellsToVisit = List(8) {
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
            }.filterNot { (row, column) -> row > maxRow || column > maxColumn }

            cellsToVisit.forEach visitCells@{
                if (visitedCells.contains(it)) {
                    return@visitCells
                }

                val number = buildNumber(input, visitedCells, it) ?: return@visitCells
                partNumbers.add(number.toInt())
            }
        }

        return partNumbers.sum()
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val partOneTestInput = readInput("Day03_part1_test")
    check(part1(partOneTestInput) == 4361) {
        "Check failed; expected: 4361, actual: ${
            part1(
                partOneTestInput
            )
        }"
    }
//    val partTwoTestInput = readInput("Day03_part2_test")
//    check(part2(partTwoTestInput) == 2286) {
//        "Check failed; expected: 2286, actual: ${
//            part2(partTwoTestInput)
//        }"
//    }

    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}