package io.github.plenglin.dormled

import javafx.application.Platform
import javafx.scene.Parent
import tornadofx.*
import java.util.logging.Logger
import kotlin.system.exitProcess

class MainWindowView : View() {
    private val logger = Logger.getLogger(javaClass.canonicalName)
    private val controller: BluetoothController by inject()

    override val root: Parent = borderpane {
        top<BluetoothSelectionView>()
        center = stackpane {
            add(find<LEDControlView>())
            progressindicator {
                visibleWhen(controller.connectingTaskProperty.isNotNull)
            }
        }
    }

    override fun onUndock() {
        logger.info("Undock")
        controller.connection?.close()
        Platform.exit()
        exitProcess(0)
    }
}