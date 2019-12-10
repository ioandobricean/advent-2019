package intcode.instructions

import intcode.Memory
import intcode.params.Parameter

data class JumpIfTrueInstruction(val pointer: Int, val parameter1: Parameter, val parameter2: Parameter) : Instruction {
    private var nextInstruction: Int = pointer + 3

    override fun nextInstructionPointer(): Int {
        return nextInstruction
    }

    override suspend fun execute(memory: Memory) {
        val value = parameter1.getValue(memory)
        if (value != 0) {
            nextInstruction = parameter2.getValue(memory)
        }
    }
}
