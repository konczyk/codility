package lessons.lesson16

import org.scalatest.FunSuite

class TieRopesTest extends FunSuite {

  import TieRopes._

  test("single rope shorter than k produces 0 tied ropes") {
    assert(solution(4, Array(1)) === 0)
  }

  test("single rope longer or equal to k produces 1 tied rope") {
    assert(solution(4, Array(4)) === 1)
  }

  test("codility example passes") {
    assert(solution(4, Array(1,2,3,4,1,1,3)) === 3)
  }

}
