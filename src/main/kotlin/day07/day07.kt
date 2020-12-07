package day07

import java.io.File

fun main(args: Array<String>) {
    val input = File(args.firstOrNull() ?: "input/day07.txt").readText()
    val bagRegulations = parseInput(input)

    val part1 = countBagsThatCanContainGoldenBag(bagRegulations)
    println("Part 1: $part1")

    val part2 = countRequiredBagsInsideGoldenBag(bagRegulations)
    println("Part 2: $part2")
}

private val innerBagPattern = """(\d+) (.*) bags?""".toRegex()

fun parseInput(input: String): Map<String, Map<String, Int>> = input.lines().associate { line ->
    val (outerBagColor, innerBags) = line.split(" bags contain ")

    val regulation = innerBags.dropLast(1).split(", ").mapNotNull { innerBag ->
        val (amount, color) = innerBagPattern.matchEntire(innerBag)?.destructured ?: return@mapNotNull null
        color to amount.toInt()
    }.toMap()

    outerBagColor to regulation
}

fun countBagsThatCanContainGoldenBag(bagRegulations: Map<String, Map<String, Int>>): Int {
    val cashedResults = mutableMapOf<String, Boolean>()

    fun canContainGoldBag(bagColor: String): Boolean = if (bagColor in cashedResults) {
        cashedResults[bagColor]!!
    } else {
        val containedBags = bagRegulations[bagColor]!!
        val result = "shiny gold" in containedBags || containedBags.any { (color, _) -> canContainGoldBag(color) }
        cashedResults[bagColor] = result
        result
    }

    return bagRegulations.count { (bagColor, _) -> canContainGoldBag(bagColor) }
}

fun countRequiredBagsInsideGoldenBag(bagRegulations: Map<String, Map<String, Int>>): Int {
    val cashedResults = mutableMapOf<String, Int>()

    fun containedBagCount(color: String): Int = if (color in cashedResults) {
        cashedResults[color]!!
    } else {
        val result = bagRegulations[color]!!.entries.fold(1) { acc, (color, count) ->
            acc + count * containedBagCount(color)
        }
        cashedResults[color] = result
        result
    }

    return containedBagCount("shiny gold") - 1
}