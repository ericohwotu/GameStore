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


class UIstaffWindow extends Scene {
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
	
	val createNewButton:Button = new Button("Create New Employee")
	createNewButton.relocate(741, 7)
	createNewButton.onMouseClicked = (e:MouseEvent) => Main.setWindow("newE")
	
	def update():Unit = {
		if(Main.loggedIn != null) {
			loggedInText.text = s"Logged in as: ${Main.loggedIn.fName} ${Main.loggedIn.lName}"
		} else {
			loggedInText.text = "Log in error"
		}
		employeeList.selectionModel().clearSelection()
		eFName.text = ""
		eLName.text = ""
		eGender.text = ""
		eAge.text = ""
		eJob.text = ""
		clearSearch()
		if(Main.loggedIn == null) {
			createNewButton.visible = false
		} else {
			Main.loggedIn.getClass.getSimpleName match {
				case "Manager" => createNewButton.visible = true
				case _ => createNewButton.visible = false
			}
		}
		allEmployees = ListBuffer()
		names = ListBuffer()
		Main.employees.foreach(i => allEmployees += i)
		Main.managers.foreach(i => allEmployees += i)
		allEmployees.foreach(i => {
			names += s"${i.fName} ${i.lName}"
			println("Name added")
		})
		employeeList.items = new ComboBox(names).getItems
	}
	def clearSearch():Unit ={
		sFName.text = ""
		sLName.text = ""
		sJob.selectionModel().clearSelection()
		sGender.selectionModel().clearSelection()
		searchResults.items = new ListView[String]().getItems
	}
	def searchEmployees(fName:String, lName:String, gender:String, jobRole:String):Unit = {
		searchList = ListBuffer()
		allEmployees.copyToBuffer(searchList)
		if(fName != "") {
			searchList = searchList.filter(_.fName == fName)
		}
		if(lName != "") {
			searchList = searchList.filter(_.lName == lName)
		}
		if(gender != null) {
			searchList = searchList.filter(_.Gender == gender)
		}
		if(jobRole != null) {
			allEmployees.foreach(i=>println(i.getClass.getSimpleName))
			searchList = searchList.filter(_.getClass.getSimpleName == jobRole)
		}
		var newListView:ListBuffer[String] = ListBuffer()
		searchList.foreach(i=>newListView += s"${i.fName} ${i.lName}")
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
	val employeeTitle:Text = new Text("Employee Details")
	employeeTitle.font = titleFont
	employeeTitle.fill = textColour
	employeeTitle.relocate(315, 50)
	val searchTitle:Text = new Text("Search")
	searchTitle.font = titleFont
	searchTitle.fill = textColour
	searchTitle.relocate(15, 50)
	var allEmployees:ListBuffer[Person] = ListBuffer()
	
	var names:ListBuffer[String] = new ListBuffer()
	
	val employeeList:ComboBox[String] = new ComboBox[String](names)
	employeeList.relocate(15, 110)
	employeeList.maxWidth = 260
	employeeList.promptText = "Choose an employee"
	employeeList.onAction = (e:ActionEvent) => {
		for(i <- allEmployees.indices) {
			if(employeeList.selectionModel().isSelected(i)) {
				currentlySelected = allEmployees(i)
				updateSelected(currentlySelected)
			}
		}
	}
	//========================================================= Employee Info Pane
	var employeeInfoPane:Pane = new Pane(){relocate(315, 80)}
	
	val eFNameLabel:Text = new Text("First Name"){relocate(0, 30);fill=textColour;font=mainFont}
	val eFName:TextField = new TextField(){relocate(0,50);editable=false;maxWidth=200;minWidth=200}
	
	val eLNameLabel:Text = new Text("Surname"){relocate(210, 30);fill=textColour;font=mainFont}
	val eLName:TextField = new TextField(){relocate(210,50);editable=false;maxWidth=200;minWidth=200}
	
	val eGenderLabel:Text = new Text("Gender"){relocate(0, 100);fill=textColour;font=mainFont}
	val eGender:TextField = new TextField(){relocate(0,120);editable=false;maxWidth=200;minWidth=200}
	
	val eAgeLabel:Text = new Text("Age"){relocate(210, 100);fill=textColour;font=mainFont}
	val eAge:TextField = new TextField(){relocate(210,120);editable=false;maxWidth=200;minWidth=200}
	
	val eJobLabel:Text = new Text("Job Role"){relocate(0, 170);fill=textColour;font=mainFont}
	val eJob:TextField = new TextField(){relocate(0,190);editable=false;maxWidth=200;minWidth=200}
	
	employeeInfoPane.children.addAll(eFName, eFNameLabel, eLName, eLNameLabel, eGenderLabel, eGender, eAgeLabel, eAge, eJobLabel, eJob)
	
	//========================================================= Search Pane
	var searchPane:Pane = new Pane(){relocate(15, 150)}
	
	val sFNameLabel:Text = new Text("First Name"){relocate(0, 30);fill=textColour;font=mainFont}
	val sFName:TextField = new TextField(){relocate(0,50);maxWidth=200;minWidth=200}
	
	val sLNameLabel:Text = new Text("Surname"){relocate(0, 100);fill=textColour;font=mainFont}
	val sLName:TextField = new TextField(){relocate(0,120);maxWidth=200;minWidth=200}
	
	val sGenderLabel:Text = new Text("Gender"){relocate(0, 170);fill=textColour;font=mainFont}
	val sGender:ComboBox[String] = new ComboBox(List("Male", "Female"))
	sGender.minWidth = 120;sGender.maxWidth=120;sGender.relocate(0, 190)
	
	val sJobLabel:Text = new Text("Job Role"){relocate(0, 240);fill=textColour;font=mainFont}
	val sJob:ComboBox[String] = new ComboBox(List("Employee", "Manager"))
	sJob.minWidth = 120;sJob.maxWidth=120;sJob.relocate(0, 260)
	
	val searchButton:Button = new Button("Search"){relocate(0, 320);onAction = (e:ActionEvent) => {searchEmployees(sFName.text.value, sLName.text.value, sGender.getValue, sJob.getValue)}}
	val clearButton:Button = new Button("Clear"){relocate(52, 320);onAction = (e:ActionEvent) => {clearSearch()}}
	
	var searchResults:ListView[String] = new ListView[String]() {
		maxHeight = 200;minHeight = 200;minWidth = 270;maxWidth = 270;layoutX()=0;layoutY()=380
	}
	searchResults.onMouseClicked = (e:MouseEvent) => {
		if(searchList.nonEmpty) {
			updateSelected(searchList(searchResults.getSelectionModel().getSelectedIndex))
		}
	}
	searchPane.children.addAll(sFNameLabel, sFName, sLNameLabel, sLName, sGenderLabel, sGender, sJobLabel, sJob, searchResults, searchButton, clearButton)
	
	//============================================================ Variables ============================
	var currentlySelected:Person = null
	var searchList:ListBuffer[Person] = ListBuffer()
	
	//============================================================ Functions ============================
	def updateSelected(e:Person):Unit = {
		eFName.text = e.fName
		eLName.text = e.lName
		eGender.text = e.Gender
		eAge.text = e.Age.toString
		eJob.text = e.getClass.getTypeName
	}
	
	onKeyPressed = (e:KeyEvent) => {
		if(e.code == KeyCode.Enter) {
			searchEmployees(sFName.text.value, sLName.text.value, sGender.getValue, sJob.getValue)
		}
	}
	
	content = List(divider, bg1, employeeTitle, searchTitle, employeeInfoPane, searchPane, employeeList, topBar, divider2, loggedInText, logoutButton, returnButton, createNewButton)
}
