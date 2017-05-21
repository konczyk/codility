package lessons.lesson10

import org.scalacheck.Gen
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FunSuite, Matchers}

class PeaksTest extends FunSuite
  with GeneratorDrivenPropertyChecks with Matchers {

  import Peaks._

  private val MinLen = 1
  private val MaxLen = 100000
  private val MinEl = 0
  private val MaxEl = 1000000000

  test("input with same number produces 0 peaks") {
    val numsGen = for {
      len <- Gen.chooseNum(MinLen, MaxLen)
      num <- Gen.chooseNum(MinEl, MaxEl)
      gen <- Gen.containerOfN[Array, Int](len, Gen.const(num))
    } yield gen
    forAll(numsGen)(solution(_) should equal (0))
  }

  test("input with increasing numbers produces 0 peaks") {
    val numsGen = Gen.chooseNum(MinLen, MaxLen) flatMap {
      Gen.containerOfN[Array, Int](_, Gen.choose(MinEl, MaxEl)).map(_.sorted)
    }
    forAll(numsGen)(solution(_) should equal (0))
  }

  test("input with decreasing numbers produces 0 peaks") {
    val numsGen = Gen.chooseNum(MinLen, MaxLen) flatMap {
      Gen.containerOfN[Array, Int](_, Gen.choose(MinEl, MaxEl)).map(_.sortBy(- _))
    }
    forAll(numsGen)(solution(_) should equal (0))
  }

  test("1 peak can produce only 1 block") {
    assert(solution(Array(1,2,3,2,1,2)) === 1)
  }

  test("2 peaks can produce at most 2 blocks") {
    assert(solution(Array(1,3,2,4,1,2)) === 2)
  }

  test("block with different number of peaks") {
    assert(solution(Array(1,2,3,2,4,1,6,3,5,2,5,4)) === 4)
  }

  test("codility example passes") {
    assert(solution(Array(1,2,3,4,3,4,1,2,3,4,6,2)) === 3)
  }

}
