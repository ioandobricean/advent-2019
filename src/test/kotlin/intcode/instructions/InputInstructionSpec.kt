package intcode.instructions

import intcode.Memory
import intcode.params.PositionParameter
import io.MockIO
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldEqual
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object InputInstructionSpec : Spek({
    describe("An InputInstruction") {
        it("set input to memory address") {
            val inputCode = 1123.toBigInteger()
            val memory = Memory("3,1,99")
            val inputInstruction = InputInstruction(0, PositionParameter(1), MockIO(inputCode))
            runBlocking { inputInstruction.execute(memory) }
            memory shouldEqual Memory("3,$inputCode,99")
        }
    }
})