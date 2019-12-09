package intcode.params

import intcode.Memory

data class PositionParameter(val pointer: Int) : Parameter {
    override fun getValue(memory: Memory): Int {
        val position = memory.getAddressValue(pointer)
        return memory.getAddressValue(position)
    }

    override fun setValue(value: Int, memory: Memory) {
        val position = memory.getAddressValue(pointer)
        memory.setAddressValue(position, value)
    }
}