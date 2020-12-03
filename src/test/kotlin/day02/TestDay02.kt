package day02

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class TestDay02 {
    @Test
    fun part1() {
        assertTrue(parsePolicy("1-3 a").testPart1("abcde"))
        assertFalse(parsePolicy("1-3 b").testPart1("cdefg"))
        assertTrue(parsePolicy("2-9 c").testPart1("ccccccccc"))
    }

    @Test
    fun part2() {
        assertTrue(parsePolicy("1-3 a").testPart2("abcde"))
        assertFalse(parsePolicy("1-3 b").testPart2("cdefg"))
        assertFalse(parsePolicy("2-9 c").testPart2("ccccccccc"))
    }
}