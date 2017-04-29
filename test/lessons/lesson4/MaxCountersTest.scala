package lessons.lesson4

import org.junit.runner.RunWith
import org.scalacheck.Gen
import org.scalatest.junit.JUnitRunner
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FunSuite, Matchers}

@RunWith(classOf[JUnitRunner])
class MaxCountersTest extends FunSuite
  with GeneratorDrivenPropertyChecks with Matchers {

  import lessons.lesson4.MaxCounters._

  private val MinLen = 1
  private val MaxLen = 100000

  test("increasing one counter should return n for this counter and 0 for the rest") {
    val inputGen: Gen[(Int, Array[Int], Int)] = for {
      counters <- Gen.chooseNum(MinLen, MaxLen)
      inc = scala.util.Random.nextInt(counters)+1
      ops <- Gen.chooseNum(MinLen, MaxLen)
    } yield (counters, Array.fill[Int](ops)(inc), inc)

    forAll(inputGen, minSuccessful(100)) {
      case (n, xs, inc) =>
        val expected = Array.fill[Int](n)(0).zipWithIndex.map {
          case (e, i) => if (i == inc-1) xs.length else e
        }
        solution(n, xs) should equal (expected)
    }
  }

  test("max only operations should return 0 for all counters") {
    val inputGen: Gen[(Int, Array[Int])] = for {
      counters <- Gen.chooseNum(MinLen, MaxLen)
      ops <- Gen.chooseNum(MinLen, MaxLen)
    } yield (counters, Array.fill[Int](ops)(counters+1))

    forAll(inputGen, minSuccessful(100)) {
      case (n, xs) => solution(n, xs) should equal (Array.fill[Int](n)(0))
    }
  }

  test("interleaved inc->max ops should return ops/2 for all counters") {
    val inputGen: Gen[(Int, Array[Int])] = for {
      counters <- Gen.chooseNum(MinLen, MaxLen)
      incNum <- Gen.chooseNum(MinLen, MaxLen/2)
      ops <- Gen.containerOfN[Array, Int](incNum, Gen.choose(MinLen, counters))
    } yield (counters, ops.flatMap(o => Array(o, counters+1)))

    forAll(inputGen, minSuccessful(100)) {
      case (n, xs) => solution(n, xs) should equal (Array.fill[Int](n)(xs.length/2))
    }
  }

  test("increasing different counters without max op") {
    assert(solution(3, Array(1,2,3,2,3)) === Array(1,2,2))
  }

  test("increasing different counters with max op in between") {
    assert(solution(3, Array(1,2,2,4,2,3)) === Array(2,3,3))
  }

  test("increasing different counters with consecutive max op in between") {
    assert(solution(3, Array(1,2,2,4,4,2,3)) === Array(2,3,3))
  }

  test("increasing different counters with max op at the end") {
    assert(solution(3, Array(1,2,2,3,2,4)) === Array(3,3,3))
  }

  test("codility example passes") {
    assert(solution(5, Array(3,4,4,6,1,4,4)) === Array(3,2,2,4,2))
  }

}

