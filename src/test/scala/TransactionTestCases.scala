import java.util.Date

import org.scalatest._

/**
  * Class that contains scala testing for the Transaction class method and functionalities.
  *
  * Created by alfie on 20/06/2017.
  */

class TransactionTestCases extends FlatSpec with Matchers with MainVariables{


  //========================================= Calculate price ==================================================//

  "calculate price" should "return correct price if successful" in {
    val alf = Employee(25,"Alfie","Abdullah",5,"Male",25000,"aabdullah","Private")
    val trans = Transaction(1, alf, new Date(), stocks = List.empty)
    trans.calculatePrice(39.99,0) should be (39.99)
  }

  it should "return the full price when 0 is entered in the discount field" in {
    val alf = Employee(25,"Alfie","Abdullah",5,"Male",25000,"aabdullah","Private")
    val trans = Transaction(1, alf, new Date(), stocks = List.empty)
    trans.calculatePrice(39.99,0) should be (39.99)
  }

  it should "return the correct discounted price" in {
    val alf = Employee(25,"Alfie","Abdullah",5,"Male",25000,"aabdullah","Private")
    val trans = Transaction(1, alf, new Date(), stocks = List.empty)
    trans.calculatePrice(39.99, 10) should be (35.99)
  }

  //========================================= Add transaction ==================================================//

  /*"Add transaction" should "add a transaction to the transactions list" in {
    val alf = Employee(25,"Alfie","Abdullah",5,"Male",25000,"aabdullah","Private")
    val trans = Transaction(1, alf, new Date(), stocks = List.empty)
    trans.addTransaction(trans)
    trans.transactionHistory.length should be (1)
  }*/

  //========================================= Calculate total price ==================================================//

  "Calculate price" should "return the total amount of sales made" in {
    val alf = Employee(25,"Alfie","Abdullah",5,"Male",25000,"aabdullah","Private")
    val trans = Transaction(1, alf, new Date(), stocks = List.empty)
    trans.calculatePrice(39.99, 0)
    trans.calculatePrice(39.99, 0)
    trans.totalPrices() should be (79.98)
  }

}
