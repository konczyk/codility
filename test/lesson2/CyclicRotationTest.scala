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
  private val numGen = Gen.chooseNum(MinEl, MaxEl)

  private def shiftConstraint(a: Int) =
    a >= MinLen && a <= MaxLen
  private def inputConstraint(a: Array[Int]) =
    a.length >= MinLen && a.length <= MaxLen

  test("rotated array has the same elements in proper order") {
    val shiftGen = Gen.chooseNum(MinLen, MaxLen).suchThat(shiftConstraint)
    val inputGen = Gen.containerOf[Array,Int](numGen).suchThat(inputConstraint)
    forAll(inputGen, shiftGen, minSuccessful(500)){ (xs, k) => {
      val sol = solution(xs, k)
      val shift = if (xs.isEmpty) 0 else k % xs.length
      (sol ++ sol).slice(shift, shift+xs.length) should equal (xs)
    }}
  }

  test("codility example passes") {
    assert(solution(Array(3,8,9,7,6), 3) === Array(9,7,6,3,8))
  }

}

