package day05

import java.io.File

fun main(args: Array<String>) {
    val seats = File(args.firstOrNull() ?: "input/day05.txt").readLines().map { seat(it) }.toSet()

    val part1 = seats.maxOf { it.id }
    println("Part 1: $part1")

    val seatsById = seats.associateBy { it.id }
    val seatWithGapNextToIt = seats.first { seatsById[it.id + 1] == null && seatsById[it.id + 2] != null }
    val part2 = seatWithGapNextToIt.id + 1
    println("Part 2: $part2")
}

data class Seat(val row: Int, val column: Int) {
    val id = row * 8 + column
}

fun seat(code: String) = Seat(seatRow(code.substring(0..6)), seatColumn(code.substring(7..9)))

fun seatRow(code: String): Int = bisect(code.map { it == 'F' }, 128)

fun seatColumn(code: String): Int = bisect(code.map { it == 'L' }, 8)

private fun bisect(code: List<Boolean>, endExclusive: Int): Int {
    var low = 0
    var high = endExclusive

    for (isLowerHalf in code) {
        if (isLowerHalf) high -= (high - low) / 2
        else low += (high - low) / 2
    }

    return low
}