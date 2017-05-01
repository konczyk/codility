package lessons.lesson10

object MinPerimeterRectangle {
  def solution(n: Int): Int = {

    def go(i: Int, minPerimeter: Int): Int =
      if (i * i > n) minPerimeter
      else if (i * i == n) math.min(minPerimeter, 4*i)
      else if (n % i == 0) go(i+1, math.min(minPerimeter, 2*i + 2*n/i))
      else go(i+1, minPerimeter)

    go(1, Int.MaxValue)
  }
}
