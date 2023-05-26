import com.soywiz.klock.*
import com.soywiz.korge.*
import com.soywiz.korge.box2d.*
import com.soywiz.korge.scene.*
import com.soywiz.korge.tween.*
import com.soywiz.korge.view.*
import com.soywiz.korge.view.camera.*
import com.soywiz.korim.color.*
import com.soywiz.korim.format.*
import com.soywiz.korio.file.std.*
import com.soywiz.korma.geom.*
import com.soywiz.korma.interpolation.*
import org.jbox2d.dynamics.*

suspend fun main() = Korge(width = 512, height = 512, bgcolor = Colors["#2b2b2b"]) {

    val playerImage = image(resourcesVfs["Player.png"].readBitmap()){
        scale(2.0)
        position(256, 256)
    }
//    cameraContainer(512.0, 512.0).follow(playerImage)

    addChild(solidRect(500, 10).xy(0,356).registerBodyWithFixture(type= BodyType.STATIC))
    val gameHolder = GameHolder(this, playerImage)

    camera.cameraContainer().follow(playerImage)
}
