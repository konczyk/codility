package lesson5

import org.junit.runner.RunWith
import org.scalacheck.Gen
import org.scalatest.junit.JUnitRunner
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FunSuite, Matchers}

@RunWith(classOf[JUnitRunner])
class GenomicRangeQueryTest extends FunSuite
  with GeneratorDrivenPropertyChecks with Matchers {

  import lesson5.GenomicRangeQuery._

  private val MinSeq = 1
  private val MaxSeq = 1000000
  private val MinQueries = 1
  private val MaxQueries = 50000

  private val factors = Map('A' -> 1, 'C' -> 2, 'G' -> 3, 'T' -> 4)

  test("sequence with the same nucleotide produces its factor for any query") {
    val nucleotides = factors.keys.toArray
    val inputGen = for {
      seqLen <- Gen.choose(MinSeq, MaxSeq)
      ch <- Gen.oneOf(nucleotides)
      seq <- Gen.buildableOfN[String,Char](seqLen, Gen.const(ch))
      queryNum <- Gen.chooseNum(MinQueries, MaxQueries)
      queries <- Gen.containerOfN[Array, (Int, Int)](queryNum,
        for {
          n <- Gen.choose(0, seqLen-1)
          m <- Gen.choose(n, seqLen-1)
        } yield (n,m))
    } yield (seq, queries)

    forAll(inputGen, minSuccessful(25)) {
      case (seq, pq) =>
        val (p, q) = pq.unzip
        val factor = factors(seq.head)
        solution(seq, p, q) should equal(Array.fill[Int](p.length)(factor))
    }
  }

  test("query with the lowest factor A at the end should return 1") {
    assert(solution("ACGTACT", Array(2), Array(4)) === Array(1))
  }

  test("query with the lowest factor C at the front should return 2") {
    assert(solution("ACGTACT", Array(1), Array(2)) === Array(2))
  }

  test("query with the lowest factor G in the middle should return 3") {
    assert(solution("ATGTACT", Array(1), Array(3)) === Array(3))
  }

  test("query over whole sequence with the lowest factor A should return 1") {
    assert(solution("ATGTACT", Array(0), Array(6)) === Array(1))
  }

  test("query over range==1 should return the factor of the selected nuceotide") {
    assert(solution("ATGTACT", Array(0,6), Array(0,6)) === Array(1,4))
  }

  test("codility example passes") {
    assert(solution("CAGCCTA", Array(2,5,0), Array(4,5,6)) === Array(2,4,1))
  }

}
