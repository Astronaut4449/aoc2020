package day03

import java.io.File
import common.Point2D as Point

val slopes: List<(Point) -> Point> = listOf(
        1 to 1,
        3 to 1,
        5 to 1,
        7 to 1,
        1 to 2,
).map { (dx, dy) -> { Point(it.x + dx, it.y + dy) } }

fun main(args: Array<String>) {
    val mapOfTrees = MapOfTrees(File(args.firstOrNull() ?: "input/day03.txt").readText())

    val part1 = countTrees(mapOfTrees, slopes[1])
    println("Part 1: $part1")

    val part2 = multiplyTreeCount(mapOfTrees, slopes)
    println("Part 2: $part2")
}


fun countTrees(mapOfTrees: MapOfTrees, slope: (Point) -> Point): Int = generateSequence(Point(0, 0), slope)
        .take(mapOfTrees.sizeY)
        .count { point -> mapOfTrees.isTree(point) }

fun multiplyTreeCount(mapOfTrees: MapOfTrees, slopes: List<(Point) -> Point>) = slopes
        .fold(1) { acc, slope -> acc * countTrees(mapOfTrees, slope) }

class MapOfTrees(str: String) {
    private val trees: Set<Point>

    val sizeX: Int
    val sizeY: Int

    init {
        val lines = str.lines()

        sizeY = lines.count()
        sizeX = lines.first().count()

        trees = str.lines().flatMapIndexed { y, line ->
            line.mapIndexedNotNull { x, char -> if (char == '#') Point(x, y) else null }
        }.toSet()
    }

    fun isTree(point: Point): Boolean = Point(point.x % sizeX, point.y) in trees
}