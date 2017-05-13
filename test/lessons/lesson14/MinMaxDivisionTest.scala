package lessons.lesson14

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class MinMaxDivisionTest extends FunSuite {

  import MinMaxDivision._

  test("input array with a single value produces that value") {
    assert(solution(2,5,Array(3)) === 3)
  }

  test("input array with zeros produces a zero") {
    assert(solution(3,3,Array(0,0,0,0)) === 0)
  }

  test("all elements the same and > 0") {
    assert(solution(3,6,Array(6,6,6,6,6,6,6)) === 18)
  }

  test("single element as the minimal large sum") {
    assert(solution(3,3,Array(3,0,1,1,0)) === 3)
  }

  test("codility example passes") {
    assert(solution(3,5,Array(2,1,5,1,2,2,2)) === 6)
  }

}
