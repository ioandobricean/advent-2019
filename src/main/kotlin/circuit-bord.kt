import kotlin.math.abs

class CircuitBoard {
    private val centralPoint = Point(0, 0)
    var grid: MutableMap<Point, BoardCell> = mutableMapOf()

    private fun computeGridForWire(wire: Wire): MutableMap<Point, BoardCell> {
        var cursor = centralPoint
        var localGrid: MutableMap<Point, BoardCell> = mutableMapOf()
        localGrid[cursor] = BoardCell.CENTRAL_PORT
        for ((index, segment) in wire.path.segments.withIndex()) {
            for (step in 1..segment.length) {
                cursor = cursor.next(segment.direction)
                if (step == segment.length && isNotWireEnd(index, wire)) {
                    localGrid[cursor] = BoardCell.WIRE_CURVE
                } else {
                    localGrid[cursor] = cursor.boardCell(segment.direction)
                }
            }
        }
        return localGrid
    }

    fun applyWire(wire: Wire) {
        val localGrid = computeGridForWire(wire)
        if (grid.isEmpty()) {
            grid.putAll(localGrid)
        } else {
            for ((point, cell) in localGrid) {
                val existingCell = grid[point]
                if (existingCell == null) {
                    grid[point] = cell
                } else if (existingCell.isWire()) {
                    grid[point] = BoardCell.INTERSECT
                }
            }
        }
    }

    private fun isNotWireEnd(index: Int, wire: Wire) = index != wire.path.segments.size - 1

    fun print() {
        val max_x = grid.keys.map { it.x }.max()!! + 1
        val min_x = grid.keys.map { it.x }.min()!! - 1
        val max_y = grid.keys.map { it.y }.max()!! + 1
        val min_y = grid.keys.map { it.y }.min()!! - 1
        for (x in min_x..max_x) {
            for (y in min_y..max_y) {
                val point = Point(x, y)
                if (grid.contains(point)) {
                    print(grid[point]!!.code)
                } else {
                    print(BoardCell.EMPTY.code)
                }
                if (y == max_y) {
                    print("\n")
                }
            }

        }
    }

    fun manhattanDistance(wire1: Wire, wire2: Wire): Int? {
        applyWire(wire1)
        applyWire(wire2)

        return grid.filter { (point, cell) -> cell == BoardCell.INTERSECT }
                .map { (point, cell) -> abs(point.x) + abs(point.y) }
                .min()
    }
}

data class Wire(val path: Path)

data class Path(val rawPath: String) {
    val segments = rawPath.split(",").map {
        Segment(Direction.fromCode(it.slice(0..0)), Integer.valueOf(it.substring(1)))
    }
}

data class Segment(val direction: Direction, val length: Int)

enum class Direction(val code: String) {
    Right("R"), Left("L"), Up("U"), Down("D");

    companion object {
        fun fromCode(code: String) = values().first { it.code == code }
    }
}

data class Point(val x: Int, val y: Int) {
    fun next(direction: Direction): Point {
        return when (direction) {
            Direction.Right -> Point(x, y + 1)
            Direction.Left -> Point(x, y - 1)
            Direction.Up -> Point(x - 1, y)
            Direction.Down -> Point(x + 1, y)
        }
    }

    fun boardCell(direction: Direction): BoardCell {
        return when (direction) {
            Direction.Right, Direction.Left -> BoardCell.WIRE_H
            Direction.Up, Direction.Down -> BoardCell.WIRE_V
        }
    }
}

enum class BoardCell(val code: String) {
    EMPTY("."), CENTRAL_PORT("o"), WIRE_H("-"), WIRE_V("|"), INTERSECT("X"), WIRE_CURVE("+");

    fun isWire(): Boolean {
        return when (this) {
            WIRE_V, WIRE_H, WIRE_CURVE, INTERSECT -> true
            else -> false
        }
    }
}