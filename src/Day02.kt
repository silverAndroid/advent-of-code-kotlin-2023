fun main() {
    fun part1(input: List<String>): Int {
        val maxes = mapOf("red" to 12, "green" to 13, "blue" to 14)

        return input.sumOf { line ->
            val gameSplit = line.split(": ")

            val hasExceededMaxes = gameSplit.last().split(", ", "; ").any {
                val cubeSplit = it.split(" ")
                val number = cubeSplit.first().toInt()
                val colour = cubeSplit.last()

                number > maxes.getValue(colour)
            }

            if (hasExceededMaxes) {
                0
            } else {
                val gameNumber = gameSplit.first().split(" ").last().toInt()
                gameNumber
            }
        }
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val partOneTestInput = readInput("Day02_part1_test")
    check(part1(partOneTestInput) == 8) {
        "Check failed; expected: 8, actual: ${
            part1(
                partOneTestInput
            )
        }"
    }

    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}