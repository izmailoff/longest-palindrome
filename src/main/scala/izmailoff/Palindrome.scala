package izmailoff

/**
Task:

Write an application that finds the 3 longest unique palindromes in a supplied string. For the 3 longest
palindromes, report the palindrome, start index and length, in descending order of length.

Example Output:

Given the input string: sqrrqabccbatudefggfedvwhijkllkjihxymnnmzpop , the output should be:

Text: hijkllkjih, Index: 23, Length: 10
Text: defggfed, Index: 13, Length: 8
Text: abccba, Index: 5 Length: 6
 */
object Palindrome {

  def topUniquePalindromes(str: String, topN: Int): List[(Int, String)] =
    topUniquePalindromes(str.toList, topN) map { case (startPos, pl) => (startPos, pl.mkString) }

  def topUniquePalindromes[T](seq: List[T], topN: Int): List[(Int, List[T])] =
    palindromes(seq).foldLeft(List[(Int, List[T])]()) { (top, s) => insertOrdUnique(s, top, topN) }

  def palindromes[T](seq: List[T]): Stream[(Int, List[T])] =
    subsets(seq).filter { case (_, xs) => isPalindrome(xs) }

  def subsets[T](seq: List[T], pos: Int = 0): Stream[(Int, List[T])] =
    if (seq.isEmpty) Stream.empty
    else
      (for {
        i <- (1 to seq.length).toStream
        s = seq.take(i)
      } yield pos -> s) #::: subsets(seq.tail, pos + 1)

  def isPalindrome[T](input: List[T]): Boolean =
    input.reverse == input

  protected[izmailoff] def insertOrdUnique[T](e: (Int, List[T]), into: List[(Int, List[T])], topN: Int): List[(Int, List[T])] =
    into match {
      case Nil if topN > 0 => List(e)
      case Nil => Nil
      case h :: t if e._2.length > h._2.length => e :: insertOrdUnique(h, t, topN - 1)
      case h :: t if e._1 > h._1 && e._2.length + e._1 < h._2.length + h._1 => into
      case h :: t => h :: insertOrdUnique(e, t, topN - 1)
    }

}
