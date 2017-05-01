package lessons.lesson07

import org.junit.runner.RunWith
import org.scalacheck.Gen
import org.scalatest.junit.JUnitRunner
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FunSuite, Matchers}

@RunWith(classOf[JUnitRunner])
class BracketsTest extends FunSuite
  with GeneratorDrivenPropertyChecks with Matchers {

  import Brackets._

  private val MinLen = 0
  private val MaxLen = 200000

  private val brackets = List('[', '{', '(', ')','}', ']')

  test("strings starting with a closing bracket always produce 0") {
    val stringsGen = for {
      len <- Gen.chooseNum(MinLen, MaxLen-1)
      start <- Gen.oneOf(brackets.takeRight(3))
      gen <- Gen.buildableOfN[String, Char](len, Gen.oneOf(brackets)).map(start + _)
    } yield gen
    forAll(stringsGen)(solution(_) should equal (0))
  }

  test("strings ending with an opening bracket always produce 0") {
    val stringsGen = for {
      len <- Gen.chooseNum(MinLen, MaxLen-1)
      end <- Gen.oneOf(brackets.take(3))
      gen <- Gen.buildableOfN[String, Char](len, Gen.oneOf(brackets)).map(_ + end)
    } yield gen
    forAll(stringsGen)(solution(_) should equal (0))
  }

  test("strings with a single bracket always produce 0") {
    val stringsGen = Gen.buildableOfN[String, Char](1, Gen.oneOf(brackets))
    forAll(stringsGen)(solution(_) should equal (0))
  }

  test("empty string has properly nested brackets") {
    assert(solution("") === 1)
  }

  test("codility example passes") {
    assert(solution("{[()()]}") === 1)
    assert(solution("([)()]") === 0)
  }

}
