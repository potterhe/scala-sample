package com.company.quickstart

import scala.collection.mutable

class ChecksumAccumulator {
  private var sum = 0

  def add(b:Byte):Int = {
    sum += b
    sum
  }

  def checksum():Int = {
    ~(sum & 0xFF) + 1
  }
}

// companion object, 伴生对象，伴生类
object ChecksumAccumulator {

  private val cache = mutable.Map.empty[String, Int]

  def calculate(s:String): Int = {
    if (cache.contains(s)) {
      cache(s)
    } else {
      val acc = new ChecksumAccumulator
      for (c <- s) {
        acc.add(c.toByte)
      }
      val cs = acc.checksum()
      cache(s) = cs
      cs
    }
  }
}
