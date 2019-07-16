package com.company.quickstart

import org.junit.Assert._
import org.junit._

class ChecksumAccumulatorTest {

  @Test
  def testChecksumAccumulator() = {
    val acc = new ChecksumAccumulator
    val cas = new ChecksumAccumulator
    assertEquals(acc.checksum(), 0)
    acc.add(1)
    assertEquals(acc.checksum(), -1)
  }

  def testSingleton(): Unit = {
    val sc = ChecksumAccumulator.calculate("abc")
    assertEquals(sc, 1)
  }

}
