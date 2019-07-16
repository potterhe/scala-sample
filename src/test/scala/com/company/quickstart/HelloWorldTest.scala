package com.company.quickstart

import org.junit._
import Assert._

@Test
class HelloWorldTest {

  @Test
  def testOK() = assertTrue(true)

  @Test
  def testHelloWorld() = {
    HelloWorld.main(Array("args"))
  }
  //    @Test
  //    def testKO() = assertTrue(false)

}
