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


class UIstockWindow extends Scene {
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
	
	val createNewButton:Button = new Button("Create New Stock")
	createNewButton.relocate(764, 7)
	createNewButton.onMouseClicked = (e:MouseEvent) => Main.setWindow("newS")
	
	def update():Unit = {
		if(Main.loggedIn != null) {
			loggedInText.text = s"Logged in as: ${Main.loggedIn.fName} ${Main.loggedIn.lName}"
		} else {
			loggedInText.text = "Log in error"
		}
		stockList.selectionModel().clearSelection()
		eName.text = ""
		clearSearch()
		
		allStock = ListBuffer()
		stock = ListBuffer()
		Main.stocks.foreach(i => allStock += i)
		allStock.foreach(i => {
			stock += f"${i.name}, Price: £${i.price}%2.2f, Amount: ${i.count}"
			println("Item added")
		})
		stockList.items = new ComboBox(stock).getItems
	}
	def clearSearch():Unit ={
		sName.text = ""
		sPrice.text = ""
		searchResults.items = new ListView[String]().getItems
	}
	def searchStock(name:String, price:String):Unit = {
		searchList = ListBuffer()
		allStock.copyToBuffer(searchList)
		if(name != "") {
			searchList = searchList.filter(_.name == name)
		}
		if(price != "") {
			searchList = searchList.filter(_.price == price)
		}
		var newListView:ListBuffer[String] = ListBuffer()
		searchList.foreach(i=>newListView += s"${i.name}")
		searchResults.items = new ListView[String](newListView).getItems
	}
	//============================================================ Background Layout ============================
	val divider = Rectangle(300, 0, 2, 800)
	divider.fill = Color.Gray.brighter
	val divider2 = Rectangle(0, 40, 1000, 2)
	divider2.fill = Color.Gray.brighter
	val bg1 = Rectangle(300, 800)
	bg1.fill = Color.Gray
	val topBar = Rectangle(1000, 40)
	topBar.fill = Color.DarkGrey.darker
	
	//============================================================ Content ============================
	val stockTitle:Text = new Text("Stock Details")
	stockTitle.font = titleFont
	stockTitle.fill = textColour
	stockTitle.relocate(315, 50)
	val searchTitle:Text = new Text("Search")
	searchTitle.font = titleFont
	searchTitle.fill = textColour
	searchTitle.relocate(15, 50)
	var allStock:ListBuffer[Stock] = ListBuffer()
	
	var stock:ListBuffer[String] = new ListBuffer()
	
	val stockList:ComboBox[String] = new ComboBox[String](stock)
	stockList.relocate(15, 110)
	stockList.maxWidth = 260
	stockList.promptText = "Choose an item"
	stockList.onAction = (e:ActionEvent) => {
		for(i <- allStock.indices) {
			if(stockList.selectionModel().isSelected(i)) {
				currentlySelected = allStock(i)
				updateSelected(currentlySelected)
			}
		}
	}
	//========================================================= Employee Info Pane
	var stockInfoPane:Pane = new Pane(){relocate(315, 80)}
	
	val eNameLabel:Text = new Text("Item Name"){relocate(0, 30);fill=textColour;font=mainFont}
	val eName:TextField = new TextField(){relocate(0,50);editable=false;maxWidth=200;minWidth=200}
	
	val ePriceLabel:Text = new Text("Price"){relocate(210, 30);fill=textColour;font=mainFont}
	val ePrice:TextField = new TextField(){relocate(210,50);editable=false;maxWidth=200;minWidth=200}
	
	val eDescLabel:Text = new Text("Description"){relocate(0, 170);fill=textColour;font=mainFont}
	val eDesc:TextArea = new TextArea(){relocate(0,190);editable=false;maxWidth=600;minWidth=600;maxHeight=130;minHeight=130}
	
	val eItemTypeLabel:Text = new Text("Item type"){relocate(210, 100);fill=textColour;font=mainFont}
	val eItemType:TextField = new TextField(){relocate(210,120);editable=false;maxWidth=200;minWidth=200}
	
	val eAmountLabel:Text = new Text("Amount"){relocate(0, 100);fill=textColour;font=mainFont}
	val eAmount:TextField = new TextField(){relocate(0,120);editable=false;maxWidth=200;minWidth=200}
	
	val eSpecsLabel:Text = new Text("Specifications"){relocate(0, 340);fill=textColour;font=mainFont}
	val eSpecs:TextArea = new TextArea(){relocate(0,360);editable=false;maxWidth=600;minWidth=600;maxHeight=300;minHeight=300}
	
	stockInfoPane.children.addAll(eName, eNameLabel, ePrice, ePriceLabel, eDescLabel, eDesc, eAmountLabel, eAmount, eSpecsLabel, eSpecs, eItemType, eItemTypeLabel)
	
	//========================================================= Search Pane
	var searchPane:Pane = new Pane(){relocate(15, 150)}
	
	val sNameLabel:Text = new Text("Item name"){relocate(0, 30);fill=textColour;font=mainFont}
	val sName:TextField = new TextField(){relocate(0,50);maxWidth=200;minWidth=200}
	
	val sPriceLabel:Text = new Text("Price"){relocate(0, 100);fill=textColour;font=mainFont}
	val sPrice:TextField = new TextField(){relocate(0,120);maxWidth=200;minWidth=200}
	
	val searchButton:Button = new Button("Search"){relocate(0, 160);onAction = (e:ActionEvent) => searchStock(sName.text.value, sPrice.text.value)}
	val clearButton:Button = new Button("Clear"){relocate(52, 160);onAction = (e:ActionEvent) => clearSearch()}
	
	var searchResults:ListView[String] = new ListView[String]() {
		maxHeight = 388;minHeight = 388;minWidth = 270;maxWidth = 270;layoutX()=0;layoutY()=200
	}
	searchResults.onMouseClicked = (e:MouseEvent) => {
		if(searchList.nonEmpty) {
			updateSelected(searchList(searchResults.getSelectionModel().getSelectedIndex))
		}
	}
	
	searchPane.children.addAll(sNameLabel, sName, sPriceLabel, sPrice, searchResults, searchButton, clearButton)
	
	//============================================================ Variables ============================
	var currentlySelected:Stock = null
	var searchList:ListBuffer[Stock] = ListBuffer()
	
	//============================================================ Functions ============================
	def updateSelected(e:Stock):Unit = {
		var newSpecs:String = ""
		if(e.isInstanceOf[Hardware]) {
			val f:Hardware = e.asInstanceOf[Hardware]
			newSpecs = f.config
		}
		eName.text = e.name
		ePrice.text = e.price.toString
		eAmount.text = ""
		eDesc.text = e.desc
		eSpecs.text = newSpecs
	}
	
	onKeyPressed = (e:KeyEvent) => {
		if(e.code == KeyCode.Enter) {
			searchStock(sName.text.value, sPrice.text.value)
		}
	}
	
	content = List(divider, bg1, searchTitle, stockInfoPane, searchPane, stockList, topBar, divider2, loggedInText, logoutButton, returnButton, createNewButton)
}
