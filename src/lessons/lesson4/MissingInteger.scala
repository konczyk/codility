package lessons.lesson4

object MissingInteger {
  def solution(a: Array[Int]): Int = {
    val buf = a.filter(_ > 0).toSet
    (1 to Int.MaxValue).toStream.filter(!buf.contains(_)).head
  }
}
