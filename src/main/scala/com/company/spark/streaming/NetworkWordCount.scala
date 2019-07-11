package com.company.spark.streaming

import org.apache.spark.SparkConf
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * 官方实例
  *
  * nc -lk 9999
  *
  * spark-submit --class "com.company.spark.streaming.NetworkWordCount" target/scala-sample-0.1-SNAPSHOT.jar localhost 9999
  */
object NetworkWordCount {
  def main(args: Array[String]): Unit = {
    if (args.length < 2) {
      System.err.println("Usage: NetworkWordCount <hostname> <port>")
      System.exit(1)
    }

    val sparkConf = new SparkConf().setAppName("NetworkWordCount")
    val ssc = new StreamingContext(sparkConf, Seconds(2))

    val lines = ssc.socketTextStream(args(0), args(1).toInt, StorageLevel.MEMORY_AND_DISK_SER)
    val words = lines.flatMap(line => line.split(" "))
    val wordCount = words.map(w => (w, 1)).reduceByKey((x, y) => x + y)
    wordCount.print()

    ssc.start()
    ssc.awaitTermination()
  }

}
