package io.plenglin.dormled

import javafx.scene.Parent
import javafx.scene.control.ColorPicker
import javafx.scene.control.TabPane
import tornadofx.*
import java.util.logging.Logger

class LEDControlView : View() {
    private val logger = Logger.getLogger(javaClass.canonicalName)
    private val bluetoothController: BluetoothController by inject()

    private lateinit var colorPicker: ColorPicker

    override val root: Parent = tabpane {
        enableWhen(bluetoothController.connectionProperty.isNotNull)
        tabClosingPolicy = TabPane.TabClosingPolicy.UNAVAILABLE

        tab("Fill") {
            add(find<FillModeView>())
        }
        tab("Hue") {
            add(find<HueModeView>())
        }
    }
}