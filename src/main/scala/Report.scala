import java.util.Date

import scala.collection.mutable.ListBuffer

/**
  * Class that will be invoked when a sales report has been requested for print.
  *
  * Created by alfie on 19/06/2017.
  */


case class Report(reportID: Int, transaction: ListBuffer[Transaction], dateFrom: Date, dateTo: Date){
  
  override def toString: String = s"Report ID: $reportID     " + transaction

}
