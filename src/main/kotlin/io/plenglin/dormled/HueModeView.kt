package io.plenglin.dormled

import javafx.scene.Parent
import javafx.scene.control.Slider
import javafx.scene.control.Spinner
import tornadofx.*
import java.util.logging.Logger

class HueModeView : View() {
    private val logger = Logger.getLogger(javaClass.canonicalName)
    private val bluetoothController: BluetoothController by inject()

    lateinit var delta: Spinner<Int>
    lateinit var step: Spinner<Int>
    lateinit var period: Spinner<Int>

    override val root: Parent = gridpane {
        paddingAll = 10.0
        row {
            label("Hue Delta")
            delta = spinner(min = -255, max = 255) {
                valueProperty().onChange { doChangeHue() }
            }
        }
        row {
            label("Rate")
            hbox {
                step = spinner(min = 0) {
                    valueProperty().onChange { doChangeHue() }
                }
                label("/")
                period = spinner(min = 1) {
                    valueProperty().onChange { doChangeHue() }
                }
            }
        }
    }

    private fun doChangeHue() {
        bluetoothController.connection!!.hue(delta.value, step.value, period.value)
    }
}