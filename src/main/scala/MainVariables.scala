import scala.collection.mutable.ListBuffer

/**
  * Created by Administrator on 20/06/2017.
  */
trait MainVariables {

  val transactions: ListBuffer[Transaction] = new ListBuffer[Transaction]
  val stocks: ListBuffer[Stock] = new ListBuffer[Stock]
  val employees: ListBuffer[Employee] = new ListBuffer[Employee]
  val managers: ListBuffer[Manager] = new ListBuffer[Manager]
  val reports: ListBuffer[Report] = new ListBuffer[Report]
  var loggedIn: Employee = null

  def login(username: String, Password: String): Boolean = false
  
  def logout():Unit ={
		loggedIn = null
	}
}
