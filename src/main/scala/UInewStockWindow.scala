import scalafx.Includes._
import scalafx.event.ActionEvent
import scalafx.scene.Scene
import scalafx.scene.control._
import scalafx.scene.input.{KeyCode, KeyEvent, MouseEvent}
import scalafx.scene.layout.Pane
import scalafx.scene.paint.Color
import scalafx.scene.shape.Rectangle
import scalafx.scene.text.{Font, Text}


class UInewStockWindow extends Scene {
	val titleFont:Font = new Font("Times New Roman", 40)
	val mainFont:Font = new Font("Times New Roman", 14)
  var txt = ""
  var txt2 = ""
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
	returnButton.onMouseClicked = (e:MouseEvent) => Main.setWindow("stock")
	
	def update():Unit = {
		if(Main.loggedIn != null) {
			loggedInText.text = s"Logged in as: ${Main.loggedIn.fName} ${Main.loggedIn.lName}"
		} else {
			loggedInText.text = "Log in error"
		}
		eName.text = ""
		eSpecs.text = ""
		eItemType.getSelectionModel.clearSelection()
		eDesc.text = ""
		eAmount.text = ""
		ePrice.text = ""
		eSpecsLabel.visible = false
		eSpecs.visible = false


	}
	def createStock(): Unit = {
		val name:String = eName.text.value
		var price:Double = 0
		var amount:Int = 0
		val iType:String = eItemType.getSelectionModel().getSelectedItem
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
		if(iType == "") {eItemTypeLabel.fill = Color.Red} else {eItemTypeLabel.fill = Color.White}
		
		
		if(name.length > 0 && price != 0 && iType != "" && desc.length > 0) {
			iType match {
				case "Game" => Main.createStock(Main.stocks.length, name, desc, price, 0, "", null)
				case "Console" => Main.createStock(Main.stocks.length, name, desc, price, amount, config, HardwareType.CONSOLE)
				case "Laptop" => Main.createStock(Main.stocks.length, name, desc, price, amount, config, HardwareType.LAPTOP)
				case "Phone" => Main.createStock(Main.stocks.length, name, desc, price, amount, config, HardwareType.PHONE)
				case "Mug" => Main.createStock(Main.stocks.length, name, desc, price, amount, ItemType.MUG)
				case "Poster" => Main.createStock(Main.stocks.length, name, desc, price, amount, ItemType.POSTER)
				case "Shirt" => Main.createStock(Main.stocks.length, name, desc, price, amount, ItemType.SHIRT)
			}
		}
		
	}
  //==============================================================Test ==========================//

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
	//========================================================= Employee Info Pane
	var stockInfoPane:Pane = new Pane(){relocate(315, 80)}
	
	val eNameLabel:Text = new Text("Item Name"){relocate(0, 30);fill=textColour;font=mainFont}
	val eName:TextField = new TextField(){relocate(0,50);maxWidth=200;minWidth=200}
	
	val ePriceLabel:Text = new Text("Price"){relocate(210, 30);fill=textColour;font=mainFont}
	val ePrice:TextField = new TextField(){relocate(210,50);maxWidth=200;minWidth=200}
	
	val eDescLabel:Text = new Text("Description"){relocate(0, 170);fill=textColour;font=mainFont}
	val eDesc:TextArea = new TextArea(){relocate(0,190);maxWidth=600;minWidth=600;maxHeight=130;minHeight=130}
	
	val eItemTypeLabel:Text = new Text("Item type"){relocate(210, 100);fill=textColour;font=mainFont}
	val eItemType:ComboBox[String] = new ComboBox[String](List("Game", "Console", "Laptop", "Phone", "Shirt", "Mug", "Poster")){relocate(210,120);maxWidth=200;minWidth=200}
	
	val eAmountLabel:Text = new Text("Amount"){relocate(0, 100);fill=textColour;font=mainFont}
	val eAmount:TextField = new TextField(){relocate(0,120);maxWidth=200;minWidth=200}

	val eSpecsLabel:Text = new Text("Specifications"){relocate(0, 340);fill=textColour;font=mainFont}
	val eSpecs:TextArea = new TextArea(){relocate(0,360);maxWidth=600;minWidth=600;maxHeight=300;minHeight=300}
	
	val createStockButton:Button = new Button("Add Stock"){relocate(530, 50)}
	
	stockInfoPane.children.addAll(eName, eNameLabel, ePrice, ePriceLabel, eDescLabel, eDesc, eAmountLabel, eAmount, eSpecsLabel, eSpecs, eItemType, eItemTypeLabel, createStockButton)
	
	//========================================================= Search Pane
	//============================================================ Variables ============================
	//============================================================ Functions ============================


	onKeyPressed = (e:KeyEvent) => {
		if(e.code == KeyCode.Enter) {
			createStock()
		}
	}


  eAmount.onKeyReleased = (e:KeyEvent)=>{
    println(e.code)
    if (eAmount.text.value.matches("[0-9]*"))txt=eAmount.text.value
    else {eAmount.text = txt; eAmount.positionCaret(txt.length)}
  }

  ePrice.onKeyReleased = (e:KeyEvent)=>{
    println(e.code)
    if (ePrice.text.value.matches("[0-9]*"))txt2=ePrice.text.value
    else {ePrice.text = txt2; ePrice.positionCaret(txt2.length)}
  }

	createStockButton.onMouseClicked = (e:MouseEvent) => {
		createStock()
	}
	eItemType.onAction = (e:ActionEvent) => {
		val pickedOption:String = eItemType.getSelectionModel().getSelectedItem
		var specShow:Boolean = true
		pickedOption match {
			case "Console"|"Laptop"|"Phone" => specShow = true
			case _ => specShow = false
		}
		eSpecsLabel.visible = specShow
		eSpecs.visible = specShow
	}
	content = List(divider, bg1, stockTitle, stockInfoPane, topBar, divider2, loggedInText, logoutButton, returnButton)
}