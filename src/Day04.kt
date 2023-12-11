import java.lang.Math.pow

fun main() {

    data class ScratchCard(val winningNumbers: List<Int>, val chosenNumbers: List<Int>)

    fun String.toScratchCard() : ScratchCard {
        val winningString = substringAfter(":").substringBefore("|").trim()
        val chosenString = substringAfter("|").trim()
        val winningNumbers = winningString.split("""\s+""".toRegex()).map { it.toInt() }
        val chosenNumbers = chosenString.split("""\s+""".toRegex()).map { it.toInt() }
        return ScratchCard(winningNumbers, chosenNumbers)
    }

    fun getCorrectPicksByCard(scratchCards: List<ScratchCard>): MutableList<Int> {
        val correctPicksByCard = mutableListOf<Int>()
        scratchCards.forEach {
            val intersect = it.winningNumbers.toSet().intersect(it.chosenNumbers.toSet())
            correctPicksByCard.add(intersect.size)
        }
        return correctPicksByCard
    }

    fun part1(input: List<String>) : Int {
        val scratchCards = input.map { it.toScratchCard() }
        val correctPicksByCard = getCorrectPicksByCard(scratchCards)
        val pointsByCard = correctPicksByCard.map { if (it == 0) 0 else pow(2.0, it.toFloat() - 1.0).toInt() }
        return pointsByCard.sum()
    }

    fun part2(input: List<String>) : Int {
        val scratchCards = input.map { it.toScratchCard() }
        val correctPicksByCard = getCorrectPicksByCard(scratchCards)
        var instances = Array(correctPicksByCard.size) { 1 }
        for (i in correctPicksByCard.indices) {
            var to = i + correctPicksByCard[i]
            if(to >= correctPicksByCard.size) to = correctPicksByCard.size

        }
        return 0
    }

    val testInput = readInput("Day04_test")
    check(part1(testInput) == 13)
    // check(part2(testInput) == 467835)

    val input = readInput("Day04")
    part1(input).println()
    part2(input).println()
}

