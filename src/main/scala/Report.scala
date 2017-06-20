import java.util.Date

import scala.collection.mutable.ListBuffer

/**
  * Class that will be invoked when a sales report has been requested for print.
  *
  * Created by alfie on 19/06/2017.
  */


class Report(reportID: Int, transaction: ListBuffer[Transaction]) extends MainVariables{

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

    // Need to get date from transactions.

    // Check through the transactions list and look for the start date that matches dateFrom
    // in function parameters. Return ListBuffer of transactions between date range

    transList = transactions.filter(_ == dateFrom).filter(_ == dateTo)

    // Create the report

    generatedReport = new Report(count + 1, transList)
    println(s"Successfully generated report: $generatedReport")

    reports += generatedReport

  }

  def deleteReport(id: Int): Unit ={

    val reportToDelete = reports.filter(_ == id)
    println(s"Report $reportToDelete has been successfully deleted")

  }



  override def toString: String = s"Report ID: $reportID" + transaction.toString

}
