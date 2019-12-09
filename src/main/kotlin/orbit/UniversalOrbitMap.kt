package orbit

class UniversalOrbitMap(val rawOrbitData: List<String>) {
    val orbitDelimiters = ")"

    private val spaceObjects = mutableMapOf<String, SpaceObject>()

    /**
     * The total number of direct orbits and indirect orbits
     **/
    fun orbitCountChecksum(): Int {
        for (line in rawOrbitData) {
            val (obj1, obj2) = line.split(orbitDelimiters)
            val spaceObject1 = spaceObjects.getOrPut(obj1, { SpaceObject(obj1) })
            val spaceObject2 = spaceObjects.getOrPut(obj2, { SpaceObject(obj2) })
            spaceObject2.orbitsAround(spaceObject1)
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
        return if (obj.linkedObject == null) {
            acc
        } else {
            computeOrbits(acc + 1, obj.linkedObject!!)
        }
    }

}
