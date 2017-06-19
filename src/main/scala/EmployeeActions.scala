/**
  * Created by Administrator on 19/06/2017.
  */
trait EmployeeActions {
  /*
   * Employee Managemment
   *
   * */
  def createEmployee(ID: Int, fName: String, lName: String, Age: Int, Gender: String, salary: Int, loginName: String, password: String, employeeType: EmployeeType.Value): Boolean = false

  def getEmployee(id: Int): Employee = null

  def deleteEmployee(id: Int): Boolean = false

  def getManager(id: Int): Manager = null

  def deleteManager(id: Int): Boolean = false
}
