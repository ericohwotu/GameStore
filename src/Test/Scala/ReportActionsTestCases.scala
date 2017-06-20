import java.util.Date

import org.scalatest._


/**
  * Created by alfie on 20/06/2017.
  */


class ReportActionsTestCases extends FlatSpec with Matchers with ReportActions{

  //========================================= Create Report ==================================================//

  "Create report" should "return true if successful" in {
    createReport(1, new Date(), new Date())
  }

  it should "return false if dateFrom is left empty" in {
    createReport(1, null, new Date())
  }

  it should "return false if toDate is left empty" in {
    createReport(1, new Date(), null)
  }

  it should "throw an IndexOutOfBoundException if the ID doesn't exist" in {
    a[IndexOutOfBoundsException] should be thrownBy {
      createReport(10000000, new Date(), new Date())
    }
  }

  //========================================= Get Report ==================================================//


  "Get report" should "return a report if successful" in {
    getReport(1)
  }

  it should "throw a NullPointerException if the report doesn't exist" in {
    a[NullPointerException] should be thrownBy {
      getReport(null)
    }
  }

  it should "throw an IndexOutOfBoundException if the ID doesn't exist" in {
    a[IndexOutOfBoundsException] should be thrownBy {
      getReport(10000000)
    }
  }

  //========================================= Delete Report ==================================================//

  "Delete report" should "return true if successful" in {
    deleteReport(1)
  }

  it should "throw a NullPointerException if the report doesn't exist" in {
    a[NullPointerException] should be thrownBy {
      deleteReport(null)
    }
  }

  it should "throw an IndexOutOfBoundException if the ID doesn't exist" in {
    a[IndexOutOfBoundsException] should be thrownBy {
      deleteReport(10000000)
    }

  }
}
