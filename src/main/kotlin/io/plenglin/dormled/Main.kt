package io.plenglin.dormled

import tornadofx.App
import tornadofx.launch


class DormLEDControl: App(LEDControlView::class)

fun main() {
    launch<DormLEDControl>()
}
