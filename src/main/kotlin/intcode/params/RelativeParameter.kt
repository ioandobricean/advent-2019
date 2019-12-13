package intcode.params

import intcode.Memory
import intcode.RelativeBase
import java.math.BigInteger

class RelativeParameter(val pointer: Int, val relativeBase: RelativeBase) : Parameter {
    override fun getValue(memory: Memory): BigInteger {
        val position = getMemoryAddress(memory)
        return memory.getAddressValue(position)
    }

    override fun setValue(value: BigInteger, memory: Memory) {
        val position = getMemoryAddress(memory)
        memory.setAddressValue(position, value)
    }

    override fun getMemoryAddress(memory: Memory): Int {
        return relativeBase.getOffset() + memory.getAddressValue(pointer).toInt()
    }
}

