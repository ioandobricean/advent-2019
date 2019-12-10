package io

interface IOInterface {
    suspend fun readInt(): Int
    suspend fun writeInt(code: Int)
    suspend fun close() {}
}

class ConsoleInterface : IOInterface {
    override suspend fun readInt(): Int {
        return Integer.valueOf(readLine()!!)
    }

    override suspend fun writeInt(code: Int) {
        println(code)
    }
}