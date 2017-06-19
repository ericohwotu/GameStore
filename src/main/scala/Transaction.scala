import java.util.Date;

/**
  * Created by alfie on 19/06/2017.
  *
  * Transaction class that will have all the functionalities relating to money transactions.
  *
  */

class Transaction(transactionID: Int, employee: Employee, dateAndTime: Date) {

  var trID: Int = transactionID
  var customer: Customer
  var worker: Employee = employee
  var stocks: List[Stock] = List.empty
  var customerID: String
  var dAT: Date = dateAndTime
  var price: Double
  var discount: Double

  /**
    * Getters and setters for parameters
    * @return
    */

  def getTransactionID = trID
  def getDateAndTime = dAT
  def setDate(d :Date) {this.dAT = d}
  def getStock = stocks
  def getDiscount = discount
  def setDiscount {this.discount = discount}

  /**
    * Method to add employees to any transactions.
    *
    * @param employee
    */

  def addEmployee(employee: Employee): Unit = {}

  /**
    * Method to remove employees from any transactions.
    *
    * @param employee
    */

  def removeEmployee(employee: Employee): Unit = {}

  /**
    * Method to update employees related to any transactions.
    *
    * @param employee
    */

  def updateEmployee(employee: Employee): Unit = {}

  /**
    * Method to calculate the price of the transaction by multiplying the price by the discount value (if any...)
    *
    * @param price
    * @param discount
    * @return price with discount applied (if any...)
    */

  def calculatePrice(price: Double, discount: Option[Double]): Double = {price}

  /**
    * Method to print a receipt for the transaction.
    *
    */

  def printReceipt() {println(s"Transaction ID: $transactionID" + s"\nPrice: $price" + s"\nYour checkout employee today was: $employee")}
}
