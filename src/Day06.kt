fun main() {
    fun calculateDistance(speed: Long, totalTime: Long): Long {
        if (speed == 0L || speed == totalTime) {
            return 0
        }

        // speed is equal to time taken to hold down button so subtract speed from total time
        return (totalTime - speed) * speed
    }

    fun calculateDistance(speed: Int, totalTime: Int) =
        calculateDistance(speed.toLong(), totalTime.toLong()).toInt()

    fun part1(input: List<String>): Int {
        val racesList = input.map { line -> line.split(Regex("\\s+")).drop(1).map { it.toInt() } }
        val races = List(racesList.first().size) { index ->
            // time to distance pair
            racesList.first()[index] to racesList.last()[index]
        }
        return races.mapIndexed { index, (time, _) ->
            val distances = (0..time).map { calculateDistance(it, time) }
            distances.count { distanceTravelled -> distanceTravelled > races[index].second }
        }.multiply()
    }

    fun part2(input: List<String>): Int {
        val (time, totalDistance) = input.map { line ->
            line.split(Regex("\\s+")).drop(1).reduce { acc, string -> acc + string }.toLong()
        }.let {
            it.first() to it.last()
        }

        val distancesToCount = mutableListOf<Long>()
        var currentSpeed = 0L

        var hasReachedMinimumDistance: Boolean
        do {
            hasReachedMinimumDistance = calculateDistance(currentSpeed++, time) > totalDistance
            if (hasReachedMinimumDistance) {
                currentSpeed -= 1
            }
        } while (!hasReachedMinimumDistance)

        do {
            val distance = calculateDistance(currentSpeed++, time)
            hasReachedMinimumDistance = distance > totalDistance
            if (hasReachedMinimumDistance) {
                distancesToCount.add(distance)
            }
        } while (hasReachedMinimumDistance)

        return distancesToCount.count()
    }

    // test if implementation meets criteria from the description, like:
    val partOneTestInput = readInput("Day06_part1_test")
    val partOneExpected = 288
    check(part1(partOneTestInput) == partOneExpected) {
        "Check failed; expected: $partOneExpected, actual: ${
            part1(
                partOneTestInput
            )
        }"
    }
    val partTwoTestInput = readInput("Day06_part2_test")
    val partTwoExpected = 71503
    check(part2(partTwoTestInput) == partTwoExpected) {
        "Check failed; expected: $partTwoExpected, actual: ${
            part2(partTwoTestInput)
        }"
    }

    val input = readInput("Day06")
    part1(input).println()
    part2(input).println()
}