/**
  * Created by Administrator on 19/06/2017.
  */
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage


object Main extends JFXApp with MainVariables with EmployeeActions{
	
	val mainWindow:UImainWindow = new UImainWindow
	val loginWindow:UIloginWindow = new UIloginWindow
	val transactionWindow:UItransactionWindow = new UItransactionWindow
	val staffWindow:UIstaffWindow = new UIstaffWindow
	val newEmployeeWindow:UInewEmployeeWindow = new UInewEmployeeWindow
	val reportWindow:UIreportWindow = new UIreportWindow
	val stockWindow:UIstockWindow = new UIstockWindow
	var mainStage:PrimaryStage = new PrimaryStage
	setWindow("newE")
	
	def Main(args:Array[String]):Unit = {}
	
	def setWindow(window:String):Unit = {
		window match {
			case "login" => mainStage.scene = loginWindow
				mainStage.width = 400
				mainStage.height = 400
				mainStage.title = "Elliot and Friends™ Login"
				loginWindow.update()
			case "main" => mainStage.scene = mainWindow
				mainStage.width = 1000
				mainStage.height = 800
				mainStage.title = "Elliot and Friends™ Home"
				mainWindow.update()
			case "transaction" => mainStage.scene = transactionWindow
				mainStage.width = 1000
				mainStage.height = 800
				mainStage.title = "Elliot and Friends™ Transaction"
				transactionWindow.update()
			case "report" => mainStage.scene = reportWindow
				mainStage.width = 1000
				mainStage.height = 800
				mainStage.title = "Elliot and Friends™ Report"
				reportWindow.update()
			case "stock" => mainStage.scene = stockWindow
				mainStage.width = 1000
				mainStage.height = 800
				mainStage.title = "Elliot and Friends™ Stock"
				stockWindow.update()
			case "staff" => mainStage.scene = staffWindow
				mainStage.width = 1000
				mainStage.height = 800
				mainStage.title = "Elliot and Friends™ Staff"
				staffWindow.update()
			case "newE" => mainStage.scene = newEmployeeWindow
				mainStage.width = 1000
				mainStage.height = 800
				mainStage.title = "Elliot and Friends™ New Staff"
				newEmployeeWindow.update()
			case _ => println("Not a valid window")
		}
		mainStage.centerOnScreen()
	}
	def closeWindow():Unit = mainStage.close()
}
