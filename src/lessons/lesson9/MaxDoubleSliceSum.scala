package lessons.lesson9

object MaxDoubleSliceSum {
  def solution(a: Array[Int]): Int = {

    val buf = Array.fill[Int](a.length)(0)
    (a.length-2 to 0 by -1).foreach {i =>
      buf(i) = math.max(0, buf(i+1) + a(i))
    }

    def go(i: Int, maxLeft: Int, maxSum: Int): Int =
      if (i == a.length-1) maxSum
      else {
        val prev = if (i > 1) a(i-1) else 0
        val left = math.max(0, maxLeft + prev)
        go(i+1, left, math.max(maxSum, left + buf(i+1)))
      }

    go(1, 0, Int.MinValue)

  }
}
