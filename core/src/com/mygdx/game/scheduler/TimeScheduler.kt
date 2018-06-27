package com.mygdx.game.scheduler

import com.badlogic.gdx.graphics.Color
import com.halfdeadgames.kterminal.KTerminalGlyph
import com.mygdx.game.entities.Actor
import com.mygdx.game.entities.Entity
import com.mygdx.game.entities.Player
import java.util.*

interface Actor {
    var speed: Int // property to indicate location for turn order
}

data class TurnEvent(val actor: Actor, var delay: Int)

/**
 * A class to wrap around a Priority Queue for scheduling actors, with a comparator
 * that focuses on the Actor's speed/delay
 * @property queue the internal priority queue that manages the turn order
 */
open class TimeScheduler {
    val queue: PriorityQueue<TurnEvent> = PriorityQueue(11, object : Comparator<TurnEvent> {
        override fun compare(a1: TurnEvent, a2: TurnEvent): Int {
            if (a1.delay == a2.delay) return 1
            return a2.delay - a1.delay
        }
    })

    fun addActor(actor: Actor, delay: Int) = queue.add(TurnEvent(actor, delay))

    fun removeActor(actorToRemove: Actor) = queue.removeIf { (actor): TurnEvent -> actor === actorToRemove }

    fun adjustPriorities(time: Int) {
        queue.forEach { event: TurnEvent -> event.delay += time }
    }

    fun nextTurn(): Actor {
        val (actor, delay) = queue.poll()
        adjustPriorities(delay)
        addActor(actor, actor.speed)
        return actor
    }
}

fun main(args: Array<String>) {
    val glyph: KTerminalGlyph = KTerminalGlyph('@', Color.CYAN, Color.BLACK)
    // Testing the TimeScheduler
    val ts: TimeScheduler = TimeScheduler()
    val player1 = Player(0, 0, glyph, "Player 1")
    val player2 = Player(0, 0, glyph, "Player 2")
    val player3 = Player(0, 0, glyph, "Player 3")


    ts.addActor(player1, player1.speed)
    ts.addActor(player2, player2.speed)
    ts.addActor(player3, player3.speed)


    for (i in 0..12) {
        val actor: Actor = ts.nextTurn()
        if (actor is Entity)
            println(actor.name)

        if (i == 5)
            ts.removeActor(player2)

    }
}