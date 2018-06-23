package com.mygdx.game.desktop

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import com.mygdx.game.MyGdxGame

object DesktopLauncher {
    @JvmStatic
    fun main(arg: Array<String>) {
        val config = LwjglApplicationConfiguration()
        config.title = "Kotlin Roguelike Tutorial"
        config.width = 800
        config.height = 416
        config.backgroundFPS = 60
        config.foregroundFPS = 60
        config.resizable = false
        LwjglApplication(MyGdxGame(), config)
    }
}
