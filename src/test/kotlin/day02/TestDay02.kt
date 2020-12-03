package day02

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class TestDay02 {
    @Test
    fun part1() {
        assertTrue(parsePolicy("1-3 a").validate1("abcde"))
        assertFalse(parsePolicy("1-3 b").validate1("cdefg"))
        assertTrue(parsePolicy("2-9 c").validate1("ccccccccc"))
    }

    @Test
    fun part2() {
        assertTrue(parsePolicy("1-3 a").validate2("abcde"))
        assertFalse(parsePolicy("1-3 b").validate2("cdefg"))
        assertFalse(parsePolicy("2-9 c").validate2("ccccccccc"))
    }
}