package lesson7

object Nesting {
  def solution(s: String): Int = {

    def go(i: Int, stack: Int): Int =
      if (i == s.length)
        if (stack == 0) 1 else 0
      else if (s.charAt(i) == '(')
        go(i+1, stack+1)
      else if (stack > 0)
        go(i+1, stack-1)
      else 0

    go(0, 0)
  }
}
