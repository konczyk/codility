package lessons.lesson11

import org.scalatest.FunSuite

class CountNonDivisibleTest extends FunSuite {

  import CountNonDivisible._

  test("single element input has no non-divisors") {
    assert(solution(Array(1)) === Array(0))
  }

  test("input with same elements produces 0 non-divisors") {
    assert(solution(Array(3,3,3)) === Array(0,0,0))
  }

  test("input with primes") {
    assert(solution(Array(2,3,2,3)) === Array(2,2,2,2))
  }

  test("codility example passes") {
    assert(solution(Array(3,1,2,3,6)) === Array(2,4,3,2,0))
  }

}
