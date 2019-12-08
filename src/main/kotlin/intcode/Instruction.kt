package intcode

fun buildInstruction(pointer: Int, memory: Memory): Instruction {
    val opCode = memory.getAddressValue(pointer)
    return when (opCode) {
        1 -> AddInstruction(pointer, memory)
        2 -> MultiplyInstruction(pointer, memory)
        99 -> StopInstruction(pointer)
        else -> throw Exception("Not supported operation code")
    }
}

class StopInstruction(val pointer: Int) : Instruction() {
    override fun hasNextOperation(): Boolean = false
    override fun nextInstructionPointer() = pointer + 1

    override fun execute() {
    }

}

abstract class Instruction {
    abstract fun hasNextOperation(): Boolean
    abstract fun nextInstructionPointer(): Int
    abstract fun execute()
}

data class AddInstruction(val pointer: Int, val memory: Memory) : Instruction() {
    override fun hasNextOperation(): Boolean = nextInstructionPointer() < memory.size()
    override fun nextInstructionPointer(): Int = pointer + 4

    override fun execute() {
        if (!hasNextOperation()) {
            println("Buffer Overflow")
            return
        }
        val posInput1 = memory.getAddressValue(pointer + 1)
        val posInput2 = memory.getAddressValue(pointer + 2)
        val posOutput = memory.getAddressValue(pointer + 3)
        val value1 = memory.getAddressValue(posInput1)
        val value2 = memory.getAddressValue(posInput2)
        memory.setAddressValue(posOutput, value1 + value2)
    }
}

data class MultiplyInstruction(val pointer: Int, val memory: Memory) : Instruction() {
    override fun hasNextOperation(): Boolean = nextInstructionPointer() < memory.size()
    override fun nextInstructionPointer(): Int = pointer + 4
    override fun execute() {
        if (!hasNextOperation()) {
            println("Buffer Overflow")
            return
        }
        val posInput1 = memory.getAddressValue(pointer + 1)
        val posInput2 = memory.getAddressValue(pointer + 2)
        val posOutput = memory.getAddressValue(pointer + 3)
        val value1 = memory.getAddressValue(posInput1)
        val value2 = memory.getAddressValue(posInput2)
        memory.setAddressValue(posOutput, value1 * value2)
    }
}