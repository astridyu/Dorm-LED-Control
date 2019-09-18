package io.plenglin.dormled

import com.fazecast.jSerialComm.SerialPort
import io.plenglin.dormled.BluetoothController.Companion.LAST_CONNECTED_PORT
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.scene.Parent
import javafx.scene.control.ComboBox
import javafx.scene.control.ProgressIndicator
import tornadofx.*
import java.util.logging.Logger

class BluetoothSelectionView : View() {
    private val logger = Logger.getLogger(javaClass.canonicalName)
    private val connectionController: BluetoothController by inject()

    private data class SerialPortViewModel(val port: SerialPort) {
        override fun toString(): String {
            return port.descriptivePortName
        }
    }
    private val bluetoothPorts: ObservableList<SerialPortViewModel> = FXCollections.observableArrayList(
        *SerialPort.getCommPorts()
            .map(::SerialPortViewModel)
            .sortedBy { it.port.systemPortName }
            .toTypedArray()
    )

    private lateinit var selector: ComboBox<SerialPortViewModel>

    override val root: Parent = hbox {
        label(text = "Serial port")
        selector = combobox(values = bluetoothPorts) {
            connectionController.prefs.get(LAST_CONNECTED_PORT, null)?.let { defaultPort ->
                val index = bluetoothPorts.indexOfFirst { it.port.systemPortName == defaultPort }
                selectionModel.select(index)
            }
        }
        button(text = "Connect") {
            disableProperty().bind(connectionController.isConnectingProperty)
            action {
                val selected = selector.selectedItem ?: return@action
                logger.info { "Selected comm port $selected" }
                connectionController.connectTo(selected.port)
            }
        }
    }
}