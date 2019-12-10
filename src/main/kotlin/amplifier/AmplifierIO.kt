package amplifier

import io.IOInterface

class AmplifierIO(val input1: Int, val input2: Int) : IOInterface {
    private var count = 0
    var output = 0

    override suspend fun readInt(): Int {
        count++
        return when (count) {
            1 -> input1
            2 -> input2
            else -> throw Exception("Invalid usage with more than 2 inputs")
        }
    }

    override suspend fun writeInt(code: Int) {
        output = code
    }
}