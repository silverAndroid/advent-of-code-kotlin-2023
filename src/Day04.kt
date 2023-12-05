import kotlin.math.pow

fun main() {
    fun getNumbers(numbersList: String): List<Int> {
        return numbersList.split(Regex("\\s+")).map { it.toInt() }
    }

    fun getWinningLotteryNumbers(line: String): List<Int> {
        val card = line.split(Regex("\\s*[:|]\\s*")).drop(1)
        val winningNumbers = getNumbers(card.first()).toSet()
        val lotteryNumbers = getNumbers(card.last())

        return lotteryNumbers.filter { winningNumbers.contains(it) }
    }

    fun part1(input: List<String>): Int {
        return input.sumOf { line ->
            val winningLotteryNumbers = getWinningLotteryNumbers(line)
            if (winningLotteryNumbers.isEmpty()) {
                0
            } else {
                2.0.pow(winningLotteryNumbers.size - 1).toInt()
            }
        }
    }

    fun part2(input: List<String>): Int {
        var cards = List(input.size - 1) { 1 }
        val scores = mutableListOf(1)
        var numCards = 1

        input.forEach { line ->
            val winningLotteryNumbers = getWinningLotteryNumbers(line)
            cards = cards.take(winningLotteryNumbers.size).map { it + numCards }
                .run { this + cards.drop(winningLotteryNumbers.size) }
            numCards = cards.firstOrNull() ?: return@forEach
            scores.add(numCards)
            cards = cards.drop(1)
        }

        return scores.sum()
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
    val partTwoTestInput = readInput("Day04_part2_test")
    val partTwoExpected = 30
    check(part2(partTwoTestInput) == partTwoExpected) {
        "Check failed; expected: $partTwoExpected, actual: ${
            part2(partTwoTestInput)
        }"
    }

    val input = readInput("Day04")
    part1(input).println()
    part2(input).println()
}