

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafx.scene.control.{Button, PasswordField, TextField}
import scalafx.scene.layout.BackgroundFill
import scalafx.scene.paint.Color
import scalafx.scene.shape.Rectangle
import scalafx.scene.text.{Font, Text}


object LoginWindow extends JFXApp{
	val windowBG:BackgroundFill = new BackgroundFill(Color.DarkGrey, null, null)
	var userList:List[List[String]] = List(List("test", "admin"))

	var loginStage:PrimaryStage = new PrimaryStage() {
		title="Elliot & Friends™"

		scene = new Scene(400, 400) {
			fill = Color.DarkGrey.darker.darker.darker
			val textColour:Color = Color.White
			//================================================= Text =================================================
			val headerFont:Font = new Font("Times New Roman", 36)
			val subHeaderFont:Font = new Font("Times New Roman", 24)
			val standardFont:Font = new Font("Times New Roman", 18)

			val headerText:Text = new Text("Elliot & Friends™")
			headerText.x = 10
			headerText.y = 37
			headerText.font = headerFont
			headerText.fill = textColour
			val divider = Rectangle(0, 50, 400, 2)
			divider.fill = Color.DarkGrey.darker
			val subHeadingText:Text = new Text("Employee Login")
			subHeadingText.x = 10
			subHeadingText.y = 80
			subHeadingText.font = subHeaderFont
			subHeadingText.fill = textColour
			val userText:Text = new Text("Username:")
			userText.x = 10
			userText.y = 150
			userText.font = standardFont
			userText.fill = textColour
			val passwordText:Text = new Text("Password:")
			passwordText.x = 10
			passwordText.y = userText.getY + 50
			passwordText.font = standardFont
			passwordText.fill = textColour

			//================================================= Text Input Fields =================================================
			val usernameInput:TextField = new TextField()
			usernameInput.text = "Enter username"
			usernameInput.relocate(userText.getX + 100, userText.getY-15)
			val passwordInput:PasswordField = new PasswordField()
			passwordInput.relocate(passwordText.getX + 100, passwordText.getY-16)

			//================================================= Buttons =================================================
			val loginButton:Button = new Button("Login")
			loginButton.relocate(10, 270)
			val cancelButton:Button = new Button("Cancel")
			cancelButton.relocate(56, 270)

			//================================================= Add to Scene =================================================
			content = List(divider, headerText, subHeadingText, userText, passwordText, usernameInput, passwordInput, loginButton, cancelButton)
		}
	}

}
