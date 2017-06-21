import org.scalatest._

/**
  * Created by Administrator on 20/06/2017.
  */
class StockActionsTest extends FlatSpec with Matchers with StockActions with MainVariables{


  "creating new hardware" should "return true if successful" in {
    stocks.clear
    val result = createStock(1,"HW name","HW desc", 100, "HW config",HardwareType.PHONE)
    result should be(true)
  }
  it should "return false if stock with this is id already exists" in {
    stocks.clear
    createStock(2,"HW name","HW desc", 100, "HW config",HardwareType.CONSOLE)
    val result = createStock(2,"HW name2","HW desc2", 100, "HW config2",HardwareType.LAPTOP)
    result should not be(true)
  }
  it should "have a different size reflecting the amount of stock added" in {
    stocks.clear
    createStock(1,"HW name","HW desc", 100, "HW config",HardwareType.PHONE)
    createStock(2,"HW name","HW desc", 100, "HW config",HardwareType.CONSOLE)
    stocks.length should be(2)
  }



  "creating new misc item" should "return true if successful" in {
    stocks.clear
    val result = createStock(3,"misc name","misc desc", 100, ItemType.SHIRT)
    result should be(true)
  }
  it should "return false if stock item with this id already exists" in {
    stocks.clear
    createStock(4,"misc name","misc desc", 100, ItemType.SHIRT)
    val result = createStock(4,"misc name2","misc desc2", 100, ItemType.SHIRT)
    result should not be(true)
  }
  it should "have a different size reflecting the amount of stock added" in {
    stocks.clear
    createStock(3,"misc name","misc desc", 100, ItemType.SHIRT)
    createStock(4,"misc name","misc desc", 100, ItemType.SHIRT)
    stocks.length should be(2)
  }



  "creating new game" should "return true if successful" in {
    stocks.clear
    val result = createStock(5,"game name","game desc", 100, 12, "game genre", null)
    result should be(true)
  }
  it should "return false if stock with this id already exists" in {
    stocks.clear
    createStock(6,"game name","game desc", 100, 12, "game genre", null)
    val result = createStock(6,"game name2","game desc2", 100, 12, "game genre2", null)
    result should not be(true)
  }
  it should "have a different size reflecting the amount of stock added" in {
    stocks.clear
    createStock(6,"game name","game desc", 100, 12, "game genre", null)
    createStock(7,"game name","game desc", 100, 12, "game genre", null)
    stocks.length should be(2)
  }


  "getting stock by ID" should "return true if successfull" in {
    stocks.clear
    createStock(1,"HW name","HW desc", 100, "HW config",HardwareType.PHONE)
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


  "deleting stock by ID" should "return true if successfull" in {
    stocks.clear
    createStock(1,"HW name","HW desc", 100, "HW config",HardwareType.PHONE)
    val result = deleteStock(1)
    result should be(true)
    getStock(1) should not be(true)
  }
  it should "return false if the stock doesn't exist" in {
    val result = getStock(8)
    result should not be (true)
  }


  "saving stock to file" should "return true if successful" in {
    stocks.clear()
    createStock(1,"HW name","HW desc", 100, "HW config",HardwareType.PHONE)
    createStock(2,"HW name","HW desc", 100, "HW config",HardwareType.CONSOLE)
    createStock(3,"HW name","HW desc", 100, "HW config",HardwareType.LAPTOP)
    writeStockToFile should be(true)
  }
  it should "then return true if the stock is cleared and reloaded" in {
    stocks.clear()
    readStockFromFile should be(true)
  }
  it should "then maintain a size of 3" in {
    stocks.length should be(3)
  }


}
