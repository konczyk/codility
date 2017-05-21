package lessons.lesson08

import org.scalacheck.Gen
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FunSuite, Matchers}

class EquiLeaderTest extends FunSuite
  with GeneratorDrivenPropertyChecks with Matchers {

  import EquiLeader._

  private val MinLen = 1
  private val MaxLen = 100000
  private val MinEl = -1000000000
  private val MaxEl = 1000000000

  test("single element input produces 0 equileaders") {
    val numsGen = Gen.containerOfN[Array, Int](1, Gen.chooseNum(MinEl, MaxEl))
    forAll(numsGen)(solution(_) should equal (0))
  }

  test("input with same numbers produces n-1 equileaders") {
    val numsGen = for {
      len <- Gen.chooseNum(MinLen, MaxLen)
      num <- Gen.chooseNum(MinEl, MaxEl)
      gen <- Gen.containerOfN[Array, Int](len, Gen.const(num))
    } yield gen
    forAll(numsGen)(a => solution(a) should equal (a.length-1))
  }

  test("distinct numbers produce no equileaders") {
    assert(solution(Array(4,3)) === 0)
    assert(solution(Array(4,3,2)) === 0)
  }

  test("three numbers with 2 same produce no equileader") {
    assert(solution(Array(4,3,4)) === 0)
  }

  test("four numbers with 3 same produce at least one equileader") {
    assert(solution(Array(4,4,4,3)) === 1)
    assert(solution(Array(4,3,4,4)) === 2)
  }

  test("codility example passes") {
    assert(solution(Array(4,3,4,4,4,2)) === 2)
  }

}
