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

  /*
  * Employee Managemment
  *
  * */
  def createEmployee(ID: Int, fName: String, lName: String, Age: Int, Gender: String, salary: Int, loginName: String, password: String, employeeType: EmployeeType.Value): Boolean = false

  def getEmployee(id: Int): Employee = null

  def deleteEmployee(id: Int): Boolean = false

  def getManager(id: Int): Manager = null

  def deleteManager(id: Int): Boolean = false

  /*
  * Transaction functions
  *
  * */
  def createTransaction(id: Int, employee: Employee, dateAndTime: Date): Boolean = false

  def getTransaction(id: Int): Transaction = null

  def deleteTransaction(id: Int): Boolean = false

  /*
  * Report functions
  *
  * */
  def createReport(id: Int, dateFrom: Date, dateTo: Date) = Boolean

  def getReport(id: Int): Report = null

  def deleteReport(id: Int): Boolean = false

  /*
  * Transaction functions
  *
  * */
  def createStock(id: Int, name: String, desc: String, price: Double, config: String): Boolean = false

  def createStock(id: Int, name: String, desc: String, price: Double, itemType: ItemType.Value): Boolean = false

  def createStock(id: Int, name: String, desc: String, price: Double, rating: Int, genre: String, console: Console): Boolean = false

  def getStock(id: Int): Stock = null

  def deleteStock(id: Int): Boolean = false

}
