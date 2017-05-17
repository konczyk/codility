package lessons.lesson15

object CountTriangles {
  def solution(a: Array[Int]): Int = {
    scala.util.Sorting.quickSort(a)

    def countTriples(x: Int, y: Int, z: Int, acc: Int): Int =
      if (y == a.length-1) acc
      else {
        val cnt = (z until a.length).takeWhile(a(x) + a(y) > a(_)).length
        countTriples(x, y+1, z+cnt, acc + (z-y-1) + cnt)
      }

    def go(x: Int, acc: Int): Int =
      if (x >= a.length-2) acc
      else go(x+1, acc + countTriples(x, x+1, x+2, 0))

    go(0, 0)
  }
}
