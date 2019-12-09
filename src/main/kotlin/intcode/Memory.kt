package intcode

class Memory {
    private var input: String
    private var memory: IntArray

    constructor(input: String) {
        this.input = input
        memory = input.split(",").map { Integer.valueOf(it) }.toIntArray()
    }

    fun getAddressValue(address: Int) = memory[address]
    fun setAddressValue(address: Int, value: Int) {
        memory[address] = value
    }

    fun size() = memory.size

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Memory) return false

        if (!memory.contentEquals(other.memory)) return false

        return true
    }

    override fun hashCode(): Int {
        return memory.contentHashCode()
    }

    override fun toString() = memory.joinToString(separator = ",")

    fun clone(): Memory {
        return Memory(input)
    }
}
