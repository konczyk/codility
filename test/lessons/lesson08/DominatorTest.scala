package lessons.lesson08

import org.scalacheck.Gen
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FunSuite, Matchers}

class DominatorTest extends FunSuite
  with GeneratorDrivenPropertyChecks with Matchers {

  import Dominator._

  private val MinLen = 0
  private val MaxLen = 100000
  private val MinEl = Int.MinValue
  private val MaxEl = Int.MaxValue

  test("input with same number repeated produces dominator equal to the number") {
    val numsGen = for {
      len <- Gen.chooseNum(MinLen+1, MaxLen)
      num <- Gen.chooseNum(MinEl, MaxEl)
      gen <- Gen.containerOfN[Array, Int](len, Gen.const(num))
    } yield gen
    forAll(numsGen)(solution(_) should equal (0))
  }

  test("empty input has no dominator") {
    assert(solution(Array()) === -1)
  }

  test("two distinct number produce no dominator") {
    assert(solution(Array(4,3)) === -1)
  }

  test("three numbers with one repeated have dominator equal to the repeated number") {
    assert(solution(Array(4,4,3)) === 0)
  }

  test("codility example passes") {
    assert(solution(Array(3,4,3,2,3,-1,3,3)) === 0)
  }

}
