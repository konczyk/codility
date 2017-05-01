package lessons.lesson04

object FrogRiverOne {
  def solution(x: Int, a: Array[Int]): Int = {

    def go(i: Int, leaves: Int, acc: Set[Int]): Int =
      if (i == a.length) -1
      else if (acc.contains(a(i))) go(i+1, leaves, acc)
      else if (leaves < x-1) go(i+1, leaves+1, acc + a(i))
      else i

    go(0, 0, Set())
  }
}
