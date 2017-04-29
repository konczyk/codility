package lessons.lesson5

import org.junit.runner.RunWith
import org.scalacheck.Gen
import org.scalatest.junit.JUnitRunner
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FunSuite, Matchers}

@RunWith(classOf[JUnitRunner])
class CountDivTest extends FunSuite
  with GeneratorDrivenPropertyChecks with Matchers {

  import lessons.lesson5.CountDiv._

  private val MinNum = 0
  private val MaxNum = 2000000000
  private val MinDiv = 1
  private val MaxDiv = 2000000000

  test("zeros are divisible by any k") {
    forAll(Gen.chooseNum(MinDiv, MaxDiv))(solution (0,0,_) should equal (1))
  }

  test("both endpoints divisible by k") {
    assert(solution(6, 15, 3) === 4)
  }

  test("only start divisible by k") {
    assert(solution(6, 14, 3) === 3)
  }

  test("only end divisible by k") {
    assert(solution(7, 15, 3) === 3)
  }

  test("no end divisible by k") {
    assert(solution(5, 16, 3) === 4)
  }

  test("no items in range divisible by k") {
    assert(solution(7, 16, 17) === 0)
  }

  test("max range with max divisor") {
    assert(solution(MinNum, MaxNum, MaxDiv) === 2)
  }

  test("max range with min divisor") {
    assert(solution(MinNum, MaxNum, MinDiv) === MaxNum+1)
  }

  test("codility example passes") {
    assert(solution(6,11,2) === 3)
  }

}

