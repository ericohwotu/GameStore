import java.io.{FileInputStream, FileOutputStream, ObjectInputStream, ObjectOutputStream}

import scala.collection.mutable.ListBuffer

val list = ListBuffer[Stock]()
list += Misc(100, "name", "desc", 100, ItemType.SHIRT)
list += Misc(100, "name", "desc", 100, ItemType.MUG)
list += Misc(100, "name", "desc", 100, ItemType.POSTER)



val oos = new ObjectOutputStream(new FileOutputStream("stock.dat"))
oos.writeObject(list)
oos.close


val ois = new ObjectInputStream(new FileInputStream("stock.dat"))
val stocks = ois.readObject.asInstanceOf[ListBuffer[Stock]]
ois.close
