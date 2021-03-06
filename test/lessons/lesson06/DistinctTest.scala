package lessons.lesson06

import org.scalatest.FunSuite

class DistinctTest extends FunSuite {

  import lessons.lesson06.Distinct._

  test("empty input has no distinct element") {
    assert(solution(Array()) === 0)
  }

  test("input with the same element repeated has 1 distinct element") {
    assert(solution(Array(2,2,2,2)) === 1)
  }

  test("codility example passes") {
    assert(solution(Array(2,1,1,2,3,1)) === 3)
  }

}
