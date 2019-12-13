package intcode.instructions

import intcode.Memory
import io.MockIO
import intcode.params.PositionParameter
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldEqual
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import java.math.BigInteger

object OutputInstructionSpec : Spek({
    describe("An OutputInstruction") {
        it("set input to memory address") {
            val memory = Memory("4,2,12345,99")
            val mockIO = MockIO()
            val outputInstruction = OutputInstruction(0, PositionParameter(1), mockIO)
            runBlocking { outputInstruction.execute(memory) }
            mockIO.lastWrittenCode shouldEqual BigInteger.valueOf(12345)
        }
    }
})