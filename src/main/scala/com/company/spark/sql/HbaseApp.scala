package com.company.spark.sql


import org.apache.hadoop.hbase.spark.datasources.HBaseTableCatalog
import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.SQLContext
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.yetus.audience.InterfaceAudience

@InterfaceAudience.Private
case class HBaseRecord(
                        col0: String,
                        col1: Boolean,
                        col2: Double,
                        col3: Float,
                        col4: Int,
                        col5: Long,
                        col6: Short,
                        col7: String,
                        col8: Byte)

@InterfaceAudience.Private
object HBaseRecord {
  def apply(i: Int): HBaseRecord = {
    val s = s"""row${"%03d".format(i)}"""
    HBaseRecord(s,
      i % 2 == 0,
      i.toDouble,
      i.toFloat,
      i,
      i.toLong,
      i.toShort,
      s"String$i extra",
      i.toByte)
  }
}

object HbaseApp {

  def cat = s"""{
                   |"table":{"namespace":"default", "name":"test"},
                   |"rowkey":"key",
                   |"columns":{
                   |"col0":{"cf":"rowkey", "col":"key", "type":"string"},
                   |"col1":{"cf":"cf", "col":"col1", "type":"boolean"},
                   |"col2":{"cf":"cf", "col":"col2", "type":"double"},
                   |"col3":{"cf":"cf", "col":"col3", "type":"float"},
                   |"col4":{"cf":"cf", "col":"col4", "type":"int"},
                   |"col5":{"cf":"cf", "col":"col5", "type":"bigint"},
                   |"col6":{"cf":"cf", "col":"col6", "type":"smallint"},
                   |"col7":{"cf":"cf", "col":"col7", "type":"string"},
                   |"col8":{"cf":"cf", "col":"col8", "type":"tinyint"}
                   |}
                   |}""".stripMargin

  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setAppName("HBaseSourceExample")
    val sc = new SparkContext(sparkConf)
    val sqlContext = new SQLContext(sc)

    import sqlContext.implicits._

    def withCatalog(cat: String): DataFrame = {
      sqlContext
        .read
        .options(Map(HBaseTableCatalog.tableCatalog->cat))
        .format("org.apache.hadoop.hbase.spark")
        .load()
    }

    val data = (0 to 255).map { i =>
      HBaseRecord(i)
    }

    sc.parallelize(data).toDF.write.options(
      Map(HBaseTableCatalog.tableCatalog -> cat, HBaseTableCatalog.newTable -> "5"))
      .format("org.apache.hadoop.hbase.spark")
      .save()

    val df = withCatalog(cat)
    df.show()
    df.filter($"col0" <= "row005")
      .select($"col0", $"col1").show
    df.filter($"col0" === "row005" || $"col0" <= "row005")
      .select($"col0", $"col1").show
    df.filter($"col0" > "row250")
      .select($"col0", $"col1").show
    df.registerTempTable("table1")
    val c = sqlContext.sql("select count(col1) from table1 where col0 < 'row050'")
    c.show()
  }

}
