package day05

import java.io.File

fun main(args: Array<String>) {
    val seats = File(args.firstOrNull() ?: "input/day05.txt").readLines().map { seat(it) }

    val part1 = seats.maxOf { it.id }
    println("Part 1: $part1")

    val seatsById = seats.associateBy { it.id }
    val seatWithIdGapNextToIt = seats.first { seatsById[it.id + 1] == null && seatsById[it.id + 2] != null }
    val part2 = seatWithIdGapNextToIt.id + 1
    println("Part 2: $part2")
}

class Seat(val row: Int, val column: Int) {
    val id = row * 8 + column
}

fun seat(binarySeatCode: String) = Seat(
        row = binarySeatCode.substring(0..6).parseSeatToInt(),
        column = binarySeatCode.substring(7..9).parseSeatToInt()
)

private fun String.parseSeatToInt(): Int = this
        .map { if (it == 'B' || it == 'R') '1' else '0' }
        .joinToString("")
        .toInt(2)