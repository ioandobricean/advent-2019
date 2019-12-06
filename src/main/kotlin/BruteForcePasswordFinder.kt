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
        if (!hasAdjacentDigits(stringValue)) return false
        if (!hasNeverDecreaseDigits(stringValue)) return false
        return true
    }

    fun hasValidLength(pass: String) = pass.length == 6

    fun hasAdjacentDigits(pass: String): Boolean {
        for ((index, number) in pass.withIndex()) {
            if (index > 0 && number == pass[index - 1])
                return true
        }
        return false
    }

    fun hasNeverDecreaseDigits(pass: String): Boolean {
        for ((index, number) in pass.withIndex()) {
            if (index > 0 && number < pass[index - 1])
                return false
        }
        return true
    }

}