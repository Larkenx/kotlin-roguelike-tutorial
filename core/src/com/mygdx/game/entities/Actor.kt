package com.mygdx.game.entities

import com.halfdeadgames.kterminal.KTerminalGlyph
import com.mygdx.game.MyGdxGame

/* All entities that take a turn and perform an action every tick have an act method */
open class Actor(x: Int, y: Int, glyph: KTerminalGlyph, name: String) : Entity(x, y, glyph, name) {
    open var speed: Int = 1 // property to indicate location for turn order

    open fun act(game: MyGdxGame) {
        game.nextTurn()
    }
}