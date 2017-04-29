package lessons.lesson6

object MaxProductOfThree {
  def solution(a: Array[Int]): Int = {
    scala.util.Sorting.quickSort(a)
    val pos = a.takeRight(3).product
    val neg = a(0) * a(1) * a.last
    math.max(neg, pos)
  }
}
