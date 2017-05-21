package lessons.lesson15

import org.scalatest.FunSuite

class AbsDistinctTest extends FunSuite {

  import AbsDistinct._

  test("single element produces 1") {
    assert(solution(Array(Integer.MIN_VALUE)) === 1)
  }

  test("doubled element produce distinct absolute count") {
    assert(solution(Array(-5,-5,-1,1,5)) === 2)
  }

  test("codility example passes") {
    assert(solution(Array(-5,-3,-1,0,3,6)) === 5)
  }

}
