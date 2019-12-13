package amplifier

import io.IOInterface
import kotlinx.coroutines.channels.Channel
import java.math.BigInteger

class ChannelIO(private val input: Channel<BigInteger>, private val output: Channel<BigInteger>) : IOInterface {

    override suspend fun readValue(): BigInteger {
        return input.receive()
    }

    override suspend fun writeValue(code: BigInteger) {
        output.send(code)
    }

    override suspend fun close() {
        input.close()
    }
}