/**
  * Created by Administrator on 19/06/2017.
  */
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage

object Main extends JFXApp with MainVariables{
	
	val mainWindow:UImainWindow = new UImainWindow
	val loginWindow:UIloginWindow = new UIloginWindow
	var mainStage:PrimaryStage = new PrimaryStage
	setWindow("login")
	
	def Main(args:Array[String]):Unit = {}
	
	def setWindow(window:String):Unit = {
		window match {
			case "login" => mainStage.scene = loginWindow
				mainStage.width = 400
				mainStage.height = 400
				mainStage.title = "Elliot and Friends™ Login"
			case "main" => mainStage.scene = mainWindow
				mainStage.width = 1000
				mainStage.height = 800
				mainStage.title = "Elliot and Friends™ Home"
			case "transaction" => println("Transaction open")
			case "report" => println("Report open")
			case "stock" => println("Stock list open")
			case "staff" => println("Staff list open")
			case "3" =>
		}
		mainStage.centerOnScreen()
	}
	def closeWindow = mainStage.close()
}