/**
  * Created by Administrator on 19/06/2017.
  */

import java.util.Date

import scala.collection.mutable.ListBuffer
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage

object Main extends JFXApp{

  //variable declaration
  val transactions: ListBuffer[Transaction] = new ListBuffer[Transaction]
  val stocks: ListBuffer[Stock] = new ListBuffer[Stock]
  val employees: ListBuffer[Employee] = new ListBuffer[Employee]
  val managers: ListBuffer[Manager] = new ListBuffer[Manager]
  val reports: ListBuffer[Report] = new ListBuffer[Report]
  var loggedIn: Employee = null
	setWindow("login")
	
  def Main(args: Array[String]): Unit = {}
  
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

  def login(username: String, Password: String): Boolean = true
	
	def logout:Unit ={
		loggedIn = null
	}

}
