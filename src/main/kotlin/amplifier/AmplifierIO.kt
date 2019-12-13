package amplifier

import io.IOInterface
import java.math.BigInteger

class AmplifierIO(val input1: BigInteger, val input2: BigInteger) : IOInterface {
    private var count = 0
    var output = BigInteger.ZERO

    override suspend fun readValue(): BigInteger {
        count++
        return when (count) {
            1 -> input1
            2 -> input2
            else -> throw Exception("Invalid usage with more than 2 inputs")
        }
    }

    override suspend fun writeValue(code: BigInteger) {
        output = code
    }
}