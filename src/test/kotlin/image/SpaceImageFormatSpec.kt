package image

import org.amshove.kluent.shouldEqual
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object SpaceImageFormatSpec : Spek({
    describe("A SpaceImageFormat") {
        it("with 2 layers") {
            val image = SpaceImageFormat(3, 2, "123456789012")
            val layer0 = ImageLayer(3, 2,"123456")
            val layer1 = ImageLayer(3, 2,"789012")
            image.layers shouldEqual mapOf(Pair(0, layer0), Pair(1, layer1))

            layer0.countPixels(0) shouldEqual 0
            layer0.countPixels(1) shouldEqual 1
            layer1.countPixels(0) shouldEqual 1
            layer1.countPixels(1) shouldEqual 1
        }
    }
})