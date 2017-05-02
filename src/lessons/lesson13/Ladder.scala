package lessons.lesson13

object Ladder {
  def solution(a: Array[Int], b: Array[Int]): Array[Int] = {

    lazy val fibs: Stream[Int] =
      0 #:: 1 #:: fibs.zip(fibs.tail).map(n => (n._1 + n._2) % (1 << 30))

    val maxRungs = a.max
    val buf = Array.fill[Int](maxRungs+1)(0)
    fibs.slice(1, maxRungs+2).zipWithIndex.foreach {
      case (fib, i) => buf(i) = fib
    }

    val acc = Array.fill[Int](a.length)(0)
    def go(i: Int): Array[Int] =
      if (i == a.length) acc
      else {
        acc(i) = buf(a(i)) % (1 << b(i))
        go(i+1)
      }

    go(0)

  }
}
