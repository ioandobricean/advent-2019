package intcode.params

import intcode.Memory

interface Parameter {
    fun getValue(memory: Memory): Int
    fun setValue(value: Int, memory: Memory)
}