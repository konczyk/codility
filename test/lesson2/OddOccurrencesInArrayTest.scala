package lesson2

import org.junit.runner.RunWith
import org.scalacheck.Gen
import org.scalatest.junit.JUnitRunner
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FunSuite, Matchers}

@RunWith(classOf[JUnitRunner])
class OddOccurrencesInArrayTest extends FunSuite
  with GeneratorDrivenPropertyChecks with Matchers {

  import lesson2.OddOccurrencesInArray._

  private val MinEl = 1
  private val MaxEl = 1000000000
  private val MinLen = 1
  private val MaxLen = 100000
  private val numGen = Gen.chooseNum(MinEl, MaxEl)

  private def inputConstraint(xs: Array[Int]) =
    xs.length % 2 > 0 && xs.length < MaxLen/2 && xs.length >= MinLen

  test("one element array returns its only element") {
    val singlesGen = Gen.containerOfN[Array,Int](1, numGen).suchThat(_.nonEmpty)
    forAll(singlesGen)(a => solution(a) should equal (a(0)))
  }

  test("array with at least one pair returns un-paired element") {
    val Odd = 5
    def toInput(a: Array[Int]) =
      scala.util.Random.shuffle(a.toSeq ++ a :+ Odd).toArray

    val numGenOdd = numGen suchThat (_ != Odd)
    val inputGen = Gen.containerOf[Array,Int](numGenOdd).suchThat(inputConstraint).map(toInput)
    forAll(inputGen, minSuccessful(500))(solution(_) should equal (Odd))
  }

  test("codility example passes") {
    assert(solution(Array(9,3,9,3,9,7,9)) === 7)
  }

}

