import java.util.Date

/**
  * Created by Administrator on 19/06/2017.
  */
trait TransactionActions {
  /*
  * Transaction functions
  *
  * */
  def createTransaction(id: Int, employee: Employee, dateAndTime: Date): Boolean = false

  def getTransaction(id: Int): Transaction = null

  def deleteTransaction(id: Int): Boolean = false

}
