package day02

import java.io.File

fun main(args: Array<String>) {
    val policyPasswordPairs = File(args.firstOrNull() ?: "input/day02.txt")
            .readLines()
            .map { line ->
                val (policy, password) = line.split(": ")
                Pair(parsePolicy(policy), password)
            }

    val validPasswordCount1 = policyPasswordPairs.count { (policy, password) -> policy.validate1(password) }
    println("Part 1: $validPasswordCount1")

    val validPasswordCount2 = policyPasswordPairs.count { (policy, password) -> policy.validate2(password) }
    println("Part 2: $validPasswordCount2")
}

fun parsePolicy(string: String): Policy {
    val (range, char) = string.split(' ')
    val (min, max) = range.split('-').map { it.toInt() }
    return Policy(char[0], min..max)
}

class Policy(val char: Char, val range: IntRange) {
    fun validate1(password: String) = password.count { it == char } in range
    fun validate2(password: String) = (password[range.first - 1] == char) xor (password[range.last - 1] == char)
}