package lessons.lesson11

object CountNonDivisible {
  def solution(a: Array[Int]): Array[Int] = {

    val buf = Array.fill[Int](a.length*2 + 1)(0)
    a.foreach(buf(_) += 1)

    def divisors(num: Int): Int = (1 to math.sqrt(num).toInt).foldLeft(0) {
      case (acc, i) =>
        if (i*i == num) acc + buf(i)
        else if (num % i == 0) acc + buf(i) + buf(num/i)
        else acc
    }

    val counts = Array.fill[Int](a.length)(0)
    def go(i: Int): Array[Int] =
      if (i == a.length) counts
      else {
        counts(i) = a.length - divisors(a(i))
        go(i+1)
      }

    go(0)

  }
}
