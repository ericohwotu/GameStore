import scala.collection.mutable.ListBuffer

/**
  * Created by Administrator on 20/06/2017.
  */
trait MainVariables {
	
  val transactions: ListBuffer[Transaction] = new ListBuffer[Transaction]
  val stocks: ListBuffer[Stock] = new ListBuffer[Stock]
  val employees: ListBuffer[Employee] = new ListBuffer[Employee]
  val managers: ListBuffer[Manager] = new ListBuffer[Manager]
  val reports: ListBuffer[Report] = new ListBuffer[Report]
	var newUsername: String = ""
  var loggedIn: Person = null
	
	def login(username: String, password: String): Boolean = {
		val getUser = (employees++managers).filter(person => person.loginName==username&&person.password==password)
		getUser.length match {
			case 0 => println("No User Found"); false
			case 1 => println(getUser.head.loginName); loggedIn = getUser.head; true
			case x if x > 1 => println(s"Too Many Users Found $x"); false
		}
	}

	def getNewUserName(oldStr: String):String ={
		var usrName = oldStr
		var acc = 1

		while(!(employees++managers).forall(_.loginName!=usrName)){
			if (acc > 1) usrName = usrName.dropRight((acc-1).toString.length)
			usrName = s"${usrName}$acc"
			acc+=1
			println(s"${(acc-1).toString.length} >>> $usrName >>> ${(employees++managers).forall(_.loginName!=usrName)} >>> ${employees++managers}")
		}
		newUsername = usrName
		usrName
	}
	
	def logout(): Boolean ={
		loggedIn match {
			case null => false
			case _: Person => loggedIn=null; true
			case _ => false
		}
	}
}
