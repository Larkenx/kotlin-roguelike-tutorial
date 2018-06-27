package com.mygdx.game.entities

import com.halfdeadgames.kterminal.KTerminalGlyph
import com.mygdx.game.MyGdxGame

/* All entities that take a turn and perform an action every tick have an act method */
interface Actor {
    var speed: Int // property to indicate location for turn order

    fun act(game: MyGdxGame)
}

open class Entity(var x: Int, var y: Int, var glyph: KTerminalGlyph, val name: String) {
    fun move(dx: Int, dy: Int) {
        x += dx
        y += dy
    }
}

