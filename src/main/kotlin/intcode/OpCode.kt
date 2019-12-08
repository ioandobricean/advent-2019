package intcode

data class OpCode(val pointer: Int, val code: Int) {
    fun getInstruction(): Instruction {
        val stringCode = code.toString()
        val params = extractParams(stringCode, pointer)
        println(params)
        return when (extractInstructionCode(stringCode)) {
            "01", "1" -> AddInstruction(pointer, params[0], params[1], params[2])
            "02", "2" -> MultiplyInstruction(pointer, params[0], params[1], params[2])
            "99" -> StopInstruction(pointer)
            else -> throw Exception("Not supported operation code")
        }
    }

    private fun extractParams(stringCode: String, pointer: Int): List<Parameter> {
        val numberOfParams = 3
        val pointerParam1 = pointer + 1
        val pointerParam2 = pointer + 2
        val pointerOutput = pointer + numberOfParams
        val length = stringCode.length
        if (length <= 2) {
            return listOf(PositionParameter(pointerParam1), PositionParameter(pointerParam2), PositionParameter(pointerOutput))
        } else {
            var paramsString = stringCode.substring(0 until length - 2)
            val initialLength = paramsString.length
            // add missing 0 in front of string
            for (i in 0 until (numberOfParams - initialLength)) {
                paramsString = "0$paramsString"
            }
            println(paramsString)
            val params = mutableListOf<Parameter>()
            for ((index, code) in paramsString.withIndex().reversed()) {
                if (code.toString() == "0") params.add(PositionParameter(pointer + (numberOfParams - index)))
                else if (code.toString() == "1") params.add(ImmediateParameter(pointer + (numberOfParams - index)))
                else throw Exception("Invalid parameter code $code")
            }
            return params
        }
    }

    /**
     * The rightmost 2 characters of code
     */
    private fun extractInstructionCode(stringCode: String): String {
        val length = stringCode.length
        return if (length == 1) stringCode
        else stringCode.substring((length - 2) until length)
    }
}