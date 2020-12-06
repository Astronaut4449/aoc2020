package day06

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class TestDay06 {
    private val input = """
            abc

            a
            b
            c

            ab
            ac

            a
            a
            a
            a

            b
        """.trimIndent()

    private val groups = parseAnswerGroups(input)
            

    @Test
    fun part1() {
        val anyAnswerGroups = groups.map { answers -> answers.reduce(Answers::union) }
        
        assertEquals(3, anyAnswerGroups[0].size)
        assertEquals(3, anyAnswerGroups[1].size)
        assertEquals(3, anyAnswerGroups[2].size)
        assertEquals(1, anyAnswerGroups[3].size)
        assertEquals(1, anyAnswerGroups[4].size)

        assertEquals(11, anyAnswerGroups.sumOf { it.size })
    }

    @Test
    fun part2() {
        val sameAnswerGroups = groups.map { answers -> answers.reduce(Answers::intersect) }

        assertEquals(3, sameAnswerGroups[0].size)
        assertEquals(0, sameAnswerGroups[1].size)
        assertEquals(1, sameAnswerGroups[2].size)
        assertEquals(1, sameAnswerGroups[3].size)
        assertEquals(1, sameAnswerGroups[4].size)

        assertEquals(6, sameAnswerGroups.sumOf { it.size })
    }
}