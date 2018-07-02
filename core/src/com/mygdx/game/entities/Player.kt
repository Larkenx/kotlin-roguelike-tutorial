package com.mygdx.game.entities

import com.badlogic.gdx.Input
import com.halfdeadgames.kterminal.KTerminalGlyph
import com.mygdx.game.MyGdxGame

class Player(x: Int, y: Int, glyph: KTerminalGlyph, name: String) : Actor(x, y, glyph, name) {
    override var speed = 1 // speed of 1

    override fun act(game: MyGdxGame) {}

    fun handleKeyUp(game: MyGdxGame, keycode: Int) {
        when (keycode) {
        // AWSD keys
            Input.Keys.W -> move(0, -1)
            Input.Keys.S -> move(0, 1)
            Input.Keys.A -> move(-1, 0)
            Input.Keys.D -> move(1, 0)
        // VIM keys
            Input.Keys.K -> move(0, -1)
            Input.Keys.J -> move(0, 1)
            Input.Keys.H -> move(-1, 0)
            Input.Keys.L -> move(1, 0)
            Input.Keys.Y -> move(-1, -1)
            Input.Keys.U -> move(1, -1)
            Input.Keys.B -> move(-1, 1)
            Input.Keys.N -> move(1, 1)
        // Arrow keys
            Input.Keys.UP -> move(0, -1)
            Input.Keys.DOWN -> move(0, 1)
            Input.Keys.LEFT -> move(-1, 0)
            Input.Keys.RIGHT -> move(1, 0)
        // VIM keys
            Input.Keys.NUMPAD_8 -> move(0, -1)
            Input.Keys.NUMPAD_2 -> move(0, 1)
            Input.Keys.NUMPAD_4 -> move(-1, 0)
            Input.Keys.NUMPAD_6 -> move(1, 0)
            Input.Keys.NUMPAD_7 -> move(-1, -1)
            Input.Keys.NUMPAD_9 -> move(1, -1)
            Input.Keys.NUMPAD_1 -> move(-1, 1)
            Input.Keys.NUMPAD_3 -> move(1, 1)
        }
        game.nextTurn()
    }
}

