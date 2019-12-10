package amplifier

import intcode.Memory
import io.IOInterface
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch

class AmplifierControllerSoftware {
    suspend fun maxSignal(phaseSettings: List<Int>, memory: Memory): Int {
        val ampA = Amplifier("A", memory.clone())
        val ampB = Amplifier("B", memory.clone())
        val ampC = Amplifier("C", memory.clone())
        val ampD = Amplifier("D", memory.clone())
        val ampE = Amplifier("E", memory.clone())

        val channelA = Channel<Int>()
        val channelB = Channel<Int>()
        val channelC = Channel<Int>()
        val channelD = Channel<Int>()
        val channelE = Channel<Int>()
        val channelOutput = Channel<Int>()

        val jobA = launchAmplifier(ampA, Channel3IO(channelA, channelB, channelOutput))
        val jobB = launchAmplifier(ampB, ChannelIO(channelB, channelC))
        val jobC = launchAmplifier(ampC, ChannelIO(channelC, channelD))
        val jobD = launchAmplifier(ampD, ChannelIO(channelD, channelE))
        val jobE = launchAmplifier(ampE, Channel3IO(channelE, channelA, channelOutput))

        // Send phaseSettings as first input param
        channelA.send(phaseSettings[0])
        channelB.send(phaseSettings[1])
        channelC.send(phaseSettings[2])
        channelD.send(phaseSettings[3])
        channelE.send(phaseSettings[4])
        channelA.send(0) // initial value

        var result = 0
        val jobOutput = GlobalScope.launch {
            result = channelOutput.receive()
        }

        jobA.join()
        jobB.join()
        jobC.join()
        jobD.join()
        jobE.join()
        jobOutput.join()

        return result
    }

    private fun launchAmplifier(amp: Amplifier, io: IOInterface): Job =
            GlobalScope.launch {
                println("Start ${amp.name}")
                amp.amplify(io)
                println("End ${amp.name}")
            }
}
