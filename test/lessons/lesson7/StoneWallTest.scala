package lessons.lesson7

import org.junit.runner.RunWith
import org.scalacheck.Gen
import org.scalatest.junit.JUnitRunner
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FunSuite, Matchers}

@RunWith(classOf[JUnitRunner])
class StoneWallTest extends FunSuite
  with GeneratorDrivenPropertyChecks with Matchers {

  import StoneWall._

  private val MinLen = 1
  private val MaxLen = 100000
  private val MinEl = 1
  private val MaxEl = 1000000000

  test("single element input produces 1 block") {
    val heightsGen = Gen.containerOfN[Array, Int](1, Gen.chooseNum(MinEl, MaxEl))
    forAll(heightsGen)(solution(_) should equal (1))
  }

  test("input with same heights produces 1 block") {
    val heightsGen = for {
      len <- Gen.chooseNum(MinLen, MaxLen)
      height <- Gen.chooseNum(MinEl, MaxEl)
      gen <- Gen.containerOfN[Array, Int](len, Gen.const(height))
    } yield gen
    forAll(heightsGen)(solution(_) should equal (1))
  }

  test("lower wall followed by a higher one should produce two blocks") {
    assert(solution(Array(1,2)) === 2)
  }

  test("higher wall followed by a lower one should produce two blocks") {
    assert(solution(Array(2,1)) === 2)
  }

  test("lower wall with one higher piece in the middle produces two blocks") {
    assert(solution(Array(1,1,2,2,1)) === 2)
  }

  test("higher wall with one lower piece in the middle produces three blocks") {
    assert(solution(Array(2,2,1,2)) === 3)
  }

  test("wall with two heights interleaved produces number of higher blocks + 1") {
    assert(solution(Array(1,2,1,2,1)) === 3)
  }

  test("wall with distinct heights produces number of blocks equal to the number of heights") {
    assert(solution(Array(2,1,3,3,6,5,5,7)) === 6)
  }

  test("codility example passes") {
    assert(solution(Array(8,8,5,7,9,8,7,4,8)) === 7)
  }

}
