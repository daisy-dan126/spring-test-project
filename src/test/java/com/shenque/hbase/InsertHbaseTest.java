package com.shenque.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import java.io.IOException;

public class InsertHbaseTest {

    public static void main(String[] args){

        Configuration conf = HBaseConfiguration.create();
        Connection conn = null;
        try {
            conf.set("hbase.zookeeper.quorum", "192.168.146.200");
            //增加Hbase客户端连接池大小！
            conf.set("hbase.client.ipc.pool.size", "10");
            conn = ConnectionFactory.createConnection(conf);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Table table = null;
        Put put = new Put(Bytes.toBytes("row4"));
        put.addColumn(Bytes.toBytes("f1"), Bytes.toBytes("name"), Bytes.toBytes("aaa"));
        try {
            table = conn.getTable(TableName.valueOf("t1"));
            table.put(put);
        }  catch (Exception e) {
        } finally {
            if (table != null) {
                try {
                    table.close();
                } catch (Exception e) {
                }
            }
        }

        System.out.println("添加结束");



    }
}
