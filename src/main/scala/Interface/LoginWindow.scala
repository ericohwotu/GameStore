package Interface

import scalafx.Includes._
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene


object LoginWindow {
	var loginUser:String = "test"
	var loginPassword:String = "admin"
	
	var loginStage:PrimaryStage = new PrimaryStage() {
		
		
		scene = new Scene() {
			title="Elliot and Friends™"
		}
	}
	
}
