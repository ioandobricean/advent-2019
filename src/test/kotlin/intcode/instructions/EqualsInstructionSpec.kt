package intcode.instructions

import intcode.Memory
import intcode.OpCode
import io.MockIO
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.`should be`
import org.amshove.kluent.shouldEqual
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object EqualsInstructionSpec : Spek({
    describe("A EqualsInstructionSpec") {
        it("set 1 on 3rd param if first 2 position params are equals") {
            val memory = Memory("8,1,1,5,99,111")
            val opCode = OpCode(0, 8, MockIO())
            val instruction = opCode.getInstruction()
            runBlocking { instruction.execute(memory) }
            instruction.nextInstructionPointer() `should be` 4
            memory shouldEqual Memory("8,1,1,5,99,1")
        }
        it("set 1 on 3rd param if first 2 immediate params are equals") {
            val memory = Memory("11108,1,1,11,99")
            val opCode = OpCode(0, 11108, MockIO())
            val instruction = opCode.getInstruction()
            runBlocking { instruction.execute(memory) }
            instruction.nextInstructionPointer() `should be` 4
            memory shouldEqual Memory("11108,1,1,1,99")
        }
    }
})