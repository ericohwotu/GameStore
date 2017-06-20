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
if (employee == null) false
else {
  new Transaction(id, employee, dateAndTime)
  true
  new Transaction(id, employee, dateAndTime) =
}

  }

  def getTransaction(id: Int): Transaction = null


  def deleteTransaction(id: Int): Boolean = false

}
