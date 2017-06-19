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
  val reports: ListBuffer[Reports] = new ListBuffer[Reports]
  val loggedIn: Employee = null

  //entry point
  def main(args: Array[String]): Unit = null

  def login(username: String, Password: String): Boolean = false

  def createEmployee(id: Int, name: String, employeeType: EmployeeType.Value): Boolean = false
  def getEmployee(id: Int): Employee = null
  def deleteEmployee(id: Int): Boolean = false

  def createTransaction(id: Int, stocks: List[Stock], employee: Employee, time: Date): Boolean = false
  def getTransaction(id: Int): Transaction
  def deleteTransaction(id: Int): Boolean = false

  def createReport(id: Int, transactions: List[Transaction]) = Boolean
  def getReport(id: Int): Report
  def deleteReport(id: Int): Boolean = false


  def createStock(): Boolean = false
  def createStock(): Boolean = false
  def createStock(): Boolean = false
  def getStock(id: Int): Stock
  def deleteStock(id: Int): Boolean = false


}
