package intcode.instructions

import intcode.Memory
import intcode.params.Parameter
import io.IOInterface

data class InputInstruction(val pointer: Int, val param: Parameter, val io: IOInterface) : Instruction {
    override fun nextInstructionPointer(): Int = pointer + 2
    override suspend fun execute(memory: Memory) {
        val value = io.readValue()
        memory.setAddressValue(param.getMemoryAddress(memory), value)
    }
}