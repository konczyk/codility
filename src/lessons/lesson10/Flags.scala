package lessons.lesson10

object Flags {
  def solution(a: Array[Int]): Int = {

    val (peaks, peaksNum) = (a.length-2 to 1 by -1).foldLeft((List.empty[Int], 0)) {
      case ((acc, cnt), i) =>
        if (a(i) > a(i-1) && a(i) > a(i+1)) (i::acc, cnt+1)
        else (acc, cnt)
    }

    def putFlags(dist: Int, peaks: List[Int], prev: Option[Int], used: Int): Int =
      (peaks, prev) match {
        case (peak::rest, None) =>
          putFlags(dist, rest, Some(peak), used+1)
        case (peak::rest, Some(prevFlag)) if peak - prevFlag >= dist && used < dist =>
          putFlags(dist, rest, Some(peak), used+1)
        case (_::rest, Some(_)) if used < dist =>
          putFlags(dist, rest, prev, used)
        case _ => used
      }

    def go(i: Int, maxFlags: Int): Int =
      if (i > peaksNum || (i-1)*(i-1) > a.length) maxFlags
      else go(i+1, math.max(maxFlags, putFlags(i, peaks, None, 0)))

    go(1, 0)

  }
}
