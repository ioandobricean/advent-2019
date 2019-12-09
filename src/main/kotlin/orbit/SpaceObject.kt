package orbit

data class SpaceObject(val code: String) {
    var orbitsAround: SpaceObject? = null

    fun orbitsAroundObject(mainObject: SpaceObject) {
        orbitsAround = mainObject
    }

    fun pathToObject(objectCode: String): List<String> {
        return if (orbitsAround == null) listOf()
        else pathToObject(mutableListOf<String>(), orbitsAround!!, objectCode)
    }

    fun pathToObject(acc: MutableList<String>, objectCode: SpaceObject, destObjectCode: String): List<String> {
        acc.add(objectCode.code)
        if (objectCode.code == destObjectCode || objectCode.orbitsAround == null) {
            return acc
        }
        return pathToObject(acc, objectCode.orbitsAround!!, destObjectCode)
    }
}