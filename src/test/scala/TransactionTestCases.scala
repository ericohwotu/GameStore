import java.util.Date;

import org.scalatest._

/**
  * Class that contains scala testing for the Transaction class method and functionalities.
  *
  * Created by alfie on 20/06/2017.
  */

class TransactionTestCases extends FlatSpec with Matchers with MainVariables{

  //========================================= Add employee ==================================================//

  "Add employee" should "return true if successful" in {
    val alf = Employee(25,"Alfie","Abdullah",5,"Male",25000,"aabdullah","Private")
    val trans = Transaction(1, alf, new Date())
    trans.addEmployee(alf) should be (true)
  }

  it should "return false if no employee is passed" in {
    val alf = Employee(25,"Alfie","Abdullah",5,"Male",25000,"aabdullah","Private")
    val nobody = null
    val trans = Transaction(1, alf, new Date())
    trans.addEmployee(nobody) should be (false)
  }

  //========================================= Remove employee ==================================================//

  "Remove employee" should "return true if successful" in {
    val alf = Employee(25,"Alfie","Abdullah",5,"Male",25000,"aabdullah","Private")
    val trans = Transaction(1, alf, new Date())
    trans.removeEmployee(alf) should be (true)
  }

  it should "return false if the employee is not found with the transaction" in {
    val alf = Employee(25,"Alfie","Abdullah",5,"Male",25000,"aabdullah","Private")
    val js = Employee(1,"John","Smith",24,"Male",23000,"JSmith","Smooth")
    val trans =  Transaction(1, alf, new Date())
    trans.removeEmployee(js) should be (false)
  }

  it should "return false if no employee is passed" in {
    val alf = Employee(25,"Alfie","Abdullah",5,"Male",25000,"aabdullah","Private")
    val nobody = null
    val trans = Transaction(1, alf, new Date())
    trans.removeEmployee(nobody) should be (false)
  }

  //========================================= Update employee ==================================================//

  "Update employee" should "return true if successful" in {
    val alf = Employee(25,"Alfie","Abdullah",5,"Male",25000,"aabdullah","Private")
    val js = Employee(1,"John","Smith",24,"Male",23000,"JSmith","Smooth")
    val trans =  Transaction(1, alf, new Date())
    trans.updateEmployee(js) should be (true)
  }

  it should "return false if employee is already in transaction" in {
    val alf = Employee(25,"Alfie","Abdullah",5,"Male",25000,"aabdullah","Private")
    val js = Employee(1,"John","Smith",24,"Male",23000,"JSmith","Smooth")
    val trans = Transaction(1, alf, new Date())
    trans.updateEmployee(alf) should be (false)
  }

  it should "return false if no employee is passed" in {
    val alf = Employee(25,"Alfie","Abdullah",5,"Male",25000,"aabdullah","Private")
    val nobody = null
    val trans = Transaction(1, alf, new Date())
    trans.updateEmployee(nobody) should be (false)
  }

  //========================================= Calculate price ==================================================//

  "calculate price" should "return correct price if successful" in {
    val alf = Employee(25,"Alfie","Abdullah",5,"Male",25000,"aabdullah","Private")
    val trans = Transaction(1, alf, new Date())
    trans.calculatePrice(39.99, None) should be (39.99)
  }

  it should "return the correct discounted price" in {
    val alf = Employee(25,"Alfie","Abdullah",5,"Male",25000,"aabdullah","Private")
    val trans = Transaction(1, alf, new Date())
    trans.calculatePrice(39.99, Some(10)) should be (35.99)
  }

  it should "throw an error when the discount value entered is too much (i.e. <20)" in {
    val alf = Employee(25,"Alfie","Abdullah",5,"Male",25000,"aabdullah","Private")
    val trans = Transaction(1, alf, new Date())
    a [Error] should be thrownBy {
      trans.calculatePrice(39.99, Some(20)) should be(35.99)
    }
  }

  //========================================= Add transaction ==================================================//

  "Add transaction" should "add a transaction to the transactions list" in {
    val alf = Employee(25,"Alfie","Abdullah",5,"Male",25000,"aabdullah","Private")
    val trans = Transaction(1, alf, new Date())
    trans.addTransaction(trans)
    transactions.length should be (1)
  }

}
