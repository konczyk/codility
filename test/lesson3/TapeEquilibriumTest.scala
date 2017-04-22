package lesson3

import org.junit.runner.RunWith
import org.scalacheck.Gen
import org.scalatest.junit.JUnitRunner
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FunSuite, Matchers}

@RunWith(classOf[JUnitRunner])
class TapeEquilibriumTest extends FunSuite
  with GeneratorDrivenPropertyChecks with Matchers {

  import lesson3.TapeEquilibrium._

  private val MinLen = 2
  private val MaxLen = 100000
  private val MinEl = -1000
  private val MaxEl = 1000

  test("two element arrays produce absolute difference of element 1 and 2") {
    val inputGen = Gen.chooseNum(MinLen, MaxLen/100) flatMap {
      Gen.containerOfN[Array,Int](_, Gen.chooseNum(MinEl, MaxEl))
    }

    forAll(inputGen, minSuccessful(500)) {
      a => {
        val minDiff = (1 until a.length).map {
          i =>
            val left = a.view(0, i).sum
            val right = a.view(i, a.length).sum
            math.abs(left - right)
        }.min
        solution(a) should equal(minDiff)
      }
    }
  }

  test("codility example passes") {
    assert(solution(Array(3,1,2,4,3)) === 1)
  }

}

