package day03

import java.io.File
import common.Point2D as Point

typealias Slope = (Point) -> Point

val slopes: List<Slope> = listOf(
        1 to 1,
        3 to 1,
        5 to 1,
        7 to 1,
        1 to 2,
).map { (dx, dy) -> { Point(it.x + dx, it.y + dy) } }

fun main(args: Array<String>) {
    val mapOfTrees = MapOfTrees(File(args.firstOrNull() ?: "input/day03.txt").readText())

    val part1 = mapOfTrees.treeEncounters(slopes[1])
    println("Part 1: $part1")

    val part2 = mapOfTrees.multipliedTreeEncounters(slopes)
    println("Part 2: $part2")
}

class MapOfTrees(str: String) {
    private val trees: Set<Point>

    val sizeX: Int
    val sizeY: Int

    init {
        val lines = str.lines()

        sizeY = lines.count()
        sizeX = lines.first().count()

        trees = str.lines().flatMapIndexed { y, line ->
            line.mapIndexedNotNull { x, char ->
                if (char == '#') Point(x, y) else null
            }
        }.toSet()
    }

    fun hasTreeAt(point: Point): Boolean = Point(point.x % sizeX, point.y) in trees

    fun treeEncounters(slope: Slope) = generateSequence(Point(0, 0), slope)
            .take(sizeY)
            .count { point -> hasTreeAt(point) }

    fun multipliedTreeEncounters(slopes: List<Slope>) = slopes
            .fold(1) { acc, slope -> acc * treeEncounters(slope) }
}