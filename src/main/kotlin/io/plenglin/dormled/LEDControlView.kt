package io.plenglin.dormled

import com.fazecast.jSerialComm.SerialPort
import javafx.beans.Observable
import javafx.beans.value.ObservableValue
import javafx.scene.Parent
import javafx.scene.control.TabPane
import javafx.stage.Modality
import javafx.stage.StageStyle
import tornadofx.*
import java.util.logging.Logger

class LEDControlView : View() {
    private val logger = Logger.getLogger(javaClass.canonicalName)
    private val bluetoothController: BluetoothController by inject()

    override val root: Parent = tabpane {
        tabClosingPolicy = TabPane.TabClosingPolicy.UNAVAILABLE

        tab("Fill") {
            label("test")
        }
    }

    override fun onBeforeShow() {
        //find<BluetoothSelector>().openModal(stageStyle = StageStyle.UTILITY, block = true)
    }
}