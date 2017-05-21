package lessons.lesson15

import org.scalatest.FunSuite

class MinAbsSumTwoTest extends FunSuite {

  import MinAbsSumOfTwo._

  test("input with one element produces abs of its value sum") {
    assert(solution(Array(-5)) === 10)
  }

  test("input with only positive numbers produces sum of the smallest one") {
    assert(solution(Array(1,3,4,2,9)) === 2)
  }

  test("input with only negative numbers produces sum of the largest one") {
    assert(solution(Array(-1,-3,-4,-2,-9)) === 2)
  }

  test("codility example passes") {
    assert(solution(Array(1,4,-3)) === 1)
    assert(solution(Array(-8,4,5,-10,3)) === 3)
  }

}
