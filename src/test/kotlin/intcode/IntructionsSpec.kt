package intcode

import org.amshove.kluent.shouldEqual
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object InstructionsSpec : Spek({
    describe("An InputInstruction") {
        it("set input to memory address") {
            val inputCode = 1123
            val memory = Memory("3,1,99")
            val inputInstruction = InputInstruction(0, MockIO(inputCode))
            inputInstruction.execute(memory)
            memory shouldEqual Memory("3,$inputCode,99")
        }
    }
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