package lessons.lesson4

import org.junit.runner.RunWith
import org.scalacheck.Gen
import org.scalatest.junit.JUnitRunner
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FunSuite, Matchers}

@RunWith(classOf[JUnitRunner])
class MissingIntegerTest extends FunSuite
  with GeneratorDrivenPropertyChecks with Matchers {

  import lessons.lesson4.MissingInteger._

  private val MinLen = 1
  private val MaxLen = 100000
  private val MinEl = Int.MinValue
  private val MaxEl = Int.MaxValue

  test("input with values <= 0 only produces 1") {
    val inputGen = Gen.chooseNum(MinLen, MaxLen) flatMap {
      Gen.containerOfN[Array,Int](_, Gen.chooseNum(MinEl, 0))
    }
    forAll(inputGen, minSuccessful(100))(solution(_) should equal (1))
  }

  test("missing integer is returned") {
    val inputGen = Gen.chooseNum(MinLen, MaxLen) flatMap {
      Gen.containerOfN[Array,Int](_, Gen.chooseNum(MinEl, MaxEl))
    }
    forAll(inputGen, minSuccessful(100)) { a =>
      whenever(a.max > 0) {
        val items = a.filter(_ > 0).distinct.sorted.zipWithIndex
        val expected = items.find(e => e._1 > e._2+1).map(_._2+1).getOrElse(a.max+1)
        solution(a) should equal (expected)
      }
    }
  }

  test("codility example passes") {
    assert(solution(Array(1,3,6,4,1,2)) === 5)
  }

}

