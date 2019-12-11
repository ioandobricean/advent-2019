package app

import image.SpaceImageFormat

fun main() {
    val input = SpaceImageFormat::class.java.getResource("../app/image-day8.txt").readText()
    val image = SpaceImageFormat(25, 6, input)
    val (layerIndex, _) = image.layers
            .map { (index, layer) -> Pair(index, layer.countPixels(0)) }
            .minBy { it.second }!!

    val layer = image.layers[layerIndex] ?: error("No layer was found")
    println(layer)
    val count1 = layer.countPixels(1)
    val count2 = layer.countPixels(2)
    println("Count 1's = $count1")
    println("Count 2's = $count2")
    println("1's * 2's =  ${count1 * count2}")

    println(image.decode())
}