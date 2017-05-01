package lessons.lesson06

object NumberOfDiscIntersections {
  def solution(a: Array[Int]): Int = {
    val heads = a.zipWithIndex.map{case(r,i) => i.toLong - r}.sorted
    val tails = a.zipWithIndex.map{case(r,i) => i.toLong + r}.sorted

    def sweep(i: Int, bound: Long, acc: Int): Int =
      if (i == heads.length || heads(i) > bound) acc
      else sweep(i+1, bound, acc+1)

    def go(t: Int, h: Int, prev: Int, acc: Long): Int =
      if (acc > 10000000) -1
      else if (t == tails.length) acc.toInt
      else {
        val discs = sweep(h, tails(t), 0)
        val change = prev + discs - 1
        go(t+1, h+discs, change, acc+change)
      }

    go(0, 0, 0, 0)
  }
}
