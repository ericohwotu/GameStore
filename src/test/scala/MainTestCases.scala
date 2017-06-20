/**
  * Created by Administrator on 19/06/2017.
  */

import java.util.Date

import org.scalatest._
import Main._

class MainTestCases extends FlatSpec with Matchers with TransactionActions with EmployeeActions with MainVariables{

  //========================================= Logging In ==================================================//

  "logging in" should "return true if successful" in {
    createEmployee(1,"Eric","Ohwotu",28,"Male",23000,"CallMeMaster","NoneYaBusiness", EmployeeType.EMPLOYEE)
    login("CallMeMaster", "NoneYaBusiness") should be (true)
  }

  it should "return false if the username and password doesnt exist" in {
    login("CallMeMaster", "NoneYaBusiness") should be (false)
  }

  it should "return false if empty strings are passed as username" in {
    login("", "NoneYaBusiness") should be (false)
  }

  it should "return false if empty strings are passed as password" in {
    login("Eric", "") should be (false)
  }

  it should "set the current logged in user" in {
    createEmployee(1,"Eric","Ohwotu",28,"Male",23000,"CallMeMaster","NoneYaBusiness", EmployeeType.EMPLOYEE)
    login("CallMeMaster", "NoneYaBusiness")
    loggedIn should not be (null)
  }
}

