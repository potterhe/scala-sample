package com.company.quickstart

import org.junit.Assert._
import org.junit._

@Test
class RationalTest {

  @Test
  def testOneAndZero(): Unit = {
    /**
      * 针对异常的测试
      * https://github.com/junit-team/junit4/wiki/Exception-testing
      */
    try {
      val x = new Rational(1, 0)
    } catch {
      case ex:Exception => {
        assertTrue(ex.isInstanceOf[IllegalArgumentException])
      }
    }
  }

  @Test
  def testAdd(): Unit = {
    val a = new Rational(1, 2)
    val b = new Rational(1, 3)
    val c = a.+(b)
    println(a, b, c)
    assertEquals(c.numer, 5)
    assertEquals(c.denom, 6)

    val d = new Rational(1)
    println(d, d.+(c))

    assertEquals(new Rational(1), new Rational(1, 1))
    assertEquals(new Rational(1, 2), new Rational(2, 4))

    val e = a + 1
    assertEquals(e, new Rational(3, 2))

    val f = 1 + a
    assertEquals(f, new Rational(3, 2))
  }

}
