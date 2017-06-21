import java.io.{FileInputStream, FileOutputStream, ObjectInputStream, ObjectOutputStream}

import scala.collection.mutable.ListBuffer

/**
  * Created by Administrator on 19/06/2017.
  */
trait EmployeeActions extends MainVariables {
  /*
   * Employee Managemment
   *
   * */
  def createEmployee(id: Int, fName: String, lName: String, Age: Int, Gender: String, salary: Int, loginName: String, password: String, employeeType: EmployeeType.Value): Boolean = {

    employeeType match {

      case EmployeeType.MANAGER =>

        val isInList = managers.filter(_.ID==id).length > 0

        isInList match {
          case false =>
            managers += new Manager (id, fName, lName, Age, Gender, salary, loginName, password)
            true

          case true =>
            println("Sorry Id already exists")
            false
        }

      case EmployeeType.EMPLOYEE =>

        val isInList = employees.filter(_.ID==id).length > 0

        isInList match {
          case false =>
            employees += new Employee(id, fName, lName, Age, Gender, salary, loginName, password)
            true

          case true =>
            println("Sorry Id already exists")
            false
        }

      case _ => false

    }
  }

  def getEmployee(id: Int): Employee = {
    val result = employees.filter(_.ID == id)
    result.length match {
      case 0 => println("Sorry Employee Doesnt Exist"); null
      case 1 => result.head
      case x if x > 1 => println("Multiple Found"); null
    }
  }

  def deleteEmployee(id: Int): Boolean = {
    val result = employees.filter(_.ID == id)
    result.length match {
      case 0 => println("Sorry Employee Doesnt Exist"); false
      case 1 => employees -= result.head; true
      case x if x > 1 => println("Multiple Found"); false
    }
  }

  def getManager(id: Int): Manager ={
    val result = managers.filter(_.ID == id)
    result.length match {
      case 0 => println("Sorry Manager Doesnt Exist"); null
      case 1 => result.head
      case x if x > 1 => println("Multiple Found"); null
    }
  }

  def deleteManager(id: Int): Boolean ={
    val result = managers.filter(_.ID == id)
    result.length match {
      case 0 => println("Sorry Manager Doesnt Exist"); false
      case 1 => managers -= result.head; true
      case x if x > 1 => println("Multiple Found"); false
    }
  }

  def outputEmployeesToFile: Boolean = {
    try {
      val employeeOutputStream = new ObjectOutputStream(new FileOutputStream("employees.dat"))
      employeeOutputStream.writeObject(employees)
      employeeOutputStream.flush()
      true
    } catch {
      case x: Exception => false
    }

  }

  def outputManagersToFile: Boolean = {
    try {
      val managerOutputStream = new ObjectOutputStream(new FileOutputStream("managers.dat"))
      managerOutputStream.writeObject(managers)
      managerOutputStream.flush()
      true
    } catch {
      case x: Exception => false
    }
  }

  def readEmployeesFromFile: Boolean = {
    try {
      val employeeInputStream = new ObjectInputStream(new FileInputStream("employees.dat"))
      employees.clear()
      employees ++= employeeInputStream.readObject.asInstanceOf[ListBuffer[Employee]]
      true
    } catch {
      case x: Exception => false
    }
  }
  def readManagersFromFile: Boolean = {
    try {
      val managerInputStream = new ObjectInputStream(new FileInputStream("managers.dat"))
      managers.clear()
      var newList = managerInputStream.readObject.asInstanceOf[ListBuffer[Manager]]
      managers ++= newList
      true
    } catch {
      case x: Exception => false
    }
  }
}
