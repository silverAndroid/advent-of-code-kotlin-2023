import kotlin.math.pow

fun main() {
    fun getNumbers(numbersList: String): List<Int> {
        return numbersList.split(Regex("\\s+")).map { it.toInt() }
    }

    fun part1(input: List<String>): Int {
        return input.sumOf { line ->
            val card = line.split(Regex("\\s*[:|]\\s*")).drop(1)
            val winningNumbers = getNumbers(card.first()).toSet()
            val lotteryNumbers = getNumbers(card.last())

            val winningLotteryNumbers = lotteryNumbers.filter { winningNumbers.contains(it) }
            if (winningLotteryNumbers.isEmpty()) {
                0
            } else {
                2.0.pow(winningLotteryNumbers.size - 1).toInt()
            }
        }
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val partOneTestInput = readInput("Day04_part1_test")
    val partOneExpected = 13
    check(part1(partOneTestInput) == partOneExpected) {
        "Check failed; expected: $partOneExpected, actual: ${
            part1(
                partOneTestInput
            )
        }"
    }
//    val partTwoTestInput = readInput("Day04_part2_test")
//    val partTwoExpected = 4361
//    check(part2(partTwoTestInput) == partTwoExpected) {
//        "Check failed; expected: $partTwoExpected, actual: ${
//            part2(partTwoTestInput)
//        }"
//    }

    val input = readInput("Day04")
    part1(input).println()
    part2(input).println()
}