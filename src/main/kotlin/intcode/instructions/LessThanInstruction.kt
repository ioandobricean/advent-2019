package intcode.instructions

import intcode.Memory
import intcode.params.Parameter

data class LessThanInstruction(val pointer: Int, val param1: Parameter, val param2: Parameter, val param3: Parameter) : Instruction {

    override fun nextInstructionPointer(): Int {
        return pointer + 4
    }

    override suspend fun execute(memory: Memory) {
        val value1 = param1.getValue(memory)
        val value2 = param2.getValue(memory)
        val value = if (value1 < value2) 1 else 0
        param3.setValue(value.toBigInteger(), memory)
    }
}
