data class RocketModule(val mass: Int) {
    fun requiredFuel(): Int {
        val fuel = computeRequiredFuel(mass)
        return fuel + requiredFuelForFuel(fuel)
    }

    private fun computeRequiredFuel(mass: Int): Int {
        return mass / 3 - 2
    }

    private fun requiredFuelForFuel(fuelMass: Int): Int {
        val computeRequiredFuel = computeRequiredFuel(fuelMass);
        return if (computeRequiredFuel <= 0)
            0
        else
            computeRequiredFuel + requiredFuelForFuel(computeRequiredFuel)
    }
}

