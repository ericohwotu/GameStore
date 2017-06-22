/**
  * Created by Administrator on 20/06/2017.
  */
import java.util.Date

import org.scalatest._
import Main._

class TransactionActionsTests extends FlatSpec with Matchers with TransactionActions with MainVariables {
  //======================================== Transaction Section =========================================//
/*

  //creating the transaction
  "creating new Transaction" should "return true if successful" in {
//    loggedIn = Manager(1, "Emmanuel", "Haastrup", 28, "Male", 60000, "LordManny", "LordOfSmallLords")
    val employee = Employee(1,"Eric","Ohwotu",28,"Male",67000,"CallMeMaster","NoneYaBusiness")
    createTransaction(1,employee,new Date(), List(Game(1, "FIFA '17", "the best game", 20, 3,12,"SPORTS","XBOX", new Date()))) should be (true)
  }

  it should "return false if the employee is null is passed to it" in {
    transactions.clear()
    val employee = null
    createTransaction(1,employee,new Date(), List(Game(1, "FIFA '17", "the best game", 20, 3,12,"SPORTS","XBOX", new Date()))) should be (false)
  }
 it should "reduce the stock count" in {
    transactions.clear()
    val employee = Employee(1,"Eric","Ohwotu",28,"Male",67000,"CallMeMaster","NoneYaBusiness")
    val game = Game(1, "FIFA '17", "the best game", 20, 3,12,"SPORTS","XBOX", new Date())
   createTransaction(1,employee,new Date(), List(game))
   game.count should be (2)
*/


 // }

  /*it should "add the transaction to the transaction list" in{
    transactions.clear()
    val employee = Employee(1,"Eric","Ohwotu",28,"Male",67000,"CallMeMaster","NoneYaBusiness")
    createTransaction(1,employee,new Date(), List(Game(1, "FIFA '17", "the best game", 20, 3,12,"SPORTS","XBOX", new Date())))
    createTransaction(2,employee,new Date(), List(Game(1, "FIFA '17", "the best game", 20, 3,12,"SPORTS","XBOX", new Date())))
    transactions.length should be (2)
  }

  //getting transactions
  "getting a transaction" should "return a Transaction" in {
    transactions.clear()
    val employee = Employee(1,"Eric","Ohwotu",28,"Male",23000,"CallMeMaster","NoneYaBusiness")
    createTransaction(1,employee,new Date(), List(Game(1, "FIFA '17", "the best game", 20, 3,12,"SPORTS","XBOX", new Date())))
    val result = getTransaction(1)
    val isTransactionInstance = result.isInstanceOf[Transaction]

    result should not be (null)
    isTransactionInstance should be (true)
  }*/

  it should "return false if the transaction doesnt exist" in {
    transactions.clear
    val result = getTransaction(1)
    result should be (null)
    result.isInstanceOf[Transaction] should not be(true)
  }

  /*"deleting a transaction" should "return true if successful" in {
    transactions.clear
    loggedIn = Manager(1, "Emmanuel", "Haastrup", 28, "Male", 60000, "LordManny", "LordOfSmallLords")
    val employee = Employee(1,"Eric","Ohwotu",28,"Male",23000,"CallMeMaster","NoneYaBusiness")
    createTransaction(1,employee,new Date(), List(Game(1, "FIFA '17", "the best game", 20, 3,12,"SPORTS","XBOX", new Date())))
    val result = deleteTransaction(1)
    result should be (true)
  }

  it should "remove the transaction from the list" in {
    transactions.clear
    val employee = Employee(1,"Eric","Ohwotu",28,"Male",23000,"CallMeMaster","NoneYaBusiness")

    createTransaction(1,employee,new Date(), List(Game(1, "FIFA '17", "the best game", 20, 3,12,"SPORTS","XBOX", new Date())))
    transactions.length should be (1)

    deleteTransaction(1)
    transactions.length should be (0)
  }*/

/*  //**it should "return false if id doesnt exist" in {
    val result = deleteTransaction(1)
    result should be (false)
  }

  it should "Only allow manager to delete a transaction" in{
    deleteTransaction(1) by Manager
    */
  }*/

  /*"outputting transactions to file" should "return true if successful" in {
      transactions.clear()
      val employee = Employee(1,"Eric","Ohwotu",28,"Male",23000,"CallMeMaster","NoneYaBusiness")
    createTransaction(1,employee,new Date(), List(Game(1, "FIFA '17", "the best game", 20, 3,12,"SPORTS","XBOX", new Date())))
    createTransaction(2,employee,new Date(), List(Game(1, "FIFA '17", "the best game", 20, 3,12,"SPORTS","XBOX", new Date())))
    createTransaction(3,employee,new Date(), List(Game(1, "FIFA '17", "the best game", 20, 3,12,"SPORTS","XBOX", new Date())))
      outputTransactionsToFile should be(true)
    }
    it should "then return true if the transaction is cleared and reloaded" in {
      transactions.clear()
      readTransactionsFromFile should be(true)
    }
    it should "then maintain a size of 3" in {
      transactions.length should be(3)
    }*/


  //======================================= end ======================================================//
}
