package lessons.lesson15

object CountDistinctSlices {
  def solution(m: Int, a: Array[Int]): Int = {

    def count(j: Int, buf: Set[Int], acc: Int): (Set[Int], Int) =
      if (j >= a.length || buf.contains(a(j))) (buf, acc)
      else count(j+1, buf + a(j), acc+1)

    def go(i: Int, j: Int, acc: Int, buf: Set[Int]): Int =
      if (i == a.length) acc
      else if (acc > 1000000000) 1000000000
      else {
        val (newBuf, cnt) = count(j+1, buf + a(i), 1)
        go(i+1, math.max(i+1, j+cnt-1), acc + j-i + cnt, newBuf - a(i))
      }

    go(0, 0, 0, Set())
  }
}
