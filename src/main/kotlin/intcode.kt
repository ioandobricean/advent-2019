import sun.reflect.generics.reflectiveObjects.NotImplementedException

data class Intcode(val input: String) {
    fun execute(): Intcode {
        val split: ArrayList<Int> = ArrayList(input.split(",").map { Integer.valueOf(it) })
        var opIndex = 0
        do {
            val operation = operationFactory(opIndex, split)
            if (!operation.hasNextOperation()) {
                break
            }
            println(operation)
            operation.execute()
            opIndex = operation.nextOperationIndex()
        } while (opIndex < split.size)
        return Intcode(split.joinToString(separator = ","))
    }

}

fun operationFactory(opIndex: Int, code: ArrayList<Int>): Operation {
    val opCode = code[opIndex]
    return when (opCode) {
        1 -> AddOperation(opIndex, code)
        2 -> MultiplyOperation(opIndex, code)
        99 -> StopOperation()
        else -> throw NotImplementedException()
    }
}

class StopOperation : Operation() {
    override fun hasNextOperation(): Boolean = false
    override fun nextOperationIndex(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun execute() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}

abstract class Operation {
    abstract fun hasNextOperation(): Boolean
    abstract fun nextOperationIndex(): Int
    abstract fun execute()
}

data class AddOperation(val opIndex: Int, val code: ArrayList<Int>) : Operation() {
    override fun hasNextOperation(): Boolean = nextOperationIndex() < code.size
    override fun nextOperationIndex(): Int = opIndex + 4

    override fun execute() {
        val posInput1 = code[opIndex + 1]
        val posInput2 = code[opIndex + 2]
        val posOutput = code[opIndex + 3]
        code[posOutput] = code[posInput1] + code[posInput2]
        println("[$posOutput] = [$posInput1] + [$posInput2] = ${code[posInput1]} + ${code[posInput2]}")
    }
}

data class MultiplyOperation(val opIndex: Int, val code: ArrayList<Int>) : Operation() {
    override fun hasNextOperation(): Boolean = nextOperationIndex() < code.size
    override fun nextOperationIndex(): Int = opIndex + 4
    override fun execute() {
        val posInput1 = code[opIndex + 1]
        val posInput2 = code[opIndex + 2]
        val posOutput = code[opIndex + 3]
        code[posOutput] = code[posInput1] * code[posInput2]
    }
}