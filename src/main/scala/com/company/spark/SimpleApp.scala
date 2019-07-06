package com.company.spark

import org.apache.spark.sql.SparkSession

/*
spark-submit \
  --class "com.company.spark.SimpleApp" \
  --master local[4] \
  target/scala-sample-0.1-SNAPSHOT.jar
 */
object SimpleApp {
  def main(args: Array[String]) {
    //val logFile = sys.env.getOrElse("SPARK_HOME", "") + "/README.md" // Should be some file on your system
    val logFile = "/user/cloudera/README.md" // hdfs://quickstart.cloudera:8022/user/cloudera/README.md
    val spark = SparkSession.builder.appName("Simple Application").getOrCreate()
    val logData = spark.read.textFile(logFile).cache()
    val numAs = logData.filter(line => line.contains("a")).count()
    val numBs = logData.filter(line => line.contains("b")).count()
    println(s"Lines with a: $numAs, Lines with b: $numBs")
    spark.stop()
  }
}
