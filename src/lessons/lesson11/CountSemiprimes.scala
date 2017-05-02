package lessons.lesson11

object CountSemiprimes {
  def solution(n: Int, p: Array[Int], q: Array[Int]): Array[Int] = {

    val buf = Array.fill[Int](n+1)(0)
    (2 to math.sqrt(n).toInt).foreach { i =>
      if (buf(i) == 0)
        (i*i to n by i).withFilter(buf(_) == 0).foreach(buf(_) = i)
    }

    val semiprimes = Array.fill[Int](n+1)(0)
    (2 to n).foreach { i =>
      if (buf(i) != 0 && buf(i/buf(i)) == 0) semiprimes(i) = semiprimes(i-1) + 1
      else semiprimes(i) = semiprimes(i-1)
    }

    val counts = Array.fill[Int](p.length)(0)
    def go(i: Int): Array[Int] =
      if (i == p.length) counts
      else {
        counts(i) = semiprimes(q(i)) - semiprimes(p(i)-1)
        go(i+1)
      }

    go(0)

  }
}
