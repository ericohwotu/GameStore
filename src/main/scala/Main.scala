/**
  * Created by Administrator on 19/06/2017.
  */
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage

object Main extends JFXApp with MainVariables{
	
	val mainWindow:UImainWindow = new UImainWindow
	val loginWindow:UIloginWindow = new UIloginWindow
	val transactionWindow:UItransactionWindow = new UItransactionWindow
	val staffWindow:UIstaffWindow = new UIstaffWindow
	var mainStage:PrimaryStage = new PrimaryStage
	setWindow("login")
	
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
			case "report" => print("Report list open")
			case "stock" => println("Stock list open")
			case "staff" => mainStage.scene = staffWindow
				mainStage.width = 1000
				mainStage.height = 800
				mainStage.title = "Elliot and Friends™ Staff"
				staffWindow.update()
			case "3" =>
		}
		mainStage.centerOnScreen()
	}
	def closeWindow():Unit = mainStage.close()
}