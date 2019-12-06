package app

import RocketModule
import java.io.File

fun main() {
    val filePath = RocketModule::class.java.getResource("app/day1.txt").file
    val input = File(filePath).readLines().map { Integer.valueOf(it) }
    val requiredFuel = input.map { RocketModule(it).requiredFuel() }.sum()
    print("Required Fuel is $requiredFuel")
}