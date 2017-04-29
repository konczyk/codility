package lessons.lesson3

import org.junit.runner.RunWith
import org.scalacheck.Gen
import org.scalatest.junit.JUnitRunner
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FunSuite, Matchers}

@RunWith(classOf[JUnitRunner])
class PermMissingElemTest extends FunSuite
  with GeneratorDrivenPropertyChecks with Matchers {

  import lessons.lesson3.PermMissingElem._

  private val MinLen = 0
  private val MaxLen = 100000
  private val MinEl = MinLen+1
  private val MaxEl = MaxLen+1

  test("empty array returns 1") {
    assert(solution(Array()) === 1)
  }

  test("non empty array returns missing element") {
    import scala.util.Random.shuffle

    val inputGen: Gen[(Array[Int], Int)] = for {
      n <- Gen.chooseNum(MinEl, MaxEl)
      input = shuffle(MinEl to n+1)
    } yield (input.drop(1).toArray, input.head)

    forAll(inputGen, minSuccessful(500)) {
      case (xs, missing) => solution(xs) should equal (missing)
    }
  }

  test("codility example passes") {
    assert(solution(Array(2,3,1,5)) === 4)
  }

}

