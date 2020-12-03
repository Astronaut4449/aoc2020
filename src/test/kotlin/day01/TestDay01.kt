package day01

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TestDay01 {
    private val numbers = listOf(1721, 979, 366, 299, 675, 1456)

    @Test
    fun part1() {
        assertEquals(514579, pairProduct(numbers))
    }

    @Test
    fun part2() {
        assertEquals(241861950, tripleProduct(numbers))
    }
}