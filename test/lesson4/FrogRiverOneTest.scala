package lesson4

import org.junit.runner.RunWith
import org.scalacheck.Gen
import org.scalatest.junit.JUnitRunner
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FunSuite, Matchers}

@RunWith(classOf[JUnitRunner])
class FrogRiverOneTest extends FunSuite
  with GeneratorDrivenPropertyChecks with Matchers {

  import lesson4.FrogRiverOne._

  private val MinLen = 1
  private val MaxLen = 100000

  test("missing leaf from a permutation never lets the frog jump to the outermost bank") {
    import scala.util.Random.shuffle
    val inputGen = Gen.choose(MinLen, MaxLen).map { n =>
      val leaves = shuffle(1 to n).toArray
      leaves(0) = leaves.last
      leaves
    }

    forAll(inputGen, minSuccessful(100))(a => solution(a.length, a) should equal (-1))
  }

  test("bank too far away never lets the frog jump") {
    import scala.util.Random
    val inputGen = Gen.choose(MinLen, MaxLen).map { n =>
      Random.shuffle((1 to n/2) ++ (1 to n/2)).toArray
    }

    forAll(inputGen, minSuccessful(100))(a => solution(a.length/2+1, a) should equal (-1))
  }

  test("river of width 1 with a fallen leaf lets the frog jump at time 0") {
    assert(solution(1, Array(1)) === 0)
  }

  test("leaves in descending order let the frog jump at the min time of leaf 1") {
    assert(solution(4, Array(4,3,3,2,2,1,1,1)) === 5)
  }

  test("codility example passes") {
    assert(solution(5, Array(1,3,1,4,2,3,5,4)) === 6)
  }

}

