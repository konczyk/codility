package lessons.lesson09

import org.junit.runner.RunWith
import org.scalacheck.Gen
import org.scalatest.junit.JUnitRunner
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FunSuite, Matchers}

@RunWith(classOf[JUnitRunner])
class MaxProfitTest extends FunSuite
  with GeneratorDrivenPropertyChecks with Matchers {

  import MaxProfit._

  private val MinLen = 0
  private val MaxLen = 400000
  private val MinEl = 0
  private val MaxEl = 200000

  test("non-changing price of share produces max profit of 0") {
    val pricesGen = for {
      days <- Gen.chooseNum(MinLen, MaxLen)
      price <- Gen.chooseNum(MinEl, MaxEl)
      gen <- Gen.containerOfN[Array,Int](days, Gen.const(price))
    } yield gen

    forAll(pricesGen)(solution(_) should equal (0))
  }

  test("non-increasing stock price produces max profit of 0") {
    assert(solution(Array(100,90,90,80,70)) === 0)
  }

  test("rising stock price produces positive profit") {
    assert(solution(Array(20,70,10,40,80)) === 70)
  }

  test("codility example passes") {
    assert(solution(Array(23171,21011,21123,21366,21013,21367)) === 356)
  }

}
