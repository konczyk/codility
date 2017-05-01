package lessons.lesson9

import org.junit.runner.RunWith
import org.scalacheck.Gen
import org.scalatest.junit.JUnitRunner
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FunSuite, Matchers}

@RunWith(classOf[JUnitRunner])
class MaxSliceSumTest extends FunSuite
  with GeneratorDrivenPropertyChecks with Matchers {

  import MaxSliceSum._

  private val MinLen = 1
  private val MaxLen = 1000000
  private val MinEl = -1000000
  private val MaxEl = 1000000

  test("single element input produces sum equal to the element") {
    val numsGen = Gen.containerOfN[Array, Int](1, Gen.chooseNum(MinEl, MaxEl))
    forAll(numsGen)(a => solution(a) should equal (a.head))
  }

  test("input with negative numbers produces max sum equal to the largest one") {
    val numsGen = Gen.chooseNum(MinLen+1, MaxLen) flatMap {
      Gen.containerOfN[Array, Int](_, Gen.chooseNum(MinEl, -1))
    }
    forAll(numsGen)(a => solution(a) should equal (a.max))
  }

  test("input with non-negative numbers produces max sum equal to the sum of all elements") {
    val numsGen = Gen.chooseNum(MinLen+1, MaxLen/1000) flatMap {
      Gen.containerOfN[Array, Int](_, Gen.chooseNum(1, MaxEl))
    }
    forAll(numsGen)(a => solution(a) should equal (a.sum))
  }

  test("max slice sum for mixed input") {
    assert(solution(Array(0,-1,3,4,-9,7,8,-5,4)) === 15)
  }

  test("codility example passes") {
    assert(solution(Array(3,2,-6,4,0)) === 5)
  }

}
