package intcode.params

import intcode.Memory
import java.math.BigInteger

data class ImmediateParameter(val pointer: Int) : Parameter {
    override fun getValue(memory: Memory): BigInteger {
        return memory.getAddressValue(pointer)
    }

    override fun setValue(value: BigInteger, memory: Memory) {
        memory.setAddressValue(pointer, value)
    }

    override fun getMemoryAddress(memory: Memory): Int {
        return pointer
    }
}