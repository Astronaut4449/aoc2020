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

    @Test
    fun part1() {
        val groups = input.groupAnswersBy(Set<Char>::union)

        assertEquals(3, groups[0].size)
        assertEquals(3, groups[1].size)
        assertEquals(3, groups[2].size)
        assertEquals(1, groups[3].size)
        assertEquals(1, groups[4].size)

        assertEquals(11, groups.sumOf { it.size })
    }

    @Test
    fun part2() {
        val groups = input.groupAnswersBy(Set<Char>::intersect)

        assertEquals(3, groups[0].size)
        assertEquals(0, groups[1].size)
        assertEquals(1, groups[2].size)
        assertEquals(1, groups[3].size)
        assertEquals(1, groups[4].size)

        assertEquals(6, groups.sumOf { it.size })
    }
}