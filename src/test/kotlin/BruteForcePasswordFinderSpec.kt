import org.amshove.kluent.shouldBe
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object BruteForcePasswordFinderSpec : Spek({
    describe("A password") {
        it("should have length 6 ") {
            val passwordFinder = BruteForcePasswordFinder()
            passwordFinder.hasValidLength("123456") shouldBe true
            passwordFinder.hasValidLength("12345") shouldBe false
            passwordFinder.hasValidLength("1234567") shouldBe false
        }
        it("should have 2 adjacent digits") {
            val passwordFinder = BruteForcePasswordFinder()
            passwordFinder.hasAdjacentDigits("122345") shouldBe true
            passwordFinder.hasAdjacentDigits("111111") shouldBe true
            passwordFinder.hasAdjacentDigits("123456") shouldBe false
        }
        it("should only increase digits") {
            val passwordFinder = BruteForcePasswordFinder()
            passwordFinder.hasNeverDecreaseDigits("111111") shouldBe true
            passwordFinder.hasNeverDecreaseDigits("223450") shouldBe false
        }
        it("match all password conditions") {
            val passwordFinder = BruteForcePasswordFinder()
            passwordFinder.matchPass(111111) shouldBe true
            passwordFinder.matchPass(223450) shouldBe false
            passwordFinder.matchPass(123789) shouldBe false
        }
    }
})