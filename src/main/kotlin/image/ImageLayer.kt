package image

class ImageLayer {
    private val wide: Int
    private val height: Int
    private val data: String
    private var memory: MutableMap<Int, IntArray> = mutableMapOf()

    internal constructor(wide: Int, height: Int) {
        this.wide = wide
        this.height = height
        (0 until height).forEach { memory[it] = IntArray(wide) { 0 } }
        this.data = ""
    }

    internal constructor(wide: Int, height: Int, data: String) {
        this.wide = wide
        this.height = height
        this.data = data

        var indexHeight = 0
        var dataIndex = 0
        while (dataIndex < data.length) {
            val row = data.substring(dataIndex until (dataIndex + wide)).map { Integer.valueOf(it.toString()) }.toIntArray()
            memory[indexHeight] = row
            indexHeight++
            dataIndex += wide
        }
    }

    fun countPixels(pixelValue: Int): Int {
        return memory.values
                .map { countPixelsPerRow(pixelValue, it) }
                .filter { it > 0 }
                .sum()
    }

    private fun countPixelsPerRow(pixelValue: Int, row: IntArray): Int {
        return row.filter { it == pixelValue }.count()
    }

    fun setPixel(indexWide: Int, indexHeight: Int, decodePixel: Int) {
        memory[indexHeight]?.set(indexWide, decodePixel)
    }

    fun getPixel(indexWide: Int, indexHeight: Int): Int? {
        return memory[indexHeight]?.get(indexWide)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ImageLayer) return false

        if (wide != other.wide) return false
        if (height != other.height) return false
        if (toString() != other.toString()) return false

        return true
    }

    override fun hashCode(): Int {
        var result = wide
        result = 31 * result + height
        result = 31 * result + memoryString().hashCode()
        return result
    }

    override fun toString(): String {
        return memoryString()
    }

    private fun memoryString(): String {
        return (0 until height).fold("", { acc, i -> acc + "\n" + memory[i]!!.toList().map { Color.TRANSPARENT.fromCode(it) }.joinToString(separator = "") })
    }
}