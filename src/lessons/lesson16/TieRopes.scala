package lessons.lesson16

object TieRopes {
  def solution(k: Int, a: Array[Int]): Int = {

    def go(i: Int, tied: Int, acc: Int): Int =
      if (i == a.length) acc + (if (tied >= k) 1 else 0)
      else if (tied >= k) go(i+1, a(i), acc+1)
      else go(i+1, tied+a(i), acc)

    go(0, 0, 0)
  }
}
