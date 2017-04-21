package lesson1

import org.junit.runner.RunWith
import org.scalacheck.Gen
import org.scalacheck.Prop._
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner
import org.scalatest.prop.Checkers

@RunWith(classOf[JUnitRunner])
class BinaryGapTest extends FunSuite with Checkers {

  private def pow2(exp: Int) = math.pow(2, exp).toInt
  private def bin2int(s: String) = Integer.parseInt(s, 2)
  private def ones(n: Int) = bin2int(("1" * n).padTo(31,"0").mkString)

  test("0 has no binary gap") {
    assert(BinaryGap.solution(0) === 0)
  }

  test("number 2^n have no binary gap") {
    val powersGen = Gen.chooseNum(0,31).map(pow2)
    check(forAll(powersGen)(BinaryGap.solution(_) === 0))
  }

  test("numbers with consecutive ones in binary have no binary gap") {
    val onesGen = Gen.chooseNum(2,31).map(ones)
    check(forAll(onesGen)(BinaryGap.solution(_) === 0))
  }

  test("single binary gap with prefix 1 is found") {
    assert(BinaryGap.solution(bin2int("0001001")) === 2)
  }

  test("single binary gap with prefix 0 is found") {
    assert(BinaryGap.solution(bin2int("000100100")) === 2)
  }

  test("biggest binary gap is found when it's first") {
    assert(BinaryGap.solution(bin2int("010100100")) === 2)
  }

  test("biggest binary gap is found when it's last") {
    assert(BinaryGap.solution(bin2int("110001101")) === 3)
  }

  test("codility examples passes") {
    assert(BinaryGap.solution(1041) === 5)
  }

}

