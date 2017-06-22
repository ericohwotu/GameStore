/**
  * Created by Administrator on 19/06/2017.
  */
abstract class Stock{
  val id: Int
  var price: Double
  var name: String
  var desc: String
  var count: Int

  def increaseCount(add:Int):Unit = {
    count += add
  }
  def decreaseCount(sub:Int):Boolean = {
    if(count>=sub) {
      count -= sub
      true
    }else false
  }

  def setPrice(newPrice:Double): Unit ={
    price = newPrice
  }
  def setName(newName:String): Unit ={
    name = newName
  }
  def setDesc(newDesc:String): Unit ={
    desc = newDesc
  }



  override def toString():String = {
    s"$name, Price: $price"
  }
}
