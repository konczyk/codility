package lessons.lesson01

object BinaryGap {
  def solution(n: Int): Int = {

    def go(n: Int, currGap: Option[Int], maxGap: Int): Int = {
      if (n == 0) maxGap
      else (n & 1, currGap) match {
        case (0, Some(gap)) => go(n/2, Some(gap+1), maxGap)
        case (1, Some(gap)) => go(n/2, Some(0), math.max(maxGap, gap))
        case (1, None) => go(n/2, Some(0), maxGap)
        case _ => go(n/2, currGap, maxGap)
      }
    }

    go(n, None, 0)
  }
}
