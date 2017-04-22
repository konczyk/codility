package lesson2

import org.junit.runner.RunWith
import org.scalacheck.Gen
import org.scalatest.junit.JUnitRunner
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FunSuite, Matchers}

@RunWith(classOf[JUnitRunner])
class CyclicRotationTest extends FunSuite
    with GeneratorDrivenPropertyChecks with Matchers {

  import lesson2.CyclicRotation._

  private val MinEl = -1000
  private val MaxEl = 1000
  private val MinLen = 0
  private val MaxLen = 100

  test("rotated array has the same elements in proper order") {
    val shiftGen = Gen.chooseNum(MinLen, MaxLen)
    val inputGen = Gen.chooseNum(MinLen, MaxLen) flatMap {
      Gen.containerOfN[Array,Int](_, Gen.chooseNum(MinEl, MaxEl))
    }

    forAll(inputGen, shiftGen, minSuccessful(500)) {
      (xs, k) => {
        val sol = solution(xs, k)
        val shift = if (xs.isEmpty) 0 else k % xs.length
        (sol ++ sol).slice(shift, shift+xs.length) should equal (xs)
      }
    }
  }

  test("codility example passes") {
    assert(solution(Array(3,8,9,7,6), 3) === Array(9,7,6,3,8))
  }

}

