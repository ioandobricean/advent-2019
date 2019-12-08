package intcode

abstract class Parameter {
    abstract fun getValue(memory: Memory): Int
    abstract fun setValue(value: Int, memory: Memory)
}

data class PositionParameter(val pointer: Int) : Parameter() {
    override fun getValue(memory: Memory): Int {
        val position = memory.getAddressValue(pointer)
        return memory.getAddressValue(position)
    }

    override fun setValue(value: Int, memory: Memory) {
        val position = memory.getAddressValue(pointer)
        memory.setAddressValue(position, value)
    }
}

data class ImmediateParameter(val pointer: Int) : Parameter() {
    override fun getValue(memory: Memory): Int {
        return memory.getAddressValue(pointer)
    }

    override fun setValue(value: Int, memory: Memory) {
        memory.setAddressValue(pointer, value)
    }
}