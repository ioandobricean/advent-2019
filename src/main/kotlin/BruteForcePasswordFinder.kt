class BruteForcePasswordFinder {

    fun withinRange(start: Int, end: Int): List<Int> {
        val matchingPass = mutableListOf<Int>()
        for (pass in start..end) {
            if (matchPass(pass)) matchingPass.add(pass)
        }
        return matchingPass
    }

    fun matchPass(pass: Int): Boolean {
        val stringValue = pass.toString()
        if (!hasValidLength(stringValue)) return false
        if (!hasAdjacentOnly2Digits(stringValue)) return false
        if (!hasNeverDecreaseDigits(stringValue)) return false
        return true
    }

    fun hasValidLength(pass: String) = pass.length == 6

    fun hasAdjacentOnly2Digits(pass: String): Boolean {
        val groups = mutableListOf<String>()
        var startGroup = 0
        for ((index, number) in pass.withIndex()) {
            if (index > 0) {
                if (pass[index - 1] != number) {
                    groups.add(pass.slice(startGroup until index))
                    startGroup = index
                }
                if (index == pass.length - 1) { // last digit
                    groups.add(pass.slice(startGroup until index + 1))
                }
            }
        }
        return groups.any { it.length == 2 }
    }

    fun hasNeverDecreaseDigits(pass: String): Boolean {
        for ((index, number) in pass.withIndex()) {
            if (index > 0 && number < pass[index - 1])
                return false
        }
        return true
    }

}