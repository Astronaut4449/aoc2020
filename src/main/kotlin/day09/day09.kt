package day09

import java.io.File
import java.util.*

fun main(args: Array<String>) {
    val transmission = File(args.firstOrNull() ?: "input/day09.txt").readLines().map(String::toLong)

    val part1 = findInvalidNumber(transmission)
    println("Part 1: $part1")

    val window = findSumWindow(transmission, part1)
    val part2 = window.minOrNull()!! + window.maxOrNull()!!
    println("Part 2: $part2")
}

fun findInvalidNumber(numbers: List<Long>, preamble: Int = 25): Long {
    val cashedSums = LinkedList<Set<Long>>()

    // Fill preamble sums
    for (i in 0..(preamble - 2)) {
        val nextPreambleItemsSums = ((i + 1) until preamble).map { k -> numbers[i] + numbers[k] }.toSet()
        cashedSums.add(nextPreambleItemsSums)
    }

    // Find number
    for (i in preamble..numbers.lastIndex) {
        val isItemPresentInSums = cashedSums.none { numbers[i] in it }

        if (isItemPresentInSums) {
            return numbers[i]
        } else {
            cashedSums.removeFirst()
            val nextItemsSums = ((i - preamble + 1) until i).map { k -> numbers[i] + numbers[k] }.toSet()
            cashedSums.add(nextItemsSums)
        }
    }

    error("No number found.")
}

fun findSumWindow(numbers: List<Long>, invalidNumber: Long): List<Long> {
    var windowStart = 0
    var windowEndInclusive = 0
    var sum = numbers[0]

    while (sum != invalidNumber) {
        when {
            sum < invalidNumber -> {
                windowEndInclusive += 1
                sum += numbers[windowEndInclusive]
            }

            sum > invalidNumber -> {
                sum -= numbers[windowStart]
                windowStart += 1
            }
        }
    }

    return numbers.subList(windowStart, windowEndInclusive + 1)
}