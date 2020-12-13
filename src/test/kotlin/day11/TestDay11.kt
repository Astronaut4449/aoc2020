package day11

import day11.SeatState.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class TestDay11 {
   private  val input = """
        L.LL.LL.LL
        LLLLLLL.LL
        L.L.L..L..
        LLLL.LL.LL
        L.LL.LL.LL
        L.LLLLL.LL
        ..L.L.....
        LLLLLLLLLL
        L.LLLLLL.L
        L.LLLLL.LL
    """.trimIndent()

    private val seatPlan = parseSeatPlan(input)

    @Test
    fun part1() {
        assertEquals(37, seatPlan.stabilizeAdjacentRule().count { (_, seatState) -> seatState == Occupied })
    }

    @Test
    fun part2() {
        assertEquals(26, seatPlan.stabilizeLineOfSightRule().count { (_, seatState) -> seatState == Occupied })
    }
}