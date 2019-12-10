package amplifier

import intcode.Intcode
import intcode.Memory

class Amplifier(val name: String = "", val memory: Memory) {
    suspend fun amplify(io: ChannelIO) {
        val intcode = Intcode(memory, io, name)
        intcode.execute()
    }
}
