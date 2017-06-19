/**
  * Created by Emmanuel Haastrup on 19/06/2017.
  */

import java.util.{Date}

abstract class Person {
  val ID: Int
  val fName: String
  val lName: String
  def fullName: String =  s"$fName $lName"
  val Age: Int
  val Gender: String


}
