package lessons.lesson14

object MinMaxDivision {
  def solution(k: Int, m: Int, a: Array[Int]): Int = {

    def check(i: Int, k: Int, max: Int, acc: Int): Boolean =
      if (k == 0)
        false
      else if (i == a.length)
        k > 0 && acc <= max
      else if (acc + a(i) <= max)
        check(i+1, k, max, acc+a(i))
      else
        check(i+1, k-1, max, a(i))

    def go(lo: Int, hi: Int, minMaxSum: Int): Int =
      if (lo > hi) minMaxSum
      else {
        val mid = (lo + hi) / 2
        if (check(0, k, mid, 0))
          go(lo, mid-1, mid)
        else
          go(mid+1, hi, minMaxSum)
      }

    go(a.max, a.sum, 0)
  }
}
