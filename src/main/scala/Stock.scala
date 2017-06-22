/**
  * Created by Administrator on 19/06/2017.
  */
abstract class Stock{
  val id: Int
  val price: Double
  val name: String
  val desc: String
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
  override def toString():String = {
    s"$name, Price: $price"
  }
}



//val increaseCount: (Int) => (Unit) = add => count += add

