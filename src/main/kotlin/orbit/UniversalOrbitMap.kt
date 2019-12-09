package orbit

class UniversalOrbitMap(val rawOrbitData: List<String>) {
    val orbitDelimiters = ")"
    val centerOfMassCode = "COM"

    private val spaceObjects = mutableMapOf<String, SpaceObject>()

    fun minOrbitalTransfer(fromObjectCode: String, toObjectCode: String): Int {
        if (spaceObjects.isEmpty()) {
            orbitCountChecksum()
        }
        val fromObject = spaceObjects[fromObjectCode] ?: throw Exception("Invalid $fromObjectCode object code in map")
        val toObject = spaceObjects[toObjectCode] ?: throw Exception("Invalid $toObjectCode object code in map")
        return distanceBetweenObjects(fromObject, toObject)
    }

    private fun distanceBetweenObjects(fromObject: SpaceObject, toObject: SpaceObject): Int {
        val path1 = fromObject.pathToObject(centerOfMassCode)
        val path2 = toObject.pathToObject(centerOfMassCode)
        val commonObject = path1.intersect(path2).first()
        return fromObject.pathToObject(commonObject).size + toObject.pathToObject(commonObject).size - 2 // -2 direct orbits don;t count
    }

    /**
     * The total number of direct orbits and indirect orbits
     **/
    fun orbitCountChecksum(): Int {
        for (line in rawOrbitData) {
            val (obj1, obj2) = line.split(orbitDelimiters)
            val spaceObject1 = spaceObjects.getOrPut(obj1, { SpaceObject(obj1) })
            val spaceObject2 = spaceObjects.getOrPut(obj2, { SpaceObject(obj2) })
            spaceObject2.orbitsAroundObject(spaceObject1)
        }
        return computeChecksum()
    }

    private fun computeChecksum(): Int {
        var sum = 0
        for (obj in spaceObjects.values) {
            sum += computeOrbits(0, obj)
        }
        return sum
    }

    private fun computeOrbits(acc: Int, obj: SpaceObject): Int {
        return if (obj.orbitsAround == null) {
            acc
        } else {
            computeOrbits(acc + 1, obj.orbitsAround!!)
        }
    }
}
