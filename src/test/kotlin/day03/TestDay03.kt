package day03

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import common.Point2D as Point

class TestDay03 {
    private val input = """
        ..##.......
        #...#...#..
        .#....#..#.
        ..#.#...#.#
        .#...##..#.
        ..#.##.....
        .#.#.#....#
        .#........#
        #.##...#...
        #...##....#
        .#..#...#.#
    """.trimIndent()

    private val mapOfTrees = MapOfTrees(input)

    @Test
    fun isTree() {
        // In grid
        assertTrue(mapOfTrees.isTree(Point(2, 0)))
        assertTrue(mapOfTrees.isTree(Point(1, 2)))
        assertTrue(mapOfTrees.isTree(Point(10, 10)))
        assertFalse(mapOfTrees.isTree(Point(0, 0)))

        // outside grid
        assertTrue(mapOfTrees.isTree(Point(11, 1)))
        assertTrue(mapOfTrees.isTree(Point(23, 7)))
    }

    @Test
    fun part1() {
        assertEquals(7, countTrees(mapOfTrees, slopes[1]))
    }

    @Test
    fun part2() {
        assertEquals(336, multiplyTreeCount(mapOfTrees, slopes))
    }
}