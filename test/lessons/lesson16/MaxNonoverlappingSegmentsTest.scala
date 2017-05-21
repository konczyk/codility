package lessons.lesson16

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class MaxNonoverlappingSegmentsTest extends FunSuite {

  import MaxNonoverlappingSegments._

  test("empty input produces 0 segments") {
    assert(solution(Array(), Array()) === 0)
  }

  test("one element input produces 1 segment") {
    assert(solution(Array(1), Array(2)) === 1)
  }

  test("all elements overlapping produce 1 segment") {
    assert(solution(Array(1,2,3), Array(3,4,5)) === 1)
  }

  test("all elements non overlapping produce n segment") {
    assert(solution(Array(1,2,3), Array(1,2,3)) === 3)
  }

  test("codility example passes") {
    assert(solution(Array(1,3,7,9,9), Array(5,6,8,9,10)) === 3)
  }

}
