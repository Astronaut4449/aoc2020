package day05

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TestDay05 {
    @Test
    fun seatTest() {
        val seats = listOf("FBFBBFFRLR", "BFFFBBFRRR", "FFFBBBFRRR", "BBFFBBFRLL").map { seat(it) }

        assertEquals(44, seats[0].row)
        assertEquals(5, seats[0].column)
        assertEquals(357, seats[0].id)

        assertEquals(70, seats[1].row)
        assertEquals(7, seats[1].column)
        assertEquals(567, seats[1].id)

        assertEquals(14, seats[2].row)
        assertEquals(7, seats[2].column)
        assertEquals(119, seats[2].id)

        assertEquals(102, seats[3].row)
        assertEquals(4, seats[3].column)
        assertEquals(820, seats[3].id)
    }
}