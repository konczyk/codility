package challenges.challenge2011

object CountPalindromicSlices {
  def solution(s: String): Int = {

    val limit = 100000000
    val str = '^' + s.flatMap(ch => Array('|',ch)) + "|$"
    val size = Array.fill[Int](str.length)(0)

    def expand(i: Int, len: Int): Int =
      if (str(i-len-1) == str(i+len+1)) expand(i, len+1)
      else len

    def count(): Int = {
      def loop(i: Int, acc: Int): Int =
        if (acc > limit) -1
        else if (i == size.length) acc
        else loop(i+1, acc + size(i)/2)
      loop(0, 0)
    }

    def go(i: Int, center: Int, right: Int): Int =
      if (i == str.length-1) count()
      else {
        if (right > i) size(i) = math.min(right-i, size(2*center-i))

        size(i) = expand(i, size(i))

        if (i + size(i) > right)
          go(i+1, i, i+size(i))
        else
          go(i+1, center, right)
      }

    go(1, 0, 0)
  }
}
