package day11

import day11.SeatState.*
import java.io.File

fun main(args: Array<String>) {
    val input = File(args.firstOrNull() ?: "input/day11.txt").readText()
    val seatPlan = parseSeatPlan(input)

    val part1 = seatPlan.stabilizeAdjacentRule().count { (_, seatState) -> seatState == Occupied }
    println("Part 1: $part1")

    val part2 = seatPlan.stabilizeLineOfSightRule().count { (_, seatState) -> seatState == Occupied }
    println("Part 2: $part2")
}

data class Location(val row: Int, val column: Int) {
    fun walk(direction: Direction): Location = Location(row + direction.row, column + direction.column)
}

enum class Direction(val row: Int, val column: Int) {
    North(-1, 0),
    NorthEast(-1, 1),
    East(0, 1),
    SouthEast(1, 1),
    South(1, 0),
    SouthWest(1, -1),
    West(0, -1),
    NorthWest(-1, -1);
}

enum class SeatState { Floor, Empty, Occupied }

typealias SeatPlan = Map<Location, SeatState>

private fun SeatPlan.nextState(determineNeighbours: SeatPlan.(Location) -> List<Location>, tolerance: Int): SeatPlan {
    fun Location.hasFreeSeat(): Boolean = get(this) == Empty
    fun Location.hasOccupiedSeat(): Boolean = get(this) == Occupied
    fun Location.neighbours(): List<Location> = determineNeighbours(this)

    return mapValues { (location, seatState) ->
        when {
            location.hasFreeSeat() && location.neighbours().none(Location::hasOccupiedSeat) -> Occupied
            location.hasOccupiedSeat() && location.neighbours().count(Location::hasOccupiedSeat) >= tolerance -> Empty
            else -> seatState
        }
    }
}

fun SeatPlan.stabilizeAdjacentRule(): SeatPlan {
    fun SeatPlan.adjacentNeighbours(location: Location) = Direction.values().mapNotNull { direction ->
        val adjacentLocation = location.walk(direction)
        if (contains(adjacentLocation)) adjacentLocation else null
    }

    return stabilize(SeatPlan::adjacentNeighbours, tolerance = 4)
}

fun SeatPlan.stabilizeLineOfSightRule(): SeatPlan {
    fun SeatPlan.neighboursInLineOfSight(location: Location): List<Location> = Direction.values().mapNotNull { direction ->
        generateSequence(location.walk(direction)) { it.walk(direction) }
                .filter { get(it) != Floor }
                .map { if(get(it) == null) null else it }
                .first()
    }

    return stabilize(SeatPlan::neighboursInLineOfSight, tolerance = 5)
}

private fun SeatPlan.stabilize(determineNeighbours: SeatPlan.(Location) -> List<Location>, tolerance: Int): SeatPlan {
    var curr: SeatPlan = this
    while (true) {
        val next = curr.nextState(determineNeighbours, tolerance)
        if (next == curr) return next
        curr = next
    }
}

fun parseSeatPlan(input: String): SeatPlan = input.lines().flatMapIndexed { row, line ->
    line.mapIndexed { column, char ->
        Location(row, column) to if (char == 'L') Empty else Floor
    }
}.toMap()