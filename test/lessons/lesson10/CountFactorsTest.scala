package lessons.lesson10

import org.scalatest.FunSuite

class CountFactorsTest extends FunSuite {

  import CountFactors._

  test("1 has 1 factor") {
    assert(solution(1) === 1)
  }

  test("max int has 2 factors - 1 and itself") {
    assert(solution(Int.MaxValue) === 2)
  }

  test("16 has 5 factors") {
    assert(solution(16) === 5)
  }

  test("codility example passes") {
    assert(solution(24) === 8)
  }

}
