package amplifier

import io.IOInterface
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay

class Channel3IO(private val input: Channel<Int>, private val outputInput: Channel<Int>, private val output: Channel<Int>) : IOInterface {

    override suspend fun readInt(): Int {
        return input.receive()
    }

    override suspend fun writeInt(code: Int) {
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