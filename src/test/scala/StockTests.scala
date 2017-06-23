import java.util.Date

import org.scalatest.{FeatureSpec, GivenWhenThen}

/**
  * Created by Administrator on 20/06/2017.
  */
class StockTests extends FeatureSpec with GivenWhenThen {

  info("As an employee")
  info("I want to be able to create new new stock items")
  info("so they can be sold")
  info("and their details can be recorded")
  feature("Stock") {

    scenario("User creates new Console"){
      Given("All console details are supplied")
      val id = 1
      val name = "console name"
      val desc = "console description"
      val price = 100.0
      val count = 10000
      val config = "console config"

      When("New Console is created")
      val console = new Console(id, name, desc, price, count, config)

      Then("new console with supplied details should exist")
      assert(console.id==1)
      assert(console.name=="console name")
      assert(console.desc=="console description")
      assert(console.price==100.0)
      assert(console.config=="console config")
    }

    scenario("User creates new Laptop"){
      Given("All laptop details are supplied")
      val id = 2
      val name = "laptop name"
      val desc = "laptop description"
      val price = 200.0
      val count = 10000
      val config = "laptop config"

      When("New laptop is created")
      val laptop = new Laptop(id, name, desc, price, count, config)

      Then("new laptop with supplied details should exist")
      assert(laptop.id==2)
      assert(laptop.name=="laptop name")
      assert(laptop.desc=="laptop description")
      assert(laptop.price==200.0)
      assert(laptop.config=="laptop config")
    }

    scenario("User creates new phone"){
      Given("All phone details are supplied")
      val id = 3
      val name = "phone name"
      val desc = "phone description"
      val price = 300.0
      val count = 10000
      val config = "phone config"

      When("New phone is created")
      val phone = new Phone(id, name, desc, price, count, config)

      Then("new phone with supplied details should exist")
      assert(phone.id==3)
      assert(phone.name=="phone name")
      assert(phone.desc=="phone description")
      assert(phone.price==300.0)
      assert(phone.config=="phone config")
    }

    scenario("User creates new game"){
      Given("All game details are supplied")
      val id = 4
      val name = "game name"
      val desc = "game description"
      val price = 400.0
      val count = 10000
      val rating = 12
      val genre = "game genre"
      val console = "game console"

      When("New game is created")
      val game = new Game(id, name, desc, price, count, rating, genre, console, new Date())

      Then("new phone with supplied details should exist")
      assert(game.id==4)
      assert(game.name=="game name")
      assert(game.desc=="game description")
      assert(game.price==400.0)
      assert(game.rating==12)
      assert(game.genre=="game genre")
      assert(game.console==console)
    }

    scenario("User creates new misc item"){
      Given("All misc item details are supplied")
      val id = 5
      val name = "misc item name"
      val desc = "misc item description"
      val price = 500.0
      val count = 10000

      When("New misc item is created")
      val misc = new Misc(id, name, desc, price, count, ItemType.MUG)

      Then("new phone with supplied details should exist")
      assert(misc.id==5)
      assert(misc.name=="misc item name")
      assert(misc.desc=="misc item description")
      assert(misc.price==500.0)
      assert(misc.itemType==ItemType.MUG)
    }

  }

}
