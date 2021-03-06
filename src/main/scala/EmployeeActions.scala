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
    val username = getNewUserName(loginName)
    if (loggedIn.isInstanceOf[Manager]) {
      println(employees ++ managers)

      employeeType match {

        case EmployeeType.MANAGER =>

          val isInList = managers.filter(_.ID == id).length > 0
          isInList match {
            case false =>
              managers += new Manager(id, fName, lName, Age, Gender, salary, username, password)
              writeManagersToFile
              readManagersFromFile
              true
            case true =>
              println("Sorry Id already exists")
              false
          }

        case EmployeeType.EMPLOYEE =>

          val isInList = employees.filter(_.ID == id).length > 0

          isInList match {
            case false =>
              employees += new Employee(id, fName, lName, Age, Gender, salary, username, password)
              writeEmployeesToFile
              readEmployeesFromFile
              true

            case true =>
              println("Sorry Id already exists")
              false
          }

        case _ => false
      }
    }
    else {
      println("Insufficient Priviledges")
      false
    }
  }

  def createEmployee(id: Int, fName: String, lName: String, age: Int, gender: String, salary: Int, points: Int): Boolean = {
    if (!loggedIn.isInstanceOf[Customer]) {
      val isInList = customers.filter(_.ID == id).length > 0

      isInList match {
        case false =>
          customers += new Customer(id, fName, lName, age, gender, salary, points)
          writeCustomersToFile
          readCustomersFromFile
          true

        case true =>
          println("Sorry Id already exists")
          false
      }
    }else false
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
    if (loggedIn.isInstanceOf[Manager]) {
      val result = employees.filter(_.ID == id)
      result.length match {
        case 0 => println("Sorry Employee Doesnt Exist"); false
        case 1 => employees -= result.head; writeEmployeesToFile; readEmployeesFromFile; true
        case x if x > 1 => println("Multiple Found"); false
      }
    } else {
      println("You have insufficient priviledges to delete")
      false
    }
  }

  def getManager(id: Int): Manager = {
    val result = managers.filter(_.ID == id)
    result.length match {
      case 0 => println("Sorry Manager Doesnt Exist"); null
      case 1 => result.head
      case x if x > 1 => println("Multiple Found"); null
    }
  }

  def deleteManager(id: Int): Boolean = {
    if (loggedIn.isInstanceOf[Manager]) {
      val result = managers.filter(_.ID == id)
      result.length match {
        case 0 => println("Sorry Manager Doesnt Exist"); false
        case 1 => managers -= result.head; writeManagersToFile; readManagersFromFile; true
        case x if x > 1 => println("Multiple Found"); false
      }
    } else {
      println("You have insufficient priviledges to delete")
      false
    }
  }

  def getCustomer(id: Int): Customer = {
    val result = customers.filter(_.ID == id)
    result.length match {
      case 0 => println("Sorry customer Doesnt Exist"); null
      case 1 => result.head
      case x if x > 1 => println("Multiple Found"); null
    }
  }

  def deleteCustomer(id: Int): Boolean = {
    if (!loggedIn.isInstanceOf[Customer]) {
      val result = customers.filter(_.ID == id)
      result.length match {
        case 0 => println("Sorry Customer Doesnt Exist"); false
        case 1 => customers -= result.head; writeCustomersToFile; readManagersFromFile; true
        case x if x > 1 => println("Multiple Found"); false
      }
    } else {
      println("You have insufficient priviledges to delete")
      false
    }
  }

  def writeEmployeesToFile: Boolean = {
    try {
      val employeeOutputStream = new ObjectOutputStream(new FileOutputStream("employees.dat"))
      employeeOutputStream.writeObject(employees)
      employeeOutputStream.flush()
      true
    } catch {
      case x: Exception => false
    }

  }

  def writeManagersToFile: Boolean = {
    try {
      val managerOutputStream = new ObjectOutputStream(new FileOutputStream("managers.dat"))
      managerOutputStream.writeObject(managers)
      managerOutputStream.flush()
      true
    } catch {
      case x: Exception => false
    }
  }

  def writeCustomersToFile: Boolean = {
    try {
      val customerOutputStream = new ObjectOutputStream(new FileOutputStream("customers.dat"))
      customerOutputStream.writeObject(customers)
      customerOutputStream.flush()
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
      case x: Exception =>
        loggedIn = Manager(1, "Supreme", "Overlord - Eric", 28, "Male", 27000, "admin", "admin")
        createEmployee(1, "Supreme", "Overlord - Eric", 28, "Male", 27000, "admin", "admin", EmployeeType.MANAGER)
        logout()
        false
    }
  }
  def readCustomersFromFile: Boolean = {
    try {
      val customerInputStream = new ObjectInputStream(new FileInputStream("customers.dat"))
      customers.clear()
      var newList = customerInputStream.readObject.asInstanceOf[ListBuffer[Customer]]
      customers ++= newList
      true
    } catch {
      case x: Exception => false
    }
  }
}
