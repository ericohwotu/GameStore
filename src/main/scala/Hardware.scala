/**
  * Created by Administrator on 19/06/2017.
  */
abstract class Hardware extends Stock{
  var config: String

  def setConfig(newConfig:String): Unit ={
    config = newConfig
  }

}
