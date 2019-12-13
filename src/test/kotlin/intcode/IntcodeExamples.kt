package intcode

import io.MockIO
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldEqual
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import java.math.BigInteger

object IntcodeExamples : Spek({
    describe("A Compare instruction") {
        it("with input 8 outputs 1 using position params") {
            val memory = Memory("3,9,8,9,10,9,4,9,99,-1,8")
            val mockIO = MockIO(8.toBigInteger())
            val intcode = Intcode(memory, mockIO)
            runBlocking { intcode.execute() }
            mockIO.lastWrittenCode shouldEqual BigInteger.ONE
        }
        it("with input <>8 outputs 0 using position params") {
            val memory = Memory("3,9,8,9,10,9,4,9,99,-1,8")
            val mockIO = MockIO(7.toBigInteger())
            val intcode = Intcode(memory, mockIO)
            runBlocking { intcode.execute() }
            mockIO.lastWrittenCode shouldEqual BigInteger.ZERO
        }
    }

    describe("Big numbers") {
        it("should produce a large number") {
            val memory = Memory("104,1125899906842624,99")
            val mockIO = MockIO()
            val intcode = Intcode(memory, mockIO)
            runBlocking { intcode.execute() }
            mockIO.lastWrittenCode shouldEqual BigInteger("1125899906842624")
        }
    }

    describe("A Quine instruction set") {
        it("should produce a copy of itself") {
            val input = "109,1,204,-1,1001,100,1,100,1008,100,16,101,1006,101,0,99"
            val memory = Memory(input)
            val io = MockIO()
            val intcode = Intcode(memory, io)
            runBlocking { intcode.execute() }
            io.writtenOutput() shouldEqual input
        }
    }
})