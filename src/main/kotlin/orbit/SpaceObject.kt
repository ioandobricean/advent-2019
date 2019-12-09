package orbit

data class SpaceObject(val code: String) {
    var linkedObject: SpaceObject? = null

    fun orbitsAround(mainObject: SpaceObject) {
        linkedObject = mainObject
    }
}