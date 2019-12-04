import java.io.File

fun main() {
    val filePath = Module::class.java.getResource("day1.txt").file
    val input = File(filePath).readLines().map { Integer.valueOf(it) }
    val requiredFuel = input.map { Module(it).requiredFuel() }.sum()
    print("Required Fuel is $requiredFuel")
}