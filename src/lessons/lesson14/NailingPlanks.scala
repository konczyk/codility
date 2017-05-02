package lessons.lesson14

object NailingPlanks {
  def solution(a: Array[Int], b: Array[Int], c: Array[Int]): Int = {

    def allNailed(nails: Int): Boolean = {
      val buf = Array.fill[Int](c.length*2 + 1)(0)
      c.take(nails).foreach(buf(_) = 1)
      buf.indices.drop(1).foreach(i => buf(i) = buf(i) + buf(i-1))

      a.indices.forall(k => buf(b(k)) - buf(a(k)-1) > 0)
    }

    def go(from: Int, to: Int, minNails: Int): Int =
      if (from > to) minNails
      else {
        val mid = (from + to) / 2
        if (allNailed(mid))
          go(from, mid-1, mid)
        else
          go(mid+1, to, minNails)
      }

    go(1, c.length, -1)
  }
}
