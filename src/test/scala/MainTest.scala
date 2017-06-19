import org.scalatest.{FeatureSpec, GivenWhenThen}

/**
  * Created by Administrator on 19/06/2017.
  */
class MainTest extends FeatureSpec with GivenWhenThen {

  info("hi")
  feature("testLogin") {
    scenario("user requests login"){

      Given("the login details")
      val lin = Main.login("hehehe","hahaha")
      assert(lin)

      When("ddd")

      Then("sdufh")
      assert(lin)
    }
  }

}
