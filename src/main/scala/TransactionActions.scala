import java.io._
import java.util.Date

import scala.collection.mutable.ListBuffer

/**
  * Created by Administrator on 19/06/2017.
  */
trait TransactionActions extends MainVariables {
  /*
  * Transaction functions
  *
  * */
  def createTransaction(id: Int, employee: Employee, dateAndTime: Date): Boolean = {
    if (transactions.find(t => t.transactionID == id) != None || employee == null) {
      false
    } else {
      transactions += new Transaction(id, employee, dateAndTime)
      true
    }
  }


  def getTransaction(id: Int): Transaction = {
    val transactionSearch = transactions.find(t => t.transactionID == id).getOrElse(null)
    transactionSearch
  }


  def deleteTransaction(id: Int): Boolean = {
      val transactionSearch = transactions.find(t => t.transactionID == id)
    if(loggedIn.isInstanceOf[Manager]){


    if (transactionSearch != None) {
      transactions -= transactionSearch.get
      true
    } else {
      false
    } else {
        throw new IOException("Unauthorized access; not allowed")
      }
    }
  }

  def outputTransactionsToFile: Boolean = {
    try {
      val transactionsOutputStream = new ObjectOutputStream(new FileOutputStream("transaction.dat"))
      transactionsOutputStream.writeObject(transactions)
      transactionsOutputStream.close()
      true
    } catch {
      case x: Exception => false
    }
  }

  def readTransactionsFromFile: Boolean = {
    try {
      val transactionInputStream = new ObjectInputStream(new FileInputStream("transaction.dat"))
      transactions.clear()
      transactions ++= transactionInputStream.readObject.asInstanceOf[ListBuffer[Transaction]]
      true
    } catch {
      case x: Exception => false
    }
  }

}
