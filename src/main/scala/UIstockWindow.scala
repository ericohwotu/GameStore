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
		eItemType.text = ""
		eSpecs.text = ""
		eDesc.text=""
		eAmount.text = ""
		ePrice.text = ""
		eDate.text = ""
		clearSearch()
		
		allStock = ListBuffer()
		stock = ListBuffer()
		Main.stocks.foreach(i => allStock += i)
		allStock.foreach(i => {
			stock += f"${i.name}, Price: £${i.price}%2.2f, Amount: ${i.count}"
			println("Item added")
		})
		stockList.items = new ComboBox(stock).getItems
		currentlySelected = null
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
	
	val eDateLabel:Text = new Text("Release Date"){relocate(425, 100);fill=textColour;font=mainFont}
	val eDate:TextField = new TextField(){relocate(425,120);editable=false;minWidth=200;maxWidth=200}
	
	val eEditButton:Button = new Button("Edit Stock"){relocate(425,50);visible=false}
	val eSaveButton:Button = new Button("Save Changes"){relocate(425, 50);visible=false}
	val eDiscardButton:Button = new Button("Discard Changes"){relocate(515, 50);visible=false}
	
	stockInfoPane.children.addAll(eName, eNameLabel, ePrice, ePriceLabel, eDescLabel, eDesc, eAmountLabel, eAmount, eSpecsLabel, eSpecs, eItemType, eItemTypeLabel, eDateLabel, eDate, eEditButton, eSaveButton, eDiscardButton)
	
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
	
	//For save security
	var tempName:String = ""
	var tempPrice:String = ""
	var tempDesc:String = ""
	var tempItemType:String = ""
	var tempAmount:String = ""
	var tempSpecs:String = ""
	var tempDate:String = ""
	//============================================================ Functions ============================

	var amountValue = ""
	eAmount.onKeyReleased = (e:KeyEvent)=>{
		println(e.code)
		if (eAmount.text.value.matches("[0-9]*.?[0-9]?[0-9]?"))amountValue=eAmount.text.value
		else {eAmount.text = amountValue; eAmount.positionCaret(amountValue.length)}
	}

	var priceValue = ""
	ePrice.onKeyReleased = (e:KeyEvent)=>{
		println(e.code)
		if (ePrice.text.value.matches("[0-9]*.?[0-9]?[0-9]?"))priceValue=ePrice.text.value
		else {ePrice.text = priceValue; ePrice.positionCaret(priceValue.length)}
	}

	var sPriceValue = ""
	sPrice.onKeyReleased = (e:KeyEvent)=>{
		println(e.code)
		if (sPrice.text.value.matches("[0-9]*.?[0-9]?[0-9]?"))sPriceValue=sPrice.text.value
		else {sPrice.text = sPriceValue; sPrice.positionCaret(sPriceValue.length)}
	}

	def updateSelected(e:Stock):Unit = {
		var newSpecs:String = ""
		if(e.isInstanceOf[Hardware]) {
			val f:Hardware = e.asInstanceOf[Hardware]
			newSpecs = f.config
		}
		eName.text = e.name
		ePrice.text = e.price.toString
		eAmount.text = e.count.toString
		eDesc.text = e.desc
		eSpecs.text = newSpecs
		eItemType.text = e.getClass.getSimpleName
		if(e.getClass.getSimpleName == "Game") {
			eDate.text = e.asInstanceOf[Game].releaseDate.toString
		}
		eEditButton.visible = true
		eSaveButton.visible = false
		eDiscardButton.visible = false
	}
	
	onKeyPressed = (e:KeyEvent) => {
		if(e.code == KeyCode.Enter) {
			searchStock(sName.text.value, sPrice.text.value)
		}
	}
	eEditButton.onMouseClicked = (e:MouseEvent) => {
		tempName = eName.text.value
		tempPrice = ePrice.text.value
		tempDesc = eDesc.text.value
		tempItemType = eItemType.text.value
		tempAmount = eAmount.text.value
		tempSpecs = eSpecs.text.value
		tempDate = eDate.text.value
		
		eName.editable = true
		ePrice.editable = true
		eDesc.editable = true
		eAmount.editable = true
		eSpecs.editable = true
		
		eEditButton.visible = false
		eSaveButton.visible = true
		eDiscardButton.visible = true
	}
	eSaveButton.onMouseClicked = (e:MouseEvent) => {
		val name:String = eName.text.value
		var price:Double = 0
		var amount:Int = 0
		val desc:String = eDesc.text.value
		val config:String = eSpecs.text.value
		
		if(ePrice.text.value.length > 0) {
			try {
				price = ePrice.text.value.toDouble
			} catch {
				case e:Exception => println("wrong price input")
			}
		}
		if(eAmount.text.value.length > 0) {
			try {
				amount = eAmount.text.value.toInt
			} catch {
				case e:Exception => println("wrong amount input")
			}
		}
		
		
		
		if(name.length == 0) {eNameLabel.fill = Color.Red} else {eNameLabel.fill = Color.White}
		if(price == 0) {ePriceLabel.fill = Color.Red} else {ePriceLabel.fill = Color.White}
		if(amount == 0) {eAmountLabel.fill = Color.Red} else {eAmountLabel.fill = Color.White}
		
		if(name.length > 0 && price != 0) {
			currentlySelected.getClass.getSimpleName match {
				case "Console" | "Laptop" | "Phone" =>	currentlySelected.setName(eName.text.value);currentlySelected.setPrice(price);currentlySelected.count=eAmount.text.value.toInt;currentlySelected.asInstanceOf[Hardware].setConfig(eSpecs.text.value)
				case _ => currentlySelected.setName(eName.text.value);currentlySelected.setPrice(ePrice.text.value.toDouble);currentlySelected.count=eAmount.text.value.toInt
			}
		}
		eName.editable = false
		ePrice.editable = false
		eDesc.editable = false
		eAmount.editable = false
		eSpecs.editable = false
		
		eEditButton.visible = true
		eSaveButton.visible = false
		eDiscardButton.visible = false
		Main.writeStockToFile
	}
	eDiscardButton.onMouseClicked = (e:MouseEvent) => {
		eName.text = tempName
		ePrice.text = tempPrice
		eDesc.text = tempDesc
		eItemType.text = tempItemType
		eAmount.text = tempAmount
		eSpecs.text = tempSpecs
		eDate.text = tempDate
		
		eName.editable = false
		ePrice.editable = false
		eDesc.editable = false
		eAmount.editable = false
		eSpecs.editable = false
		
		eEditButton.visible = true
		eSaveButton.visible = false
		eDiscardButton.visible = false
	}
	content = List(divider, bg1, searchTitle, stockInfoPane, searchPane, stockList, topBar, divider2, loggedInText, logoutButton, returnButton, createNewButton, stockTitle)
}
