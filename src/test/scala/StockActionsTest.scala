import org.scalatest._

/**
  * Created by Administrator on 20/06/2017.
  */
class StockActionsTest extends FlatSpec with Matchers with StockActions{


  "creating new hardware" should "return true if successful" in {
    val result = createStock(1,"HW name","HW desc", 100, "HW config")
    result should be(true)
  }
  it should "return false if stock with this is id already exists" in {
    createStock(2,"HW name","HW desc", 100, "HW config")
    val result = createStock(2,"HW name2","HW desc2", 100, "HW config2")
    result should not be(true)
  }


  "creating new misc item" should "return true if successful" in {
    val result = createStock(3,"misc name","misc desc", 100, ItemType.SHIRT)
    result should be(true)
  }
  it should "return false if stock item with this id already exists" in {
    createStock(4,"misc name","misc desc", 100, ItemType.SHIRT)
    val result = createStock(4,"misc name2","misc desc2", 100, ItemType.SHIRT)
    result should not be(true)
  }


  "creating new game" should "return true if successful" in {
    val result = createStock(5,"game name","game desc", 100, 12, "game genre", null)
    result should be(true)
  }
  it should "return false if stock with this id already exists" in {
    createStock(6,"game name","game desc", 100, 12, "game genre", null)
    val result = createStock(6,"game name2","game desc2", 100, 12, "game genre2", null)
    result should be(true)
  }


  "getting stock by ID" should "return true if successfull" in {
    val testStock = createStock(1,"game name","game desc", 100, 12, "game genre", null)
    val result = getStock(1)
    result should not be(null)
    result == asInstanceOf[Stock] should be(true)
  }
  it should "return false if the stock doesn't exist" in {
    val result = getStock(1)
    result should be(null)
    result == asInstanceOf[Stock] should not be(true)
  }


  "deleting stock by ID" should "return true if successfull" in {
    val testStock = createStock(7,"game name","game desc", 100, 12, "game genre", null)
    val result = deleteStock(7)
    result should be(true)
    getStock(7) should not be(true)
  }
  it should "return false if the stock doesn't exist" in {
    val result = getStock(8)
    result should not be (true)
  }





}
