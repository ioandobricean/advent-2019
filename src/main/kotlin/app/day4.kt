package app

import BruteForcePasswordFinder

fun main() {
    val passwordFinder = BruteForcePasswordFinder()
    val possiblePasswords = passwordFinder.withinRange(372037, 905157)

    println(possiblePasswords)
    println(possiblePasswords.size)
}