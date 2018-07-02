package test.com.mygdx.game

import com.badlogic.gdx.graphics.Color
import com.halfdeadgames.kterminal.KTerminalGlyph
import com.mygdx.game.entities.Actor
import com.mygdx.game.entities.Player
import com.mygdx.game.scheduler.TimeScheduler
import org.junit.Before as before
import org.junit.Test as test

class TimeSchedulerTest {
    lateinit var timeScheduler: TimeScheduler
    lateinit var actors: ArrayList<Actor>

    @before
    fun setup() {
        timeScheduler = TimeScheduler()
        actors = ArrayList()
    }

    @test
    fun testAddActor() {
        val turnRounds: Int = 3
        val glyph: KTerminalGlyph = KTerminalGlyph('@', Color.CYAN, Color.BLACK)
        val player1 = Player(0, 0, glyph, "a")
        val player2 = Player(0, 0, glyph, "b")
        val player3 = Player(0, 0, glyph, "c")
        player1.speed *= 2
        player2.speed *= 1
        player3.speed *= 1
        timeScheduler.addActor(player1)
        timeScheduler.addActor(player2)
        timeScheduler.addActor(player3)
        val table: HashMap<Actor, Int> = HashMap()
        table.put(player1, 0)
        table.put(player2, 0)
        table.put(player3, 0)

        var turns: Int = player1.speed + player2.speed + player3.speed
        turns *= turnRounds

        for (i in 1..turns) {
            val (actor, delay) = timeScheduler.nextTurnWithTurnEvent()
            if (table.containsKey(actor)) {
                val count: Int = table.get(actor) as Int
                table.put(actor, count + 1)
            }
        }

        for ((actor, count) in table.entries) {
            assert(count == actor.speed * turnRounds)
        }

    }
}