package com.company.quickstart

// 有理数
class Rational(n:Int, d:Int) {

  require(d != 0)

  private val g = gcd(n.abs, d.abs)
  val numer = n / g
  val denom = d / g

  def this(n:Int) = this(n, 1)

  private def gcd(a:Int, b:Int):Int = {
    if (b == 0) a else gcd(b, a % b)
  }

  def add(that:Rational): Rational = {
    new Rational(numer * that.denom + denom * that.numer, denom * that.denom)
  }

  def add(i:Int): Rational = {
    new Rational(numer + i * denom, denom)
  }

  override def equals(obj: Any): Boolean = {
    obj match {
      case that:Rational => that.numer == numer && that.denom == denom
      case _ => false
    }
  }

  override def toString: String = {
    super.toString + s",$n/$d"
  }
}