package intcode.params

import intcode.Memory
import java.math.BigInteger

data class PositionParameter(val pointer: Int) : Parameter {
    override fun getValue(memory: Memory): BigInteger {
        return memory.getAddressValue(getMemoryAddress(memory))
    }

    override fun setValue(value: BigInteger, memory: Memory) {
        memory.setAddressValue(getMemoryAddress(memory), value)
    }

    override fun getMemoryAddress(memory: Memory): Int {
        return memory.getAddressValue(pointer).toInt()
    }
}