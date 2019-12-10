package intcode.instructions

import intcode.Memory

interface Instruction {
    fun hasNextOperation(memory: Memory): Boolean = nextInstructionPointer() < memory.size()
    fun nextInstructionPointer(): Int
    suspend fun execute(memory: Memory)
}