package lessons.lesson13

object FibFrog {
  def solution(a: Array[Int]): Int = {

    val buf = Array.fill[Int](a.length+1)(-1)
    lazy val jumps = fibs(0, 1, Nil)

    def fibs(prev: Int, curr: Int, acc: List[Int]): List[Int] =
      if (prev + curr > 100000) acc
      else fibs(curr, prev+curr, curr :: acc)

    def validJumps(pos: Int): List[Int] =
      jumps.filter(jump => jump == pos+1 || (jump < pos+1 && buf(pos-jump) > -1))

    def markPosition(pos: Int)(jump: Int) = {
      val prevLeaf = pos - jump
      buf(pos) =
        if (prevLeaf == -1) 1
        else if (buf(pos) == -1) buf(prevLeaf) + 1
        else math.min(buf(pos), buf(prevLeaf) + 1)
    }

    def go(i: Int): Int =
      if (i == buf.length) buf.last
      else {
        if (i == a.length || a(i) == 1)
          validJumps(i).foreach(markPosition(i))
        go(i+1)
      }

    go(0)

  }
}
