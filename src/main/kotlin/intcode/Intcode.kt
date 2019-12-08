package intcode

data class Intcode(val memory: Memory) {
    fun execute() {
        var pointer = 0
        do {
            val operation = buildInstruction(pointer, memory)
            operation.execute()
            pointer = operation.nextInstructionPointer()
        } while (operation.hasNextOperation())
    }

}