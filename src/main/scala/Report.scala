import java.io.IOException
import java.util.Date

import scala.collection.mutable.ListBuffer

/**
  * Class that will be invoked when a sales report has been requested for print.
  *
  * Created by alfie on 19/06/2017.
  */


case class Report(reportID: Int, transaction: ListBuffer[Transaction]) extends MainVariables{

  var generatedReport: Report = null

  /**
    * Method that will allow other classes to get a report generated.
    *
    * @param id
    */

  def getReport(id: Int) = generatedReport

  /**
    * Method that will take 3 parameters and generate a report which will display transactions between a date
    * range.
    *
    * @param id
    * @param dateFrom
    * @param dateTo
    */

  def createReport(id: Int, dateFrom: Date, dateTo: Date): Unit = {

    var transList: ListBuffer[Transaction] = new ListBuffer[Transaction]
    val count = 0

    if(loggedIn.isInstanceOf[Manager]) {
      transList = transactions.filter(_.dateAndTime.after(dateFrom)).filter(_.dateAndTime.before(dateTo))
      generatedReport = new Report(count + 1, transList)
      println(s"Successfully generated report: $generatedReport")
      reports += generatedReport
    } else {
      throw new IOException("You are not authorized to access this function")
    }
  }

  def deleteReport(id: Int): Unit ={
    if(loggedIn.isInstanceOf[Manager]) {
      val reportToDelete = reports.filter(_ == id)
      println(s"Report $reportToDelete has been successfully deleted")
    } else {
      throw new IOException("You are not authorized to access this function")
    }
  }

  override def toString: String = s"Report ID: $reportID" + transaction

}
