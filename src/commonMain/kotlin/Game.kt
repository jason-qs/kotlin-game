
import com.soywiz.korev.*
import com.soywiz.korge.box2d.*
import com.soywiz.korge.ui.*
import com.soywiz.korge.view.*
import com.soywiz.korge.view.camera.*
import com.soywiz.korge.view.camera.Camera
import com.soywiz.korim.color.*
import com.soywiz.korma.geom.*
import com.soywiz.korui.*
import org.jbox2d.collision.shapes.*
import org.jbox2d.dynamics.*
import java.awt.SystemColor.text

class Game(val stage: Stage, playerImage: Image) {

    private fun Key.isPressed(): Boolean = stage.views.input.keys[this]

    private var gameOver = false
    private var player = Player(playerImage)

    var destination: Point = player.player.pos.copy() as Point
    private val playerSpeed = .50
    private val step = 20

    //val levelClear = UIText(text = "Level Cleared")

    val endFlag = SolidRect(40, 40,Colors.GREEN).xy(1500,346).registerBodyWithFixture(type= BodyType.STATIC)
    val endFlagCollision = endFlag.onCollision({it==player.player}) {
        cameraInit.addChild(UIText("Level Cleared").xy(256,256))
    }



    private val cameraInit = stage.cameraContainer(512.0, 512.0) {
        addChild(
            player.player.apply {
                xy(256,256)
            }
        )
        addChild(solidRect(20000, 10).xy(0,356).registerBodyWithFixture(type= BodyType.STATIC))
        addChild(endFlag)
    }
    private val cameraFollow = cameraInit.follow(player.player, setImmediately = true)


     private val stageUpdater = stage.addUpdater { time ->


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
            playerImage.registerBodyWithFixture(type= BodyType.DYNAMIC, density = 100, gravityScale = 4, shape = CircleShape(5))
        }


    }
}
