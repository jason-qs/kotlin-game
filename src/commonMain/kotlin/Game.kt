import com.soywiz.klock.*
import com.soywiz.korev.*
import com.soywiz.korge.view.*
import com.soywiz.korim.awt.*
import com.soywiz.korim.format.*
import com.soywiz.korio.file.std.*
import com.soywiz.korma.geom.*

class Game(val stage: Stage, playerImage: Image) {

    private fun Key.isPressed(): Boolean = stage.views.input.keys[this]

    private var gameOver = false
    private val player = Player(playerImage)


    var destination: Point = player.player.pos.copy() as Point
    private val playerSpeed = .50
    private val step = 20


    private val stageUpdater = stage.addUpdater { time ->

        if (destination.distanceTo(player.player.pos) > playerSpeed) {
            moveToDestination()
        }

        if(Key.LEFT.isPressed()) {
            moveLeft()
        }
        if (Key.RIGHT.isPressed()) {
            moveRight()
        }

        if (Key.SPACE.isPressed()){
            moveUp()
        }

        if (Key.DOWN.isPressed()){
            moveDown()
        }

    }

    private fun moveToDestination(){
        player.player.pos = destination
    }

    private fun moveLeft() {
        destination.x -= step
    }

    private fun moveRight() {
        destination.x += step
    }

    private fun moveDown() {
        destination.y += step
    }

    private fun moveUp() {
        destination.y -= step
    }

    private fun resetDestination() {
        destination.copyFrom(player.player.pos)
    }

    inner class Player(playerImage: Image){
        val player = with(stage) {
            playerImage

        }
    }
}
