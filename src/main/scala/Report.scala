
/**
  * Class that will be invoked when a sales report has been requested for print.
  *
  * Created by alfie on 19/06/2017.
  */


abstract class Report(reportID: Int, transaction: List[Transaction]) {

   val trans: Transaction

  override def toString: String = trans.toString

}
