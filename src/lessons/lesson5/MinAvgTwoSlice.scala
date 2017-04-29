package lessons.lesson5

object MinAvgTwoSlice {
  def solution(a: Array[Int]): Int = {

    def avg(slice: Array[Int]): Double = {
      val avg2 = slice.slice(0,2).sum/2d
      val avg3 = if (slice.length == 2) avg2 else slice.sum/3d
      math.min(avg2, avg3)
    }

    def go(i: Int, minIdx: Int, minAvg: Double): Int =
      if (i == a.length-1) minIdx
      else {
        val newAvg = avg(a.slice(i, i+3))
        if (newAvg < minAvg) go(i+1, i, newAvg)
        else go(i+1, minIdx, minAvg)
      }

    go(0, 0, Double.MaxValue)
  }
}
