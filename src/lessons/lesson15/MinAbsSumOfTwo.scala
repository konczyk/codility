package lessons.lesson15

object MinAbsSumOfTwo {
  def solution(a: Array[Int]): Int = {
    scala.util.Sorting.quickSort(a)

    def go(i: Int, j: Int, min: Int): Int = {
      val newMin = math.abs(a(i) + a(j))
      if (i == j)
        math.min(min, newMin)
      else if (math.abs(a(i+1) + a(j)) < math.abs(a(i) + a(j-1)))
        go(i+1, j, math.min(min, newMin))
      else
        go(i, j-1, math.min(min, newMin))
    }

    go(0, a.length-1, Integer.MAX_VALUE)
  }
}
