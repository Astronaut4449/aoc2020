package day07

import java.io.File

fun main(args: Array<String>) {
    val input = File(args.firstOrNull() ?: "input/day07.txt").readText()
    val bagRegulations = parseInput(input)

    val part1 = countBagsThatCanContainGoldenBag(bagRegulations)
    println("Part 1: $part1")

    val part2 = countBagsInsideGoldenBag(bagRegulations)
    println("Part 2: $part2")
}

private typealias BagType = String
private typealias BagRegulations = Map<BagType, Map<BagType, Int>>

private val innerBagPattern = """(\d+) (.*) bags?""".toRegex()

fun parseInput(input: String): BagRegulations = input.lines().associate { line ->
    val (outerBagType, innerBagsPart) = line.split(" bags contain ")

    val regulations = innerBagsPart.dropLast(1).split(", ").mapNotNull { innerBagPart ->
        innerBagPattern.matchEntire(innerBagPart)?.destructured?.let { (amount, type) -> type to amount.toInt() }
    }.toMap()

    outerBagType to regulations
}

fun countBagsThatCanContainGoldenBag(bagRegulations: BagRegulations): Int {
    val cashedResults = mutableMapOf<BagType, Boolean>()

    fun containsGoldenBag(bagType: BagType): Boolean = cashedResults.getOrPut(bagType) {
        bagRegulations[bagType]!!.keys.let { containedBags ->
            "shiny gold" in containedBags || containedBags.any(::containsGoldenBag)
        }
    }

    return bagRegulations.count { (bagType, _) -> containsGoldenBag(bagType) }
}

fun countBagsInsideGoldenBag(bagRegulations: BagRegulations): Int {
    val cashedResults = mutableMapOf<BagType, Int>()

    fun bagTotal(bagType: BagType): Int = cashedResults.getOrPut(bagType) {
        bagRegulations[bagType]!!.entries.fold(1) { total, (containedBag, containedAmount) ->
            total + containedAmount * bagTotal(containedBag)
        }
    }

    return bagTotal("shiny gold") - 1
}