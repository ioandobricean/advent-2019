package intcode.instructions

import intcode.Memory
import io.IOInterface

data class InputInstruction(val pointer: Int, val io: IOInterface) : Instruction {
    override fun nextInstructionPointer(): Int = pointer + 2
    override fun execute(memory: Memory) {
        val value = io.readInt()
        val outputAddress = memory.getAddressValue(pointer + 1)
        memory.setAddressValue(outputAddress, value)
    }
}