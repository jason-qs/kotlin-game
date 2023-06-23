import com.soywiz.klock.*
import com.soywiz.korge.*
import com.soywiz.korge.view.*
import com.soywiz.korim.color.*
import com.soywiz.korim.format.*
import com.soywiz.korio.file.std.*

suspend fun main() = Korge(width = 512, height = 512, bgcolor = Colors["#2b2b2b"]) {
    val playerImage = image(resourcesVfs["Player.png"].readBitmap()){
        scale(2.0)
        position(256, 256)
    }

    val backgroundImage = image(resourcesVfs["background.png"].readBitmap()){
    scale(1.0)
    position(256, 256)
}

    val enemyImage = image(resourcesVfs["Enemy.png"].readBitmap()){
        scale(2.0)
        position(256, 256)
    }

    val gameHolder = GameHolder(this, playerImage, backgroundImage, enemyImage)

}
