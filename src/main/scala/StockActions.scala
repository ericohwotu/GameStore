/**
  * Created by Administrator on 19/06/2017.
  */
trait StockActions {
  /*
    * Transaction functions
    *
    * */
  def createStock(id: Int, name: String, desc: String, price: Double, config: String): Boolean = false

  def createStock(id: Int, name: String, desc: String, price: Double, itemType: ItemType.Value): Boolean = false

  def createStock(id: Int, name: String, desc: String, price: Double, rating: Int, genre: String, console: Console): Boolean = false

  def getStock(id: Int): Stock = null

  def deleteStock(id: Int): Boolean = false

}
