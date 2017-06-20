import scalafx.Includes._
import scalafx.scene.Scene
import scalafx.scene.control.Button
import scalafx.scene.input.MouseEvent
import scalafx.scene.paint.Color
import scalafx.scene.text.{Font, Text}


class UItransactionWindow extends Scene {
	val mainFont:Font = new Font("Times New Roman", 18)
	val textColour:Color = Color.White
	fill = Color.DarkGrey.darker.darker
	
	//==================================================== Text ===========
	var loggedInText:Text = new Text("")
	loggedInText.relocate(5, 5)
	loggedInText.font = mainFont
	loggedInText.fill = textColour
	
	//==================================================== Buttons ===========
	val logoutButton:Button = new Button("Logout")
	logoutButton.relocate(926, 7)
	logoutButton.onMouseClicked = (e:MouseEvent) =>{
		Main.logout()
		Main.setWindow("login")
	}
	val returnButton:Button = new Button("Return")
	returnButton.relocate(874, 7)
	returnButton.onMouseClicked = (e:MouseEvent) =>{
		Main.setWindow("main")
	}
	
	def update():Unit = {
		if(Main.loggedIn != null) {
			loggedInText.text = s"Logged in as: ${Main.loggedIn.fName} ${Main.loggedIn.lName}"
		} else {
			loggedInText.text = "Log in error"
		}
	}
	
	content = List(loggedInText, logoutButton, returnButton)
}
