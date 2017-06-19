/**
  * Created by Administrator on 19/06/2017.
  */

import java.util.Date

import org.scalatest._
import Main._

class MainTestCases extends FlatSpec with Matchers with TransactionActions with EmployeeActions{

  //========================================= Logging In ==================================================//

  "logging in" should "return true if successful" in {
    login("Eric", "NoneYaBusiness") should be(true)
  }

  it should "return false if empty strings are passed as username" in {
    login("", "NoneYaBusiness") should be (false)
  }

  it should "return false if empty strings are passed as password" in {
    login("Eric", "") should be (false)
  }

  it should "set the current logged in user" in {
    login("Eric", "NoneYaBusiness")
    loggedIn should not be (null)
  }

  //======================================== Transaction Section =========================================//

  //creating the transaction
  "creating new Transaction" should "return true if successful" in {
    val employee = Employee(1,"Eric","Ohwotu",28,"Male",23000,"CallMeMaster","NoneYaBusiness")
    createTransaction(1,employee,new Date()) should be (true)
  }

  it should "return false if the employee is null is passed to it" in {
    val employee = null
    createTransaction(1,employee,new Date()) should be (false)
  }

  it should "add the transaction to the transaction list" in{
    val employee = Employee(1,"Eric","Ohwotu",28,"Male",23000,"CallMeMaster","NoneYaBusiness")
    createTransaction(1,employee,new Date())
    createTransaction(2,employee,new Date())
    transactions.length should be (2)
  }

  //getting transactions
  "getting a transaction" should "return a Transaction" in {
    getTransaction(1) should not be (null)
  }

  it should "throw an IndexOutOfBoundException if the ID doesn't exist" in {
    a [IndexOutOfBoundsException] should be thrownBy {
      getTransaction(100000)
    }
  }

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


}

