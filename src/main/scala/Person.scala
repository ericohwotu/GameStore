/**
  * Created by Emmanuel Haastrup on 19/06/2017.
  */

import java.util.{Date}

abstract class Person {
  val ID: Int
  var fName: String
  var lName: String
  def fullName: String =  s"$fName $lName"
  val Age: Int
  val Gender: String
  var loginName: String
  var password: String

  def editUsername(str: String): Unit ={
    loginName = str
  }

  def editPassword(str: String): Unit ={
    password = str
  }

  def editFirstName(str: String): Unit ={
    fName = str
  }

  def editLastName(str: String): Unit = {
    lName = str
  }
}
