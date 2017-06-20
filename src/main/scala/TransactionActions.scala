import java.util.Date

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
    if (transactionSearch != None) {
      transactions -= transactionSearch.get
      true
    } else {
      false
    }
  }


}
