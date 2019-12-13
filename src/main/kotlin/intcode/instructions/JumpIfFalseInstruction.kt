package intcode.instructions

import intcode.Memory
import intcode.params.Parameter

data class JumpIfFalseInstruction(val pointer: Int, val param1: Parameter, val param2: Parameter) : Instruction {
    private var nextInstruction: Int = pointer + 3

    override fun nextInstructionPointer(): Int {
        return nextInstruction
    }

    override suspend fun execute(memory: Memory) {
        val value = param1.getValue(memory)
        if (value.toInt() == 0) {
            nextInstruction = param2.getValue(memory).toInt()
        }
    }
}
