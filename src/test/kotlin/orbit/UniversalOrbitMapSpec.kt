package orbit

import org.amshove.kluent.shouldEqual
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object UniversalOrbitMapSpec : Spek({
    describe("An UniversalOrbitMap") {
        it("compute checksum") {
            val rawMap = """
                COM)B
                B)C
                C)D
                D)E
                E)F
                B)G
                G)H
                D)I
                E)J
                J)K
                K)L
            """.trimIndent()
            val map = UniversalOrbitMap(rawMap.lines())
            map.orbitCountChecksum() shouldEqual 42
        }
        it("compute checksum") {
            val rawMap = """
                COM)B
                B)C
                C)D
                D)E
                E)F
                B)G
                G)H
                D)I
                E)J
                J)K
                K)L
                K)YOU
                I)SAN
            """.trimIndent()
            val map = UniversalOrbitMap(rawMap.lines())
            map.minOrbitalTransfer("YOU", "SAN") shouldEqual 4
        }
    }
})