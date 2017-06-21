import java.util.Date

import scala.collection.mutable.ListBuffer;

/**
  * Created by alfie on 19/06/2017.
  *
  * Transaction class that will have all the functionalities relating to money transactions.
  *
  */

case class Transaction(transactionID: Int, var employee: Employee, dateAndTime: Date, stock :List[Stock]) {

 // var stocks: List[Stock] = List.empty
  var transactionHistory: ListBuffer[Transaction] = new ListBuffer[Transaction]
  var price: Double = 0.0
  var discount: Double = 0.0
//  var employeeList: ListBuffer[Employee] = new ListBuffer[Employee]

  /**
    * Getters and setters for parameters
    * @return
    */

  //def getStock = stocks
  def getTransactionHistory = transactionHistory
  def getDiscount = discount
  def setDiscount {this.discount = discount}

 /* /**
    * Method to add employees to any transactions.
    *
    * if the employee doesn't exist in the employees list add to the list.
    *
    * @param employee
    */

  def addEmployee(employee: Employee): Boolean = {
    val findEmployee = employeeList.find(t => t == employee)
    if (findEmployee.contains(employee)) {
      employeeList += employee
      true
    }else{
      false
    }
  }*/

  /**
    * Method to remove employees from any transactions.
    *
    * @param employee
    */

 /* def removeEmployee(employee: Employee): Boolean = {
    val findEmployee = employeeList.find(t => t == employee)
    if(findEmployee.contains(employee)) {
      employeeList -= employee
      true
    } else {
      false
    }
  }*/

  /**
    * Method to update employees related to any transactions.
    *
    * @param employee
    */

  def updateEmployee(employee: Employee): Unit = {
    this.employee = employee}

  /**
    * Method to calculate the price of the transaction by multiplying the price by the discount value (if any...)
    *
    * @param price
    * @param discount
    * @return price with discount applied (if any...)
    */

  def calculatePrice(price: Double, discount: Double): Double = {
    var finalPrice: Double = 0
    finalPrice = price * discount
    finalPrice
    }

  /**
    * Method to add a transaction to a transaction List.
    *
    * @param transaction
    */

  def addTransaction(transaction: Transaction): Unit = {
    if(!transactionHistory.contains(transaction)) {
      transactionHistory += transaction
    }
    println(s"Error: $transaction already exists.")
  }

  /**
    * Method to print a receipt for the transaction.
    *
    */

  def printReceipt() {println(s"Transaction ID: $transactionID" + s"\nPrice: $price" + s"\nYour checkout employee today was: $employee")}
}