/**
  * Created by Administrator on 20/06/2017.
  */
import java.util.Date

import org.scalatest._
import Main._

class TransactionActionsTests extends FlatSpec with Matchers with TransactionActions with MainVariables {
  //======================================== Transaction Section =========================================//

  //creating the transaction
  "creating new Transaction" should "return true if successful" in {
    val employee = Employee(1,"Eric","Ohwotu",28,"Male",67000,"CallMeMaster","NoneYaBusiness")
    createTransaction(1,employee,new Date()) should be (true)
  }

  it should "return false if the employee is null is passed to it" in {
    val employee = null
    createTransaction(1,employee,new Date()) should be (false)
  }

  it should "add the transaction to the transaction list" in{
    val employee = Employee(1,"Eric","Ohwotu",28,"Male",67000,"CallMeMaster","NoneYaBusiness")
    createTransaction(1,employee,new Date())
    createTransaction(2,employee,new Date())
    transactions.length should be (2)
  }

  //getting transactions
  "getting a transaction" should "return a Transaction" in {
    val employee = Employee(1,"Eric","Ohwotu",28,"Male",23000,"CallMeMaster","NoneYaBusiness")
    createTransaction(1,employee,new Date())
    val result = getTransaction(1)
    val isTransactionInstance = result.isInstanceOf[Transaction]

    result should not be (null)
    isTransactionInstance should be (true)
  }

  it should "throw an IndexOutOfBoundException if the ID doesn't exist" in {
    a [IndexOutOfBoundsException] should be thrownBy {
      getTransaction(1)
    }
  }

  "deleting a transaction" should "return true if successful" in {
    val employee = Employee(1,"Eric","Ohwotu",28,"Male",23000,"CallMeMaster","NoneYaBusiness")
    createTransaction(1,employee,new Date())
    val result = deleteTransaction(1)
    result should be (true)
  }

  it should "remove the transaction from the list" in {
    val employee = Employee(1,"Eric","Ohwotu",28,"Male",23000,"CallMeMaster","NoneYaBusiness")

    createTransaction(1,employee,new Date())
    transactions.length should be (1)

    deleteTransaction(1)
    transactions.length should be (0)
  }

  it should "return false if id doesnt exist" in {
    val result = deleteTransaction(1)
    result should be (false)
  }

  //======================================= end ======================================================//
}
