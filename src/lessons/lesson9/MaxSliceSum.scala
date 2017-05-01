package lessons.lesson9

object MaxSliceSum {
  def solution(a: Array[Int]): Int = {

    def go(i: Int, maxEnd: Int, maxSum: Int): Int =
      if (i == a.length) maxSum
      else {
        val endSum = math.max(0, maxEnd) + a(i)
        go(i+1, endSum, math.max(maxSum, endSum))
      }

    go(0, Int.MinValue, Int.MinValue)

  }
}
