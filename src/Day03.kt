fun main() {

    fun updateSymbolMatrix(oldMatrix: Array<IntArray>, symbolLocation : Pair<Int, Int>) : Array<IntArray> {
        val lineLength = oldMatrix[0].size
        val newMatrix = oldMatrix.clone()
        val xStart = if (symbolLocation.first == 0) 0 else symbolLocation.first - 1
        val xEnd = if (symbolLocation.first == lineLength - 1) symbolLocation.first else symbolLocation.first + 1
        val yStart = if (symbolLocation.second == 0) 0 else symbolLocation.second - 1
        val yEnd = if (symbolLocation.second == oldMatrix.size) symbolLocation.second else symbolLocation.second + 1

        for (i in xStart..xEnd) {
            for (j in yStart..yEnd) {
                newMatrix[i][j] = 1
            }
        }
        return newMatrix
    }

    data class PartNr(val partNr: Int)

    fun buildPositionNumberMap(input: List<String>) : Map<Pair<Int,Int>, PartNr> {
        return buildMap {
            val numberRegex = """\d+""".toRegex()
            for (y in input.indices) {
                val matches = numberRegex.findAll(input[y])
                for (match in matches) {
                    for (x in match.range.start..match.range.endInclusive) {
                        put(Pair(x, y), PartNr(match.value.toInt()))
                    }
                }
            }
        }
    }


    fun part1(input: List<String>) : Int {
        var symbolMatrix = Array<IntArray>(input.size) { IntArray(input[0].length) }
        val symbolRegex = """[^\d.]""".toRegex()
        for (y: Int in input.indices) {
            val matches = symbolRegex.findAll(input[y])
            for (match in matches) {
                val x = match.range.start
                symbolMatrix = updateSymbolMatrix(symbolMatrix, Pair(x, y))
            }
        }

        val validNums = mutableListOf<Int>()
        val numberRegex = """\d+""".toRegex()
        for (y in input.indices) {
            val matches = numberRegex.findAll(input[y])
            for (match in matches) {
                for (x in match.range.start..match.range.endInclusive) {
                    if (symbolMatrix[x][y] == 1) {
                        validNums.add(match.value.toInt())
                        break
                    }
                }
            }
        }
        return validNums.sum()
    }

    fun part2(input: List<String>) : Int {
        val gearRatios = mutableListOf<Int>()
        val positionNumberMap = buildPositionNumberMap(input)
        val symbolRegex = """\*""".toRegex()
        for (y: Int in input.indices) {
            val matches = symbolRegex.findAll(input[y])
            for (match in matches) {
                val x = match.range.start
                val xRangeStart = if (x == 0) 0 else x - 1
                val xRangeEnd = if (x == input[y].length - 1) x else x + 1
                val yRangeStart = if (y == 0) 0 else y - 1
                val yRangeEnd = if (y == input.size - 1) y else y + 1
                val gearPartNrs = mutableSetOf<PartNr>()
                for (posY in yRangeStart..yRangeEnd) {
                    for (posX in xRangeStart..xRangeEnd) {
                        val partNr = positionNumberMap[Pair(posX, posY)]
                        if (partNr != null) {
                            gearPartNrs.add(partNr)
                        }
                    }
                }
                if (gearPartNrs.size == 2) {
                    val partNrs = gearPartNrs.toList().map { it.partNr }
                    gearRatios.add(partNrs[0] * partNrs[1])
                }
            }
        }
        return gearRatios.sum()
    }

    val testInput = readInput("Day03_test")
    check(part1(testInput) == 4361)
    check(part2(testInput) == 467835)

    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}

