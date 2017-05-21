package lessons.lesson11

import org.scalatest.FunSuite

class CountSemiprimesTest extends FunSuite {

  import CountSemiprimes._

  test("no semiprimes for 1-3 ranges") {
    assert(solution(10, Array(1,1), Array(1,3)) === Array(0,0))
  }

  test("range with the same start and end produces at most 1 semiprime") {
    assert(solution(10, Array(7,6), Array(8,6)) === Array(0,1))
  }

  test("range with the primes only produces 2 semiprimes") {
    assert(solution(10, Array(2,9), Array(3,9)) === Array(0,1))
  }

  test("codility example passes") {
    assert(solution(26, Array(1,4,16), Array(26,10,20)) === Array(10,4,0))
  }

}
