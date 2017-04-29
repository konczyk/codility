package lessons.lesson7

object StoneWall {
  def solution(h: Array[Int]): Int = {

    def prune(stack: List[Int], level: Int): List[Int] = stack match {
      case hd::tl if hd > level => prune(tl, level)
      case _ => stack
    }

    def go(i: Int, acc: Int, stack: List[Int]): Int =
      if (i == h.length) acc
      else {
        val newStack = prune(stack, h(i))
        if (newStack.isEmpty || newStack.head < h(i))
          go(i+1, acc+1, h(i)::newStack)
        else
          go(i+1, acc, newStack)
      }

    go(0, 0, Nil)
  }
}
