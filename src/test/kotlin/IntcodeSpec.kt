import org.amshove.kluent.shouldEqual
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object IntcodeSpec : Spek({
    describe("An Intcode program") {
        it("adds 2 numbers") {
            val intcode = Intcode("1,5,6,3,99,10,20")
            intcode.execute() shouldEqual Intcode("1,5,6,30,99,10,20")
        }
        it("multiply 2 numbers") {
            val intcode = Intcode("2,5,6,3,99,10,20")
            intcode.execute() shouldEqual Intcode("2,5,6,200,99,10,20")
        }
        it("add then multiply 2 numbers") {
            val intcode = Intcode("1,9,10,3,2,9,10,7,99,10,20")
            intcode.execute() shouldEqual Intcode("1,9,10,30,2,9,10,200,99,10,20")
        }
    }

    describe("Intcode test for") {
        it("adds 2 numbers") {
            val intcode = Intcode("1,0,0,0,99")
            intcode.execute() shouldEqual Intcode("2,0,0,0,99")
        }
        it("multiply 2 numbers") {
            val intcode = Intcode("2,3,0,3,99")
            intcode.execute() shouldEqual Intcode("2,3,0,6,99")
        }
        it("multiply 2 other numbers") {
            val intcode = Intcode("2,4,4,5,99,0")
            intcode.execute() shouldEqual Intcode("2,4,4,5,99,9801")
        }
        it("add then multiply 2 numbers") {
            val intcode = Intcode("1,1,1,4,99,5,6,0,99")
            intcode.execute() shouldEqual Intcode("30,1,1,4,2,5,6,0,99")
        }
    }
})