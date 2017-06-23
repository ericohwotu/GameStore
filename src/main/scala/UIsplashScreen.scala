import scala.concurrent.{ExecutionContext, Future}
import scalafx.Includes._
import scalafx.scene.Scene
import scalafx.scene.image.{Image, ImageView}
import scalafx.scene.input.{KeyCode, KeyEvent}
import scalafx.scene.paint.Color

/**
	* Created by duane on 22/06/2017.
	*/
class UIsplashScreen extends Scene{
	
	val elliotAndFriends:Image = new Image("file:src/main/images/ElliotAndFriends.png")
	val elliotAndFriendsHolder:ImageView = new ImageView(elliotAndFriends)
	
	fill = Color.Transparent
	
	content = List(elliotAndFriendsHolder)
	
	onKeyPressed = (e:KeyEvent) => {
		if(e.code == KeyCode.Enter) {
			Main.setWindow("login")
		}
	}

	implicit val ec = ExecutionContext.global

	Future{
		Thread.sleep(2000)
		Main.setWindow("login")
	}
	
}
