package com.company.quickstart

object FuncExample {

  def greet(): Unit = {
    println("greet")
  }

  def counter(v: Int): Int = {
    v + 1
  }

  /**
    * function literal
    * 函数字面量的语法是：用圆括号括起来的一组带名字的参数，一个右箭头和函数体
    */
  val fnMax = (a:Int, b:Int) => {
    if (a > b)
      a
    else
      b
  }

  /**
    * 函数类型字面量语法
    */
  val fnMax2: (Int, Int) => Int = (a:Int, b:Int) => {
    if (a > b)
      a
    else
      b
  }

  /**
    * 重复参数,可变长参数列表
    *
    * @param args
    */
  def echoString(args: String*):Unit = {
    for (arg <- args) {
      println(arg)
    }

    args.foreach(a => println(a))
    args.foreach(println)
  }

  /**
    * 重复参数,可变长参数列表
    *
    * @param args
    */
  def echoAny(args: Any*) = {
    args.foreach(println)
  }

  /**
    * 函数类型表示
    *
    * @param a
    * @param fn
    */
  def arrayWalk(a: Array[Any], fn:(Any) => Unit): Unit = {
    a.foreach(fn)
  }

  /**
    * 函数默认值
    *
    * @param distance
    * @param time
    * @return
    */
  def speed(distance: Int, time: Int = 2): Int= {
    distance / time
  }

}

object TailRecurisive {
  /**
    * 尾递归(tail recursive): 在最后一步调用自己的函数.
    * [Scala编译8.9]
    */
  def approximate(guess: Double): Double = {
    if (isGoodEnough(guess)) guess
    else approximate(improve(guess))
  }

  def isGoodEnough(guess: Double): Boolean = {
    guess > 0.5
  }

  def improve(guess: Double): Double = {
    guess + 0.1
  }

}

object ApplyStub {
  var v: Array[String] = new Array[String](3)
  def apply(i: Int) = {
    v(i)
  }

  def update(i:Int, s:String) = {
    v(i) = s
  }

}

object FuncComposeAndThen {
  val fComposeG = f _ compose g _
  val gComposeF = g _ compose f _
  val fAndThenG = f _ andThen g _

  def f(s:String) = "f(" + s + ")"
  def g(s:String) = "g(" + s + ")"
}