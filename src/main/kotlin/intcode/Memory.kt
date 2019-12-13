package intcode

import java.math.BigInteger

open class Memory {
    private var input: String
    protected var memory: MutableMap<Int, BigInteger> = mutableMapOf()
    private var size = 0

    constructor(input: String) {
        this.input = input
        input.split(",").forEachIndexed { index, value -> memory[index] = BigInteger(value) }
        size = memory.size
    }

    fun getAddressValue(address: Int): BigInteger {
        return memory.getOrDefault(address, BigInteger.ZERO)
    }
    fun setAddressValue(address: Int, value: BigInteger) {
        if (size < address) {
            size = address
        }
        memory[address] = value
    }

    fun size() = size

    override fun hashCode(): Int {
        return memory.hashCode()
    }

    override fun toString() = memory.values.joinToString(separator = ",")

    fun clone(): Memory {
        return Memory(input)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Memory) return false

        if (memory != other.memory) return false
        if (size != other.size) return false

        return true
    }
}
