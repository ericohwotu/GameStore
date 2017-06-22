/**
  * Created by Administrator on 22/06/2017.
  */
case class Customer (ID: Int,var fName: String, var lName: String, Age: Int, Gender: String,salary: Int,/* var loginName: String, var password: String, */var balance: Int) extends Person {

  def increaseBalance(add:Int):Unit = {
    balance += add
  }

  def decreaseBalance(sub:Int): Boolean ={
    if(balance>=sub) {
      balance -= sub
      true
    }else false
  }

  override var loginName: String = _
  override var password: String = _
}
