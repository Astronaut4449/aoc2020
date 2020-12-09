package day09

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TestDay09 {
    private val input = """
        35
        20
        15
        25
        47
        40
        62
        55
        65
        95
        102
        117
        150
        182
        127
        219
        299
        277
        309
        576
    """.trimIndent()

    private val transmission = input.lines().map(String::toLong)

    @Test
    fun day01() {
        assertEquals(127, findInvalidNumber(transmission, preamble = 5))
    }

    @Test
    fun day02() {
        assertEquals(listOf(15L,25L,47L,40L), findSumWindow(transmission, 127))
    }
}