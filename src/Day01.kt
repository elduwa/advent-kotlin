fun main() {
    fun part1(input: List<String>): Int {
        val calibrations = mutableListOf<Int>()
        for (line : String in input) {
            val firstDigit = line.find { it.isDigit() }.toString()
            val lastDigit = line.findLast { it.isDigit() }.toString()
            val calibration = firstDigit.plus(lastDigit).toInt()
            calibrations.add(calibration)
        }
        return calibrations.sum()
    }


    fun part2(input: List<String>): Int {
        val numbers = hashMapOf("one" to "1", "two" to "2", "three" to "3", "four" to "4", "five" to "5",
        "six" to "6", "seven" to "7", "eight" to "8", "nine" to "9")
        val calibrations = mutableListOf<Int>()
        for (line : String in input) {
            var cal = ""
            outer@ for (i in line.indices) {
                if (line[i].isDigit()) {
                    cal = cal.plus(line[i])
                    break@outer
                }
                for (it in numbers) {
                    if (i + it.key.length <= line.length && line.substring(i, i + it.key.length) == it.key) {
                        cal = cal.plus(it.value)
                        break@outer
                    }
                }
            }

            outer@ for (i in line.length - 1 downTo 0) {
                if (line[i].isDigit()) {
                    cal = cal.plus(line[i])
                    break@outer
                }
                for (it in numbers) {
                    if (i + it.key.length <= line.length && line.substring(i, i + it.key.length) == it.key) {
                        cal = cal.plus(it.value)
                        break@outer
                    }
                }
            }
            calibrations.add(cal.toInt())
        }
        return calibrations.sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part2(testInput) == 281)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
