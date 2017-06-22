
import scala.collection.mutable.ListBuffer
import scalafx.Includes._
import scalafx.scene.Scene
import scalafx.scene.control._
import scalafx.scene.input.{KeyCode, KeyEvent, MouseEvent}
import scalafx.scene.layout.Pane
import scalafx.scene.paint.Color
import scalafx.scene.shape.Rectangle
import scalafx.scene.text.{Font, Text}


class UItransactionWindow extends Scene {
	val titleFont:Font = new Font("Times New Roman", 40)
	val mainFont:Font = new Font("Times New Roman", 14)
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
	logoutButton.onMouseClicked = (e:MouseEvent) => {
		Main.logout()
		Main.setWindow("login")
	}

	val returnButton:Button = new Button("Return")
	returnButton.relocate(874, 7)
	returnButton.onMouseClicked = (e:MouseEvent) => Main.setWindow("newT")

	def update():Unit = {
		if(Main.loggedIn != null) {
			loggedInText.text = s"Logged in as: ${Main.loggedIn.fName} ${Main.loggedIn.lName}"
		} else {
			loggedInText.text = "Log in error"
		}
		eEmployee.text = ""
		prevTransactions.items = new ListView[Transaction](Main.transactions).getItems
	}
	//============================================================ Background Layout ============================
	val divider = Rectangle(400, 0, 2, 800)
	divider.fill = Color.Gray.brighter
	val divider2 = Rectangle(0, 40, 1000, 2)
	divider2.fill = Color.Gray.brighter
	val bg1 = Rectangle(400, 800)
	bg1.fill = Color.Gray
	val topBar = Rectangle(1000, 40)
	topBar.fill = Color.DarkGrey.darker

	//============================================================ Content ============================
	val stockTitle:Text = new Text("Transaction Details")
	stockTitle.font = titleFont
	stockTitle.fill = textColour
	stockTitle.relocate(415, 50)
	val searchTitle:Text = new Text("Transaction History")
	searchTitle.font = titleFont
	searchTitle.fill = textColour
	searchTitle.relocate(15, 50)
	var allTransactions:ListBuffer[Stock] = ListBuffer()
	
	//========================================================= Employee Info Pane
	var stockInfoPane:Pane = new Pane(){relocate(415, 80)}

	val eEmployeeLabel:Text = new Text("Employee"){relocate(0, 30);fill=textColour;font=mainFont}
	val eEmployee:TextField = new TextField(){relocate(0,50);editable=false;maxWidth=200;minWidth=200}

	val eDateTimeLabel:Text = new Text("Date"){relocate(210, 30);fill=textColour;font=mainFont}
	val eDateTime:TextField = new TextField(){relocate(210,50);editable=false;maxWidth=200;minWidth=200}

	val eSaleListLabel:Text = new Text("Sales List"){relocate(0, 100);fill=textColour;font=mainFont}
	val eSaleList:ListView[Transaction] = new ListView[Transaction](){relocate(0,120);editable=false;maxWidth=500;minWidth=500;minHeight=518;maxHeight=518}
	
	stockInfoPane.children.addAll(eEmployee, eEmployeeLabel, eDateTime, eDateTimeLabel, eSaleListLabel, eSaleList)

	//========================================================= Search Pane
	var searchPane:Pane = new Pane(){relocate(15, 120)}

	var prevTransactions:ListView[Transaction] = new ListView[Transaction]() {
		maxHeight = 600;minHeight = 600;minWidth = 370;maxWidth = 370;layoutX() = 0;layoutY() = 0
	}
	prevTransactions.onMouseClicked = (e:MouseEvent) => {
		if(searchList.nonEmpty) {
			eSaleList.items = new ListView[Transaction](prevTransactions.getSelectionModel().getSelectedItem.transactionHistory).getItems
		}
	}
	
	searchPane.children.addAll(prevTransactions)

	//============================================================ Variables ============================
	var currentlySelected:Stock = null
	var searchList:ListBuffer[Stock] = ListBuffer()

	//============================================================ Functions ============================
	def updateSelected(e:Transaction):Unit = {
		eEmployee.text = s"${e.employee.fName} ${e.employee.lName}"
		eDateTime.text = e.dateAndTime.toString
	}

	onKeyPressed = (e:KeyEvent) => {
		if(e.code == KeyCode.Enter) {
		
		}
	}

	content = List(divider, bg1, searchTitle, stockInfoPane, searchPane, topBar, divider2, loggedInText, logoutButton, returnButton)
}