package day13

import java.io.File

fun main(args: Array<String>) {
    val input = File(args.firstOrNull() ?: "input/day13.txt").readText()
    val (startTime, busLines) = parseTimeAndBusLines(input)

    val (bus, time) = firstDepartingBusAndTime(busLines, startTime)
    val part1 = (time - startTime) * bus.number
    println("Part 1: $part1")

    val part2 = firstTimeOfSubsequentDepartures(busLines)
    println("Part 2: $part2")
}

class BusLine(val number: Int)

fun parseTimeAndBusLines(input: String): Pair<Int, List<BusLine?>> = input.lines().let { (first, second) ->
    first.toInt() to second.split(',').map(String::toIntOrNull).map { it?.let(::BusLine) }
}

fun firstDepartingBusAndTime(busLines: List<BusLine?>, startTime: Int): Pair<BusLine, Int> {
    generateSequence(startTime) { it + 1 }.forEach { time ->
        busLines.filterNotNull().forEach { busLine ->
            if (time % busLine.number == 0) return busLine to time
        }
    }
    error("No bus found.")
}

fun firstTimeOfSubsequentDepartures(busLines: List<BusLine?>): Long {
    val busOffsetPairs = busLines.mapIndexedNotNull { index, busLine ->
        if (busLine == null) null else busLine.number.toLong() to index
    }

    var time = 0L
    var step = 1L
    busOffsetPairs.forEach { (busLineNumber, offset) ->
        while ((time + offset) % busLineNumber != 0L) {
            time += step
        }
        step *= busLineNumber
    }

    return time
}