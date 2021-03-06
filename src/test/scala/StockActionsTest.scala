import java.util.Date

import org.scalatest._

/**
  * Created by Administrator on 20/06/2017.
  */
class StockActionsTest extends FlatSpec with Matchers with StockActions with MainVariables{


  "creating new hardware as a manager" should "return true if successful" in {
    stocks.clear
    loggedIn = Manager(100, "fname", "lname", 100, "male", 10000, "loginName", "password")
    val result = createStock(1,"HW name","HW desc", 100, 10000, "HW config",HardwareType.PHONE)
    result should be(true)
  }
  it should "return false if you are not a manager" in {
    stocks.clear
    loggedIn = Employee(100, "fname", "lname", 100, "male", 10000, "loginName", "password")
    val result = createStock(1,"HW name","HW desc", 100, 10000, "HW config",HardwareType.PHONE)
    result should be(false)
  }
  it should "return false if stock with this is id already exists" in {
    stocks.clear
    loggedIn = Manager(100, "fname", "lname", 100, "male", 10000, "loginName", "password")
    createStock(2,"HW name","HW desc", 100, 10000, "HW config",HardwareType.CONSOLE)
    val result = createStock(2,"HW name2","HW desc2", 100, 10000, "HW config2",HardwareType.LAPTOP)
    result should not be(true)
  }
  it should "have a different size reflecting the amount of stock added" in {
    stocks.clear
    loggedIn = Manager(100, "fname", "lname", 100, "male", 10000, "loginName", "password")
    createStock(1,"HW name","HW desc", 100, 10000, "HW config",HardwareType.PHONE)
    createStock(2,"HW name","HW desc", 100, 10000, "HW config",HardwareType.CONSOLE)
    stocks.length should be(2)
  }

//yo

  "creating new misc item" should "return true if successful" in {
    stocks.clear
    loggedIn = Manager(100, "fname", "lname", 100, "male", 10000, "loginName", "password")
    val result = createStock(3,"misc name","misc desc", 100, 10000, ItemType.SHIRT)
    result should be(true)
  }
  it should "return false if you are not a manager" in {
    stocks.clear
    loggedIn = Employee(100, "fname", "lname", 100, "male", 10000, "loginName", "password")
    val result = createStock(3,"misc name","misc desc", 100, 10000, ItemType.SHIRT)
    result should be(false)
  }
  it should "return false if stock item with this id already exists" in {
    stocks.clear
    loggedIn = Manager(100, "fname", "lname", 100, "male", 10000, "loginName", "password")
    createStock(4,"misc name","misc desc", 100, 10000, ItemType.SHIRT)
    val result = createStock(4,"misc name2","misc desc2", 100, 10000, ItemType.SHIRT)
    result should not be(true)
  }
  it should "have a different size reflecting the amount of stock added" in {
    stocks.clear
    loggedIn = Manager(100, "fname", "lname", 100, "male", 10000, "loginName", "password")
    createStock(3,"misc name","misc desc", 100, 10000, ItemType.SHIRT)
    createStock(4,"misc name","misc desc", 100, 10000, ItemType.SHIRT)
    stocks.length should be(2)
  }




  "creating new game" should "return true if successful" in {
    stocks.clear
    loggedIn = Manager(100, "fname", "lname", 100, "male", 10000, "loginName", "password")
    val result = createStock(5,"game name","game desc", 100, 10000, 12, "game genre", "game console", new Date())
    result should be(true)
  }
  it should "return false if you are not a manager" in {
    stocks.clear
    loggedIn = Employee(100, "fname", "lname", 100, "male", 10000, "loginName", "password")
    val result = createStock(6,"game name2","game desc2", 100, 10000, 12, "game genre2", "game console", new Date())
    result should be(false)
  }
  it should "return false if stock with this id already exists" in {
    stocks.clear
    loggedIn = Manager(100, "fname", "lname", 100, "male", 10000, "loginName", "password")
    createStock(6,"game name","game desc", 100, 10000, 12, "game genre", "game console", new Date())
    val result = createStock(6,"game name2","game desc2", 100, 10000, 12, "game genre2", "game console", new Date())
    result should not be(true)
  }
  it should "have a different size reflecting the amount of stock added" in {
    stocks.clear
    loggedIn = Manager(100, "fname", "lname", 100, "male", 10000, "loginName", "password")
    createStock(6,"game name","game desc", 100, 10000, 12, "game genre", "game console", new Date())
    createStock(7,"game name","game desc", 100, 10000, 12, "game genre", "game console", new Date())
    stocks.length should be(2)
  }


