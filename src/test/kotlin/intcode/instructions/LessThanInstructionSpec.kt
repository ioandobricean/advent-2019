package intcode.instructions

import intcode.*
import org.amshove.kluent.`should be`
import org.amshove.kluent.shouldEqual
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object LessThanInstructionSpec : Spek({
    describe("A LessThanInstruction") {
        it("set 1 when first position parameter is less than second") {
            val memory = Memory("7,1,4,5,99,111")
            val opCode = OpCode(0, 7, MockIO())
            val instruction = opCode.getInstruction();
            instruction.execute(memory)
            instruction.nextInstructionPointer() `should be` 4
            memory shouldEqual Memory("7,1,4,5,99,1")
        }
        it("set 0 when first position parameter equal with second") {
            val memory = Memory("7,5,5,5,99,111")
            val opCode = OpCode(0, 7, MockIO())
            val instruction = opCode.getInstruction();
            instruction.execute(memory)
            instruction.nextInstructionPointer() `should be` 4
            memory shouldEqual Memory("7,5,5,5,99,0")
        }
        it("set 0 when first position parameter greater than second") {
            val memory = Memory("7,5,2,5,99,111")
            val opCode = OpCode(0, 7, MockIO())
            val instruction = opCode.getInstruction();
            instruction.execute(memory)
            instruction.nextInstructionPointer() `should be` 4
            memory shouldEqual Memory("7,5,2,5,99,0")
        }
        it("set 1 when first immediate parameter is less than second") {
            val memory = Memory("11107,1,4,5,99")
            val opCode = OpCode(0, 11107, MockIO())
            val instruction = opCode.getInstruction();
            instruction.execute(memory)
            instruction.nextInstructionPointer() `should be` 4
            memory shouldEqual Memory("11107,1,4,1,99")
        }
        it("set 0 when first immediate parameter equal with second") {
            val memory = Memory("11107,1,1,5,99")
            val opCode = OpCode(0, 11107, MockIO())
            val instruction = opCode.getInstruction();
            instruction.execute(memory)
            instruction.nextInstructionPointer() `should be` 4
            memory shouldEqual Memory("11107,1,1,0,99")
        }
        it("set 0 when first immediate parameter greater than second") {
            val memory = Memory("11107,4,1,5,99")
            val opCode = OpCode(0, 11107, MockIO())
            val instruction = opCode.getInstruction();
            instruction.execute(memory)
            instruction.nextInstructionPointer() `should be` 4
            memory shouldEqual Memory("11107,4,1,0,99")
        }
    }
})