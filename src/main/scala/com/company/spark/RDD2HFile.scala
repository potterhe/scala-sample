package com.company.spark

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat
import org.apache.hadoop.io.Text
import org.apache.hadoop.io.IntWritable


object RDD2HFile {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setAppName("RDD2HFileExample")
    val sc = new SparkContext(sparkConf)

    val rdd = sc.makeRDD(Array(("A",2),("A",1),("B",6),("B",3),("B",7)))
    rdd.saveAsNewAPIHadoopFile("/tmp/hfile/",classOf[Text],classOf[IntWritable],classOf[TextOutputFormat[Text,IntWritable]])
  }

}
