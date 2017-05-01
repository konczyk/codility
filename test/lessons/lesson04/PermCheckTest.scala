package lessons.lesson04

import org.junit.runner.RunWith
import org.scalacheck.Gen
import org.scalatest.junit.JUnitRunner
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FunSuite, Matchers}

@RunWith(classOf[JUnitRunner])
class PermCheckTest extends FunSuite
  with GeneratorDrivenPropertyChecks with Matchers {

  import lessons.lesson04.PermCheck._

  private val MinLen = 1
  private val MaxLen = 100000
  private val MinEl = 1
  private val MaxEl = 1000000000

  test("random values return proper value") {
    val inputGen = Gen.chooseNum(MinLen, MaxLen) flatMap {
      Gen.containerOfN[Array,Int](_, Gen.chooseNum(MinEl, MaxEl))
    }
    forAll(inputGen, minSuccessful(100)) { a =>
      val items = a.sorted.zipWithIndex
      val expected = items.find(e => e._1 > e._2+1).map(_ => 0).getOrElse(1)
      solution(a) should equal(expected)
    }
  }

  test("permutations return 1") {
    import scala.util.Random.shuffle
    val inputGen = Gen.chooseNum(MinLen, MaxLen).map(n => shuffle(1 to n).toArray)

    forAll(inputGen, minSuccessful(100))(solution(_) should equal (1))
  }

  test("codility example passes") {
    assert(solution(Array(4,1,3,2)) === 1)
    assert(solution(Array(4,1,3)) === 0)
  }

}

