package com.mygdx.game

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
        // if it's the player's turn, then we process the input
        return true
    }
}