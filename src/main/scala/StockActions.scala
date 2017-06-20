/**
  * Created by Administrator on 19/06/2017.
  */
trait StockActions extends MainVariables{
  /*
    * Transaction functions
    *
    * */
  def createStock(id: Int, name: String, desc: String, price: Double, config: String, hardwareType: HardwareType.Value): Boolean = {
    if (stocks.find(s => s.id == id) == None) {
      hardwareType match{
        case HardwareType.CONSOLE => {
          stocks += new Console(id, name, desc, price, config)
          true
        }
        case HardwareType.LAPTOP =>{
          stocks += new Laptop(id, name, desc, price, config)
          true
        }
        case HardwareType.PHONE =>{
          stocks += new Phone(id,name,desc,price,config)
          true
        }
        case _ => false
      }
    }else{
      false
    }
  }

  def createStock(id: Int, name: String, desc: String, price: Double, itemType: ItemType.Value): Boolean = {
    if (stocks.find(s => s.id == id) == None) {
      stocks += new Misc(id, name, desc, price, itemType)
      true
    }else{
      false
    }
  }

  def createStock(id: Int, name: String, desc: String, price: Double, rating: Int, genre: String, console: Console): Boolean = {
    if (stocks.find(s => s.id == id) != None || console == null) {
      false
    }else{
      stocks += new Game(id, name, desc, price, rating, genre, console)
      true
    }
  }

  def getStock(id: Int): Stock = {
    val stock = stocks.find(s => s.id==id)
    if(stock!=None){
      stock.get
    }else{
      null
    }
  }

  def deleteStock(id: Int): Boolean = {
    val stock = stocks.find(s => s.id==id)
    if(stock!=None){
      stocks -= stock.get
      true
    }else {
      false
    }
  }

}
