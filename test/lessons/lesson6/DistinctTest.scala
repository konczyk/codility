package lessons.lesson6

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class DistinctTest extends FunSuite {

  import lessons.lesson6.Distinct._

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
