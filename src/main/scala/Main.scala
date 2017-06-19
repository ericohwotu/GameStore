/**
  * Created by Administrator on 19/06/2017.
  */

import java.util.Date
import javax.xml.stream.XMLReporter

import scala.collection.mutable.ListBuffer

object Main {

  //variable declaration
  val transactions: ListBuffer[Transaction] = new ListBuffer[Transaction]
  val stocks: ListBuffer[Stock] = new ListBuffer[Stock]
  val employees: ListBuffer[Employee] = new ListBuffer[Employee]
  val managers: ListBuffer[Manager] = new ListBuffer[Manager]
  val reports: ListBuffer[Report] = new ListBuffer[Report]
  val loggedIn: Employee = null

  //entry point
  def main(args: Array[String]): Unit = {}


  def login(username: String, Password: String): Boolean = false

}
