package lessons.lesson10

object Peaks {
  def solution(a: Array[Int]): Int = {

    val buf = Array.fill[Int](a.length)(0)
    (1 until a.length).foreach { i =>
      if (i < a.length-1 && a(i) > a(i-1) && a(i) > a(i+1)) buf(i) = buf(i-1) + 1
      else buf(i) = buf(i-1)
    }

    def blocksHavePeaks(blockSize: Int): Boolean =
      (blockSize-1 until a.length by blockSize).forall { i =>
        val prevBlock = if (i < blockSize) 0 else buf(i-blockSize)
        buf(i) - prevBlock > 0
      }

    def go(i: Int): Int =
      if (i > a.length) 0
      else if (a.length % i == 0 && blocksHavePeaks(i)) a.length/i
      else go(i+1)

    go(1)

  }
}
