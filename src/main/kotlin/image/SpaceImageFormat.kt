package image

class SpaceImageFormat() {
    val layers: MutableMap<Int, ImageLayer> = mutableMapOf()

    constructor(wide: Int, height: Int, data: String) : this() {
        val layerSize = wide * height
        if (data.length % layerSize != 0) {
            error("Invalid input data. Should be multiple of $wide * $height")
        }
        var index = 0
        var dataIndex = 0
        while (dataIndex < data.length) {
            val layer = ImageLayer(wide, height, data.substring(dataIndex until (dataIndex + layerSize)))
            layers[index] = layer
            index++
            dataIndex += layerSize
        }
    }

}
