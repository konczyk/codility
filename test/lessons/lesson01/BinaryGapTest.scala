package lessons.lesson01

import org.scalacheck.Gen
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FunSuite, Matchers}

class BinaryGapTest extends FunSuite
  with GeneratorDrivenPropertyChecks with Matchers {

  import lessons.lesson01.BinaryGap._

  private def pow2(exp: Int) = math.pow(2, exp).toInt
  private def bin2int(s: String) = Integer.parseInt(s, 2)
  private def ones(n: Int) = bin2int(("1" * n).padTo(31,"0").mkString)

  test("0 has no binary gap") {
    assert(solution(0) === 0)
  }

  test("number 2^n have no binary gap") {
    val powersGen = Gen.chooseNum(0,31).map(pow2)
    forAll(powersGen)(solution(_) should be (0))
  }

  test("numbers with consecutive ones in binary have no binary gap") {
    val onesGen = Gen.chooseNum(2,31).map(ones)
    forAll(onesGen)(solution(_) should be (0))
  }

  test("single binary gap with prefix 1 is found") {
    assert(solution(bin2int("0001001")) === 2)
  }

  test("single binary gap with prefix 0 is found") {
    assert(solution(bin2int("000100100")) === 2)
  }

  test("biggest binary gap is found when it's first") {
    assert(solution(bin2int("010100100")) === 2)
  }

  test("biggest binary gap is found when it's last") {
    assert(solution(bin2int("110001101")) === 3)
  }

  test("codility example passes") {
    assert(solution(1041) === 5)
  }

}

