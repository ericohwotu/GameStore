import scalafx.Includes._
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafx.scene.control.Button
import scalafx.scene.paint.Color
import scalafx.scene.image.{Image, ImageView}
import scalafx.scene.input.MouseEvent
import scalafx.scene.text.Font

class UImainWindow extends PrimaryStage {
	title = "Elliot & Friends™"
	
	val mainFont:Font = new Font("Times New Roman", 18)
	val textColour:Color = Color.White
	
	scene = new Scene(1000, 800) {
		fill = Color.DarkGrey.darker.darker
		val salesIcon:ImageView = new ImageView(new Image("file:src/main/images/Sale.png"))
		salesIcon.relocate(100, 100)
		
		//==================================================== Text ===========
		
		//==================================================== Buttons ===========
		val logoutButton:Button = new Button("Logout")
		logoutButton.relocate(940, 7)
		
		salesIcon.onMouseClicked = (e:MouseEvent) =>{
			UIAppMain.setWindow("transaction")
		}
		
		content = List(salesIcon, logoutButton)
	}
}
