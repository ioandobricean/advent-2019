package intcode.instructions

import intcode.Memory
import intcode.OpCode
import io.MockIO
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldEqual
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object JumpIfTrueInstructionSpec : Spek({
    describe("A JumpIfTrueInstruction") {
        it("jumps to the 0 position using position parameters") {
            val memory = Memory("5,1,4,99,0")
            val opCode = OpCode(0, 5, MockIO())
            val instruction = opCode.getInstruction()
            runBlocking { instruction.execute(memory) }
            instruction.nextInstructionPointer() shouldEqual 0
        }
        it("jumps to the 0 position using immediate parameters") {
            val memory = Memory("1105,1,0,99")
            val opCode = OpCode(0, 1105, MockIO())
            val instruction = opCode.getInstruction()
            runBlocking { instruction.execute(memory) }
            instruction.nextInstructionPointer() shouldEqual 0
        }
        it("do nothing using position parameters") {
            val memory = Memory("5,4,4,99,0")
            val opCode = OpCode(0, 5, MockIO())
            val instruction = opCode.getInstruction()
            runBlocking { instruction.execute(memory) }
            instruction.nextInstructionPointer() shouldEqual 3
        }
        it("do nothing using immediate parameters") {
            val memory = Memory("1105,0,0,99")
            val opCode = OpCode(0, 1105, MockIO())
            val instruction = opCode.getInstruction()
            runBlocking { instruction.execute(memory) }
            instruction.nextInstructionPointer() shouldEqual 3
        }
    }
})