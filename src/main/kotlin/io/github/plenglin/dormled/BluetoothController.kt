package io.github.plenglin.dormled

import com.fazecast.jSerialComm.SerialPort
import javafx.beans.property.BooleanProperty
import javafx.beans.property.SimpleBooleanProperty
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

    val isConnectingProperty = connectingTaskProperty.isNotNull

    val prefs = Preferences.userNodeForPackage(DormLEDControl::class.java)

    fun connectTo(port: SerialPort) {
        connectingTask = runAsync {
            try {
                connection?.close()
                connection = ArduinoConnection(port)
                prefs.put(LAST_CONNECTED_PORT, port.systemPortName)
                prefs.flush()
            } finally {
                connectingTask = null
            }
        }
    }

    override fun close() {
        connection?.close()
    }

    companion object {
        const val LAST_CONNECTED_PORT = "last-connected-port"
    }
}
