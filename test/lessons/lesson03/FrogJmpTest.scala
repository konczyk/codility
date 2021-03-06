package lessons.lesson03

import org.scalacheck.Gen
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FunSuite, Matchers}

class FrogJmpTest extends FunSuite
  with GeneratorDrivenPropertyChecks with Matchers {

  import lessons.lesson03.FrogJmp._

  private val MinEl = 1
  private val MaxEl = 1000000000
  private val numGen = Gen.chooseNum(MinEl, MaxEl)

  test("solution returns a valid number of jumps") {
    forAll(numGen, numGen, numGen, minSuccessful(500)) {
      case ((x,y,d)) => whenever(x <= y) {
        val jumps = (x until y by d).size
        solution(x, y, d) should equal(jumps)
      }
    }
  }

  test("codility example passes") {
    assert(solution(10,85,30) === 3)
  }

}

