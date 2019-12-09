package intcode.instructions

import intcode.Memory
import io.IOInterface

data class OutputInstruction(val pointer: Int, val io: IOInterface) : Instruction {
    override fun nextInstructionPointer(): Int = pointer + 2
    override fun execute(memory: Memory) {
        val outputAddress = memory.getAddressValue(pointer + 1)
        val outputValue = memory.getAddressValue(outputAddress)
        io.writeInt(outputValue)
    }
}