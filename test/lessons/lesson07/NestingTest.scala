package lessons.lesson07

import org.scalacheck.Gen
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FunSuite, Matchers}

class NestingTest extends FunSuite
  with GeneratorDrivenPropertyChecks with Matchers {

  import Nesting._

  private val MinLen = 0
  private val MaxLen = 1000000

  private val parens = List('(', ')')

  test("strings starting with a closing paren always produce 0") {
    val stringsGen = Gen.chooseNum(MinLen, MaxLen-1) flatMap {
      Gen.buildableOfN[String, Char](_, Gen.oneOf(parens)).map(')' + _)
    }
    forAll(stringsGen)(solution(_) should equal (0))
  }

  test("strings ending with a open paren always produce 0") {
    val stringsGen = Gen.chooseNum(MinLen, MaxLen-1) flatMap {
      Gen.buildableOfN[String, Char](_, Gen.oneOf(parens)).map(_ + '(')
    }
    forAll(stringsGen)(solution(_) should equal (0))
  }

  test("strings with a single bracket always produce 0") {
    val stringsGen = Gen.buildableOfN[String, Char](1, Gen.oneOf(parens))
    forAll(stringsGen)(solution(_) should equal (0))
  }

  test("empty string has properly nested parens") {
    assert(solution("") === 1)
  }

  test("codility example passes") {
    assert(solution("(()(())())") === 1)
    assert(solution("())") === 0)
  }

}
