package intcode

class StopInstruction(val pointer: Int) : Instruction() {
    override fun hasNextOperation(memory: Memory): Boolean = false
    override fun nextInstructionPointer() = pointer + 1

    override fun execute(memory: Memory) {
    }

}

abstract class Instruction {
    abstract fun hasNextOperation(memory: Memory): Boolean
    abstract fun nextInstructionPointer(): Int
    abstract fun execute(memory: Memory)
}

data class AddInstruction(val pointer: Int, val param1: Parameter, val param2: Parameter, val output: Parameter) : Instruction() {
    override fun hasNextOperation(memory: Memory): Boolean = nextInstructionPointer() < memory.size()
    override fun nextInstructionPointer(): Int = pointer + 4

    override fun execute(memory: Memory) {
        if (!hasNextOperation(memory)) {
            println("Buffer Overflow")
            return
        }
        val value1 = param1.getValue(memory)
        val value2 = param2.getValue(memory)
        output.setValue(value1 + value2, memory)
    }
}

data class MultiplyInstruction(val pointer: Int, val param1: Parameter, val param2: Parameter, val output: Parameter) : Instruction() {
    override fun hasNextOperation(memory: Memory): Boolean = nextInstructionPointer() < memory.size()
    override fun nextInstructionPointer(): Int = pointer + 4
    override fun execute(memory: Memory) {
        if (!hasNextOperation(memory)) {
            println("Buffer Overflow")
            return
        }
        val value1 = param1.getValue(memory)
        val value2 = param2.getValue(memory)
        output.setValue(value1 * value2, memory)
    }
}