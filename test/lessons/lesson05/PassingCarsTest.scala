package lessons.lesson05

import org.scalacheck.Gen
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FunSuite, Matchers}

class PassingCarsTest extends FunSuite
  with GeneratorDrivenPropertyChecks with Matchers {

  import lessons.lesson05.PassingCars._

  private val MinLen = 1
  private val MaxLen = 1000000
  private val Limit = 1000000000

  test("no passing cars when everybody drives east") {
    val carsGen = Gen.chooseNum(MinLen, MaxLen) flatMap {
      Gen.containerOfN[Array, Int](_, Gen.const(0))
    }
    forAll(carsGen)(solution(_) should equal (0))
  }

  test("no passing cars when everybody drives west") {
    val carsGen = Gen.chooseNum(MinLen, MaxLen) flatMap {
      Gen.containerOfN[Array, Int](_, Gen.const(1))
    }
    forAll(carsGen)(solution(_) should equal (0))
  }

  test("no passing cars when first half drives west and second half drives east") {
    val carsGen = for {
      west <- Gen.chooseNum(MinLen, MaxLen/2)
      east <- Gen.chooseNum(MinLen, MaxLen-west)
    } yield Array.fill[Int](west)(1) ++ Array.fill[Int](east)(0)
    forAll(carsGen)(solution(_) should equal (0))
  }

  test("first car drives east, rest drives west produces 3 passing cars") {
    assert(solution(Array(0,1,1,1)) === 3)
  }

  test("second to last car drives east, rest drives west produces 1 passing car") {
    assert(solution(Array(1,1,0,1)) === 1)
  }

  test("first car drives east, second car drives west produces 1 passing car") {
    assert(solution(Array(0,1)) === 1)
  }

  test("two cars drive east and the other two cars drive west should produce 4") {
    assert(solution(Array(1,0,0,1,1,0)) === 4)
  }

  test("number of passed card over the limit produces -1") {
    assert(solution(Array.fill[Int](500001)(0) ++ Array.fill[Int](2000)(1)) === -1)
  }

  test("codility example passes") {
    assert(solution(Array(0,1,0,1,1)) === 5)
  }

}
