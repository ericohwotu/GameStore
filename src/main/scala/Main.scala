/**
  * Created by Administrator on 19/06/2017.
  */

import java.util.Date

import scala.collection.mutable.ListBuffer
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage

object Main extends JFXApp with MainVariables{
	
	setWindow("login")
	
	def Main(args:Array[String]):Unit = {}
	
	def setWindow(window:String):Unit = {
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
	
	def login(username:String, Password:String):Boolean = true
	
	def logout:Unit = {
		loggedIn = null
	}
}