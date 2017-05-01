package lessons.lesson10

object CountFactors {
  def solution(n: Int): Int = {

    def go(i: Long, acc: Int): Int =
      if (i * i > n) acc
      else if (i * i == n) acc+1
      else if (n % i == 0) go(i+1, acc+2)
      else go(i+1, acc)

    go(1, 0)
  }
}
