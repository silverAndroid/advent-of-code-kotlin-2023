fun main() {
    fun part1(input: List<String>): Int {
        return input.sumOf { line ->
            val firstDigit = calculateDigit(line, isLastDigit = false, includeLetters = false)
            val lastDigit = calculateDigit(line, isLastDigit = true, includeLetters = false)

            (firstDigit + lastDigit).toInt()
        }
    }

    fun part2(input: List<String>): Int {
        return input.sumOf { line ->
            val firstDigit = calculateDigit(line, isLastDigit = false, includeLetters = true)
            val lastDigit = calculateDigit(line, isLastDigit = true, includeLetters = true)

            (firstDigit + lastDigit).toInt()
        }
    }

    // test if implementation meets criteria from the description, like:
    val partOneTestInput = readInput("Day01_part1_test")
    check(part1(partOneTestInput) == 142) {
        "Check failed; expected: 142, actual: ${
            part1(
                partOneTestInput
            )
        }"
    }
    val partTwoTestInput = readInput("Day01_part2_test")
    check(part2(partTwoTestInput) == 281) {
        "Check failed; expected: 281, actual: ${
            part2(
                partTwoTestInput
            )
        }"
    }

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
