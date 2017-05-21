package lessons.lesson06

import org.scalacheck.Gen
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FunSuite, Matchers}

class NumberOfDiscIntersectionsTest extends FunSuite
  with GeneratorDrivenPropertyChecks with Matchers {

  import NumberOfDiscIntersections._

  private val MaxLen = 100000
  private val MinEl = 0
  private val MaxEl = Int.MaxValue

  test("single disc produces no intersections") {
    val discsGen = Gen.containerOfN[Array, Int](1, Gen.chooseNum(MinEl, MaxEl))
    forAll(discsGen)(solution(_) should equal (0))
  }

  test("point-size discs produce no intersections") {
    val discsGen = Gen.chooseNum(1, MaxLen) flatMap {
      Gen.containerOfN[Array, Int](_, Gen.const(0))
    }
    forAll(discsGen)(solution(_) should equal (0))
  }

  test("no discs produce no intersections") {
    assert(solution(Array()) === 0)
  }

  test("discs too far away produce no intersections") {
    assert(solution(Array(1,0,0,1)) === 2)
  }

  test("two discs starting in the same place produce and intersection") {
    assert(solution(Array(1,2)) === 1)
  }

  test("two discs ending in the same place produce an intersection") {
    assert(solution(Array(2,1)) === 1)
  }

  test("two discs sharing the rim produce an intersection") {
    assert(solution(Array(1,0)) === 1)
  }

  test("codility example passes") {
    assert(solution(Array(1,5,2,1,4,0)) === 11)
  }

}
