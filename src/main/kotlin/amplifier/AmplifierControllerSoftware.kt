package amplifier

import intcode.Memory

class AmplifierControllerSoftware {
    fun maxSignal(phaseSettings: List<Int>, memory: Memory): Int {
        val ampA = Amplifier(phaseSettings[0], memory.clone())
        val ampB = Amplifier(phaseSettings[1], memory.clone())
        val ampC = Amplifier(phaseSettings[2], memory.clone())
        val ampD = Amplifier(phaseSettings[3], memory.clone())
        val ampE = Amplifier(phaseSettings[4], memory.clone())

        val outputAmpA = ampA.amplify(0)
        val outputAmpB = ampB.amplify(outputAmpA)
        val outputAmpC = ampC.amplify(outputAmpB)
        val outputAmpD = ampD.amplify(outputAmpC)
        return ampE.amplify(outputAmpD)
    }
}
