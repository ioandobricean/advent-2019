package intcode.instructions

import intcode.Memory
import intcode.MockIO
import org.amshove.kluent.shouldEqual
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object OutputInstructionSpec : Spek({
    describe("An OutputInstruction") {
        it("set input to memory address") {
            val memory = Memory("4,2,12345,99")
            val mockIO = MockIO()
            val outputInstruction = OutputInstruction(0, mockIO)
            outputInstruction.execute(memory)
            mockIO.writtenCode shouldEqual 12345
        }
    }
})