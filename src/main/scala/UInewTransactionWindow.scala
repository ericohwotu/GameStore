import java.util.Date

import scala.collection.mutable.ListBuffer
import scalafx.Includes._
import scalafx.scene.Scene
import scalafx.scene.control._
import scalafx.scene.image.{Image, ImageView}
import scalafx.scene.input.MouseEvent
import scalafx.scene.paint.Color
import scalafx.scene.text.{Font, Text}

class UInewTransactionWindow extends Scene{
	val titleFont:Font = new Font("Times New Roman", 40)
	val mainFont:Font = new Font("Times New Roman", 14)
	val textColour:Color = Color.White
	fill = Color.DarkGrey.darker.darker
	
	//==================================================== Text ===========
	var loggedInText:Text = new Text("")
	loggedInText.relocate(530, 60)
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
	returnButton.onMouseClicked = (e:MouseEvent) => Main.setWindow("main")
	
	val transactionsButton:Button = new Button("Previous Transactions")
	transactionsButton.relocate(743, 7)
	transactionsButton.onMouseClicked = (e:MouseEvent) => Main.setWindow("transaction")
	
	def update():Unit = {
		updateDialogChoices()
		if(Main.loggedIn != null) {
			loggedInText.text = s"Logged in as: ${Main.loggedIn.fName} ${Main.loggedIn.lName}"
		} else {
			loggedInText.text = "Log in error"
		}
		currentItems.items = new ListView[Stock]().getItems
		curSale = ListBuffer()
		runningTotal.text = "0"
	}
	
	val windowIcon:ImageView = new ImageView(new Image("file:src/main/images/ElliotIcon.png")){fitHeight=50;fitWidth=50;relocate(10,740)}
	
	//================================================ Content ================================
	val titleText:Text = new Text("Sale"){relocate(10,30);font=titleFont;fill=textColour}
	val currentItems:ListView[Stock] = new ListView[Stock](){relocate(10, 60);maxHeight=600;minHeight=600;minWidth=500;maxWidth=500}
	val newItemButton:Button = new Button("Add Item Manually"){relocate(10, 670)}
	val performSaleButton:Button = new Button("Complete"){relocate(127, 670)}
	val runningTotal:TextField = new TextField(){editable=false;relocate(200, 670)}
	
	var curSale:ListBuffer[Stock] = ListBuffer()
	content = List(logoutButton, returnButton, transactionsButton, titleText, currentItems, newItemButton, performSaleButton, loggedInText, runningTotal, windowIcon)
	
	//================================================ Functions ================================
	newItemButton.onMouseClicked = (e:MouseEvent) => {
		val selectedStockToAdd = new ChoiceDialog(defaultChoice="Select Product",choices=updateDialogChoices()){title = "Select Item";headerText = "Select an Item to add to the Transaction";contentText = "Item"}.showAndWait()
		selectedStockToAdd match {
			case Some(choice) => curSale += choice.asInstanceOf[Stock]
			case _ => println("No valid selection")
		}
		currentItems.items = new ListView[Stock](curSale).getItems
		var runTot:Double = 0
		curSale.foreach(i => runTot += i.price)
		runningTotal.text = runTot.toString
	}
	performSaleButton.onMouseClicked = (e:MouseEvent) => {
		val newID = if(Main.transactions.nonEmpty) Main.transactions.last.transactionID + 1 else 0
		if(curSale.nonEmpty) {
			Main.createTransaction(newID, Main.loggedIn, new Date(), curSale.toList)
			Main.setWindow("main")
		} else {
			println("Empty sale list")
		}
	}
	def updateDialogChoices():List[Stock] = {
		var returnList:ListBuffer[Stock] = ListBuffer[Stock]()
			returnList = Main.stocks
			returnList.toList
	}
}
