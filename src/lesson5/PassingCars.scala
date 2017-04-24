package lesson5

object PassingCars {
  def solution(a: Array[Int]): Int = {

    val limit = 1000000000

    def go(i: Int, east: Int, acc: Int): Int =
      if (i == a.length) acc
      else if (a(i) == 0) go(i+1, east+1, acc)
      else if (a(i) == 1 && acc + east > limit) -1
      else go(i+1, east, acc+east)

    go(0, 0, 0)
  }
}
