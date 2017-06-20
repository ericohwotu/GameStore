import org.scalatest._


class PersonTest extends FeatureSpec with GivenWhenThen {

  info("As a user")
  info("I would like to create a person")
  info("The person should have the ability to carry out various actions")


  feature("Add user") {

    scenario("User creates new Manager") {
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

    scenario("User creates new Employee") {
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
