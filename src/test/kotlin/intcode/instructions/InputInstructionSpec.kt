package intcode.instructions

import intcode.Memory
import intcode.MockIO
import org.amshove.kluent.shouldEqual
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object InputInstructionSpec : Spek({
    describe("An InputInstruction") {
        it("set input to memory address") {
            val inputCode = 1123
            val memory = Memory("3,1,99")
            val inputInstruction = InputInstruction(0, MockIO(inputCode))
            inputInstruction.execute(memory)
            memory shouldEqual Memory("3,$inputCode,99")
        }
    }
})