package day06

import java.io.File

typealias Answers = Set<Char>
typealias AnswerGroup = List<Answers>

fun main(args: Array<String>) {
    val input = File(args.firstOrNull() ?: "input/day06.txt").readText()
    val answerGroups = parseAnswerGroups(input)

    val part1 = answerGroups
            .map { group -> group.reduce(Answers::union).size }
            .sum()

    println("Part 1: $part1")

    val part2 = answerGroups
            .map { group -> group.reduce(Answers::intersect).size }
            .sum()

    println("Part 2: $part2")
}

fun parseAnswerGroups(input: String): List<AnswerGroup> = input
        .splitOnBlankLines()
        .map { group ->
            group.lines().map { answers ->
                answers.toCharArray().toSet()
            }
        }

fun String.splitOnBlankLines() = split("""(\r?\n){2}""".toRegex())