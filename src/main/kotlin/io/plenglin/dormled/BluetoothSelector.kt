package io.plenglin.dormled

import com.fazecast.jSerialComm.SerialPort
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.scene.Parent
import javafx.scene.control.ComboBox
import tornadofx.*
import java.util.logging.Logger

class BluetoothSelector : View() {
    private val logger = Logger.getLogger(javaClass.canonicalName)

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

    override val root: Parent = vbox {
        label(text = "Select serial port:")
        selector = combobox(values = bluetoothPorts)
        button(text = "Connect") {
            action {
                val selected = selector.selectedItem ?: return@action
                logger.info { "Selected comm port $selected" }
                find<LEDControlView>().openWindow()
                close()
            }
        }
    }
}