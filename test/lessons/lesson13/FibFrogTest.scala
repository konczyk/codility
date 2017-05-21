package lessons.lesson13

import org.scalatest.FunSuite

class FibFrogTest extends FunSuite {

  import FibFrog._

  test("river of width 0 takes 1 jump") {
    assert(solution(Array()) === 1)
  }

  test("river of width 1 takes 1 jump") {
    assert(solution(Array(0)) === 1)
    assert(solution(Array(1)) === 1)
  }

  test("river of width 2 takes 1 jump") {
    assert(solution(Array(0,0)) === 1)
    assert(solution(Array(1,1)) === 1)
  }

  test("river of width 3 takes at least 2 jumps if leaves are in") {
    assert(solution(Array(0,1,0)) === 2)
    assert(solution(Array(1,0,1)) === 2)
  }

  test("river of width 3 takes with no leaves cannot be crossed") {
    assert(solution(Array(0,0,0)) === -1)
  }

  test("codility example passes") {
    assert(solution(Array(0,0,0,1,1,0,1,0,0,0,0)) === 3)
  }

}
