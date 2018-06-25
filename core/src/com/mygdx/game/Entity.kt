package com.mygdx.game

import com.halfdeadgames.kterminal.KTerminalGlyph

open class Entity(var x: Int, var y: Int, var glyph: KTerminalGlyph) {
    fun move(dx: Int, dy: Int) {
        x += dx
        y += dy
    }
}