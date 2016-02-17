package izmailoff

import org.specs2.mutable.Specification
import Palindrome._

class PalindromeSuite extends Specification {

  "Subsets of a list" should {

    "return empty list if input list is empty" in {
      subsets(Nil) must be equalTo Stream.empty
    }

    "return single element for a single element list" in {
      subsets(List(1)) must be equalTo Stream(0 -> List(1))
    }

    "return single and double element lists for 2 element list" in {
      subsets(List(1, 2)) must containTheSameElementsAs(Stream(0 -> List(1), 0 -> List(1, 2), 1 -> List(2)))
    }

    "return correct results for list with multiple elements" in {
      subsets("abcde".toList) must containTheSameElementsAs(Stream(
        0 -> List('a'), 0 -> List('a', 'b'), 0 -> List('a', 'b', 'c'), 0 -> List('a', 'b', 'c', 'd'), 0 -> List('a', 'b', 'c', 'd', 'e'),
        1 -> List('b'), 1 -> List('b', 'c'), 1 -> List('b', 'c', 'd'), 1 -> List('b', 'c', 'd', 'e'),
        2 -> List('c'), 2 -> List('c', 'd'), 2 -> List('c', 'd', 'e'),
        3 -> List('d'), 3 -> List('d', 'e'),
        4 -> List('e')))
    }
  }

  "IsPalindrome" should {

    "return true for an empty list" in {
      isPalindrome(Nil) must beTrue
    }

    "return true for a list of one element" in {
      isPalindrome(List(1)) must beTrue
    }

    "return true for an odd length palindrome" in {
      isPalindrome(List(1, 2, 1)) must beTrue
    }

    "return true for an even length palindrome" in {
      isPalindrome(List(1, 1)) must beTrue
    }

    "return false for a non palindrome" in {
      isPalindrome(List(1, 2, 3)) must beFalse
    }
  }

  "InsertOrdUnique" should {

    "return one element list for empty top list with top N > 0" in {
      insertOrdUnique(1 -> List('a'), Nil, 1) must be equalTo List(1 -> List('a'))
    }

    "return empty list when N = 0" in {
      insertOrdUnique(1 -> List('a'), Nil, 1) must be equalTo List(1 -> List('a'))
    }

    "put element on top if it's longer than current top" in {
      insertOrdUnique(1 -> List(1, 1, 1), List(2 -> List(2, 2)), 2) must be equalTo List(1 -> List(1, 1, 1), 2 -> List(2, 2))
    }

    "put element after the top element if it's shorter than current top" in {
      insertOrdUnique(22 -> List(22, 22), List(1 -> List(1, 1, 1)), 2) must be equalTo List(1 -> List(1, 1, 1), 22 -> List(22, 22))
    }

    "not insert a non-unique palindrome, i.e. one contained in the larger one" in {
      insertOrdUnique(2 -> List(2, 2), List(1 -> List(1, 2, 2, 1)), 2) must be equalTo List(1 -> List(1, 2, 2, 1))
    }
  }

  "Palindromes" should {

    "return nothing for empty input" in {
      palindromes(Nil) must be equalTo Stream.empty
    }

    "return all non-unique palindromes" in {
      palindromes(List(1, 2, 2, 1, 3, 3)) must containTheSameElementsAs(Stream(
        (0, List(1)), (0, List(1, 2, 2, 1)),
        (1, List(2)), (1, List(2, 2)),
        (2, List(2)),
        (3, List(1)),
        (4, List(3)), (4, List(3, 3)),
        (5, List(3))))
    }
  }

  "TopUniquePalindromes" should {

    "return empty list for empty input" in {
      topUniquePalindromes(List(), 1) must be equalTo Nil
    }

    "return empty list for non-empty input for top N = 0" in {
      topUniquePalindromes("abcdef", 0) must be equalTo Nil
    }

    "return one palindrome if 2 palindromes of equal length exist and top N = 1" in {
      val res = topUniquePalindromes("aabb", 1)

      res.size must be equalTo 1
      println(res)
      (res == List(0 -> "aa") || res == List(2 -> "bb")) must beTrue
    }

    "return unique, non-nested, longest palindromes in descending order of length" in {
      val res = topUniquePalindromes("sqrrqabccbatudefggfedvwhijkllkjihxymnnmzpop", 3)
      val resWithLength = res.map { case (startPos, p) => (startPos, p, p.length) }

      resWithLength must be equalTo (23, "hijkllkjih", 10) ::(13, "defggfed", 8) ::(5, "abccba", 6) :: Nil
    }
  }


}
