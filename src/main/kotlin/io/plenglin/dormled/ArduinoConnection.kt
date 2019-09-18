package io.plenglin.dormled

import com.fazecast.jSerialComm.SerialPort
import java.io.PrintStream
import java.util.*
import java.util.concurrent.locks.ReentrantLock
import java.util.logging.Logger
import kotlin.concurrent.withLock

class ArduinoConnection(val serialPort: SerialPort) : AutoCloseable {
    private val logger = Logger.getLogger(javaClass.canonicalName)

    init {
        serialPort.openPort()
    }

    private val tx = PrintStream(serialPort.outputStream)
    private val timer = Timer()

    private fun write(cmd: String) {
        logger.fine { "Sending: $cmd" }
        tx.println(cmd)
    }

    fun fill(r: Int, g: Int, b: Int) {
        tx.println("fill $r $g $b")
    }

    fun hue(delta: Int, step: Int, period: Int) {
        tx.println("hue $delta $step $period")
    }

    override fun close() {
        logger.info("Closing")
        tx.close()
    }

}