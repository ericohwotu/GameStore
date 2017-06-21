import scala.collection.mutable.ListBuffer
import scalafx.Includes._
import scalafx.event.ActionEvent
import scalafx.scene.Scene
import scalafx.scene.control._
import scalafx.scene.input.{KeyCode, KeyEvent, MouseEvent}
import scalafx.scene.layout.Pane
import scalafx.scene.paint.Color
import scalafx.scene.shape.Rectangle
import scalafx.scene.text.{Font, Text}

class UInewTransactionWindow extends Scene{
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
	returnButton.onMouseClicked = (e:MouseEvent) => Main.setWindow("main")
	
	def update():Unit = {
		updateDialogChoices()
	}
	
	//================================================ Content ================================
	val titleText:Text = new Text("Sale"){relocate(10,30);font=titleFont;fill=textColour}
	val currentItems:ListView[String] = new ListView[String](){relocate(10, 60);maxHeight=600;minHeight=600;minWidth=500;maxWidth=500}
	val newItemButton:Button = new Button("Add Item Manually"){relocate(10, 670)}
	val selectedAmountLabel:Text = new Text("Quantity"){relocate(530, 60);font=mainFont;fill=Color.White}
	val selectedAmount:TextField = new TextField(){relocate(530, 80)}
	
	content = List(logoutButton, returnButton, titleText, currentItems, newItemButton, selectedAmount, selectedAmountLabel)
	
	//================================================ Functions ================================
	newItemButton.onMouseClicked = (e:MouseEvent) => {
		new ChoiceDialog(defaultChoice="Select Product",choices=updateDialogChoices()){title = "Select Item";headerText = "Select an Item to add to the Transaction";contentText = "Item"}.showAndWait()
	}
	def updateDialogChoices():List[Stock] = {
		var returnList:ListBuffer[Stock] = ListBuffer[Stock]()
	/*	Main.stocks.foreach(i => {
			returnList += i.name
		})
		returnList.toList*/
		returnList = Main.stocks
		returnList.toList
	}
	def selection(dialog:ChoiceDialog[Stock]):Option[Stock] = {
		dialog.title = "Select Item"
		dialog.headerText = "Select an Item to add to the Transaction"
		dialog.contentText = "Item"
		dialog.showAndWait()
		//var selected:String = dialog.getSelectedItem
	}
}
