package intcode

data class RelativeBase(private var offset: Int = 0) {
    fun updateOffset(value: Int) {
        this.offset += value
    }

    fun getOffset(): Int {
        return offset
    }
}
