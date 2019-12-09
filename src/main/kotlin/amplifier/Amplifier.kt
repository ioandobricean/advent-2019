package amplifier

import intcode.Intcode
import intcode.Memory

class Amplifier(val phaseSetting: Int, val memory: Memory) {
    fun amplify(input: Int): Int {
        val io = AmplifierIO(phaseSetting, input)
        val intcode = Intcode(memory, io)
        intcode.execute()
        return io.output
    }

}
