package intcode

import io.IOInterface

class MockIO(val inputCode: Int = 0) : IOInterface() {
    var writtenCode = 0;
    override fun readInt(): Int {
        return inputCode
    }

    override fun writeInt(code: Int) {
        writtenCode = code
    }
}