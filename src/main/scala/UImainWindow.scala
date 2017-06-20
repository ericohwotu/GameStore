import scalafx.Includes._
import scalafx.scene.Scene
import scalafx.scene.control.Button
import scalafx.scene.paint.Color
import scalafx.scene.image.{Image, ImageView}
import scalafx.scene.input.MouseEvent
import scalafx.scene.text.{Font, Text}

class UImainWindow extends Scene {
	
	val mainFont:Font = new Font("Times New Roman", 18)
	val textColour:Color = Color.White
	
	fill = Color.DarkGrey.darker.darker
	val salesIcon:ImageView = new ImageView(new Image("file:src/main/images/Sale.png"))
	val reportIcon:ImageView = new ImageView(new Image("file:src/main/images/Report.png"))
	val stockIcon:ImageView = new ImageView(new Image("file:src/main/images/Stock.png"))
	val staffIcon:ImageView = new ImageView(new Image("file:src/main/images/Staff.png"))
	salesIcon.relocate(100, 100)
	reportIcon.relocate(500, 100)
	stockIcon.relocate(100, 400)
	staffIcon.relocate(500, 400)
	
	//==================================================== Text ===========
	var loggedInText:Text = new Text(s"Logged in as: ")
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
	salesIcon.onMouseClicked = (e:MouseEvent) =>{
		Main.setWindow("transaction")
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
	
	content = List(salesIcon, reportIcon, stockIcon, staffIcon, logoutButton, loggedInText)
}
