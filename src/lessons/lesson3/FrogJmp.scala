package lessons.lesson3

object FrogJmp {
  def solution(x: Int, y: Int, d: Int): Int = {
    (y-x + (d-1)) / d
  }
}
