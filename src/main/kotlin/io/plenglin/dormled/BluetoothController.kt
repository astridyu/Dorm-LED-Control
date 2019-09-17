package io.plenglin.dormled

import com.fazecast.jSerialComm.SerialPort
import tornadofx.*
import java.util.logging.Logger

class BluetoothController : Controller() {
    private val logger = Logger.getLogger(javaClass.canonicalName)
    var serialPort: SerialPort? = null
    val serialPortProperty = getProperty(BluetoothController::serialPort)

    init {
        println(configPath)
    }
}