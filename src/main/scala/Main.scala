/**
  * Created by Administrator on 19/06/2017.
  */

import java.util.Date

import scala.collection.mutable.ListBuffer
import scalafx.application.JFXApp

object Main extends JFXApp{

  //variable declaration
  val transactions: ListBuffer[Transaction] = new ListBuffer[Transaction]
  val stocks: ListBuffer[Stock] = new ListBuffer[Stock]
  val employees: ListBuffer[Employee] = new ListBuffer[Employee]
  val managers: ListBuffer[Manager] = new ListBuffer[Manager]
  val reports: ListBuffer[Report] = new ListBuffer[Report]
  val loggedIn: Employee = null
  //var loginWindow:UIloginWindow = new UIloginWindow
  //stage = loginWindow
  
  def Main(args: Array[String]): Unit = {}
  

  def login(username: String, Password: String): Boolean = false

}
