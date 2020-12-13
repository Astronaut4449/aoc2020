package day12

import day12.MoveDirection.*
import day12.TurnDirection.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TestDay12 {
    private val input = """
        F10
        N3
        F7
        R90
        F11
    """.trimIndent()

    private val instructions = parseNavigationInstructions(input)

    @Test
    fun day01() {
        val finalPosition = instructions.navigatePart1()

        assertEquals(17, finalPosition.x)
        assertEquals(-8, finalPosition.y)
        assertEquals(25, finalPosition.manhattanDistanceFromStart())
    }

    @Test
    fun day02() {
        val position = instructions.navigatePart2()

        assertEquals(214, position.x)
        assertEquals(-72, position.y)
        assertEquals(286, position.manhattanDistanceFromStart())
    }
}