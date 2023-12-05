fun main() {
    fun part1(input: List<String>): Int {
        val maxRow = input.size
        val maxColumn = input[0].length

        val symbols = findAllSymbols(input)
        val visitedCells = mutableSetOf(*(symbols.map { it.coordinate }.toTypedArray()))
        val partNumbers = mutableListOf<Int>()

        symbols.forEach { (_, coordinate) ->
            val cellsToVisit =
                getSurroundingCells(coordinate).filterNot { (row, column) -> row > maxRow || column > maxColumn }

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
        val zero = 0

        val symbols = findAllSymbols(input).filter { it.symbol == '*' }
        val visited = mutableSetOf<Coordinate>()

        return symbols.sumOf { (_, coordinate) ->
            val adjacentNumbers = getAdjacentNumbers(input, visited, coordinate)
            if (adjacentNumbers.size < 2) {
                return@sumOf zero
            }

            adjacentNumbers.fold(1) { acc, num -> acc * num }
        }
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
    val partTwoTestInput = readInput("Day03_part2_test")
    check(part2(partTwoTestInput) == 467835) {
        "Check failed; expected: 467835, actual: ${
            part2(partTwoTestInput)
        }"
    }

    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}