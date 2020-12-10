package day10

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TestDay10 {
    val adapters1 = """
        16
        10
        15
        5
        1
        11
        7
        19
        6
        12
        4
    """.trimIndent().let { parseInput(it) }

    val adapters2 = """
        28
        33
        18
        42
        31
        14
        46
        20
        48
        47
        24
        23
        49
        45
        19
        38
        39
        11
        1
        32
        25
        35
        8
        17
        7
        9
        4
        2
        34
        10
        3
    """.trimIndent().let { parseInput(it) }

    @Test
    fun part1() {
        val differences1 = differenceCounts(adapters1)
        assertEquals(7, differences1[1])
        assertEquals(5, differences1[3])

        val differences2 = differenceCounts(adapters2)
        assertEquals(22, differences2[1])
        assertEquals(10, differences2[3])
    }

    @Test
    fun part2() {
        val arrangements1 = countArrangements(adapters1)
        assertEquals(8, arrangements1)

        val arrangements2 = countArrangements(adapters2)
        assertEquals(19208, arrangements2)
    }
}