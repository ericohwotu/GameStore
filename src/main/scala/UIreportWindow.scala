import java.util.Date

import scalafx.Includes._
import scalafx.event.ActionEvent
import scalafx.scene.Scene
import scalafx.scene.control.{Button, DatePicker, ListView}
import scalafx.scene.image.{Image, ImageView}
import scalafx.scene.input.MouseEvent
import scalafx.scene.layout.Pane
import scalafx.scene.paint.Color
import scalafx.scene.text.{Font, Text}


class UIreportWindow extends Scene{
	val mainFont:Font = new Font("Times New Roman", 18)
	val titleFont:Font = new Font("Times New Roman", 40)
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
	logoutButton.onMouseClicked = (e:MouseEvent) =>{
		Main.logout()
		Main.setWindow("login")
	}
	val returnButton:Button = new Button("Return")
	returnButton.relocate(874, 7)
	returnButton.onMouseClicked = (e:MouseEvent) =>{
		Main.setWindow("main")
	}
	val deleteButton:Button = new Button("Delete Report")
	deleteButton.relocate(784, 7)
	deleteButton.onMouseClicked = (e:MouseEvent) => {
		try {
			if(curReports.getSelectionModel().getSelectedItem.getClass != null) {
				Main.deleteReport(curReports.getSelectionModel().getSelectedItem.reportID)
			}
		} catch {
			case e:Exception => println("No report selected")
		}
	}
	
	def update():Unit = {
		if(Main.loggedIn != null) {
			loggedInText.text = s"Logged in as: ${Main.loggedIn.fName} ${Main.loggedIn.lName}"
		} else {
			loggedInText.text = "Log in error"
		}
		startDatePicker.value = null
		endDatePicker.value = null
		curReports.items = new ListView[Report](Main.reports.toList.reverse).getItems
		if(Main.loggedIn.getClass.getSimpleName == "Manager") {
			deleteButton.visible = true
			startDatePicker.visible = true
			endDatePicker.visible = true
			createReportButton.visible = true
			startDateLabel.visible = true
			endDateLabel.visible = true
		} else {
			deleteButton.visible = false
			startDatePicker.visible = false
			endDatePicker.visible = false
			createReportButton.visible = false
			startDateLabel.visible = false
			endDateLabel.visible = false
		}
	}
	
	//======================================================== Create Pane ====================
	val createPane:Pane = new Pane(){relocate(200, 70)}
	
	val reportTitle:Text = new Text("Reports Home"){font=titleFont;fill=textColour;}
	val startDateLabel:Text = new Text("From"){relocate(0, 60);fill=textColour;font=mainFont}
	val endDateLabel:Text = new Text("To"){relocate(250, 60);fill=textColour;font=mainFont}
	val startDatePicker:DatePicker = new DatePicker(){relocate(0, 80);promptText="Select From Date";editable=false}
	val endDatePicker:DatePicker = new DatePicker(){relocate(250, 80);promptText="Select To Date";editable=false}
	val createReportButton:Button = new Button("Create Report"){relocate(500,80)}
	
	var startDateAsDate:Date = new Date()
	var endDateAsDate:Date = new Date()
	
	startDatePicker.onAction = (e:ActionEvent) => {
		try {
			var day:Int = startDatePicker.value.value.getDayOfMonth
			var month:Int = startDatePicker.value.value.getMonthValue
			var year:Int = startDatePicker.value.value.getYear
			startDateAsDate = new Date(year - 1900, month - 1, day)
		} catch {
			case e:Exception => println("Start Date Picker Invalid")
		}
	}
	endDatePicker.onAction = (e:ActionEvent) => {
		try {
			var day:Int = endDatePicker.value.value.getDayOfMonth
			var month:Int = endDatePicker.value.value.getMonthValue
			var year:Int = endDatePicker.value.value.getYear
			endDateAsDate = new Date(year - 1900, month - 1, day)
		} catch {
			case e:Exception => println("End Date Picker Invalid")
		}
	}
	createPane.children.addAll(reportTitle, startDateLabel, endDateLabel, startDatePicker, endDatePicker, createReportButton)
	
	//======================================================== Current Pane ====================
	val currentPane:Pane = new Pane(){relocate(0,300)}
	
	val currentPaneTitle:Text = new Text("Current Reports"){relocate(30,0);font=mainFont;fill=textColour}
	val curReports:ListView[Report] = new ListView(List[Report]()){relocate(30,30);maxHeight=350;minHeight=350;maxWidth=900;minWidth=900}
	val openReport:Button = new Button("Open Report"){relocate(830,400)}
	
	currentPane.children.addAll(currentPaneTitle, curReports, openReport)
	
	
	//========================================================================================== Main Content List ====================
	
	val windowIcon:ImageView = new ImageView(new Image("file:src/main/images/ElliotIcon.png")){fitHeight=50;fitWidth=50;relocate(10,740)}
	
	content = List(loggedInText, logoutButton, returnButton, deleteButton, createPane, currentPane, windowIcon)
	//=================================================================================================================================
	
	
	//======================================================== Functions ====================
	createReportButton.onMouseClicked = (e:MouseEvent) => {
		if(startDatePicker.value.value != null && endDatePicker.value.value != null) {
			if(endDateAsDate.after(startDateAsDate)) {
				var newID:Int = 0
				if(Main.reports.nonEmpty) {
					newID = Main.reports.last.reportID + 1
				}
				Main.createReport(newID, startDateAsDate, endDateAsDate)
				curReports.items = new ListView[Report](Main.reports.toList.reverse).getItems
			}
		}
	}
	openReport.onMouseClicked = (e:MouseEvent) => {
		if(curReports.getSelectionModel().getSelectedItem != null) {
			Main.reportViewWindow.openReport(curReports.getSelectionModel().getSelectedItem)
			Main.setWindow("reportV")
		}
	}
}
