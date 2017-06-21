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

        val isInList = Main.managers.filter(_.ID==id).length > 0

        isInList match {
          case false =>
            Main.managers += new Manager (id, fName, lName, Age, Gender, salary, loginName, password)
            true

          case true =>
            println("Sorry Id already exists")
            false
        }

      case EmployeeType.EMPLOYEE =>

        val isInList = Main.employees.filter(_.ID==id).length > 0

        isInList match {
          case false =>
            Main.employees += new Employee(id, fName, lName, Age, Gender, salary, loginName, password)
            true

          case true =>
            println("Sorry Id already exists")
            false
        }

      case _ => false

    }
  }

  def getEmployee(id: Int): Employee = {
    val result = Main.employees.filter(_.ID == id)
    result.length match {
      case 0 => println("Sorry Employee Doesnt Exist"); null
      case 1 => result.head
      case x if x > 1 => println("Multiple Found"); null
    }
  }

  def deleteEmployee(id: Int): Boolean = {
    val result = Main.employees.filter(_.ID == id)
    result.length match {
      case 0 => println("Sorry Employee Doesnt Exist"); false
      case 1 => Main.employees -= result.head; true
      case x if x > 1 => println("Multiple Found"); false
    }
  }

  def getManager(id: Int): Manager ={
    val result = Main.managers.filter(_.ID == id)
    result.length match {
      case 0 => println("Sorry Manager Doesnt Exist"); null
      case 1 => result.head
      case x if x > 1 => println("Multiple Found"); null
    }
  }

  def deleteManager(id: Int): Boolean ={
    val result = Main.managers.filter(_.ID == id)
    result.length match {
      case 0 => println("Sorry Manager Doesnt Exist"); false
      case 1 => Main.managers -= result.head; true
      case x if x > 1 => println("Multiple Found"); false
    }
  }
}
