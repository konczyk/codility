package lesson2

import org.junit.runner.RunWith
import org.scalacheck.Gen
import org.scalacheck.Prop._
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner
import org.scalatest.prop.Checkers

@RunWith(classOf[JUnitRunner])
class OddOccurrencesInArrayTest extends FunSuite with Checkers {

  import lesson2.OddOccurrencesInArray._

  private val MinEl = 1
  private val MaxEl = 1000000000
  private val MinLen = 1
  private val MaxLen = 100000
  private val numGen = Gen.chooseNum(MinEl, MaxEl)

  test("one element array returns its only element") {
    val singlesGen = Gen.containerOfN[Array,Int](1, numGen).suchThat(_.nonEmpty)
    check(forAll(singlesGen)(a => solution(a) === a(0)))
  }

  test("array with at least one pair returns un-paired element") {
    val Odd = 5
    def isValid(a: Array[Int]) =
      a.length % 2 > 0 && a.length < MaxLen/2 && a.length >= MinLen
    def toInput(a: Array[Int]) =
      scala.util.Random.shuffle(a.toSeq ++ a :+ Odd).toArray

    val numGenOdd = numGen suchThat (_ != Odd)
    val inputGen = Gen.containerOf[Array,Int](numGenOdd).suchThat(isValid).map(toInput)
    check(forAllNoShrink(inputGen)(solution(_) === Odd))
  }

  test("codility example passes") {
    val a = Array(9,3,9,3,9,7,9)
    assert(solution(a) === 7)
  }

}

