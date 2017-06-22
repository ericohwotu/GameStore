/**
  * Created by Administrator on 19/06/2017.
  */
case class Game(id: Int, var name: String, var desc: String, var price: Double, var count:Int, var rating: Int, var genre: String, var console: String) extends Stock{
  def setRating(newRating:Int): Unit ={
    rating = newRating
  }

  def setGenre(newGenre:String): Unit ={
    genre = newGenre
  }

  def setConsole(newConsole:String): Unit ={
    console = newConsole
  }

}
