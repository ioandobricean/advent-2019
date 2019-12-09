package intcode.params

import intcode.Memory

data class ImmediateParameter(val pointer: Int) : Parameter {
    override fun getValue(memory: Memory): Int {
        return memory.getAddressValue(pointer)
    }

    override fun setValue(value: Int, memory: Memory) {
        memory.setAddressValue(pointer, value)
    }
}