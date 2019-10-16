

import scala.math.BigInt

object t9
{  
  def main(args: Array[String]) = {
    println(streamR(1,10))
    println(fibonacciSequence1.take(10).toList)
    primes(streamR(2,10)) take 4 foreach println
    println(sqrtStream(25))
  }.;///
  
  def streamR(x: Int, y: Int): Stream[Int] ={
    val str = (x to y).toStream
    return str
  }

  def fibonacciSequence1: Stream[BigInt]={
    val fib: Stream[BigInt] = Stream.cons(1, Stream.cons(1, (fib zip fib.tail).map(p => p._1 + p._2)))
     fib
  }

  def primes(str: Stream[Int]): Stream[Int]={
    str.head #:: primes(str.tail.view.filter (_ % str.head != 0).toStream)
  }

  def sqrtStream(x: Double): Stream[Double]={
    def sqrtIter(guess: Double): Stream[Double]=
      if (isGoodEnough(guess)) Stream(guess)
      else sqrtIter(improve(guess))
    def improve(guess: Double) = (guess + x / guess) / 2
    def isGoodEnough(guess: Double) = math.abs((guess * guess - x) / x) < 0.0001
    sqrtIter(1.0)
  }
}