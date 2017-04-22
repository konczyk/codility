package lesson3

object PermMissingElem {
  def solution(a: Array[Int]): Int = {
    val len = a.length.toLong
    val expectedSum = ((len+1) * (len+2)) / 2
    (expectedSum - a.sum).toInt
  }
}
