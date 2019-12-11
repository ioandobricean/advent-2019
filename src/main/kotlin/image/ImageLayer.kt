package image

class ImageLayer {
    private val wide: Int
    private val height: Int
    private val data: String
    private var memory: MutableMap<Int, IntArray> = mutableMapOf()

    internal constructor(wide: Int, height: Int, data: String) {
        this.wide = wide
        this.height = height
        this.data = data

        var index = 0
        var dataIndex = 0
        while (dataIndex < data.length) {
            val row = data.substring(dataIndex until (dataIndex + wide)).map { Integer.valueOf(it.toString()) }.toIntArray()
            memory[index] = row
            index++
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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ImageLayer) return false

        if (wide != other.wide) return false
        if (height != other.height) return false
        if (data != other.data) return false

        return true
    }

    override fun hashCode(): Int {
        var result = wide
        result = 31 * result + height
        result = 31 * result + data.hashCode()
        return result
    }

    override fun toString(): String {
        return "ImageLayer(wide=$wide, height=$height, data='$data')"
    }
}