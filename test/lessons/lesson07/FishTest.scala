package lessons.lesson07

import org.scalacheck.Gen
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FunSuite, Matchers}

class FishTest extends FunSuite
  with GeneratorDrivenPropertyChecks with Matchers {

  import Fish._

  private val MinLen = 1
  private val MaxLen = 100000
  private val MinFish = 0
  private val MaxFish = 1000000000

  test("all fish moving downstream produce all fish alive") {
    val fishGen = for {
      len <- Gen.chooseNum(MinLen, MaxLen)
      sizes <- Gen.containerOfN[Array, Int](len, Gen.chooseNum(MinFish, MaxFish))
      fish <- Gen.containerOfN[Array, Int](len, Gen.const(1))
    } yield (sizes, fish)
    forAll(fishGen) {
      case (sizes, fish) => solution(sizes, fish) should equal (sizes.length)
    }
  }

  test("all fish moving upstream produce all fish alive") {
    val fishGen = for {
      len <- Gen.chooseNum(MinLen, MaxLen)
      sizes <- Gen.containerOfN[Array, Int](len, Gen.chooseNum(MinFish, MaxFish))
      fish <- Gen.containerOfN[Array, Int](len, Gen.const(0))
    } yield (sizes, fish)
    forAll(fishGen) {
      case (sizes, fish) => solution(sizes, fish) should equal (sizes.length)
    }
  }

  test("big fish moving downstream eats all smaller fish moving upstream") {
    val fishGen: Gen[(Array[Int], Array[Int])] = for {
      len <- Gen.chooseNum(MinLen, MaxLen-1)
      bigSize <- Gen.choose(MinFish+1, MaxFish)
      sizes <- Gen.containerOfN[Array, Int](len, Gen.chooseNum(MinFish, bigSize-1))
      fish <- Gen.containerOfN[Array, Int](len, Gen.const(0))
    } yield (bigSize +: sizes, 1 +: fish)
    forAll(fishGen) {
      case (sizes, fish) => solution(sizes, fish) should equal (1)
    }
  }

  test("big fish moving upstream eats all smaller fish moving downstream") {
    val fishGen: Gen[(Array[Int], Array[Int])] = for {
      len <- Gen.chooseNum(MinLen, MaxLen-1)
      bigSize <- Gen.choose(MinFish+1, MaxFish)
      sizes <- Gen.containerOfN[Array, Int](len, Gen.chooseNum(MinFish, bigSize-1))
      fish <- Gen.containerOfN[Array, Int](len, Gen.const(1))
    } yield (sizes :+ bigSize, fish :+ 0)
    forAll(fishGen) {
      case (sizes, fish) => solution(sizes, fish) should equal (1)
    }
  }

  test("no fish eats any other fish") {
    assert(solution(Array(4,3,2,1), Array(0,0,1,1)) === 4)
  }

  test("fish eat one another and one survives") {
    assert(solution(Array(4,3,2,5), Array(1,1,0,0)) === 1)
  }

  test("codility example passes") {
    assert(solution(Array(4,3,2,1,5), Array(0,1,0,0,0)) === 2)
  }

}
