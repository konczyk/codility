package lessons.lesson15

object AbsDistinct {
  def solution(a: Array[Int]): Int = {
    a.foldLeft(Set.empty[Int])((acc, e) => acc + math.abs(e)).size
  }
}
