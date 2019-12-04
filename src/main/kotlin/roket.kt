data class Module(val mass: Int) {
    fun requiredFuel(): Int {
        return mass / 3 - 2
    }
}