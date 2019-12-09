package app

import orbit.SpaceObject
import orbit.UniversalOrbitMap
import java.io.File

fun main() {
    val filePath = SpaceObject::class.java.getResource("../app/orbit-map.txt").file
    val orbitMap = UniversalOrbitMap(File(filePath).readLines())
    println(orbitMap.orbitCountChecksum())
}