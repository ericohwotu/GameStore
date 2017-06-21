import java.io.{FileInputStream, FileOutputStream, ObjectInputStream, ObjectOutputStream}
import java.util.Date

import scala.collection.mutable.ListBuffer

/**
  * Created by Administrator on 19/06/2017.
  */
trait ReportActions extends MainVariables{
  /*
  * Report functions
  *
  * */
  def createReport(id: Int, dateFrom: Date, dateTo: Date): Boolean = false

  def getReport(id: Int): Report = null

  def deleteReport(id: Int): Boolean = false

  def writeReportToFile: Boolean = {
    try {
      val save = new ObjectOutputStream(new FileOutputStream("report.dat"))
      save.writeObject(reports)
      save.close
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
      report.foreach(r => report += r)
      load.close
      true
    } catch {
      case e :Exception => false
    }
  }
}
