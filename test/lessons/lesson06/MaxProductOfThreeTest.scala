package lessons.lesson06

import org.junit.runner.RunWith
import org.scalacheck.Gen
import org.scalatest.junit.JUnitRunner
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FunSuite, Matchers}

@RunWith(classOf[JUnitRunner])
class MaxProductOfThreeTest extends FunSuite
  with GeneratorDrivenPropertyChecks with Matchers {

  import MaxProductOfThree._

  private val MinLen = 3
  private val MaxLen = 100000
  private val MinEl = -1000
  private val MaxEl = 1000

  test("input with the same element repeated returns el*3") {
    val numsGen = for {
      len <- Gen.chooseNum(MinLen, MaxLen)
      num <- Gen.chooseNum(MinEl, MaxEl)
      nums <- Gen.containerOfN[Array, Int](len, Gen.const(num))
    } yield nums

    forAll(numsGen)(a => solution(a) should equal (a.slice(0,3).product))
  }

  test("max when input has min elements") {
    assert(solution(Array(8,-4,2)) === -4*2*8)
  }

  test("max with positive factors") {
    assert(solution(Array(-3,-1,4,1,9,2,8)) === 4*8*9)
  }

  test("max with negative factors") {
    assert(solution(Array(-3,-1,-2,-1,-9)) === (-1)*(-1)*(-2))
  }

  test("max with two negative factors and one positive") {
    assert(solution(Array(5,2,7,-3,-6,2)) === (-3)*(-6)*7)
  }

  test("codility example passes") {
    assert(solution(Array(-3,1,2,-2,5,6)) === 60)
  }

}
