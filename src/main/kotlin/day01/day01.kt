package day01

import java.io.File

fun main(args: Array<String>) {
    val numbers = File(args.firstOrNull() ?: "input/day01.txt")
            .readLines()
            .map { it.toInt() }

    part1@ for (i in numbers.indices) {
        val a = numbers[i]
        for (j in (i + 1)..numbers.lastIndex) {
            val b = numbers[j]
            if (a + b == 2020) {
                println("Part 1: ${a * b}")
                break@part1
            }
        }
    }

    part2@ for (i in numbers.indices) {
        val a = numbers[i]
        for (j in (i + 1)..numbers.lastIndex) {
            val b = numbers[j]
            val ab = a + b
            if (ab < 2020) {
                for (k in (j+1)..numbers.lastIndex) {
                    val c = numbers[k]
                    val abc = ab + c
                    if (abc == 2020) {
                        println("Part 2: ${a*b*c}")
                        break@part2
                    }
                }
            }
        }
    }
}