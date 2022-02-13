package com.company.spark

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.catalyst.dsl.expressions.StringToAttributeConversionHelper
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.{functions => f}
import org.apache.spark.sql.functions.{expr, lit}
import org.apache.spark.sql.types.{LongType, StringType}
/*
spark-submit \
  --class "com.company.spark.LearnDuration" \
  --master local[4] \
  target/scala-sample-0.1-SNAPSHOT.jar
 */
object LearnDuration {
  def main(args: Array[String]) {
    //val logFile = sys.env.getOrElse("SPARK_HOME", "") + "/README.md" // Should be some file on your system
    val sensorDataFile = "/Users/heqiang/projects/scala-sample/learn-duration-data/sensor-data"
    val spark = SparkSession.builder.appName("Learn Duration").getOrCreate()
    val dfSensorEvent = spark.read
      .format("csv")
      .option("header", "true")
      .option("inferSchema", "true")
      .load(sensorDataFile)

    dfSensorEvent.printSchema()
    dfSensorEvent.show()

    dfSensorEvent.createOrReplaceTempView("event_view")
    spark.sql(
      """
        |SELECT *, ROW_NUMBER() OVER (PARTITION BY event ORDER BY time DESC) rank
        |FROM event_view
        |""".stripMargin).show()

    spark.sql(
      """
        |SELECT event, sum(used_time_clean)
        |FROM event_view
        |GROUP BY event
        |""".stripMargin).show()

    val df2 = dfSensorEvent
      //.withColumn("col_a", lit(1))
      //.withColumn("user", expr("login_id"))
      .withColumn("ts", expr("time * 1000").cast(LongType))
      .withColumn("pk", f.concat(f.col("login_id"), lit("_"), f.col("time")))
      .withColumn("session", expr("""IF(event == 'finish', pk, NULL)""").cast(StringType))
    df2.printSchema()
    df2.show()
    val specs = Window.partitionBy(f.col("login_id")).orderBy(f.col("time").desc)
    val df3 = df2.withColumn( "newsession", MyUDWF.calculateSession(f.col("ts"), f.col("session")) over specs)
    df3.orderBy("time").show()

    spark.stop()
  }
}
