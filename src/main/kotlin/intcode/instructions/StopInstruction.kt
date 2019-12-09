package intcode.instructions

import intcode.Memory

data class StopInstruction(val pointer: Int) : Instruction {
    override fun hasNextOperation(memory: Memory): Boolean = false
    override fun nextInstructionPointer() = pointer + 1

    override fun execute(memory: Memory) {
    }
}