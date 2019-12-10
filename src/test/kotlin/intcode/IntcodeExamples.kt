package intcode

import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldEqual
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object IntcodeExamples : Spek({
    describe("A Compare instruction") {
        it("with input 8 outputs 1 using position params") {
            val memory = Memory("3,9,8,9,10,9,4,9,99,-1,8")
            val mockIO = MockIO(8)
            val intcode = Intcode(memory, mockIO)
            runBlocking { intcode.execute() }
            mockIO.writtenCode shouldEqual 1
        }
        it("with input <>8 outputs 0 using position params") {
            val memory = Memory("3,9,8,9,10,9,4,9,99,-1,8")
            val mockIO = MockIO(7)
            val intcode = Intcode(memory, mockIO)
            runBlocking { intcode.execute() }
            mockIO.writtenCode shouldEqual 0
        }
    }
})