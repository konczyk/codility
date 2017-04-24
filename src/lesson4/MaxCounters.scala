package lesson4

object MaxCounters {
  def solution(n: Int, a: Array[Int]): Array[Int] = {

    val counters = Array.fill[Int](n)(0)

    def go(i: Int, base: Int, max: Int): Array[Int] =
      if (i == a.length) counters.map(math.max(_, base))
      else if (a(i) > n) go(i+1, max, max)
      else {
        val k = a(i)-1
        counters(k) = math.max(base, counters(k)) + 1
        go(i+1, base, math.max(max, counters(k)))
      }

    go(0, 0, 0)
  }
}
