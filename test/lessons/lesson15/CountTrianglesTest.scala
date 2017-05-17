package lessons.lesson15

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class CountTrianglesTest extends FunSuite {

  import CountTriangles._

  test("empty input produces 0 triangles") {
    assert(solution(Array()) === 0)
  }

  test("input with one element produces 0 triangles") {
    assert(solution(Array(1)) === 0)
  }

  test("input with two elements produces 0 triangles") {
    assert(solution(Array(1,3)) === 0)
  }

  test("input with three elements might produce a triangle") {
    assert(solution(Array(5,3,4)) === 1)
  }

  test("too long sticks produce 0 triangles") {
    assert(solution(Array(7,9,1,2)) === 0)
  }

  test("codility example passes") {
    assert(solution(Array(10,2,5,1,8,12)) === 4)
  }

}
