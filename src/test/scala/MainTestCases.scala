/**
  * Created by Administrator on 19/06/2017.
  */

import java.util.Date

import org.scalatest._
import Main._

class MainTestCases extends FlatSpec with Matchers with TransactionActions with EmployeeActions with MainVariables{

  //========================================= Logging In ==================================================//

  "logging in" should "return true if successful" in {
    employees += new Employee(1,"Eric","Ohwotu",28,"Male",23000,"CallMeMaster","NoneYaBusiness")
    login("CallMeMaster", "NoneYaBusiness") should be (true)
  }

  it should "set the current logged in user" in {
    employees.clear()
    employees += new Employee(1,"Eric","Ohwotu",28,"Male",23000,"CallMeMaster","NoneYaBusiness")
    login("CallMeMaster", "NoneYaBusiness")
    loggedIn should not be (null)
  }

  it should "return false if the username and password doesnt exist" in {
    employees.clear()
    login("CallMeMaster", "NneYaBusiness") should be (false)
  }

  it should "return false if empty strings are passed as username" in {
    employees.clear()
    login("", "NoneYaBusiness") should be (false)
  }

  it should "return false if empty strings are passed as password" in {
    employees.clear()
    login("Eric", "") should be (false)
  }

  "logging out" should "return true if successful" in {
    loggedIn = new Employee(1,"Eric","Ohwotu",28,"Male",23000,"CallMeMaster","NoneYaBusiness")
    logout() should be (true)
  }

  it should "return false if no user is logged in" in {
    loggedIn = null
    logout() should be (false)
  }
}

