import org.amshove.kluent.`should be`
import org.amshove.kluent.shouldEqualTo
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object ModuleSpec : Spek({
    describe("A Module") {
        it("of mass 12 require fuel 2") {
            val module = Module(12)
            module.requiredFuel() shouldEqualTo 2
        }
        it("of mass 14 require fuel 2") {
            val module = Module(12)
            module.requiredFuel() shouldEqualTo 2
        }
        it("of mass 1969 require fuel 654") {
            val module = Module(1969)
            module.requiredFuel() shouldEqualTo 654
        }
        it("of mass 100756 require fuel 33583") {
            val module = Module(100756)
            module.requiredFuel() shouldEqualTo 33583
        }
    }
})
