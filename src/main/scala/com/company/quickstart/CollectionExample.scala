package com.company.quickstart

object CollectionExample {
  /**
    * 元素内容可变
    */
  def awesomeArray() = {
    val arr: Array[String] = new Array[String](3)
    arr(0) = "0"
    arr(1) = "1"
    arr(2) = "2"

    // https://stackoverflow.com/questions/6833501/efficient-iteration-with-index-in-scala
    for (i <- 0 to arr.length - 1) {
      println("String # " + i + " is "+ arr(i))
    }

    for (i <- 0 until arr.length) {
      println("String # " + i + " is "+ arr(i))
    }

    for(i <- arr.indices) {
      println("String # " + i + " is "+ arr(i))
    }

    arr.foldLeft(0) (
      (i, x) => {
        println("String # " + i + " is "+ x)
        i + 1
      }
    )

    for (elem <- arr) {
      println(elem)
    }

    arr.foreach(s => println("arr:" + s))
  }

  def awesomeList(): Unit = {
    // 不可变,相同类型的对象序列
    val l1:List[Int] = List(1, 2, 3)
    val l2 = List(4)
    // 列表拼接
    val l3 = l1:::l2
    println(l1, l2, l3)

    /**
      * 在一个已有列表的最前面添加一个新的元素
      * "::"是右操作元的方法。如果方法名的最后一个字符是冒号(:),该方法的调用会发生在它的右操作元上.
      * 1 :: l1等价 l1.::(1)
      */
    val l4 = 1 :: l1
    println("l4", l4)
    val l5 = l1.::(1)
    println("l5", l5)

    // 空列表
    val l6 = Nil
    println("l6", l6)
    val l7 = 1 :: 2 :: 3 :: Nil
    println("l7", l7)

    /**
      * 末尾追回元素,append(:+)
      * 往列表末尾追回元素的操作所需要的时间随着列表的大小线性增加，在列表的前面添加元素只需要常量时间.
      * reverse
      * ListBuffer.toList
      * p40
      */
    val l8 = l7 :+ 4
    println("l8", l8)
    val l9 = l8.reverse
    println("l9", l9)
  }

  def awesomeTuple(): Unit = {
    /**
      * 不可变,可容纳不同类型元素
      * 句点(.),下划线(_),从1开始的序号(其它支持静态类型元组的语言设定的传统,Haskell,ML)
      * Scala标准库仅定义到Tuple22
      * p43
      */
    val t1 = (1, "s")
    println("tuple-1", t1._1, t1._2)

    val t2: Tuple2[String, Int] = ("s", 1)
    println("tuple-2", t1._1, t1._2)
  }

  def awesomeSet(): Unit = {
    // Set[Any]
    val s1 = Set(1, "s")
    println(s1, s1.getClass.getSimpleName)

    val s2: Set[String] = Set("a", "b")
    println(s2, s2.getClass.getSimpleName)

    val s3 = s2 + "c"
    println("Set add elem", s2, s3)

    // 可变集
    val emptySet:scala.collection.mutable.Set[String] = scala.collection.mutable.Set()
    println("Empty Set", emptySet)
    emptySet += "a"
    println("Empty Set add elem", emptySet)

    val mutableSet = scala.collection.mutable.Set("a", "b")
    val ms4 = mutableSet + "c"
    println("mutable Set + elem", mutableSet, ms4)

    val ms5 = mutableSet += "c"
    println("mutable Set += elem", mutableSet, ms5)

    val hset = scala.collection.immutable.HashSet("a", "b")
    val hset1 = hset + "c"
    println("mutable HashSet += elem", hset, hset1)
  }

  def awesomeMap(): Unit = {
    // empty map
    val m1 = scala.collection.mutable.Map[Int, String]()
    m1(1) = "a"
    m1 += (2 -> "b")
    println("Map:", m1, m1(2), m1(1))

    val m2 = Map("a" -> 1, "b" -> 2)
    println("Map2:", m2)

    m2.foreach(e => {println("m2 foreach:", e._1, e._2)})
    val m3 = m2.map(e => {(e._1, e._2 + 10)})
    println("m2.map", m2, m3)
  }

}
