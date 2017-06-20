/**
  * Created by Administrator on 19/06/2017.
  */

import java.util.Date

import org.scalatest._
import Main._

class EmployeeActionsTests extends FlatSpec with Matchers with EmployeeActions {
  //============================================ Employee Actions =====================================================/

  //creating employees
  "creating an employee" should "return true if successful" in {
    val result = createEmployee(1,"Eric","Ohwotu",28,"Male",23000,"CallMeMaster","NoneYaBusiness", EmployeeType.EMPLOYEE)
    result should be (true)
  }

  it should "return false if EmployeeType is null" in{
    val result = createEmployee(1,"Eric","Ohwotu",28,"Male",23000,"CallMeMaster","NoneYaBusiness", null)
    result should be (false)
  }
  "creating a Manager" should "return true if successful" in {
    val result = createEmployee(1,"Eric","Ohwotu",28,"Male",23000,"CallMeMaster","NoneYaBusiness", EmployeeType.MANAGER)
    result should be (true)
  }

  "getting a manager" should "return a Manager" in{
    createEmployee(1,"Eric","Ohwotu",28,"Male",23000,"CallMeMaster","NoneYaBusiness", EmployeeType.MANAGER)
    val result = getEmployee(1)
    val isManagerInstance = result.isInstanceOf[Manager]

    result should not be (null)
    isManagerInstance should be (true)
  }
  "getting an Employee" should "return an employee" in{
    createEmployee(1,"Eric","Ohwotu",28,"Male",23000,"CallMeMaster","NoneYaBusiness", EmployeeType.MANAGER)
    val result = getEmployee(1)
    val isEmployeeInstance = result.isInstanceOf[Employee]

    result should not be (null)
    isEmployeeInstance should be (true)
  }

  "deleting an employee" should "return true if succesful" in {
    createEmployee(1,"Eric","Ohwotu",28,"Male",23000,"CallMeMaster","NoneYaBusiness", EmployeeType.EMPLOYEE)
    val result = deleteEmployee(1)
    result should be (true)
  }

}
