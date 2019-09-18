package io.github.plenglin.dormled

import javafx.geometry.Insets
import javafx.geometry.Orientation
import javafx.scene.Parent
import javafx.scene.control.Slider
import tornadofx.*
import java.util.logging.Logger

class FillModeView : View() {
    private val logger = Logger.getLogger(javaClass.canonicalName)
    private val bluetoothController: BluetoothController by inject()

    lateinit var r: Slider
    lateinit var g: Slider
    lateinit var b: Slider

    override val root: Parent = gridpane {
        paddingAll = 10.0
        row {
            label("Red")
            r = slider(0, 1, 0) {
                valueProperty().onChange { doFill() }
            }
        }
        row {
            label("Green")
            g = slider(0, 1, 0) {
                valueProperty().onChange { doFill() }
            }
        }
        row {
            label("Blue")
            b = slider(0, 1, 0) {
                valueProperty().onChange { doFill() }
            }
        }
    }

    private fun doFill() {
        val r = (r.value * 255).toInt()
        val g = (g.value * 255).toInt()
        val b = (b.value * 255).toInt()
        bluetoothController.connection!!.fill(r, g, b)
    }

}