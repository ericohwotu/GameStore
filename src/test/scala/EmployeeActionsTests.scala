/**
  * Created by Administrator on 19/06/2017.
  */


import org.scalatest._


class EmployeeActionsTests extends FlatSpec with Matchers with EmployeeActions with MainVariables {
  //============================================ Employee Actions =====================================================/

  //creating employees
  "creating an employee" should "return true if successful" in {
    val result = createEmployee(1,"Eric","Ohwotu",28,"Male",23000,"CallMeMaster","NoneYaBusiness", EmployeeType.EMPLOYEE)
    result should be (true)
  }

  it should "return false if EmployeeType is null" in {
    val result = createEmployee(1, "Eric", "Ohwotu", 28, "Male", 23000, "CallMeMaster", "NoneYaBusiness", null)
    result should be(false)
  }

  it should "add the employee to the employees list" in {
    createEmployee(1, "Eric", "Ohwotu", 28, "Male", 23000, "CallMeMaster", "NoneYaBusiness", EmployeeType.EMPLOYEE)
    createEmployee(2, "Eric", "Ohwotu", 22, "Male", 23000, "CallMeMaster", "NoneYaBusiness", EmployeeType.EMPLOYEE)
    employees.length should be (2)
  }

  it should "return false if id already exists" in {
    createEmployee(1, "Eric", "Ohwotu", 28, "Male", 23000, "CallMeMaster", "NoneYaBusiness", EmployeeType.EMPLOYEE)
    createEmployee(2, "Eric", "Ohwotu", 22, "Male", 23000, "CallMeMaster", "NoneYaBusiness", EmployeeType.EMPLOYEE)
  }

  "creating a Manager" should "return true if successful" in {
    val result = createEmployee(1,"Eric","Ohwotu",28,"Male",23000,"CallMeMaster","NoneYaBusiness", EmployeeType.MANAGER)
    result should be (true)
  }

  it should "add the manager to the managers list" in {
    createEmployee(1, "Eric", "Ohwotu", 28, "Male", 23000, "CallMeMaster", "NoneYaBusiness", EmployeeType.MANAGER)
    createEmployee(2, "Eric", "Ohwotu", 22, "Male", 23000, "CallMeMaster", "NoneYaBusiness", EmployeeType.MANAGER)
    managers.length should be (2)
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

  it should "remove them from the employees list" in {
    createEmployee(1,"Eric","Ohwotu",28,"Male",23000,"CallMeMaster","NoneYaBusiness", EmployeeType.EMPLOYEE)
    deleteEmployee(1)
    employees.length should be (0)
  }

  "deleting a Manager" should "return true if succesful" in {
    createEmployee(1,"Eric","Ohwotu",28,"Male",67000,"CallMeMaster","NoneYaBusiness", EmployeeType.MANAGER)
    val result = deleteManager(1)
    result should be (true)
  }

  it should "remove them from the Managers list" in {
    createEmployee(1,"Eric","Ohwotu",28,"Male",23000,"CallMeMaster","NoneYaBusiness", EmployeeType.MANAGER)
    deleteManager(1)
    managers.length should be (0)
  }

  it should "return false if id doesnt exist" in {
    val result = deleteEmployee(1)
    result should be (false)
  }
  //======================================================= Done =======================================================//
}
