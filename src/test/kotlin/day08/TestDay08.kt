package day08

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TestDay08 {
    private val input = """
        nop +0
        acc +1
        jmp +4
        acc +3
        jmp -3
        acc -99
        acc +1
        jmp -4
        acc +6
    """.trimIndent()

    private val instructions = parseInput(input)

    @Test
    fun part1() {
        assertEquals(5, (process(instructions) as Result.Loop).accumulatorBeforeLoop)
    }

    @Test
    fun part2() {
        assertEquals(8, accumulatorAfterFix(instructions))
    }
}