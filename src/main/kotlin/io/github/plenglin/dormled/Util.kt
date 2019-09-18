package io.github.plenglin.dormled

import javafx.scene.paint.Color

fun Color.asFillCommand() : String {
    return "fill ${(red * 255).toInt()} ${(green * 255).toInt()} ${(blue * 255).toInt()}"
}