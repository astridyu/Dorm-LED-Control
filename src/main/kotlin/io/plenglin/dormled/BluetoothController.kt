package io.plenglin.dormled

import com.fazecast.jSerialComm.SerialPort
import javafx.beans.property.SimpleObjectProperty
import javafx.concurrent.Task
import tornadofx.*
import java.util.logging.Logger
import java.util.prefs.Preferences

class BluetoothController : Controller(), AutoCloseable {
    private val logger = Logger.getLogger(javaClass.canonicalName)

    val connectingTaskProperty = SimpleObjectProperty<Task<*>>()
    var connectingTask by connectingTaskProperty

    val connectionProperty = SimpleObjectProperty<ArduinoConnection?>()
    var connection: ArduinoConnection? by connectionProperty

    val prefs = Preferences.userNodeForPackage(DormLEDControl::class.java)

    override fun close() {
        connection?.close()
    }
}
