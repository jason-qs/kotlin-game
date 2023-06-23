
import com.soywiz.korev.*
import com.soywiz.korge.box2d.*
import com.soywiz.korge.ui.*
import com.soywiz.korge.view.*
import com.soywiz.korge.view.camera.*
import com.soywiz.korim.color.*
import com.soywiz.korma.geom.*
import org.jbox2d.collision.shapes.*
import org.jbox2d.dynamics.*

class Game(val stage: Stage, playerImage: Image, backgroundImage : Image, enemyImage: Image) {

    private fun Key.isPressed(): Boolean = stage.views.input.keys[this]

    private var gameOver = false
    private var player = Player(playerImage)
    private var enemy = Enemy(enemyImage)

    var destination: Point = player.player.pos.copy() as Point
    private val playerSpeed = .50
    private val step = 20
    private var facingRight = true
    private val dashLength = 35
    private var health = 3
    //val levelClear = UIText(text = "Level Cleared")

    val endFlag = SolidRect(40, 100,Colors.GREEN).xy(3600,246).registerBodyWithFixture(type= BodyType.STATIC)
    val endFlagCollision = endFlag.onCollision({it==player.player}) {
        cameraInit.addChild(UIText("Level Cleared").xy(256,256))
    }



    private val cameraInit = stage.cameraContainer(512.0, 512.0) {
        addChild(backgroundImage.apply {
            xy(256,256)
        })

        addChild(
            player.player.apply {
                xy(256,256)
            }
        )

        addChild(
            enemy.enemy.apply {
                xy(296, 256)
            }
        )

        addChild(solidRect(1000, 10).xy(0,356).registerBodyWithFixture(type= BodyType.STATIC))
        addChild(solidRect(2000, 10).xy(1100,356).registerBodyWithFixture(type= BodyType.STATIC))
        addChild(solidRect(1000, 10).xy(3100,256).registerBodyWithFixture(type= BodyType.STATIC))
        addChild(solidRect(1000, 10).xy(0,356).registerBodyWithFixture(type= BodyType.STATIC))
        addChild(solidRect(1000, 10).xy(0,356).registerBodyWithFixture(type= BodyType.STATIC))
        addChild(solidRect(1000, 10).xy(0,356).registerBodyWithFixture(type= BodyType.STATIC))
        addChild(solidRect(1000, 10).xy(0,356).registerBodyWithFixture(type= BodyType.STATIC))
        addChild(endFlag)
    }
    private val cameraFollow = cameraInit.follow(player.player, setImmediately = true)


     private val stageUpdater = stage.addUpdater { time ->


        if(Key.LEFT.isPressed()) {
            facingRight = false
            moveLeft()
        }
        if (Key.RIGHT.isPressed()) {
            moveRight()
            facingRight = true
        }

        if (Key.SPACE.isPressed()){
            moveUp()
        }

        if (Key.DOWN.isPressed()){
            moveDown()
        }

        if (Key.LEFT_SHIFT.isPressed()){
            dash()
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

    private fun dash() {
        if(facingRight) {
            player.player.x += dashLength
        } else if(!facingRight){
            player.player.x -= dashLength
        }
    }

    private fun resetDestination() {
        destination.copyFrom(player.player.pos)

    }

    private fun shoot() {
//        cameraInit.addChild()
    }

    private fun getHit() {
        health -= 1
    }

    inner class Projectile{
        val projectile = SolidRect(40, 40,Colors.RED).xy(1500,346).registerBodyWithFixture(type= BodyType.STATIC)
    }

    inner class Enemy(enemyImage: Image){
        val enemy = with(stage) {
            enemyImage.registerBodyWithFixture(type= BodyType.DYNAMIC, density = 100, gravityScale = 4, shape = CircleShape(5))
        }
    }

    inner class Player(playerImage: Image){
        val player = with(stage) {
            playerImage.registerBodyWithFixture(type= BodyType.DYNAMIC, density = 100, gravityScale = 4, shape = CircleShape(5))
        }

    }
}
