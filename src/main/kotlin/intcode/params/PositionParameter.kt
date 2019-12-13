package intcode.params

import intcode.Memory
import java.math.BigInteger

data class PositionParameter(val pointer: Int) : Parameter {
    override fun getValue(memory: Memory): BigInteger {
        val position = memory.getAddressValue(pointer)
        return memory.getAddressValue(position.toInt())
    }

    override fun setValue(value: BigInteger, memory: Memory) {
        val position = memory.getAddressValue(pointer)
        memory.setAddressValue(position.toInt(), value)
    }
}