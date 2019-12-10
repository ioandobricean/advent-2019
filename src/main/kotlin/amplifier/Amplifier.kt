package amplifier

import intcode.Intcode
import intcode.Memory
import io.IOInterface

class Amplifier(val name: String = "", val memory: Memory) {
    suspend fun amplify(io: IOInterface) {
        val intcode = Intcode(memory, io, name)
        intcode.execute()
    }
}
