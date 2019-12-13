package intcode.params

import intcode.Memory
import java.math.BigInteger

interface Parameter {
    fun getValue(memory: Memory): BigInteger
    fun setValue(value: BigInteger, memory: Memory)
    fun getMemoryAddress(memory: Memory): Int
}