package lessons.lesson05

object CountDiv {
  def solution(a: Int, b: Int, k: Int): Int = {
    if (a % k == 0) b/k - a/k + 1
    else b/k - a/k
  }
}
