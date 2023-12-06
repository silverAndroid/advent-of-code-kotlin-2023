fun main() {
    fun listOfCubes(game: String): List<Pair<String, Int>> {
        return game.split(", ", "; ").map {
            val cubeSplit = it.split(" ")
            val number = cubeSplit.first().toInt()
            val colour = cubeSplit.last()

            colour to number
        }
    }

    fun part1(input: List<String>): Int {
        val maxes = mapOf("red" to 12, "green" to 13, "blue" to 14)

        return input.sumOf { line ->
            val gameSplit = line.split(": ")

            val hasExceededMaxes = listOfCubes(gameSplit.last()).any { (colour, number) ->
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
        return input.sumOf { line ->
            val game = line.split(": ").last()

            listOfCubes(game).groupBy({ it.first }) { it.second }.mapValues { it.value.max() }
                .values.multiply()
        }
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
    val partTwoTestInput = readInput("Day02_part2_test")
    check(part2(partTwoTestInput) == 2286) {
        "Check failed; expected: 2286, actual: ${
            part2(partTwoTestInput)
        }"
    }

    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}