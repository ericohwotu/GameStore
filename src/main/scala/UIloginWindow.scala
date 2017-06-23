import scalafx.Includes._
import scalafx.event.ActionEvent
import scalafx.scene.Scene
import scalafx.scene.control.Alert.AlertType
import scalafx.scene.control.{Alert, Button, PasswordField, TextField}
import scalafx.scene.image.{Image, ImageView}
import scalafx.scene.input.{KeyCode, KeyEvent, MouseEvent}
import scalafx.scene.paint.Color
import scalafx.scene.shape.Rectangle
import scalafx.scene.text.{Font, Text}

class UIloginWindow extends Scene {

	def update(): Unit = {
		usernameInput.requestFocus()
	}
	
	def sendLogin(): Unit = {
		val attempt: Boolean = Main.login(usernameInput.text.value, passwordInput.text.value)
		passwordInput.text = ""
		if (attempt) {
			usernameInput.text = "Enter username"
			Main.setWindow("main")
		} else {
			new Alert(AlertType.Error, "Wrong username and/or password").showAndWait()
		}
	}

	def updatePasswordInput(): Unit ={
		if (usernameInput.isFocused && usernameInput.text.value == "Enter username") usernameInput.text.value = ""
		if (!usernameInput.isFocused && usernameInput.text.value == "") usernameInput.text.value = "Enter username"
	}

	fill = Color.DarkGrey.darker.darker.darker
	val textColour: Color = Color.White
	//================================================= Text =================================================
	val headerFont: Font = new Font("Times New Roman", 36)
	val subHeaderFont: Font = new Font("Times New Roman", 24)
	val standardFont: Font = new Font("Times New Roman", 18)
	
	val windowIcon:ImageView = new ImageView(new Image("file:src/main/images/ElliotIcon.png")){fitHeight=50;fitWidth=50;relocate(10,0)}
	
	val headerText: Text = new Text("Elliot & Friends™")
	headerText.x = 70
	headerText.y = 37
	headerText.font = headerFont
	headerText.fill = textColour
	val divider = Rectangle(0, 50, 400, 2)
	divider.fill = Color.DarkGrey.darker
	val subHeadingText: Text = new Text("Employee Login")
	subHeadingText.x = 110
	subHeadingText.y = 80
	subHeadingText.font = subHeaderFont
	subHeadingText.fill = textColour
	val userText: Text = new Text("Username:")
	userText.x = 70
	userText.y = 150
	userText.font = standardFont
	userText.fill = textColour
	val passwordText: Text = new Text("Password:")
	passwordText.x = userText.getX
	passwordText.y = userText.getY + 50
	passwordText.font = standardFont
	passwordText.fill = textColour

	//================================================= Text Input Fields =================================================
	val usernameInput: TextField = new TextField()
	usernameInput.text = "Enter username"
	usernameInput.relocate(userText.getX + 100, userText.getY - 15)
	val passwordInput: PasswordField = new PasswordField()
	passwordInput.relocate(passwordText.getX + 100, passwordText.getY - 16)

	//================================================= Buttons =================================================
	val loginButton: Button = new Button("Login")
	loginButton.relocate(150, 270)
	val cancelButton: Button = new Button("Cancel")
	cancelButton.relocate(196, 270)

	//================================================= Add to Scene =================================================
	content = List(divider, headerText, subHeadingText, userText, passwordText, usernameInput, passwordInput, loginButton, cancelButton, windowIcon)

	//================================================= Functionality =================================================
	onMouseMoved = (me: MouseEvent) => {
		updatePasswordInput()
	}
	
	usernameInput.onMouseClicked= (me: MouseEvent) => {
		updatePasswordInput()
	}

	usernameInput.onKeyPressed = (ke: KeyEvent) => {
		updatePasswordInput()
	}

	passwordInput.onMouseClicked= (me: MouseEvent) => {
		updatePasswordInput()
	}

	loginButton.onAction = (ae:ActionEvent) => {
		sendLogin()
	}
	cancelButton.onAction = (ae:ActionEvent) => {
		Main.closeWindow
	}
	onKeyPressed = (e:KeyEvent) => {
		if(e.code == KeyCode.Enter && (usernameInput.focused.value || passwordInput.focused.value || loginButton.focused.value)) {
			sendLogin()
		}
		if(e.code == KeyCode.Enter && cancelButton.focused.value) {
			Main.closeWindow()
		}
	}
}