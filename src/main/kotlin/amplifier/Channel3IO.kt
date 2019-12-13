package amplifier

import io.IOInterface
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import java.math.BigInteger

class Channel3IO(private val input: Channel<BigInteger>, private val outputInput: Channel<BigInteger>, private val output: Channel<BigInteger>) : IOInterface {

    override suspend fun readValue(): BigInteger {
        return input.receive()
    }

    override suspend fun writeValue(code: BigInteger) {
        delay(1)  // sometimes the processing is too fast and doesn't execute Halt Operation
        if (outputInput.isClosedForSend || outputInput.isClosedForReceive) {
            output.send(code)
        } else {
            outputInput.send(code)
        }
    }

    override suspend fun close() {
        if (!input.isEmpty) {
            // send value to other output before closing the channel
            // maye the processing was too fast to execute Halt Operation
            output.send(input.receive())
        }
        input.close()
    }
}