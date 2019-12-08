package io

abstract class IOInterface {
    abstract fun readInt() : Int
    abstract fun writeInt(code: Int)
}

class ConsoleInterface: IOInterface() {
    override fun readInt(): Int {
        return Integer.valueOf(readLine()!!)
    }

    override fun writeInt(code: Int) {
        println(code)
    }

}