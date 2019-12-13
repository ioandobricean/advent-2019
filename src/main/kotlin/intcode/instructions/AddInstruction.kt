package intcode.instructions

import intcode.Memory
import intcode.params.Parameter

data class AddInstruction(val pointer: Int, val param1: Parameter, val param2: Parameter, val output: Parameter) : Instruction {
    override fun nextInstructionPointer(): Int = pointer + 4

    override suspend fun execute(memory: Memory) {
        if (!hasNextOperation(memory)) {
            println("Buffer Overflow")
            return
        }
        val value1 = param1.getValue(memory)
        val value2 = param2.getValue(memory)
        output.setValue(value1 + value2, memory)
    }
}