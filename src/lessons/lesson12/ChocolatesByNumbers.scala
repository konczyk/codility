package lessons.lesson12

object ChocolatesByNumbers {
  def solution(n: Int, m: Int): Int = {
    def gcd(a: Int, b: Int, res: Int = 1): Int =
      if (a == b) a*res
      else if (a%2 == 0 && b%2 == 0) gcd(a/2, b/2, 2*res)
      else if (a%2 == 0) gcd(a/2, b, res)
      else if (b%2 == 0) gcd(a, b/2, res)
      else if (a > b) gcd(a-b, b, res)
      else gcd(a, b-a, res)

    n / gcd(n, m)
  }
}
