package intcode

import io.IOInterface

class MockIO(val inputCode: Int = 0) : IOInterface {
    var writtenCode = 0;
    override suspend fun readInt(): Int {
        return inputCode
    }

    override suspend fun writeInt(code: Int) {
        writtenCode = code
    }
}