package lessons.lesson09

import org.scalacheck.Gen
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FunSuite, Matchers}

class MaxDoubleSliceSumTest extends FunSuite
  with GeneratorDrivenPropertyChecks with Matchers {

  import MaxDoubleSliceSum._

  private val MinLen = 3
  private val MaxLen = 100000
  private val MinEl = -10000
  private val MaxEl = 10000

  test("input with three elements produces max double slice sum equal to 0") {
    val numsGen = Gen.containerOfN[Array, Int](3, Gen.chooseNum(MinEl, MaxEl))
    forAll(numsGen)(solution(_) should equal (0))
  }

  test("input with all negative elements produces max double slice sum equal to 0") {
    val numsGen = Gen.chooseNum(MinLen+1, MaxLen) flatMap {
      Gen.containerOfN[Array, Int](_, Gen.chooseNum(MinEl, -1))
    }
    forAll(numsGen)(solution(_) should equal (0))
  }

  test("codility example passes") {
    assert(solution(Array(3,2,6,-1,4,5,-1,2)) === 17)
  }

}
