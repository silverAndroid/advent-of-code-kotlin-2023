fun calculateDigit(line: String, isLastDigit: Boolean, includeLetters: Boolean): String {
    if (includeLetters) {
        return calculateDigitWithLetters(line, isLastDigit)
    }

    return if (isLastDigit) {
        line.findLast { it.isDigit() }
    } else {
        line.find { it.isDigit() }
    }.toString()
}

private fun calculateDigitWithLetters(line: String, isLastDigit: Boolean): String {
    val (_, digitStr) = if (isLastDigit) {
        line.findLastAnyOf(numbersAsStrings)
    } else {
        line.findAnyOf(numbersAsStrings)
    }!!

    return if (digitStr.length > 1) {
        numbersAsStrings.indexOf(digitStr).toString()
    } else {
        digitStr
    }
}

private val numbersAsStrings =
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