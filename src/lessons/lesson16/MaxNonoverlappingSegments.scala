package lessons.lesson16

object MaxNonoverlappingSegments {
  def solution(a: Array[Int], b: Array[Int]): Int = {

    def go(i: Int, end: Int, acc: Int): Int =
      if (i == a.length) acc
      else if (a(i) > end) go(i+1, b(i), acc+1)
      else go(i+1, end, acc)

    go(0, -1, 0)
  }
}
