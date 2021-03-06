package com.codeeval.easy.bitpositions

trait Bit {
  type Bit = (Int) => Int

  protected val n: Int
  protected val b: Bit = (p: Int) => if (((n >> p) & 1) == 1) 1 else 0
}

trait BitLogic extends Bit {

  def isOn(p: Int): Boolean = b(p) == 1

  def isOff(p: Int): Boolean = !isOn(p)

  def areOn(ps: Int*): Boolean = if (ps.isEmpty) true else (b(ps.head) == 1) && areOn(ps.tail: _*)
}

trait BitOperations extends Bit {

  def isOn(p: Int): Boolean

  def isOff(p: Int): Boolean

  def bitOn(p: Int): Int = if (isOn(p)) n else n | 1 << p

  def bitOff(p: Int): Int = if (isOff(p)) n else n ^ 1 << p

  def shiftLeft(np: Int) = n >> np

  def shiftRight(np: Int) = n << np

}

class Bin(number: Int) extends BitOperations with BitLogic {
  override protected val n: Int = number
  val bit: Bit = b

  def numOfBits: Int = {
    def numOfBitsImpl(num: Int, acc: Int): Int = if (num == 0) acc else numOfBitsImpl(num >> 1, acc + 1)

    numOfBitsImpl(n, 0)
  }

  def toBin: String = List.range(0, numOfBits).map(bit).reverse.mkString
}

object BinaryOperations {
  implicit def binImpl(n: Int): Bin = new Bin(n)
}
