package com.company

import org.apache.hadoop.hbase.HBaseConfiguration
import org.apache.hadoop.hbase.client.{ ConnectionFactory, Admin, Put, Delete, Get, Table, ResultScanner, Scan, Result }
import org.apache.hadoop.hbase.{ TableName, HTableDescriptor, HColumnDescriptor }
import org.apache.hadoop.hbase.util.Bytes

/**
  * https://www.jianshu.com/p/cc9bfb116886
  */
object HBaseApp {

  def main(args: Array[String]): Unit = {
    val conf = HBaseConfiguration.create()
    conf.set("hbase.zookeeper.quorum", "localhost:2181");
    val conn = ConnectionFactory.createConnection(conf)
    val admin = conn.getAdmin
    val wetagTable = TableName.valueOf("wetag")

    // 删除表
    if (admin.tableExists(wetagTable)) {
      println("drop table `wetag` ")
      admin.disableTable(wetagTable)
      admin.deleteTable(wetagTable)
      println("done")
    }

    // 创建表
    val tableDescr = new HTableDescriptor(wetagTable)
    tableDescr.addFamily(new HColumnDescriptor("attr".getBytes))
    tableDescr.addFamily(new HColumnDescriptor("bhvr".getBytes))

    if (!admin.tableExists(wetagTable)) {
      println("Creating table `wetag` ")
      admin.createTable(tableDescr)
      println("Table `wetag` created  ")
    }

    // 插入(更新)记录
    val key="12345678"
    val family="attr"
    val column="population.age"
    val value="30"
    val table=conn.getTable(wetagTable)

    var p=new Put(key.getBytes)
    p.addColumn(family.getBytes,column.getBytes,value.getBytes())
    p.addColumn(family.getBytes, "population.sex".getBytes, "male".getBytes)
    table.put(p)

    p=new Put(key.getBytes)
    p.addColumn(family.getBytes, "population.age".getBytes, "45".getBytes)
    table.put(p)

    // 查询某条记录
    val g = new Get(key.getBytes)
    val result = table.get(g)
    val gvalue = Bytes.toString(result.getValue(family.getBytes, column.getBytes))
    println("GET: " + key + " " + gvalue)

    // 删除记录
    val d=new Delete(key.getBytes)
    d.addColumn(family.getBytes(), "population.sex".getBytes())
    table.delete(d)
    println("Delete: " + key + "sex")

    // 扫描记录
    var scanner:ResultScanner=null
    try{
      val s=new Scan()
      s.addFamily(family.getBytes())
      scanner = table.getScanner(s)

      var result:Result=scanner.next()
      while(result!=null) {
        println("Found row:" + result)
        println("Found value: "+Bytes.toString(result.getValue(family.getBytes(),column.getBytes())))
        result=scanner.next()
      }
    } finally {
      if(table!=null)
        table.close()
      scanner.close()
    }
  }

}
