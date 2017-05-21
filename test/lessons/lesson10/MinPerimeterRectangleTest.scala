package lessons.lesson10

import org.scalatest.FunSuite

class MinPerimeterRectangleTest extends FunSuite {

  import MinPerimeterRectangle._

  test("rectangle with area 1 has min perimeter of 4") {
    assert(solution(1) === 4)
  }

  test("rectangle with area 2 has min perimeter of 6") {
    assert(solution(2) === 6)
  }

  test("rectangle with area 4 has min perimeter of 8") {
    assert(solution(4) === 8)
  }

  test("codility example passes") {
    assert(solution(30) === 22)
  }

}
