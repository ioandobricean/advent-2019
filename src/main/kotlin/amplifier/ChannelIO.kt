package amplifier

import io.IOInterface
import kotlinx.coroutines.channels.Channel

class ChannelIO(private val input: Channel<Int>, private val output: Channel<Int>) : IOInterface {
    override suspend fun readInt(): Int {
        return input.receive()
    }

    override suspend fun writeInt(code: Int) {
        output.send(code)
    }

    override suspend fun close() {
        input.close()
    }
}