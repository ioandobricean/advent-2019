package intcode

import io.ConsoleInterface
import io.IOInterface

class Intcode(private val memory: Memory, private val io: IOInterface = ConsoleInterface(), val parent: String = "") {
    suspend fun execute() {
        var pointer = 0
        do {
            val code = memory.getAddressValue(pointer)
            val opCode = OpCode(pointer, code, io)
            val instruction = opCode.getInstruction()
            instruction.execute(memory)
            pointer = instruction.nextInstructionPointer()
        } while (instruction.hasNextOperation(memory))
    }
}