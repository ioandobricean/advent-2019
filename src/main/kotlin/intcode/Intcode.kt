package intcode

data class Intcode(val memory: Memory) {
    fun execute() {
        var pointer = 0
        do {
            val code = memory.getAddressValue(pointer)
            val opCode = OpCode(pointer, code)
            val instruction = opCode.getInstruction()
            println("$instruction")
            instruction.execute(memory)
            pointer = instruction.nextInstructionPointer()
        } while (instruction.hasNextOperation(memory))
    }

}