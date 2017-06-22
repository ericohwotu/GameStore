import org.scalatest._


class PersonTest extends FeatureSpec with GivenWhenThen {

  info("As a user")
  info("I would like to create a person")
  info("The person should have the ability to carry out various actions")


  feature("Add user") {

    scenario("new Manager is instantiated") {
      Given("All Managers details are supplied")
      val ID = 1
      val fName = "Armani"
      val lName = "Bond"
      val Age = 18
      val Gender = "Male"
      val Salary = 30000
      val login = "ABond"
      val password = "IGetMoney"

      When("New User is Created")
      val newUser = new Manager(ID, fName, lName, Age, Gender, Salary, login, password)

      Then("new manager with supplied details should exist")
      assert(newUser.ID == 1)
      assert(newUser.loginName == "ABond")
      assert(newUser.password == "IGetMoney")
      assert(newUser.salary == 30000)
      assert(newUser.Gender == "Male")
      assert(newUser.fName == "Armani")
      assert(newUser.lName == "Bond")

    }

    scenario("Person is edited"){
      Given("An employee")
      val employee = Manager(ID=33,Gender = "Male",lName = "Bond", fName = "Jamima", loginName = "you-cant-see-me", password = "hahahahahertyuiop",Age = 14,salary = 350090)

      When("There Details are changed")
      employee.editFirstName("James")
      employee.editLastName("Buckets")
      employee.editPassword("rt*^^*l)..3tu")
      employee.editUsername("H3r3254ve")

      Then("employee should take on new details")
      assert(employee.fName=="James")
      assert(employee.lName=="Buckets")
      assert(employee.password=="rt*^^*l)..3tu")
      assert(employee.loginName=="H3r3254ve")
    }
    scenario("new employee is instantiated") {
      Given("All employees details are supplied")
      val ID = 2
      val fName = "Jason"
      val lName = "Bond"
      val Age = 18
      val Gender = "Male"
      val Salary = 28000
      val login = "JasonB"
      val password = "Baller4Life"

      When("New User is Created")
      val newUser = new Employee(ID, fName, lName, Age, Gender, Salary, login, password)

      Then("new employee with supplied details should exist")
      assert(newUser.ID == 2)
      assert(newUser.loginName == "JasonB")
      assert(newUser.password == "Baller4Life")
      assert(newUser.salary == 28000)
      assert(newUser.Gender == "Male")
      assert(newUser.fName == "Jason")
      assert(newUser.lName == "Bond")

    }
  }
}
