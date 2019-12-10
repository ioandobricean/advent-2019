package amplifier

import intcode.Memory
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch

class AmplifierControllerSoftware {
    suspend fun maxSignal(phaseSettings: List<Int>, memory: Memory): Int {
        val ampA = Amplifier("A", memory.clone())
        val ampB = Amplifier("B", memory.clone())
        val ampC = Amplifier("C", memory.clone())
        val ampD = Amplifier("D", memory.clone())
        val ampE = Amplifier("E", memory.clone())

        val channel1 = Channel<Int>()
        val channel2 = Channel<Int>()
        val channel3 = Channel<Int>()
        val channel4 = Channel<Int>()
        val channel5 = Channel<Int>()
        val channelOutput = Channel<Int>()

        val job1 = GlobalScope.launch {
            println("Start ${ampA.name}")
            ampA.amplify(ChannelIO(channel1, channel2))
            println("End ${ampA.name}")
        }
        val job2 = GlobalScope.launch {
            println("Start ${ampB.name}")
            ampB.amplify(ChannelIO(channel2, channel3))
            println("End ${ampB.name}")
        }
        val job3 = GlobalScope.launch {
            println("Start ${ampC.name}")
            ampC.amplify(ChannelIO(channel3, channel4))
            println("End ${ampC.name}")
        }
        val job4 = GlobalScope.launch {
            println("Start ${ampD.name}")
            ampD.amplify(ChannelIO(channel4, channel5))
            println("End ${ampD.name}")
        }
        val job5 = GlobalScope.launch {
            println("Start ${ampE.name}")
            ampE.amplify(ChannelIO(channel5, channelOutput))
            println("End ${ampE.name}")
        }

        channel1.send(phaseSettings[0])
        channel2.send(phaseSettings[1])
        channel3.send(phaseSettings[2])
        channel4.send(phaseSettings[3])
        channel5.send(phaseSettings[4])
        channel1.send(0) // initial value

        var result = 0
        val jobOutput = GlobalScope.launch {
            result = channelOutput.receive()
        }

        job1.join()
        job2.join()
        job3.join()
        job4.join()
        job5.join()
        jobOutput.join()

        return result
    }
}
