package challenges.challenge2011

import org.scalacheck.Gen
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FunSuite, Matchers}

class CountPalindromicSlicesTest extends FunSuite
    with GeneratorDrivenPropertyChecks with Matchers {

  import CountPalindromicSlices._

  private val MinLen = 0
  private val MaxLen = 20000
  private val Limit = 100000000

  test("empty/single char repeated strings should produce n(n-1)/2 palindromes") {
    val stringsGen = Gen.chooseNum(MinLen, MaxLen) flatMap {
      Gen.listOfN[String](_, Gen.const("a")).map(_.mkString)
    }
    forAll(stringsGen, minSuccessful(25)) {
      str =>
        val total = (str.length * (str.length-1)) / 2
        if (total > Limit) {
          solution(str) should equal(-1)
        }
        else
          solution(str) should equal(total)
    }
  }

  test("smaller odd-length palindromes within a large palindrome") {
    assert(solution("zbababo") === 4)
  }

  test("smaller even-length palindromes within a large palindrome") {
    assert(solution("zbaabo") === 2)
  }

  test("overlapping odd-length palindromes") {
    assert(solution("taratar") === 4)
  }

  test("overlapping even-length palindromes") {
    assert(solution("tarattar") === 5)
  }

  test("full length odd-length palindromes") {
    assert(solution("babab") === 4)
  }

  test("full length even-length palindromes") {
    assert(solution("baab") === 2)
  }

  test("large palindrome covering smaller, differently centered one") {
    assert(solution("paradaraz") === 5)
  }

  test("odd-length palindromes sharing 1-char border") {
    assert(solution("parada") === 2)
  }

  test("even-length palindromes sharing 1-char border") {
    assert(solution("bobaabo") === 4)
  }

  test("codility example passes") {
    assert(solution("baababa") === 6)
  }

}
