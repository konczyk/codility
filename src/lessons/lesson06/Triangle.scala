package lessons.lesson06

object Triangle {
  def solution(a: Array[Int]): Int = {
    scala.util.Sorting.quickSort(a)

    def go(i: Int): Int =
      if (i-2 < 0 || a(i-2) < 0) 0
      else if (a(i-2).toLong + a(i-1) > a(i)) 1
      else go(i-1)

    go(a.length-1)
  }

}

