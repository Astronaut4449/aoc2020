package day12

import day12.MoveDirection.*
import day12.TurnDirection.*
import java.io.File
import kotlin.math.abs

fun main(args: Array<String>) {
    val input = File(args.firstOrNull() ?: "input/day12.txt").readText()
    val instructions = parseNavigationInstructions(input)

    val part1 = instructions.navigatePart1().manhattanDistanceFromStart()
    println("Part 1: $part1")

    val part2 = instructions.navigatePart2().manhattanDistanceFromStart()
    println("Part 2: $part2")
}

data class Position(val x: Int, val y: Int)

sealed class Instruction
enum class MoveDirection { North, East, South, West }
data class Move(val direction: MoveDirection, val length: Int) : Instruction()
enum class TurnDirection { Left, Right }
data class Turn(val direction: TurnDirection, val degrees: Int) : Instruction()
data class Forward(val length: Int) : Instruction()

fun parseNavigationInstructions(input: String): List<Instruction> = input.lines().map { line ->
    val number = line.drop(1).toInt()
    when (val char = line.first()) {
        'N' -> Move(North, number)
        'E' -> Move(East, number)
        'S' -> Move(South, number)
        'W' -> Move(West, number)
        'F' -> Forward(number)
        'L' -> Turn(Left, number)
        'R' -> Turn(Right, number)
        else -> error("Unknown instruction '$char'")
    }
}

fun List<Instruction>.navigatePart1(): Position {
    var position = Position(0, 0)
    var orientation = East

    fun MoveDirection.turn(turn: Turn): MoveDirection {
        val directions = listOf(North, East, South, West).let { if (turn.direction == Left) it.reversed() else it }
        val index = directions.indexOf(this) + turn.degrees / 90
        return directions[index % 4]
    }

    fun navigate(instruction: Instruction) {
        when(instruction) {
            is Move -> {
                position = when (instruction.direction) {
                    North -> Position(position.x, position.y + instruction.length)
                    East -> Position(position.x + instruction.length, position.y)
                    South -> Position(position.x, position.y - instruction.length)
                    West -> Position(position.x - instruction.length, position.y)
                }
            }
            is Turn -> orientation = orientation.turn(instruction)
            is Forward -> navigate(Move(orientation, instruction.length))
        }
    }

    forEach { instruction -> navigate(instruction) }

    return position
}

fun List<Instruction>.navigatePart2(): Position {
    var shipPosition = Position(0, 0)
    var waypointPosition = Position(10, 1)

    fun Position.rotate(turn: Turn): Position {
        val degrees = when (turn.direction) {
            Right -> 360 - turn.degrees
            Left -> turn.degrees
        }

        val (sin, cos) = when (degrees) {
            90 -> 1 to 0
            180 -> 0 to -1
            270 -> -1 to 0
            else -> error("Unexpected turn $turn")
        }

        return Position(x * cos - y * sin, x * sin + y * cos)
    }

    forEach { instruction ->
        when (instruction) {
            is Move -> {
                waypointPosition = when (instruction.direction) {
                    North -> Position(waypointPosition.x, waypointPosition.y + instruction.length)
                    East -> Position(waypointPosition.x + instruction.length, waypointPosition.y)
                    South -> Position(waypointPosition.x, waypointPosition.y - instruction.length)
                    West -> Position(waypointPosition.x - instruction.length, waypointPosition.y)
                }
            }

            is Turn -> {
                waypointPosition = waypointPosition.rotate(instruction)
            }

            is Forward -> {
                val dx = waypointPosition.x * instruction.length
                val dy = waypointPosition.y * instruction.length
                shipPosition = Position(shipPosition.x + dx, shipPosition.y + dy)
            }
        }
    }

    return shipPosition
}

fun Position.manhattanDistanceFromStart() = abs(x) + abs(y)