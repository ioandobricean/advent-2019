package intcode.instructions

import intcode.Memory
import intcode.OpCode
import intcode.RelativeBase
import io.MockIO
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldEqual
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import java.math.BigInteger

object AdjustRelativeBaseInstructionSpec : Spek({
    describe("An AdjustRelativeBaseInstruction") {
        it("set the relative base") {
            val memory = Memory("109,19,204,-34")
            val relativeBase = RelativeBase(2000)
            var pointer = 0
            val io = MockIO()
            val code1 = memory.getAddressValue(pointer)
            val opCode1 = OpCode(pointer, code1.toInt(), io, relativeBase)
            val adjustBaseInst = opCode1.getInstruction()
            runBlocking { adjustBaseInst.execute(memory) }
            relativeBase.getOffset() shouldEqual 2019

            memory.setAddressValue(1985, BigInteger.TEN)
            pointer = adjustBaseInst.nextInstructionPointer()
            val code2 = memory.getAddressValue(pointer)
            val opCode2 = OpCode(pointer, code2.toInt(), io, relativeBase)
            val outputInst = opCode2.getInstruction()
            runBlocking { outputInst.execute(memory) }

            io.lastWrittenCode shouldEqual BigInteger.TEN
            outputInst.nextInstructionPointer() shouldEqual 4
        }
    }
})