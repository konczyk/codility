package lessons.lesson08

object EquiLeader {
  def solution(a: Array[Int]): Int = {

    def findLeader(i: Int, leader: Option[Int], stack: Int): Option[Int] =
      (leader, a.lift(i), stack) match {
        case (Some(num), None, _) if a.count(_ == num) > a.length/2 => leader
        case (_, None, _ ) => None
        case (None, _, _) => findLeader(i+1, Some(a(i)), stack+1)
        case (Some(num), _, len) if num != a(i) =>
          if (len > 1) findLeader(i+1, leader, stack-1)
          else findLeader(i+1, None, 0)
        case _ => findLeader(i+1, leader, stack+1)
      }

    findLeader(0, None, 0) match {
      case None => 0
      case Some(leader) =>
        val total = a.count(_ == leader)
        def go(i: Int, seen: Int, acc: Int): Int =
          if (i == a.length-1) acc
          else {
            val incSeen = if (a(i) == leader) seen+1 else seen
            if (incSeen > (i+1)/2 && total - incSeen > (a.length-i-1)/2)
              go(i+1, incSeen, acc+1)
            else
              go(i+1, incSeen, acc)
          }
        go(0, 0, 0)
    }

  }
}
