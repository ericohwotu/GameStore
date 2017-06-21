import Compound.isOpen
// EvaluationOrder.scala

val sunny = true
val hoursSleep = 6
val exercise = false
val temp = 55

val happy1 = sunny && temp > 50 || exercise && hoursSleep > 7
println(happy1)

val sameHappy1 = (sunny && temp > 50) || (exercise && hoursSleep > 7)
println(sameHappy1)

val notSame =
  (sunny && temp > 50 || exercise) && hoursSleep > 7
println(notSame)

//compound expressions
val hour = 6

val isOpen = {
  val opens = 9
  val closes = 20
  println("Operating hours: " + opens + "-" + closes)
  if(hour >= opens && hour <= closes){
    true
  } else  {
    false
  }
}
println(isOpen)

// CompoundExpressions2.scala
object Compound {
  val activity = "swimming"
  val hour = 10

  val isOpen = {
    if (activity == "swimming" || activity == "ice skating") {
      val opens = 9
      val closes = 20
      println("Operating Hours: " + opens + "-" + closes)
      if (hour >= opens && hour <= closes) {
        true
      } else {
        false
      }
    } else {
      false
    }
  }
}

println(isOpen)
