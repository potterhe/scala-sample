package com.company.quickstart

import org.junit._
import Assert._

class FuncExampleTest {
  @Test
  def testCounter() = {
    val n = FuncExample.counter(1)
    assertTrue(n == 2)
  }

  def dataProviderCounter (): Unit = {

  }

}
