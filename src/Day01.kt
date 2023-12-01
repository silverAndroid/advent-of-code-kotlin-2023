fun main() {
    fun part1(input: List<String>): Int {
        return input.sumOf { line ->
            val firstDigit = line.find { it.isDigit() }!!
            val lastDigit = line.findLast { it.isDigit() }!!
            String(charArrayOf(firstDigit, lastDigit)).toInt()
        }
    }

    val numbersAsStrings =
        listOf(
            "zero",
            "one",
            "two",
            "three",
            "four",
            "five",
            "six",
            "seven",
            "eight",
            "nine",
            "0",
            "1",
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9",
        )

    fun part2(input: List<String>): Int {
        return input.sumOf { line ->
            val (_, firstDigitStr) = line.findAnyOf(numbersAsStrings)!!
            val firstDigit = if (firstDigitStr.length > 1) {
                numbersAsStrings.indexOf(firstDigitStr).toString()
            } else {
                firstDigitStr
            }

            val (_, lastDigitStr) = line.findLastAnyOf(numbersAsStrings)!!
            val lastDigit = if (lastDigitStr.length > 1) {
                numbersAsStrings.indexOf(lastDigitStr).toString()
            } else {
                lastDigitStr
            }

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
