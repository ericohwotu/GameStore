import java.io.{FileInputStream, FileOutputStream, ObjectInputStream, ObjectOutputStream}

import scala.collection.mutable.ListBuffer

/**
  * Created by Administrator on 19/06/2017.
  */
trait StockActions extends MainVariables{
  /*
    * Transaction functions
    *
    * */
  def createStock(id: Int, name: String, desc: String, price: Double, count:Int, config: String, hardwareType: HardwareType.Value): Boolean = {
    if (stocks.find(s => s.id == id) == None && loggedIn.isInstanceOf[Manager]) {
      hardwareType match{
        case HardwareType.CONSOLE => {
          stocks += new Console(id, name, desc, price, count, config)
          writeStockToFile
          true
        }
        case HardwareType.LAPTOP =>{
          stocks += new Laptop(id, name, desc, price, count,config)
          writeStockToFile
          true
        }
        case HardwareType.PHONE =>{
          stocks += new Phone(id,name,desc,price, count,config)
          writeStockToFile
          true
        }
        case _ => false
      }
      writeStockToFile
    }else{
      false
    }
  }

  def createStock(id: Int, name: String, desc: String, price: Double, count:Int, itemType: ItemType.Value): Boolean = {
    if (stocks.find(s => s.id == id) == None && loggedIn.isInstanceOf[Manager]) {
      stocks += new Misc(id, name, desc, price, count, itemType)
      writeStockToFile
      true
    }else{
      false
    }
  }

//  GAMES NEED TO BE LOOKED AT AS THEY TAKE A CONSOLE AS AN ARGUMENT, BUT THIS DOES NOT MAKE SENSE
  def createStock(id: Int, name: String, desc: String, price: Double, count:Int, rating: Int, genre: String, console: String): Boolean = {
    if (stocks.find(s => s.id == id) == None && loggedIn.isInstanceOf[Manager]) {
      stocks += new Game(id, name, desc, price, count, rating, genre, console)
      writeStockToFile
      true
    }else{
      false
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
    if(stock!=None && loggedIn.isInstanceOf[Manager]){
      stocks -= stock.get
      writeStockToFile
      true
    }else {
      false
    }
  }


  def writeStockToFile: Boolean ={
    try {
      val save = new ObjectOutputStream(new FileOutputStream("stock.dat"))
      save.writeObject(stocks)
      save.close
      true
    }catch{
      case e: Exception => false
    }
  }

  def readStockFromFile: Boolean ={
    try {
      val load = new ObjectInputStream(new FileInputStream("stock.dat"))
      val tempStocks = load.readObject.asInstanceOf[ListBuffer[Stock]]
      stocks.clear()
      tempStocks.foreach(s => stocks += s)
      load.close
      true
    }catch{
      case e: Exception => false
    }
  }

}
