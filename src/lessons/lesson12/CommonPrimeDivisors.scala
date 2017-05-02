package lessons.lesson12

object CommonPrimeDivisors {
  def solution(a: Array[Int], b: Array[Int]): Int = {

    def gcd(n: Int, m: Int): Int =
      if (n % m == 0) m
      else gcd(m, n % m)

    def factorizes(n: Int, factors: Int): Boolean = {
      val g = gcd(n, factors)
      if (g == n) true
      else if (g == 1) false
      else factorizes(n/g, factors)
    }

    def go(i: Int, acc: Int): Int =
      if (i == a.length) acc
      else {
        val g = gcd(a(i), b(i))
        val (n, m) = (a(i)/g, b(i)/g)
        if (factorizes(n, g) && factorizes(m, g)) go(i+1, acc+1)
        else go(i+1, acc)
      }

    go(0, 0)
  }
}
