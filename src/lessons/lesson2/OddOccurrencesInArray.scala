package lessons.lesson2

object OddOccurrencesInArray {
  def solution(a: Array[Int]): Int = {

    def go(i: Int, result: Int): Int =
      if (i == a.length) result
      else go(i+1, result ^ a(i))

    go(1, a(0))
  }
}
