import scalafx.Includes._
import scalafx.event.ActionEvent
import scalafx.scene.Scene
import scalafx.scene.control._
import scalafx.scene.image.{Image, ImageView}
import scalafx.scene.input.{KeyCode, KeyEvent, MouseEvent}
import scalafx.scene.layout.Pane
import scalafx.scene.paint.Color
import scalafx.scene.shape.Rectangle
import scalafx.scene.text.{Font, Text}


class UInewEmployeeWindow extends Scene {
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
	returnButton.onMouseClicked = (e:MouseEvent) => Main.setWindow("staff")
	
	def update():Unit = {
		if(Main.loggedIn != null) {
			loggedInText.text = s"Logged in as: ${Main.loggedIn.fName} ${Main.loggedIn.lName}"
		} else {
			loggedInText.text = "Log in error"
		}
		eFName.clear()
		eLName.clear()
		eGender.selectionModel().clearSelection()
		eJob.selectionModel().clearSelection()
		eSalary.clear()
		eAge.clear()
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
	
	val windowIcon:ImageView = new ImageView(new Image("file:src/main/images/ElliotIcon.png")){fitHeight=50;fitWidth=50;relocate(10,740)}
	
	//============================================================ Content ============================
	val employeeTitle:Text = new Text("Employee Details")
	employeeTitle.font = titleFont
	employeeTitle.fill = textColour
	employeeTitle.relocate(315, 50)
	//========================================================= Employee Info Pane
	var employeeInfoPane:Pane = new Pane(){relocate(315, 80)}
	
	val eFNameLabel:Text = new Text("First Name"){relocate(0, 30);fill=textColour;font=mainFont}
	val eFName:TextField = new TextField(){relocate(0,50);maxWidth=200;minWidth=200}
	
	val eLNameLabel:Text = new Text("Surname"){relocate(210, 30);fill=textColour;font=mainFont}
	val eLName:TextField = new TextField(){relocate(210,50);maxWidth=200;minWidth=200}
	
	val eGenderLabel:Text = new Text("Gender"){relocate(0, 100);fill=textColour;font=mainFont}
	val eGender:ComboBox[String] = new ComboBox[String](List("Male", "Female")){relocate(0,120);editable=false;maxWidth=200;minWidth=200}
	
	val eAgeLabel:Text = new Text("Age"){relocate(210, 100);fill=textColour;font=mainFont}
	val eAge:TextField = new TextField(){relocate(210,120);maxWidth=200;minWidth=200}
	
	val eSalaryLabel:Text = new Text("Salary"){relocate(210, 170);fill=textColour;font=mainFont}
	val eSalary:TextField = new TextField(){relocate(210,190);maxWidth=200;minWidth=200}
	
	val eJobLabel:Text = new Text("Job Role"){relocate(0, 170);fill=textColour;font=mainFont}
	val eJob:ComboBox[String] = new ComboBox(List("Employee", "Manager", "Customer"))
	eJob.minWidth=200;eJob.maxWidth=200;eJob.relocate(0, 190)
	
	val eCreateButton:Button = new Button("Create Employee"){relocate(210, 240)}
	
	employeeInfoPane.children.addAll(eFName, eFNameLabel, eLName, eLNameLabel, eGenderLabel, eGender, eAgeLabel, eAge, eJobLabel, eJob, eSalaryLabel, eSalary, eCreateButton)
	
	//============================================================ Variables ============================
	//============================================================ Functions ============================
	def createEmployee(): Unit = {
		val fName:String = eFName.text.value
		val lName:String = eLName.text.value
		val age:String = eAge.text.value
		val gender:String = if(eGender.selectionModel().getSelectedItem != null) eGender.selectionModel().getSelectedItem.toString else ""
		val salary:String = eSalary.text.value
		val job:String = if(eJob.selectionModel().getSelectedItem != null) eJob.selectionModel().getSelectedItem.toString else ""
		
		var nAge:Int = 0
		var nSalary:Int = 0
		
		if(age != "") {nAge=age.toInt}
		if(salary.length > 0) {nSalary=age.toInt}
		if(job != "") {
			var eType:EmployeeType.Value = null
			var newID:Int = 0
			job match {
				case "Manager" => eType = EmployeeType.MANAGER;newID=if(Main.managers.nonEmpty) Main.managers.last.ID+1 else 0
				case "Employee" => eType = EmployeeType.EMPLOYEE;newID=if(Main.employees.nonEmpty) Main.employees.last.ID+1 else 0
				case "Customer" => eType = EmployeeType.CUSTOMER;newID=if(Main.customers.nonEmpty) Main.customers.last.ID+1 else 0
			}
			eType match {
				case EmployeeType.MANAGER | EmployeeType.EMPLOYEE =>
					if(fName.length > 0 && lName.length > 0 && nAge > 17 && gender.length > 0 && nSalary > 0) {
						println("Created")
						println(s"New username: ${fName(0).toString}${lName(0).toString}")
						Main.createEmployee(newID, fName, lName, nAge, gender, nSalary, s"${fName(0).toString}${lName(0).toString}", "password", eType)
						Main.setWindow("staff")
					}
				case EmployeeType.CUSTOMER =>
					if(fName.length > 0 && lName.length > 0 && nAge > 17 && gender.length > 0 && nSalary > 0) {
						println("Created Customer")
						Main.createEmployee(newID, fName, lName, nAge, gender, 0, 0)
						Main.setWindow("staff")
					}
			}
		}
		if(fName.length == 0) {eFNameLabel.fill = Color.Red} else {eFNameLabel.fill = Color.White}
		if(lName.length == 0) {eLNameLabel.fill = Color.Red} else {eLNameLabel.fill = Color.White}
		if(nAge <= 17) {eAgeLabel.fill = Color.Red} else {eAgeLabel.fill = Color.White}
		if(gender == "") {eGenderLabel.fill = Color.Red} else {eGenderLabel.fill = Color.White}
		if(nSalary <= 0) {eSalaryLabel.fill = Color.Red} else {eSalaryLabel.fill = Color.White}
		if(job == "") {eJobLabel.fill = Color.Red} else {eJobLabel.fill = Color.White}
	}
	
	onKeyPressed = (e:KeyEvent) => {
		if(e.code == KeyCode.Enter) {
			createEmployee()
		}
	}
	eCreateButton.onAction = (e:ActionEvent) => {
		createEmployee()
	}
	
	content = List(divider, bg1, employeeTitle, employeeInfoPane, topBar, divider2, loggedInText, logoutButton, returnButton, windowIcon)
}
