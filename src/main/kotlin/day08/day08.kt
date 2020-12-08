package day08

import day08.Result.Loop
import day08.Result.Success
import java.io.File

fun main(args: Array<String>) {
    val input = File(args.firstOrNull() ?: "input/day08.txt").readText()
    val instructions = parseInput(input)

    val part1 = (process(instructions) as Loop).accumulatorBeforeLoop
    println("Part 1: $part1")

    val part2 = accumulatorAfterFix(instructions)
    println("Part 2: $part2")
}

data class Instruction(val command: String, val value: Int)

sealed class Result {
    class Loop(val accumulatorBeforeLoop: Int) : Result()
    class Success(val accumulator: Int) : Result()
}

fun parseInput(input: String): List<Instruction> = input.lines()
        .map {
            val (command, number) = it.split(" ")
            Instruction(command, number.toInt())
        }

fun process(instructions: List<Instruction>): Result {
    var accumulator = 0
    var index = 0
    val accumulatorAtIndex = mutableMapOf(index to accumulator)

    while (true) {
        val instruction = instructions.getOrNull(index) ?: return Success(accumulator)
        index += if (instruction.command == "jmp") instruction.value else 1

        if (index !in accumulatorAtIndex) {
            if (instruction.command == "acc") accumulator += instruction.value
            accumulatorAtIndex[index] = accumulator
        } else {
            return Loop(accumulator)
        }
    }
}

fun accumulatorAfterFix(instructions: List<Instruction>): Int {
    val modifiedInstructions = instructions.toMutableList()

    instructions.forEachIndexed { i, (command, value) ->
        if (command != "acc") {
            modifiedInstructions[i] = Instruction(if (command == "jmp") "nop" else "jmp", value)
            val result = process(modifiedInstructions)
            if (result is Success) {
                return result.accumulator
            }
            modifiedInstructions[i] = Instruction(command, value)
        }
    }

    error("No successful replacement found.")
}