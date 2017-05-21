package lessons.lesson12

import org.scalatest.FunSuite

class CommonPrimeDivisorsTest extends FunSuite {

  import CommonPrimeDivisors._

  test("1 has 1 common prime divisor with any other number") {
    assert(solution(Array(1,1), Array(1,10)) === 1)
  }

  test("different multiplicity of same divisors") {
    assert(solution(Array(4, 30, 6), Array(8, 60, 12)) === 3)
  }

  test("same prime divisors except one") {
    assert(solution(Array(30, 6), Array(15, 9)) === 0)
  }

  test("codility example passes") {
    assert(solution(Array(15,10,3), Array(75,30,5)) === 1)
  }

}
