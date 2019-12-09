package amplifier

import intcode.Memory

class AmplifierControllerSoftware {
    fun maxSignal(phaseSettings: List<Int>, memory: Memory): Int {
        val ampA = Amplifier(phaseSettings[0], memory.clone())
        val outputAmpA = ampA.amplify(0)
        val ampB = Amplifier(phaseSettings[1], memory.clone())
        val outputAmpB = ampB.amplify(outputAmpA)
        val ampC = Amplifier(phaseSettings[2], memory.clone())
        val outputAmpC = ampC.amplify(outputAmpB)
        val ampD = Amplifier(phaseSettings[3], memory.clone())
        val outputAmpD = ampD.amplify(outputAmpC)
        val ampE = Amplifier(phaseSettings[4], memory.clone())
        return ampE.amplify(outputAmpD)
    }
}
