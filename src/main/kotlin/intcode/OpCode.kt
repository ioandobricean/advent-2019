package intcode

import intcode.instructions.EqualsInstruction
import intcode.instructions.JumpIfFalseInstruction
import intcode.instructions.JumpIfTrueInstruction
import intcode.instructions.LessThanInstruction
import io.IOInterface

class OpCode(private val pointer: Int, private val code: Int, private val io: IOInterface) {
    fun getInstruction(): Instruction {
        val stringCode = normalizeCode()
        val params = extractParams(stringCode, pointer)
        return when (val instructionCode = extractInstructionCode(stringCode)) {
            "01" -> AddInstruction(pointer, params[0], params[1], params[2] as PositionParameter)
            "02" -> MultiplyInstruction(pointer, params[0], params[1], params[2] as PositionParameter)
            "03" -> InputInstruction(pointer, io)
            "04" -> OutputInstruction(pointer, io)
            "05" -> JumpIfTrueInstruction(pointer, params[0], params[1])
            "06" -> JumpIfFalseInstruction(pointer, params[0], params[1])
            "07" -> LessThanInstruction(pointer, params[0], params[1], params[2])
            "08" -> EqualsInstruction(pointer, params[0], params[1], params[2])
            "99" -> StopInstruction(pointer)
            else -> throw Exception("Not supported operation code $instructionCode from $this")
        }
    }

    private fun normalizeCode(): String {
        var stringCode = code.toString()
        val initialLength = stringCode.length
        // add missing 0 in front of the number
        for (i in 0 until (5 - initialLength)) {
            stringCode = "0$stringCode"
        }
        return stringCode
    }

    private fun extractParams(stringCode: String, pointer: Int): List<Parameter> {
        val numberOfParams = 3
        val params = mutableListOf<Parameter>()
        var paramsString = stringCode.substring(0 until numberOfParams)
        for ((index, code) in paramsString.withIndex().reversed()) {
            when (code.toString()) {
                "0" -> params.add(PositionParameter(pointer + (numberOfParams - index)))
                "1" -> params.add(ImmediateParameter(pointer + (numberOfParams - index)))
                else -> throw Exception("Invalid parameter code $code")
            }
        }
        return params
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