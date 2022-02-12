package com.company.spark

import org.apache.spark.sql.SparkSession

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

    spark.stop()
  }
}
