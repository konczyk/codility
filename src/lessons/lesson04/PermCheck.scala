package lessons.lesson04

object PermCheck {
  def solution(a: Array[Int]): Int = {
    val buf = a.toSet
    if (buf.size == a.length && buf.max == a.length) 1
    else 0
  }
}
