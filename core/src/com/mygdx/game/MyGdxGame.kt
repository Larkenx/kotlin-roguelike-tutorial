package com.mygdx.game

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.halfdeadgames.kterminal.KTerminalData
import com.halfdeadgames.kterminal.KTerminalGlyph
import com.halfdeadgames.kterminal.KTerminalRenderer
import ktx.app.clearScreen
import ktx.app.use

class MyGdxGame : ApplicationAdapter() {
    lateinit var batch: SpriteBatch
    lateinit var terminalData: KTerminalData
    lateinit var terminalRenderer: KTerminalRenderer
    var width = 50
    var height = 26

    override fun create() {
        batch = SpriteBatch()

        terminalData = KTerminalData(width, height, Color.WHITE, Color.BLACK)
        terminalRenderer = KTerminalRenderer("fontSheet.png", 1f, batch)
    }

    override fun render() {
        clearScreen(0f, 0f, 0f, 1f)
        terminalData.clearAll()
        /*  Drawing Code goes here */
        terminalData.resetCursor()
        terminalData[0, 0].drawBox(
                width = width,
                height = height,
                topRight = KTerminalData.BOX_DOUBLE_DOWN_LEFT,
                bottomLeft = KTerminalData.BOX_DOUBLE_UP_RIGHT,
                bottomRight = KTerminalData.BOX_DOUBLE_UP_LEFT,
                topLeft = KTerminalData.BOX_DOUBLE_DOWN_RIGHT,
                horizontal = KTerminalData.BOX_DOUBLE_HORIZONTAL,
                vertical = KTerminalData.BOX_DOUBLE_VERTICAL
        )
        terminalData[width / 2, height / 2].write(KTerminalGlyph('@', Color.CYAN, Color.BLACK))
        /* End drawing code */
        batch.use {
            terminalRenderer.render(0f, 0f, terminalData)
        }
    }

    override fun dispose() {
        batch.dispose()
        terminalRenderer.dispose()
    }
}
