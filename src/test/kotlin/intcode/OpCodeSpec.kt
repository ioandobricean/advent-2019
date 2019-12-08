package intcode

import org.amshove.kluent.shouldEqual
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object OpCodeSpec : Spek({
    describe("An OpCode") {
        context("for AddInstruction") {
            it("with only position mode parameters") {
                val opCode = OpCode(0, 1)
                val addInstruction = AddInstruction(0, PositionParameter(1), PositionParameter(2), PositionParameter(3))
                opCode.getInstruction() shouldEqual addInstruction
            }
            it("with only value parameters") {
                val opCode = OpCode(0, 1101)
                val addInstruction = AddInstruction(0, ImmediateParameter(1), ImmediateParameter(2), PositionParameter(3))
                opCode.getInstruction() shouldEqual addInstruction
            }
            it("with mixed parameters") {
                val opCode = OpCode(0, 1001)
                val addInstruction = AddInstruction(0, PositionParameter(1), ImmediateParameter(2), PositionParameter(3))
                opCode.getInstruction() shouldEqual addInstruction
            }
        }
    }
})