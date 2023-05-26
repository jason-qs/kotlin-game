
import com.soywiz.korma.*
import com.soywiz.klock.*
import com.soywiz.korev.*
import com.soywiz.korge.box2d.*
import com.soywiz.korge.view.*
import com.soywiz.korim.bitmap.*
import com.soywiz.korim.color.*
import com.soywiz.korio.dynamic.*
import com.soywiz.korma.geom.*
import com.soywiz.korma.geom.shape.*
import com.soywiz.korma.geom.vector.*
import org.jbox2d.dynamics.*

class Game(val stage: Stage, playerImage: Image) {

    private fun Key.isPressed(): Boolean = stage.views.input.keys[this]

    private var gameOver = false
    private var player = Player(playerImage)

    var destination: Point = player.player.pos.copy() as Point
    private val playerSpeed = .50
    private val step = 20

//    val ground =
//        Rectangle(0, 0, 100, 10)
//
//    val ground2 = Shape2d.Rectangle(ground)

//    val circle = Shape2d.Circle()


   // val test = stage.stage.addChild(ground2)

     private val stageUpdater = stage.addUpdater { time ->

//        if (destination.distanceTo(player.player.pos) > playerSpeed) {
//            moveToDestination()
//        }

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
        player.player.x -= step
    }

    private fun moveRight() {
        player.player.x += step
    }

    private fun moveDown() {
        player.player.y += step
    }

    private fun moveUp() {
        player.player.y -= step
    }

    private fun resetDestination() {
        destination.copyFrom(player.player.pos)
    }

    inner class Player(playerImage: Image){
        val player = with(stage) {
            playerImage.registerBodyWithFixture(type= BodyType.DYNAMIC, density = 100, friction = 1).hitShape {
                circle(50,50, 30)
            }
        }
    }
}
