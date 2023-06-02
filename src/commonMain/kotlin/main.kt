import com.soywiz.klock.*
import com.soywiz.korge.*
import com.soywiz.korge.animate.serialization.*
import com.soywiz.korge.box2d.*
import com.soywiz.korge.lipsync.*
import com.soywiz.korge.ui.*
import com.soywiz.korge.view.*
import com.soywiz.korge.view.camera.*
import com.soywiz.korge.view.camera.Camera
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

    val gameHolder = GameHolder(this, playerImage)

}
