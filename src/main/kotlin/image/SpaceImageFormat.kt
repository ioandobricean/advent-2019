package image

class SpaceImageFormat {
    private val wide: Int
    private val height: Int
    val layers: MutableMap<Int, ImageLayer> = mutableMapOf()

    constructor(wide: Int, height: Int, data: String) {
        this.wide = wide
        this.height = height
        val layerSize = wide * height
        if (data.length % layerSize != 0) {
            error("Invalid input data. Should be multiple of $wide * $height")
        }
        var indexHeight = 0
        var dataIndex = 0
        while (dataIndex < data.length) {
            val layer = ImageLayer(wide, height, data.substring(dataIndex until (dataIndex + layerSize)))
            layers[indexHeight] = layer
            indexHeight++
            dataIndex += layerSize
        }
    }

    fun decode(): ImageLayer {
        val finalLayer = ImageLayer(wide, height)
        for (indexWide in 0 until wide) {
            for (indexHeight in 0 until height) {
                finalLayer.setPixel(indexWide, indexHeight, decodePixel(indexWide, indexHeight))
            }
        }
        return finalLayer
    }

    private fun decodePixel(x: Int, y: Int): Int {
        for (layerIndex in 0 until layers.size) {
            val layer = layers[layerIndex]
            val pixelValue = layer?.getPixel(x, y)
            if (Color.BLACK.match(pixelValue)) {
                return Color.BLACK.code
            } else if (Color.WHITE.match(pixelValue)) {
                return Color.WHITE.code
            }
            // ignore TRANSPARENT color
        }
        return Color.TRANSPARENT.code
    }
}
