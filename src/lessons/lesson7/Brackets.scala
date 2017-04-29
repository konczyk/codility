package lessons.lesson7

object Brackets {
  def solution(s: String): Int = {

    val brackets = Map(')' -> '(', ']' -> '[', '}' -> '{')

    def go(i: Int, stack: List[Char]): Int =
      if (i == s.length)
        if (stack.isEmpty) 1
        else 0
      else if (brackets.contains(s.charAt(i)))
        if (stack.isEmpty || stack.head != brackets(s.charAt(i))) 0
        else go(i+1, stack.tail)
      else
        go(i+1, s.charAt(i)::stack)

    go(0, List())
  }
}
