package day06

import java.io.File

fun main(args: Array<String>) {
    val input = File(args.firstOrNull() ?: "input/day06.txt").readText()
    val groups = input.groupAnswersBy(Set<Char>::union)

    val part1 = groups.sumOf { it.size }
    println(part1)

    val part2 = input.groupAnswersBy(Set<Char>::intersect)
    println(part2.sumOf { it.size })
}

fun String.groupAnswersBy(combine: Set<Char>.(Set<Char>) -> Set<Char>): List<Set<Char>> = this
        .splitOnBlankLines()
        .map { group ->
            group
                    .lines()
                    .map {
                        it.toCharArray().toSet()
                    }
                    .reduce { combined, set -> combined.combine(set) }
        }

fun String.splitOnBlankLines() = split("""(\r?\n){2}""".toRegex())