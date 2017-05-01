package lessons.lesson05

object GenomicRangeQuery {

  def solution(s: String, p: Array[Int], q: Array[Int]): Array[Int] = {

    val factors = Map('A' -> 1, 'C' -> 2, 'G' -> 3, 'T' -> 4)
    val buf = Map(
      'A' -> Array.fill[Int](s.length)(0),
      'C' -> Array.fill[Int](s.length)(0),
      'G' -> Array.fill[Int](s.length)(0),
      'T' -> Array.fill[Int](s.length)(0))

    s.indices.foreach {
      i => factors.keys.foreach {
        ch =>
          if (i > 0) buf(ch)(i) = buf(ch)(i-1)
          if (ch == s.charAt(i)) buf(ch)(i) += 1
      }
    }

    def responsive(ch: Char, from: Int, to: Int) =
      if (from == 0) buf(ch)(to) > 0
      else buf(ch)(to) > buf(ch)(from-1)

    val response = Array.fill[Int](p.length)(0)
    def go(i: Int): Array[Int] =
      if (i == p.length) response
      else {
        val query = factors.keys.filter(responsive(_, p(i), q(i)))
        response(i) = query.map(factors).min
        go(i+1)
      }

    go(0)
  }
}
