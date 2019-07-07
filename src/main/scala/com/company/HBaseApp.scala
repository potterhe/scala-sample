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
  }

}
