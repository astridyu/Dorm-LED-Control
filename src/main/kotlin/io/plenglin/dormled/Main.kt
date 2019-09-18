package io.plenglin.dormled

import tornadofx.App
import tornadofx.launch


class DormLEDControl: App(MainWindowView::class)

fun main() {
    launch<DormLEDControl>()
}
