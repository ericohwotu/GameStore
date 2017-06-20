import java.util.Date

/**
  * Created by Administrator on 19/06/2017.
  */
trait ReportActions {
  /*
  * Report functions
  *
  * */
  def createReport(id: Int, dateFrom: Date, dateTo: Date): Boolean = false

  def getReport(id: Int): Report = null

  def deleteReport(id: Int): Boolean = false
}
