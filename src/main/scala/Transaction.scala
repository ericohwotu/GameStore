import java.util.Date

import scala.collection.mutable.ListBuffer;

/**
  * Created by alfie on 19/06/2017.
  *
  * Transaction class that will have all the functionalities relating to money transactions.
  *
  */

case class Transaction(transactionID: Int, employee: Person, dateAndTime: Date, stocks: List[Stock]) {

  var transactionHistory: List[Stock] = stocks
  var salesHistory: ListBuffer[Double] = new ListBuffer[Double]
  var price: Double = 0.0
  var discount: Double = 0.0
  var isPreOrder: Boolean = false
  var employeeV:Person = employee
	var iDate:Date = dateAndTime

  def getStock = stocks
  def getTransactionHistory = transactionHistory
  def getDiscount = discount
  def setDiscount {this.discount = discount}

  def updateEmployee(employee: Employee): Unit = this.employeeV = employee

  def calculatePrice(price: Double, discount: Double): Double = {
    var totalPrice: Double = 0
    Double match {
      case a if discount == 0 => totalPrice = price
      case _ => totalPrice = price * (100 - discount) / 100
    }
    BigDecimal(totalPrice).setScale(2, BigDecimal.RoundingMode.HALF_UP).toDouble
    salesHistory += totalPrice
    BigDecimal(totalPrice).setScale(2, BigDecimal.RoundingMode.HALF_UP).toDouble
  }

  /*def addTransaction(transaction: Transaction): ListBuffer[Transaction] = {
    Transaction match {
      case a if !transactionHistory.contains(transaction) => transactionHistory += transaction
      case _ => println(s"Transaction $transaction is already added.")
    }
    transactionHistory
  }*/

  def totalPrices(): Double = {
    var sum: Double = 0
    salesHistory.foreach(elem => sum += elem); sum
  }

  def printReceipt() {println(s"Transaction ID: $transactionID" + s"\nPrice: $price" + s"\nYour checkout employee today was: $employee")}
	
	override def toString ():String ={
		var tot:Double = 0
		transactionHistory.foreach(i => tot += i.price)
		s"${employeeV.fName} ${employeeV.lName}, $tot, $dateAndTime"
	}
}