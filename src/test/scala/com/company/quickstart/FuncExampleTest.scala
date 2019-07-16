package com.company.quickstart

import org.junit._
import Assert._

class FuncExampleTest {
  @Test
  def testCounter() = {
    val n = FuncExample.counter(1)
    assertEquals(n, 2)
  }

  @Test
  def testFuncMax() = {
    val n = FuncExample.fnMax(1, 2)
    assertEquals(n, 2)

    val m = FuncExample.fnMax2(1, 2)
    assertEquals(m, 2)
  }

  @Test
  def testEchoString():Unit = {
    FuncExample.echoString("a", "b", "c")

    /**
      * 将一个定义好的数组作为重复参数传入时，需在数组实参的后面加上冒号和"_*"符号，
      * 以告诉编译器将arr的每个元素作为参数,而不是将所有元素放在一起作为单个实参传入.
      */
    val arr = Array("d", "e")
    //FuncExample.echoString(arr)
    FuncExample.echoString(arr:_*)

    FuncExample.echoAny("a", 1, 1.0)
  }

  @Test
  def testArrayWalk(): Unit = {
    val arr:Array[Any] = Array("d", "e")
    FuncExample.arrayWalk(arr, println)
    FuncExample.arrayWalk(arr,
      x => {
        println("WALK:" + x)
      }
    )
  }

  @Test
  def testNamedArgs(): Unit = {
    val d = 100
    val t = 10
    val s = FuncExample.speed(d, t)
    assertEquals(s, 10)

    val s2 = FuncExample.speed(time = 10, distance = 100)
    assertEquals(s2, s)

    val s3 = FuncExample.speed(100)
    assertEquals(s3, 50)
  }

  @Test
  def testTailRecursive(): Unit ={
    val r = TailRecurisive.approximate(0)
    assertTrue(r > 0.5)
  }

  @Test
  def testApplyAndUpdate() = {
    ApplyStub(1) = "a"
    assertEquals(ApplyStub(1), "a")
    ApplyStub(2) = "b"
    assertEquals(ApplyStub(2), "b")
    ApplyStub.update(0, "c")
    assertEquals(ApplyStub(0), "c")
  }

}
