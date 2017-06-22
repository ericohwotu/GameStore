import java.time.LocalDate
import java.time.chrono.{ChronoLocalDate, Chronology}
import java.util.Date

import scalafx.Includes._
import scalafx.event.ActionEvent
import scalafx.scene.Scene
import scalafx.scene.control.{Button, DatePicker, ListView}
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
	
	def update():Unit = {
		if(Main.loggedIn != null) {
			loggedInText.text = s"Logged in as: ${Main.loggedIn.fName} ${Main.loggedIn.lName}"
		} else {
			loggedInText.text = "Log in error"
		}
		startDatePicker.value = null
		endDatePicker.value = null
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
		var day:Int = startDatePicker.value.value.getDayOfMonth
		var month:Int = startDatePicker.value.value.getMonthValue
		var year:Int = startDatePicker.value.value.getYear
		startDateAsDate = new Date(year,month,day)
	}
	endDatePicker.onAction = (e:ActionEvent) => {
		var day:Int = endDatePicker.value.value.getDayOfMonth
		var month:Int = endDatePicker.value.value.getMonthValue
		var year:Int = endDatePicker.value.value.getYear
		endDateAsDate = new Date(year,month,day)
	}
	createPane.children.addAll(reportTitle, startDateLabel, endDateLabel, startDatePicker, endDatePicker, createReportButton)
	
	//======================================================== Current Pane ====================
	val currentPane:Pane = new Pane(){relocate(0,300)}
	
	val currentPaneTitle:Text = new Text("Current Reports"){relocate(30,0);font=mainFont;fill=textColour}
	val curReports:ListView[Report] = new ListView(List[Report]()){relocate(30,30);maxHeight=350;minHeight=350;maxWidth=900;minWidth=900}
	val openReport:Button = new Button("Open Report"){relocate(830,400)}
	
	currentPane.children.addAll(currentPaneTitle, curReports, openReport)
	
	
	//========================================================================================== Main Content List ====================
	content = List(loggedInText, logoutButton, returnButton, createPane, currentPane)
	//=================================================================================================================================
	
	
	//======================================================== Functions ====================
	createReportButton.onMouseClicked = (e:MouseEvent) => {
		if(startDatePicker.value.value != null && endDatePicker.value.value != null) {
			if(endDateAsDate.after(startDateAsDate)) {
				Main.createReport(Main.reports.length, startDateAsDate, endDateAsDate)
				curReports.items = new ListView[Report](Main.reports.toList).getItems
				println(Main.reports)
			}
		}
	}
}
