package lessons.lesson9

object MaxProfit {
  def solution(a: Array[Int]): Int = {

    def go(i: Int, minPrice: Int, maxProfit: Int): Int =
      if (i == a.length) maxProfit
      else go(i+1, math.min(minPrice, a(i)), math.max(maxProfit, a(i) - minPrice))

    go(0, Int.MaxValue, 0)

  }
}
