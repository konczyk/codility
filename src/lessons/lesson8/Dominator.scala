package lessons.lesson8

object Dominator {
  def solution(a: Array[Int]): Int = {

    def go(i: Int, dominator: Option[Int], stack: Int): Int =
      (dominator, a.lift(i), stack) match {
        case (Some(num), None, _) if a.count(_ == num) > a.length/2 =>
          a.indices.dropWhile(a(_) != num).head
        case (_, None, _ ) => -1
        case (None, _, _) => go(i+1, Some(a(i)), stack+1)
        case (Some(num), _, len) if num != a(i) =>
          if (len > 1) go(i+1, dominator, stack-1)
          else go(i+1, None, 0)
        case _ => go(i+1, dominator, stack+1)
      }

    go(0, None, 0)
  }
}
