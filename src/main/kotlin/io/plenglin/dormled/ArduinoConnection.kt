package io.plenglin.dormled

import com.fazecast.jSerialComm.SerialPort
import java.io.PrintStream
import java.util.*
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

class ArduinoConnection(val serialPort: SerialPort) : AutoCloseable {

    init {
        serialPort.openPort()
    }

    private val tx = PrintStream(serialPort.outputStream)
    private val timer = Timer()

    fun write(cmd: String) {
        tx.println(cmd)
    }

    override fun close() {
        tx.close()
    }

}