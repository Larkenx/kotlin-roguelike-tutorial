package com.mygdx.game

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.halfdeadgames.kterminal.KTerminalData
import com.halfdeadgames.kterminal.KTerminalGlyph
import com.halfdeadgames.kterminal.KTerminalRenderer
import com.mygdx.game.entities.Actor
import com.mygdx.game.entities.Player
import com.mygdx.game.scheduler.TimeScheduler
import ktx.app.clearScreen
import ktx.app.use
import java.util.*

// extending the closed range to have a random function, see https://stackoverflow.com/questions/45685026/how-can-i-get-a-random-number-in-kotlin
fun ClosedRange<Int>.random() = Random().nextInt(endInclusive - start) + start

class MyGdxGame : ApplicationAdapter() {
    /* LibGDX rendering properties*/
    var width = 50
    var height = 26
    lateinit var batch: SpriteBatch
    lateinit var terminalData: KTerminalData
    lateinit var terminalRenderer: KTerminalRenderer

    /* Game engine scheduler for tracking user turns */
    lateinit var scheduler: TimeScheduler
    lateinit var actingActor: Actor

    /* Entities & Map tracking */
    lateinit var player: Player
    lateinit var actors: ArrayList<Actor>

    /* LibGDX input handler */
    lateinit var inputAdapter: InputHandler
    override fun create() {
        batch = SpriteBatch()
        terminalData = KTerminalData(width, height, Color.WHITE, Color.BLACK)
        terminalRenderer = KTerminalRenderer("fontSheet.png", 1f, batch)
        initializeScheduler()
        inputAdapter = InputHandler(this) // create a new input handler with a reference to our game
        Gdx.input.inputProcessor = inputAdapter // set libgdx's input adapter to be the one we created
        nextTurn() // start the game with the first player's turn
    }

    fun initializeScheduler() {
        scheduler = TimeScheduler()
        player = Player(width / 2, height / 2, KTerminalGlyph('@', Color.CYAN, Color.BLACK), "Larken")
        val rat: Actor = object : Actor(10, 10, KTerminalGlyph('g', Color.GREEN, Color.BLACK), "goblin") {
            override fun act(game: MyGdxGame) {
                this.move((-1..2).random(), (-1..2).random()) // move the rat in a random direction
                game.nextTurn()
            }
        }
        actors = ArrayList()
        actors.add(player)
        actors.add(rat)
        scheduler.addActor(player)
        scheduler.addActor(rat)
    }

    /**
     * A method to indicate to the game that the current actor's turn is finished, and the
     * scheduler should be moved forward to the next player's turn
     */
    fun nextTurn() {
        actingActor = scheduler.nextTurn()
        println("${actingActor.name}'s turn")
        actingActor.act(this)
    }

    override fun render() {
        clearScreen(0f, 0f, 0f, 1f)
        terminalData.clearAll()
        /*  Drawing Code goes here */
        terminalData.resetCursor()
        terminalData[0, 0][Color.CYAN, Color.BLACK].drawBox(
                width = width,
                height = height,
                topRight = KTerminalData.BOX_DOUBLE_DOWN_LEFT,
                bottomLeft = KTerminalData.BOX_DOUBLE_UP_RIGHT,
                bottomRight = KTerminalData.BOX_DOUBLE_UP_LEFT,
                topLeft = KTerminalData.BOX_DOUBLE_DOWN_RIGHT,
                horizontal = KTerminalData.BOX_DOUBLE_HORIZONTAL,
                vertical = KTerminalData.BOX_DOUBLE_VERTICAL
        )
//        terminalData[playerX(), playerY()].write(KTerminalGlyph('@', Color.CYAN, Color.BLACK))
        // loop through all the actors and render them to the terminal
        for (actor: Actor in actors) {
            terminalData[actor.x, actor.y].write(actor.glyph)
        }
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
