import org.amshove.kluent.shouldEqual
import org.amshove.kluent.shouldEqualTo
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object CircuitBoardSpec : Spek({
    describe("A path") {
        it("with one segment") {
            val path = Path("R10")
            path.segments shouldEqual listOf(Segment(Direction.Right, 10))
        }
        it("with two segment") {
            val path = Path("R10,D30")
            path.segments shouldEqual listOf(Segment(Direction.Right, 10), Segment(Direction.Down, 30))
        }
    }

    describe("A circuit board") {
        it("computes Manhattan distance #1") {
            val path1 = Path("R8,U5,L5,D3")
            val path2 = Path("U7,R6,D4,L4")
            val circuitBoard = CircuitBoard()
            circuitBoard.applyWire(Wire(path1))
            circuitBoard.applyWire(Wire(path2))

            val distance = circuitBoard.manhattanDistance()
            distance?.shouldEqualTo(6)

            val closestIntersection = circuitBoard.closestIntersection()
            closestIntersection?.shouldEqualTo(30)
        }
        it("computes Manhattan distance #2") {
            val path1 = Path("R75,D30,R83,U83,L12,D49,R71,U7,L72")
            val path2 = Path("U62,R66,U55,R34,D71,R55,D58,R83")
            val circuitBoard = CircuitBoard()
            circuitBoard.applyWire(Wire(path1))
            circuitBoard.applyWire(Wire(path2))

            val distance = circuitBoard.manhattanDistance()
            distance?.shouldEqualTo(159)

            val closestIntersection = circuitBoard.closestIntersection()
            closestIntersection?.shouldEqualTo(610)
        }
        it("computes Manhattan distance #3") {
            val path1 = Path("R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51")
            val path2 = Path("U98,R91,D20,R16,D67,R40,U7,R15,U6,R7")
            val circuitBoard = CircuitBoard()
            circuitBoard.applyWire(Wire(path1))
            circuitBoard.applyWire(Wire(path2))

            val distance = circuitBoard.manhattanDistance()
            distance?.shouldEqualTo(135)

            val closestIntersection = circuitBoard.closestIntersection()
            closestIntersection?.shouldEqualTo(410)
        }
    }
})