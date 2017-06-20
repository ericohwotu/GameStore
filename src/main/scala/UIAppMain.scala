import scalafx.application.JFXApp

/**
	* Created by duane on 20/06/2017.
	*/
object UIAppMain extends JFXApp{
	
	val mainInfo:Main = new Main
	
	setWindow("login")
	
	
	def setWindow(window:String):Unit ={
		window match {
			case "login" => new UIloginWindow
			case "main" => new UImainWindow
			case "transaction" => println("Transaction open")
			case "report" =>
			case "1" =>
			case "2" =>
			case "3" =>
		}
	}
}
