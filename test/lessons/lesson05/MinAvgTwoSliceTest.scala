package lessons.lesson05

import org.scalacheck.Gen
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FunSuite, Matchers}

class MinAvgTwoSliceTest extends FunSuite
  with GeneratorDrivenPropertyChecks with Matchers {

  import lessons.lesson05.MinAvgTwoSlice._

  private val MinLen = 2
  private val MaxLen = 1000000
  private val MinEl = -10000
  private val MaxEl = 10000

  test("arrays of the same element has min avg at the first element") {
    val inputGen = for {
      len <- Gen.chooseNum(MinLen, MaxLen)
      el <- Gen.choose(MinEl, MaxEl)
      input <- Gen.containerOfN[Array, Int](len, Gen.const(el))
    } yield input

    forAll(inputGen, minSuccessful(25))(a => solution(a) should equal (0))
  }

  test("minimal average over a 2-element slice") {
    assert(solution(Array(5,4,0,2,4)) === 2)
  }

  test("minimal average over a 3-element slice") {
    assert(solution(Array(4,1,2,0,2)) === 1)
  }

  test("minimal average over a 2-element repeated slice") {
    assert(solution(Array(5,4,0,2,8,5,0,2)) === 2)
  }

  test("minimal average at the end of the input array") {
    assert(solution(Array(5,4,0,2,8,5,-1,2)) === 6)
  }

  test("codility example passes") {
    assert(solution(Array(4,2,2,5,1,5,8)) === 1)
  }

}
