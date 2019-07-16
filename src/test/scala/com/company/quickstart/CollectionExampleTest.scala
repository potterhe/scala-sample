package com.company.quickstart

import org.junit.Assert._
import org.junit._

class CollectionExampleTest {

  @Test
  def testArry() = {
    CollectionExample.awesomeArray()
  }

  @Test
  def testList() = {
    CollectionExample.awesomeList()
  }

  @Test
  def testTuple(): Unit ={
    CollectionExample.awesomeTuple()
  }

  @Test
  def testSet(): Unit ={
    CollectionExample.awesomeSet()
  }

  @Test
  def testMap(): Unit ={
    CollectionExample.awesomeMap()
  }
}
