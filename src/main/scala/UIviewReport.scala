import scala.collection.mutable.ListBuffer
import scalafx.Includes._
import scalafx.scene.Scene
import scalafx.scene.control.{Button, ListView, TextField}
import scalafx.scene.input.{KeyCode, KeyEvent, MouseEvent}
import scalafx.scene.layout.Pane
import scalafx.scene.paint.Color
import scalafx.scene.shape.Rectangle
import scalafx.scene.text.{Font, Text}

/**
	* Created by duane on 22/06/2017.
	*/
class UIviewReport extends Scene{
	val mainFont:Font = new Font("Times New Roman", 18)
	val titleFont:Font = new Font("Times New Roman", 40)
	val textColour:Color = Color.White
	var openReport:Report = null
	var openTransactionList:ListBuffer[Transaction] = null
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
		Main.setWindow("report")
	}
	def update():Unit = {
		if(Main.loggedIn != null) {
			loggedInText.text = s"Logged in as: ${Main.loggedIn.fName} ${Main.loggedIn.lName}"
		} else {
			loggedInText.text = "Log in error"
		}
		if(openReport != null) {
			openTransactionList = openReport.transaction
		}
		eEmployee.text = ""
		eDateTime.text = ""
		eSaleList.items = new ListView[Stock](List[Stock]()).getItems
	}
	//============================================================ Background Layout ============================
	val divider = Rectangle(400, 0, 2, 800)
	divider.fill = Color.Gray.brighter
	val divider2 = Rectangle(0, 40, 1000, 2)
	divider2.fill = Color.Gray.brighter
	val bg1 = Rectangle(400, 800)
	bg1.fill = Color.Gray
	val secondDivider = Rectangle(400, 200, 600, 2)
	secondDivider.fill = Color.Gray.brighter
	val topBar = Rectangle(1000, 40)
	topBar.fill = Color.DarkGrey.darker
	
	//============================================================ Content ============================
	val stockTitle:Text = new Text("Transaction Details")
	stockTitle.font = titleFont
	stockTitle.fill = textColour
	stockTitle.relocate(415, 235)
	val searchTitle:Text = new Text("Report History")
	searchTitle.font = titleFont
	searchTitle.fill = textColour
	searchTitle.relocate(15, 50)
	var allTransactions:ListBuffer[Stock] = ListBuffer()
	
	//========================================================= Employee Info Pane
	var stockInfoPane:Pane = new Pane(){relocate(415, 180)}
	
	val eEmployeeLabel:Text = new Text("Employee"){relocate(0, 100);fill=textColour;font=mainFont}
	val eEmployee:TextField = new TextField(){relocate(0,120);editable=false;maxWidth=200;minWidth=200}
	
	val eFromDateLabel:Text = new Text("From"){relocate(0, 30);fill=textColour;font=mainFont}
	val eFromDate:TextField = new TextField(){relocate(0,50);editable=false;maxWidth=200;minWidth=200}
	
	val eToDateLabel:Text = new Text("To"){relocate(210, 30);fill=textColour;font=mainFont}
	val eToDate:TextField = new TextField(){relocate(210,50);editable=false;maxWidth=200;minWidth=200}
	
	val eDateTimeLabel:Text = new Text("Transaction Date"){relocate(210, 100);fill=textColour;font=mainFont}
	val eDateTime:TextField = new TextField(){relocate(210,120);editable=false;maxWidth=200;minWidth=200}
	
	val eSaleListLabel:Text = new Text("Sales List"){relocate(0, 170);fill=textColour;font=mainFont}
	val eSaleList:ListView[Stock] = new ListView[Stock](){relocate(0,190);editable=false;maxWidth=500;minWidth=500;minHeight=458;maxHeight=458}
	
	stockInfoPane.children.addAll(eEmployee, eEmployeeLabel, eDateTime, eDateTimeLabel, eSaleListLabel, eSaleList)
	
	
	var reportHeaderPane:Pane = new Pane(){}
	
	reportHeaderPane.children.addAll(eFromDate, eFromDateLabel, eToDate, eToDateLabel)
	
	//========================================================= Search Pane
	var searchPane:Pane = new Pane(){relocate(15, 120)}
	
	var prevTransactions:ListView[Transaction] = new ListView[Transaction]() {
		maxHeight = 600;minHeight = 600;minWidth = 370;maxWidth = 370;layoutX() = 0;layoutY() = 0
	}
	prevTransactions.onMouseClicked = (e:MouseEvent) => {
		if(Main.transactions.nonEmpty) {
			updateSelected(prevTransactions.getSelectionModel().getSelectedItem)
		}
	}
	
	searchPane.children.addAll(prevTransactions)
	
	//============================================================ Variables ============================
	var currentlySelected:Transaction = null
	
	//============================================================ Functions ============================
	def updateSelected(e:Transaction):Unit = {
		currentlySelected = e
		eEmployee.text = s"${e.employeeV.fName} ${e.employeeV.lName}"
		eDateTime.text = e.iDate.toString
		eSaleList.items = new ListView[Stock](e.transactionHistory).getItems
	}
	onKeyPressed = (e:KeyEvent) => {
		if(e.code == KeyCode.Enter) {
		
		}
	}
	content = List(divider, bg1, searchTitle, stockInfoPane, searchPane, topBar, divider2, loggedInText, logoutButton, returnButton, stockTitle, secondDivider)
}
