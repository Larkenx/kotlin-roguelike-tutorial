package com.mygdx.game.entities

import com.halfdeadgames.kterminal.KTerminalGlyph

open class Entity(var x: Int, var y: Int, var glyph: KTerminalGlyph, val name: String) {
    fun move(dx: Int, dy: Int) {
        x += dx
        y += dy
    }
}

