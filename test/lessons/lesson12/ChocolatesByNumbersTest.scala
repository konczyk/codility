package lessons.lesson12

import org.scalatest.FunSuite

class ChocolatesByNumbersTest extends FunSuite {

  import ChocolatesByNumbers._

  test("single chocolate is always eaten") {
    assert(solution(1,1) === 1)
    assert(solution(1,4) === 1)
  }

  test("prime number of chocolates and prime to omit") {
    assert(solution(3, 11) === 3)
    assert(solution(23, 3) === 23)
  }

  test("odd number of chocolates and even to omit") {
    assert(solution(31, 8) === 31)
    assert(solution(3, 4) === 3)
  }

  test("even number of chocolates and odd to omit") {
    assert(solution(6, 11) === 6)
    assert(solution(12, 5) === 12)
  }

  test("number of chocolates and number to omit with gcd > 1") {
    assert(solution(6, 14) === 3)
    assert(solution(21, 30) === 7)
  }

  test("codility example passes") {
    assert(solution(10, 4) === 5)
  }

}
