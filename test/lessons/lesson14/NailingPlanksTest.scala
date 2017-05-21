package lessons.lesson14

import org.scalatest.FunSuite

class NailingPlanksTest extends FunSuite {

  import NailingPlanks._

  test("not possible to nail all the plans") {
    assert(solution(Array(1), Array(1), Array(2)) === -1)
  }

  test("first nail to nail all two plans") {
    assert(solution(Array(1,2), Array(2,3), Array(2,3,4)) === 1)
  }

  test("first two nails to nail all four planks") {
    assert(solution(Array(1,3,5,2), Array(3,5,6,6), Array(3,5,6)) === 2)
  }

  test("codility example passes") {
    assert(solution(Array(1,4,5,8), Array(4,5,9,10), Array(4,6,7,10,2)) === 4)
  }

}
