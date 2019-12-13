package io

import java.math.BigInteger

interface IOInterface {
    suspend fun readValue(): BigInteger
    suspend fun writeValue(code: BigInteger)
    suspend fun close() {}
}

class ConsoleInterface : IOInterface {
    override suspend fun readValue(): BigInteger {
        return BigInteger(readLine()!!)
    }

    override suspend fun writeValue(code: BigInteger) {
        println(code)
    }
}