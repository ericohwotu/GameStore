import java.util.Date
import org.scalatest._

/**
  * Created by alfie on 20/06/2017.
  */

class ReportActionsTestCases extends FlatSpec with Matchers with ReportActions with MainVariables with TransactionActions{

  //========================================= Create Report ==================================================//

  "Create report" should "return true if successful" in {
    reports.clear
    readTransactionsFromFile
    loggedIn = Manager(1,"Eric","Ohwotu",28,"Male",23000,"CallMeMaster","NoneYaBusiness")
    new Date()
    val reportOne = createReport(576, dateFrom = new Date(2017-1900,5-1,22), dateTo = new Date(2017-1900,7-1,22))
    reportOne should be(true)
  }

  it should "add the report to a reports list and reflect the quantity of reports" in {
    reports.clear
    readTransactionsFromFile
    loggedIn = Manager(1,"Eric","Ohwotu",28,"Male",23000,"CallMeMaster","NoneYaBusiness")
    val reportOne = createReport(576, dateFrom = new Date(2017-1900,5-1,22), dateTo = new Date(2017-1900,7-1,22))
    reports.length should be(1)
  }

  it should "return false if dateFrom is left empty" in {
    reports.clear
    readTransactionsFromFile
    loggedIn = Manager(1,"Eric","Ohwotu",28,"Male",23000,"CallMeMaster","NoneYaBusiness")
    val noDate = null
    val reportOne = createReport(1, noDate, new Date())
    reportOne should be(false)
  }

  it should "return false if toDate is left empty" in {
    reports.clear
    readTransactionsFromFile
    loggedIn = Manager(1,"Eric","Ohwotu",28,"Male",23000,"CallMeMaster","NoneYaBusiness")
    val noDate = null
    val reportOne = createReport(1, new Date(), noDate)
    reportOne should be(false)
  }

  it should "return false if the ID is 10 characters or more" in {
    reports.clear
      createReport(10000000, new Date(), new Date()) should be (false)
    }

  //========================================= Get Report ==================================================//

  "Get report" should "return a report if successful" in {
    reports.clear
    val reportOne = createReport(1, new Date(), new Date())
    getReport(1)
  }

  it should "return false if the ID doesn't exist" in {
    reports.clear
    getReport(5) should be (null)
  }

  //========================================= Delete Report ==================================================//

  "Delete report" should "return true if successful" in {
    reports.clear
    loggedIn = Manager(1,"Eric","Ohwotu",28,"Male",23000,"CallMeMaster","NoneYaBusiness")
    createReport(576, dateFrom = new Date(2017-1900,5-1,22), dateTo = new Date(2017-1900,7-1,22))
    val r = deleteReport(576)
    r should be (true)
  }

  it should "return false if the report doesn't exist" in {
    loggedIn = Manager(1,"Eric","Ohwotu",28,"Male",23000,"CallMeMaster","NoneYaBusiness")
    deleteReport(27) should be (false)
  }

  //========================================= Serialization ==================================================//

  "Saving report to file" should "return true if successful" in {
    reports.clear()
    createReport(576, dateFrom = new Date(2017-1900,5-1,22), dateTo = new Date(2017-1900,7-1,22))
    createReport(577, dateFrom = new Date(2017-1900,5-1,22), dateTo = new Date(2017-1900,7-1,22))
    createReport(578, dateFrom = new Date(2017-1900,5-1,22), dateTo = new Date(2017-1900,7-1,22))
    writeReportToFile should be (true)
  }

  it should "then return true if the report is cleared and reloaded" in {
    reports.clear()
    readReportFromFile should be(true)
  }

  it should "then maintain a size of 3" in {
    reports.length should be(3)
  }

}
