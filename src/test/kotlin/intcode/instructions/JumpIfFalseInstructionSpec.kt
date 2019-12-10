package intcode.instructions

import intcode.Memory
import intcode.MockIO
import intcode.OpCode
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldEqual
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object JumpIfFalseInstructionSpec : Spek({
    describe("A JumpIfFalseInstruction") {
        it("jumps to the 0 position using position parameters") {
            val memory = Memory("6,4,4,99,0")
            val opCode = OpCode(0, 6, MockIO())
            val instruction = opCode.getInstruction()
            runBlocking { instruction.execute(memory) }
            instruction.nextInstructionPointer() shouldEqual 0
        }
        it("jumps to the 0 position using immediate parameters") {
            val memory = Memory("1106,0,0,99")
            val opCode = OpCode(0, 1106, MockIO())
            val instruction = opCode.getInstruction()
            runBlocking { instruction.execute(memory) }
            instruction.nextInstructionPointer() shouldEqual 0
        }
        it("do nothing using position parameters") {
            val memory = Memory("6,1,4,99,0")
            val opCode = OpCode(0, 6, MockIO())
            val instruction = opCode.getInstruction()
            runBlocking { instruction.execute(memory) }
            instruction.nextInstructionPointer() shouldEqual 3
        }
        it("do nothing using immediate parameters") {
            val memory = Memory("1106,1,0,99")
            val opCode = OpCode(0, 1106, MockIO())
            val instruction = opCode.getInstruction()
            runBlocking { instruction.execute(memory) }
            instruction.nextInstructionPointer() shouldEqual 3
        }
    }
})