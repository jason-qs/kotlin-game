import com.soywiz.korge.view.*

class GameHolder(private val stage: Stage, private val player: Image, private val background: Image, private val enemy: Image) {

    private var game = Game(stage, player, background, enemy)
}
