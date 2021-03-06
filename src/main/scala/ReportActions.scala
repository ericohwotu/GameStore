import java.io._
import java.util.Date
import scala.collection.mutable.ListBuffer

/**
  * Created by Administrator on 19/06/2017.
  */
trait ReportActions extends MainVariables{

  var generatedReport: Report = _

  def getReport(id: Int): Report = {
  val reportOne = reports.find(r => r.reportID == id)
    Report match{
      case _ if(reportOne!=None) => reportOne.get
      case _ => null
    }
  }

  def createReport(id: Int, dateFrom: Date, dateTo: Date): Boolean = {

    var transList: ListBuffer[Transaction] = new ListBuffer[Transaction]
    val count = 0
    Boolean match{
      case _ if (dateFrom != null && dateTo != null) && loggedIn.isInstanceOf[Manager] =>

        transList = transactions.filter(_.dateAndTime.after(dateFrom)).filter(_.dateAndTime.before(dateTo))

        if(!transList.isEmpty) {
          generatedReport = Report(id, transList, dateFrom, dateTo);
          println(s"Successfully generated report: $generatedReport")
          reports += generatedReport
          writeReportToFile
          true
        }else false
      case _ => false
    }
  }

  def deleteReport(id: Int): Boolean ={
    val reportID = reports.find(r => r.reportID == id )
    Boolean match {
      case _ if reportID != None && loggedIn.isInstanceOf[Manager] => val reportToDelete = reports.filter(_ == id)
        println(s"Report $reportToDelete has been successfully deleted"); writeReportToFile; true
      case _ => false
    }
  }

  def writeReportToFile: Boolean = {
    try {
      val save = new ObjectOutputStream(new FileOutputStream("report.dat"))
      save.writeObject(reports)
      save.flush()
      true
    } catch {
      case e: Exception => false
    }
  }

  def readReportFromFile: Boolean = {
    try {
      val load = new ObjectInputStream(new FileInputStream("report.dat"))
      val report = load.readObject.asInstanceOf[ListBuffer[Report]]
      reports.clear()
      reports ++= report
      true
    } catch {
      case _ :Exception => false
    }
  }
}
