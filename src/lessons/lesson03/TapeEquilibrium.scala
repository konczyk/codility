package lessons.lesson03

object TapeEquilibrium {
  def solution(a: Array[Int]): Int = {
    val total = a.sum
    def go(i: Int, acc: Int, minDiff: Int): Int =
      if (i == a.length) minDiff
      else {
        val diff = math.abs(acc - (total - acc))
        go(i+1, acc+a(i), math.min(minDiff, diff))
      }

    go(1, a(0), Int.MaxValue)
  }
}
