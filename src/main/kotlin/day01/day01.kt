package day01

import java.io.File

fun main(args: Array<String>) {
    val numbers = File(args.firstOrNull() ?: "input/day01.txt")
            .readLines()
            .map { it.toInt() }

    println("Part 1: ${pairProduct(numbers)}")
    println("Part 2: ${tripleProduct(numbers)}")
}

fun pairProduct(numbers: List<Int>): Int? {
    for (i in numbers.indices) {
        val a = numbers[i]
        for (j in (i + 1)..numbers.lastIndex) {
            val b = numbers[j]
            if (a + b == 2020) {
                return a * b
            }
        }
    }
    return null
}

fun tripleProduct(numbers: List<Int>): Int? {
    for (i in numbers.indices) {
        val a = numbers[i]
        for (j in (i + 1)..numbers.lastIndex) {
            val b = numbers[j]
            val ab = a + b
            if (ab < 2020) {
                for (k in (j + 1)..numbers.lastIndex) {
                    val c = numbers[k]
                    val abc = ab + c
                    if (abc == 2020) {
                        return a * b * c
                    }
                }
            }
        }
    }
    return null
}