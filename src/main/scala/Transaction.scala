import java.util.Date;

/**
  * Created by alfie on 19/06/2017.
  *
  * Transaction class that will have all the functionalities relating to money transactions.
  *
  */

case class Transaction(transactionID: Int, employee: Employee, dateAndTime: Date) {

  var stocks: List[Stock] = List.empty
  var transactionHistory: List[Transaction] = List.empty
  var price: Double = 0.0
  var discount: Double = 0.0

  /**
    * Getters and setters for parameters
    * @return
    */

  def getStock = stocks
  def getTransactionHistory = transactionHistory
  def getDiscount = discount
  def setDiscount {this.discount = discount}

  /**
    * Method to add employees to any transactions.
    *
    * @param employee
    */

  def addEmployee(employee: Employee): Boolean = {true}

  /**
    * Method to remove employees from any transactions.
    *
    * @param employee
    */

  def removeEmployee(employee: Employee): Boolean = {true}

  /**
    * Method to update employees related to any transactions.
    *
    * @param employee
    */

  def updateEmployee(employee: Employee): Boolean = {true}

  /**
    * Method to calculate the price of the transaction by multiplying the price by the discount value (if any...)
    *
    * @param price
    * @param discount
    * @return price with discount applied (if any...)
    */

  def calculatePrice(price: Double, discount: Option[Double]): Double = {price}

  /**
    * Method to add a transaction to a transaction List.
    *
    * @param transaction
    */

  def addTransaction(transaction: Transaction): Unit = {}

  /**
    * Method to print a receipt for the transaction.
    *
    */

  def printReceipt() {println(s"Transaction ID: $transactionID" + s"\nPrice: $price" + s"\nYour checkout employee today was: $employee")}
}