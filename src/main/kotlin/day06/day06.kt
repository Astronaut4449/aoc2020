package day06

import java.io.File

fun main(args: Array<String>) {
    val input = File(args.firstOrNull() ?: "input/day06.txt").readText()
    val groups = groupAnswers(input, Set<Char>::union)

    val part1 = groups.sumOf { it.size }
    println(part1)

    val part2 = groupAnswers(input, Set<Char>::intersect)
    println(part2.sumOf { it.size })
}

fun groupAnswers(input: String, combine: Set<Char>.(Set<Char>) -> Set<Char>): List<Set<Char>> = input
        .splitOnBlankLines()
        .map { group ->
            group
                    .lines()
                    .map {
                        it.toCharArray().toSet()
                    }
                    .reduce { union, set -> union.combine(set) }
        }

fun String.splitOnBlankLines() = split("""(\r?\n){2}""".toRegex())