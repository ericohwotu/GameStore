/**
  * Created by Administrator on 19/06/2017.
  */


import org.scalatest._


class EmployeeActionsTests extends FlatSpec with Matchers with EmployeeActions with MainVariables {

  //========================================================= Adding Employees ===============================================================//
  "creating an employee" should "return true if logged in is Manager" in {
    loggedIn = Manager(1,"Eric","Ohwotu",28,"Male",23000,"CallMe","NoneYa")
    employees.clear()
    val result = createEmployee(10,"Eric","Ohwotu",28,"Male",23000,"Sting","ness", EmployeeType.EMPLOYEE)
    result should be (true)
  }

  it should "return false if logged in is employee" in {
    loggedIn = Employee(1,"Eric","Ohwotu",28,"Male",23000,"CallMe","NoneYa")
    val result = createEmployee(20,"Eric","Ohwotu",28,"Male",23000,"CallMter","NoBusiness", EmployeeType.EMPLOYEE)
    result should be (false)
  }

  it should "return false if EmployeeType is null" in {
    loggedIn = Manager(1,"Eric","Ohwotu",28,"Male",23000,"CallMe","NoneYa")
    val result = createEmployee(78, "Eric", "DejmbaDejmba", 28, "Male", 23000, "CallMeMaster", "NoneYaBusiness", null)
    result should be(false)
  }

  it should "return false if employee id already exists" in {
    employees.clear()
    createEmployee(1, "Eric", "Ohwotu", 28, "Male", 23000, "CallMeMaster", "NoneYaBusiness", EmployeeType.EMPLOYEE)
    val result = createEmployee(1, "Eric", "DejmbaDejmba", 28, "Male", 23000, "CallMeMaster", "NoneYaBusiness", EmployeeType.EMPLOYEE)
    result should be(false)
  }

  it should "add the employee to the employees list" in {
    employees.clear()
    createEmployee(1, "Eric", "Ohwotu", 28, "Male", 23000, "CallMeMaster", "NoneYaBusiness", EmployeeType.EMPLOYEE)
    createEmployee(2, "Eric", "Ohwotu", 22, "Male", 23000, "CallMeMaster", "NoneYaBusiness", EmployeeType.EMPLOYEE)
    employees.length should be (2)
  }

  it should "return false if id already exists" in {
    createEmployee(1, "Eric", "Ohwotu", 28, "Male", 23000, "CallMeMaster", "NoneYaBusiness", EmployeeType.EMPLOYEE) should be (false)
    createEmployee(2, "Eric", "Ohwotu", 22, "Male", 23000, "CallMeMaster", "NoneYaBusiness", EmployeeType.EMPLOYEE) should be (false)
  }

  "creating a Manager" should "return true if successful" in {
    managers.clear()
    val result = createEmployee(1,"Eric","Ohwotu",28,"Male",23000,"CallMeMaster","NoneYaBusiness", EmployeeType.MANAGER)
    result should be (true)
  }

  it should "add the manager to the managers list" in {
    createEmployee(1, "Eric", "Ohwotu", 28, "Male", 23000, "CallMeMaster", "NoneYaBusiness", EmployeeType.MANAGER)
    createEmployee(2, "Eric", "Ohwotu", 22, "Male", 23000, "CallMeMaster", "NoneYaBusiness", EmployeeType.MANAGER)
    managers.length should be (2)
  }

  //========================================================= Getting Employees ===============================================================//
  "getting a manager" should "return a Manager" in{
    managers.clear()
    createEmployee(1,"Eric","Ohwotu",28,"Male",23000,"CallMeMaster","NoneYaBusiness", EmployeeType.MANAGER)
    val result = getManager(1)
    val isManagerInstance = result.isInstanceOf[Manager]

    result should not be (null)
    isManagerInstance should be (true)
  }

  "getting an Employee" should "return an employee" in{
    employees.clear()
    createEmployee(1,"Eric","Ohwotu",28,"Male",23000,"CallMeMaster","NoneYaBusiness", EmployeeType.EMPLOYEE)
    val result = getEmployee(1)
    val isEmployeeInstance = result.isInstanceOf[Employee]

    result should not be (null)
    isEmployeeInstance should be (true)
  }

  //========================================================= Deleting Employees ===============================================================//
  "deleting an employee" should "return true if succesful" in {
    employees.clear()
    createEmployee(1,"Eric","Ohwotu",28,"Male",23000,"CallMeMaster","NoneYaBusiness", EmployeeType.EMPLOYEE)
    val result = deleteEmployee(1)
    result should be (true)
  }

  it should "remove them from the employees list" in {
    employees.clear()
    createEmployee(1,"Eric","Ohwotu",28,"Male",23000,"CallMeMaster","NoneYaBusiness", EmployeeType.EMPLOYEE)
    deleteEmployee(1)
    employees.length should be (0)
  }

  "deleting a Manager" should "return true if succesful" in {
    managers.clear()
    createEmployee(1,"Eric","Ohwotu",28,"Male",67000,"CallMeMaster","NoneYaBusiness", EmployeeType.MANAGER)
    val result = deleteManager(1)
    result should be (true)
  }

  it should "remove them from the Managers list" in {
    managers.clear()
    createEmployee(1,"Eric","Ohwotu",28,"Male",23000,"CallMeMaster","NoneYaBusiness", EmployeeType.MANAGER)
    deleteManager(1)
    managers.length should be (0)
  }

  it should "return false if id doesnt exist" in {
    managers.clear()
    val result = deleteManager(1)
    result should be (false)
  }
  //========================================================= Read Employees From File ===============================================================//

  "outputing employees to file" should "return true if successful" in {
    createEmployee(1, "Eric", "Ohwotu", 28, "Male", 23000, "CallMeMster", "NoneYaBiness", EmployeeType.EMPLOYEE)
    createEmployee(2, "Eric", "Ohwotu", 22, "Male", 23000, "CalMaster", "NoaBusiness", EmployeeType.EMPLOYEE)
    outputEmployeesToFile should be (true)
  }
  "outputing managers to file" should "return true if successful" in {
    createEmployee(1, "Eric", "Ohwotu", 28, "Male", 27000, "CallMe", "NoneYa", EmployeeType.MANAGER)
    createEmployee(2, "Eric", "Ohwotu", 22, "Male", 27000, "CallMeMaster", "NoneYaBusiness", EmployeeType.MANAGER)
    outputManagersToFile should be (true)
  }
  "reading employees to file" should "return true if successful" in {
    employees.clear()
    readEmployeesFromFile
    println(employees.length)
    employees.length should not be (0)
  }
  "reading managers to file" should "return true if successful" in {
    managers.clear()
    readManagersFromFile
    managers.length should not be (0)
  }
}
