package lessons.lesson13

import org.scalatest.FunSuite

class LadderTest extends FunSuite {

  import Ladder._

  test("1 rung has only one combination of steps") {
    assert(solution(Array(1, 1), Array(1, 10)) === Array(1, 1))
  }

  test("2 rungs have 2 combinations of steps") {
    assert(solution(Array(2, 2), Array(2, 30)) === Array(2, 2))
  }

  test("codility example passes") {
    assert(solution(Array(4,4,5,5,1), Array(3,2,4,3,1)) === Array(5,1,8,0,1))
  }

}
