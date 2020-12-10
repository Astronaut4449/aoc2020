package day10

import java.io.File

typealias Difference = Int
typealias Count = Int

fun main(args: Array<String>) {
    val input = File(args.firstOrNull() ?: "input/day10.txt").readText()
    val adapters = parseInput(input)

    val differences = differenceCounts(adapters)
    val part1 = differences[1]!! * differences[3]!!
    println("Part 1: $part1")

    val part2 = countArrangements(adapters)
    println("Part 2: $part2")
}

fun parseInput(input: String): List<Int> = input.lines()
        .map(String::toInt)
        .sorted()
        .let { sortedList -> listOf(0) + sortedList + (sortedList.last() + 3) }

fun differenceCounts(adapters: List<Int>): Map<Difference, Count> = adapters
        .zipWithNext()
        .map { (a, b) -> b - a }
        .groupingBy { it }
        .eachCount()

fun countArrangements(adapters: List<Int>): Long {
    val nextNodesForNode = adapters.associateWith { node ->
        adapters.filter { it in (node+1)..(node+3) }
    }

    var arrangementCount = 1L
    var nextNodes = mutableListOf(adapters[0])
    do {
        val currNode = nextNodes.minOrNull()!!
        nextNodes.remove(currNode)
        nextNodes.addAll(nextNodesForNode[currNode]!!)
        if (nextNodes.size > 1 && nextNodes.toSet().size == 1) {
            arrangementCount *= nextNodes.size
            nextNodes = mutableListOf(nextNodes.first())
        }
    } while (nextNodesForNode[currNode]!!.isNotEmpty())

    return arrangementCount
}