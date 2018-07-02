package com.mygdx.game.scheduler

import com.mygdx.game.entities.Actor
import java.util.*

/**
 * A class to wrap around a Priority Queue for scheduling actors, with a comparator
 * that focuses on the Actor's speed
 * @property queue the internal priority queue that manages the turn order
 * @property baseTime the base speed of all actors - the speed of an actor
 * is actually used as a divisor against the base time to determine an actor's initial turn delay
 */
open class TimeScheduler {
    val baseTime: Int = 10

    data class TurnEvent(val actor: Actor, var delay: Int)

    private val queue: PriorityQueue<TurnEvent> = PriorityQueue(11, object : Comparator<TurnEvent> {
        override fun compare(a1: TurnEvent, a2: TurnEvent): Int = if (a1.delay == a2.delay) -1 else a1.delay - a2.delay
    })

    fun addActor(actor: Actor) = queue.add(TurnEvent(actor, baseTime / actor.speed))

    fun removeActor(actorToRemove: Actor) = queue.removeIf { (actor): TurnEvent -> actor === actorToRemove }

    fun adjustPriorities(time: Int) = queue.forEach { event: TurnEvent -> event.delay += time }

    fun nextTurn(): Actor {
        val (actor, delay) = queue.poll()
        adjustPriorities(-delay)
        addActor(actor)
        return actor
    }

    public fun nextTurnWithTurnEvent(): TurnEvent {
        val turnEvent = queue.poll()
        val (actor, delay) = turnEvent
        adjustPriorities(-delay)
        addActor(actor)
        return turnEvent
    }
}