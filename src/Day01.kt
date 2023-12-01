fun main() {
    /** Part 1 */
    fun part1(input: List<String>): Int {
        return input.sumOf { line ->
            val firstDigit = line.find { it.isDigit() }!!
            val lastDigit = line.findLast { it.isDigit() }!!
            String(charArrayOf(firstDigit, lastDigit)).toInt()
        }
    }

    /** End Part 1 */

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 142) { "Check failed; expected: 142, actual: ${part1(testInput)}" }

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
