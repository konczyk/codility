package lessons.lesson15

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class CountDistinctSlicesTest extends FunSuite {

  import CountDistinctSlices._

  test("single element input produces one slice") {
    assert(solution(1, Array(1)) === 1)
  }

  test("equal elements input produces number of slices equal to the number of elements") {
    assert(solution(3, Array(2,2,2)) === 3)
  }

  test("codility example passes") {
    assert(solution(6, Array(3,4,5,5,2)) === 9)
  }

}
