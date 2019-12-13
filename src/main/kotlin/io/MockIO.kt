package io

import java.math.BigInteger

class MockIO(val inputCode: BigInteger = BigInteger.ZERO) : IOInterface {
    var lastWrittenCode = BigInteger.ZERO
    private val writtenCodes = mutableListOf<BigInteger>()
    override suspend fun readValue(): BigInteger {
        return inputCode
    }

    override suspend fun writeValue(code: BigInteger) {
        lastWrittenCode = code
        writtenCodes.add(code)
    }

    fun writtenOutput(): String {
        return writtenCodes.joinToString(separator = ",")
    }
}