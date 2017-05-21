package lessons.lesson06

import org.scalacheck.Gen
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FunSuite, Matchers}

class TriangleTest extends FunSuite
  with GeneratorDrivenPropertyChecks with Matchers {

  import Triangle._

  private val MinLen = 0
  private val MaxLen = 100000
  private val MinEl = Int.MinValue
  private val MaxEl = Int.MaxValue

  test("non positive numbers produce 0") {
    val numsGen = Gen.chooseNum(3, MaxLen) flatMap {
      Gen.containerOfN[Array, Int](_, Gen.chooseNum(MinEl, 0))
    }
    forAll(numsGen)(a => solution(a) should equal (0))
  }

  test("input with less than 3 elements produces 0") {
    val numsGen = Gen.choose(MinLen, 2) flatMap {
      Gen.containerOfN[Array, Int](_, Gen.chooseNum(MinEl, MaxEl))
    }
    forAll(numsGen)(a => solution(a) should equal (0))
  }

  test("same positive numbers produce 1") {
    val numsGen = for {
      len <- Gen.chooseNum(3, MaxLen)
      num <- Gen.chooseNum(1, MaxEl)
      gen <- Gen.containerOfN[Array, Int](len, Gen.const(num))
    } yield gen
    forAll(numsGen)(a => solution(a) should equal (1))
  }

  test("big number should not overflow") {
    assert(solution(Array(MaxEl/3*2, MaxEl/3*2, MaxEl)) === 1)
  }

  test("codility example passes") {
    assert(solution(Array(10,2,5,1,8,20)) === 1)
    assert(solution(Array(10,50,5,1)) === 0)
  }

}
