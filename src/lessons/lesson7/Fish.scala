package lessons.lesson7

object Fish {
  def solution(a: Array[Int], b: Array[Int]): Int = {

    def go(i: Int, stack: List[(Int, Int)]): Int =
      if (i == a.length) stack.size
      else {
        val fish = (a(i), b(i))
        (fish, stack) match {
          case ((size1, 0), (size2, 1)::rest) =>
            if (size1 > size2) go(i, rest)
            else go(i+1, stack)
          case _ => go(i+1, fish::stack)
        }
      }

    go(0, List())
  }
}
