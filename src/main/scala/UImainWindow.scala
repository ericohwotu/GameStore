import scala.collection.mutable.ListBuffer
import scalafx.Includes._
import scalafx.scene.Scene
import scalafx.scene.control.Button
import scalafx.scene.paint.Color
import scalafx.scene.image.{Image, ImageView}
import scalafx.scene.input.MouseEvent
import scalafx.scene.text.{Font, Text}

class UImainWindow extends Scene {
	
	def update():Unit = {
		if(Main.loggedIn != null) {
			loggedInText.text = s"Logged in as: ${Main.loggedIn.fName} ${Main.loggedIn.lName}"
		} else {
			loggedInText.text = "Log in error"
		}
	}
	
	val mainFont:Font = new Font("Times New Roman", 18)
	val textColour:Color = Color.White
	
	fill = Color.DarkGrey.darker.darker
	val salesIcon:ImageView = new ImageView(new Image("file:src/main/images/Sale.png"))
	val reportIcon:ImageView = new ImageView(new Image("file:src/main/images/Report.png"))
	val stockIcon:ImageView = new ImageView(new Image("file:src/main/images/Stock.png"))
	val staffIcon:ImageView = new ImageView(new Image("file:src/main/images/Staff.png"))
	val highlightIcon:ImageView = new ImageView(new Image("file:src/main/images/Highlight.png"))
	salesIcon.relocate(100, 100)
	reportIcon.relocate(500, 100)
	stockIcon.relocate(100, 400)
	staffIcon.relocate(500, 400)
	val buttonHover:ListBuffer[Boolean] = ListBuffer(false, false, false, false)
	
	//==================================================== Text ===========
	var loggedInText:Text = new Text("")
	loggedInText.relocate(5, 5)
	loggedInText.font = mainFont
	loggedInText.fill = textColour
	
	//==================================================== Buttons ===========
	val logoutButton:Button = new Button("Logout")
	logoutButton.relocate(926, 7)
	
	onMouseMoved = (e:MouseEvent) => {
		if(salesIcon.hover.value) buttonHover(0) = {highlightIcon.relocate(100, 100); true} else buttonHover(0) = false
		if(reportIcon.hover.value) buttonHover(1) = {highlightIcon.relocate(500, 100); true} else buttonHover(1) = false
		if(stockIcon.hover.value) buttonHover(2) = {highlightIcon.relocate(100, 400); true} else buttonHover(2) = false
		if(staffIcon.hover.value) buttonHover(3) = {highlightIcon.relocate(500, 400); true} else buttonHover(3) = false
		if(buttonHover.count(_ == false) == 4) highlightIcon.visible = false else highlightIcon.visible = true
	}
	
	logoutButton.onMouseClicked = (e:MouseEvent) =>{
		Main.logout()
		Main.setWindow("login")
	}
	salesIcon.onMouseClicked = (e:MouseEvent) =>{
		Main.setWindow("newT")
	}
	reportIcon.onMouseClicked = (e:MouseEvent) =>{
		Main.setWindow("report")
	}
	stockIcon.onMouseClicked = (e:MouseEvent) =>{
		Main.setWindow("stock")
	}
	staffIcon.onMouseClicked = (e:MouseEvent) =>{
		Main.setWindow("staff")
	}
	
	content = List(salesIcon, reportIcon, stockIcon, staffIcon, highlightIcon, logoutButton, loggedInText)
}