  "getting stock by ID" should "return true if successfull" in {
    stocks.clear
    createStock(1,"HW name","HW desc", 100, 10000, "HW config",HardwareType.PHONE)
    val result = getStock(1)
    result should not be(null)
    result.isInstanceOf[Stock] should be(true)
  }
  it should "return false if the stock doesn't exist" in {
    stocks.clear
    val result = getStock(1)
    result should be(null)
    result.isInstanceOf[Stock] should not be(true)
  }


  "deleting stock by ID" should "return true if you are a manager and are successful" in {
    stocks.clear
    loggedIn = Manager(100, "fname", "lname", 100, "male", 10000, "loginName", "password")
    createStock(1,"HW name","HW desc", 100, 10000, "HW config",HardwareType.PHONE)
    val result = deleteStock(1)
    result should be(true)
    getStock(1) should not be(true)
  }
  "deleting stock by ID" should "return false if you are not a manager" in {
    stocks.clear
    loggedIn = Manager(100, "fname", "lname", 100, "male", 10000, "loginName", "password")
    createStock(1,"HW name","HW desc", 100, 10000, "HW config",HardwareType.PHONE)
    loggedIn = Employee(101, "fname", "lname", 100, "male", 10000, "loginName", "password")
    val result = deleteStock(1)
    result should be(false)
  }
  it should "return false if the stock doesn't exist" in {
    val result = getStock(8)
    result should not be (true)
  }


  "saving stock to file" should "return true if successful" in {
    stocks.clear()
    loggedIn = Manager(100, "fname", "lname", 100, "male", 10000, "loginName", "password")
    createStock(1,"HW name","HW desc", 100, 10000, "HW config",HardwareType.PHONE)
    createStock(2,"HW name","HW desc", 100, 10000, "HW config",HardwareType.CONSOLE)
    createStock(3,"HW name","HW desc", 100, 10000, "HW config",HardwareType.LAPTOP)
    writeStockToFile should be(true)
  }
  it should "then return true if the stock is cleared and reloaded" in {
    stocks.clear()
    readStockFromFile should be(true)
  }
  it should "then maintain a size of 3" in {
    stocks.length should be(3)
  }

  "changing stock values" should "return true if the values are successfully changed" in {
    stocks.clear
    loggedIn = Manager(100, "fname", "lname", 100, "male", 10000, "loginName", "password")
    createStock(1,"game name","game desc", 100, 10000, 12, "game genre", "game console", new Date())
    createStock(2,"HW name","HW desc", 100, 10000, "HW config",HardwareType.PHONE)
    createStock(3,"misc name","misc desc", 100, 10000, ItemType.SHIRT)

    getStock(1).setPrice(300)
    getStock(1).asInstanceOf[Game].setRating(8)
    getStock(1).asInstanceOf[Game].setGenre("new genre")
    getStock(1).asInstanceOf[Game].setConsole("new console")
    getStock(1).price should be(300)
    getStock(1).asInstanceOf[Game].rating should be(8)
    getStock(1).asInstanceOf[Game].genre should be("new genre")
    getStock(1).asInstanceOf[Game].console should be("new console")

    getStock(2).setDesc("new desc")
    getStock(2).asInstanceOf[Hardware].setConfig("new config")
    getStock(2).desc should be("new desc")
    getStock(2).asInstanceOf[Hardware].config should be ("new config")

    getStock(3).setName("new name")
    getStock(3).name should be ("new name")


  }

}
