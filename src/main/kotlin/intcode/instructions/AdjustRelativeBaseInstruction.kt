package intcode.instructions

import intcode.Memory
import intcode.RelativeBase
import intcode.params.Parameter

data class AdjustRelativeBaseInstruction(val pointer: Int, val param: Parameter, val relativeBase: RelativeBase) : Instruction {
    override fun nextInstructionPointer(): Int = pointer + 2

    override suspend fun execute(memory: Memory) {
        relativeBase.updateOffset(param.getValue(memory).toInt())
    }
}