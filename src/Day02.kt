fun main() {

    fun parseColorCounts(line: String) : Map<RGB,MutableList<Int>> {
        val colorCounts = hashMapOf<RGB, MutableList<Int>>(RGB.RED to mutableListOf<Int>(), RGB.GREEN to mutableListOf<Int>(), RGB.BLUE to mutableListOf<Int>())
        val formattedLine = line.substring(line.indexOf(":") + 2).replace(",", "").replace(";", "")
        val measurements = formattedLine.split(" ")
        for (i in measurements.indices step 2) {
            colorCounts[RGB.valueOf(measurements[i + 1].uppercase())]?.add(measurements[i].toInt())
        }
        return colorCounts
    }


    fun part1(input: List<String>) : Int {
        val possibleGameIds = mutableListOf<Int>()
        for (i : Int in input.indices) {
            val gameId = i + 1
            val colorCounts = parseColorCounts(input[i])
            var isPossible = true
            if (colorCounts[RGB.RED]?.maxOrNull()!! > 12) isPossible = false
            if (colorCounts[RGB.GREEN]?.maxOrNull()!! > 13) isPossible = false
            if (colorCounts[RGB.BLUE]?.maxOrNull()!! > 14) isPossible = false
            if (isPossible) possibleGameIds.add(gameId)
        }
        return possibleGameIds.sum()
    }

    fun part2(input: List<String>) : Int {
        var power = 0
        for (i: Int in input.indices) {
            val colorCounts = parseColorCounts(input[i])
            val minRed = colorCounts[RGB.RED]?.maxOrNull() ?: 0
            val minGreen = colorCounts[RGB.GREEN]?.maxOrNull() ?: 0
            val minBlue = colorCounts[RGB.BLUE]?.maxOrNull() ?: 0
            power += (minRed * minGreen).times(minBlue)
        }
        return power
    }

    val testInput = readInput("Day02_test")
    check(part1(testInput) == 8)
    check(part2(testInput) == 2286)

    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}

enum class RGB {
    RED,
    GREEN,
    BLUE
}
