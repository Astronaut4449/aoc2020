package day13

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class TestDay13 {
    private val parsedInput = parseTimeAndBusLines("""
        939
        7,13,x,x,59,x,31,19
    """.trimIndent())

    private val time = parsedInput.first
    private val busLines = parsedInput.second

    @Test
    fun part1() {
        val (bus, time) = firstDepartingBusAndTime(busLines, time)
        assertEquals(59, bus.number)
        assertEquals(944, time)
    }

    @Test
    fun part2() {
        val time = firstTimeOfSubsequentDepartures(busLines)
        assertEquals(1068781, time)

        val busLines2 = listOf(17, null, 13, 19).map { it?.let(::BusLine) }
        assertEquals(3417, firstTimeOfSubsequentDepartures(busLines2))
        val busLines3 = listOf(67, 7, 59, 61).map { it.let(::BusLine) }
        assertEquals(754018, firstTimeOfSubsequentDepartures(busLines3))
        val busLines4 = listOf(67, null, 7, 59, 61).map { it?.let(::BusLine) }
        assertEquals(779210, firstTimeOfSubsequentDepartures(busLines4))
        val busLines5 = listOf(67, 7, null, 59, 61).map { it?.let(::BusLine) }
        assertEquals(1261476, firstTimeOfSubsequentDepartures(busLines5))
        val busLines6 = listOf(1789, 37, 47, 1889).map { it.let(::BusLine) }
        assertEquals(1202161486, firstTimeOfSubsequentDepartures(busLines6))
    }
}