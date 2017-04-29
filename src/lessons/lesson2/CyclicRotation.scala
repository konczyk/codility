package lessons.lesson2

object CyclicRotation {
  def solution(a: Array[Int], k: Int): Array[Int] = {

    if (a.length < 2) a
    else {
      val (left, right) = a.splitAt(a.length - k % a.length)
      right ++ left
    }

  }
}
