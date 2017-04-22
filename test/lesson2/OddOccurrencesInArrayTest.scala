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

  test("one element array returns its only element") {
    val singlesGen = Gen.containerOfN[Array,Int](1, numGen)
    forAll(singlesGen)(a => solution(a) should equal (a(0)))
  }

  test("array with at least one pair returns un-paired element") {
    val Unpaired = 5
    def toInput(a: Array[Int]) =
      scala.util.Random.shuffle(a.toSeq ++ a :+ Unpaired).toArray

    val pairedNumGen = numGen suchThat (_ != Unpaired)
    val inputGen = Gen.chooseNum(MinLen, MaxLen/2) suchThat(_ % 2 != 0) flatMap {
      Gen.containerOfN[Array,Int](_, pairedNumGen).map(toInput)
    }
    forAll(inputGen, minSuccessful(500))(solution(_) should equal (Unpaired))
  }

  test("codility example passes") {
    assert(solution(Array(9,3,9,3,9,7,9)) === 7)
  }

}

