package com.mygdx.game

import com.badlogic.gdx.Input
import com.badlogic.gdx.math.Vector2
import ktx.app.KtxInputAdapter


class InputHandler(val game: MyGdxGame) : KtxInputAdapter {
    val up = Vector2(0f, -1f)
    val down = Vector2(0f, 1f)
    val left = Vector2(-1f, 0f)
    val right = Vector2(1f, 0f)
    val ul = Vector2(-1f, -1f)
    val ur = Vector2(1f, -1f)
    val ll = Vector2(-1f, 1f)
    val lr = Vector2(1f, 1f)

    override fun keyUp(keycode: Int): Boolean {
        when (keycode) {
        // AWSD keys
            Input.Keys.W -> game.playerPosition.add(up)
            Input.Keys.S -> game.playerPosition.add(down)
            Input.Keys.A -> game.playerPosition.add(left)
            Input.Keys.D -> game.playerPosition.add(right)
        // VIM keys
            Input.Keys.K -> game.playerPosition.add(up)
            Input.Keys.J -> game.playerPosition.add(down)
            Input.Keys.H -> game.playerPosition.add(left)
            Input.Keys.L -> game.playerPosition.add(right)
            Input.Keys.Y -> game.playerPosition.add(ul)
            Input.Keys.U -> game.playerPosition.add(ur)
            Input.Keys.B -> game.playerPosition.add(ll)
            Input.Keys.N -> game.playerPosition.add(lr)
        // Arrow keys
            Input.Keys.UP -> game.playerPosition.add(up)
            Input.Keys.DOWN -> game.playerPosition.add(down)
            Input.Keys.LEFT -> game.playerPosition.add(left)
            Input.Keys.RIGHT -> game.playerPosition.add(right)
        }

        return true
    }
}