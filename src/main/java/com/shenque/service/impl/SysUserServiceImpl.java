package com.shenque.service.impl;

import com.shenque.dao.SysUserMapper;
import com.shenque.entity.SysUser;
import com.shenque.service.ISysUserService;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

/**
 * @author Promise
 * @createTime 2018年12月27日 下午11:11:48
 * @description
 */
@Service
public class SysUserServiceImpl implements ISysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public SysUser findById(Long id) {
        return sysUserMapper.findById(id);
    }

    @Override
    public int addUser(SysUser user) {
        return sysUserMapper.addUser(user);
    }

    @Override
    public int update(SysUser user) {
        return sysUserMapper.update(user);
    }

    //事务方法
    @Transactional
    @Override
    public void affairAddUser() throws Exception {
        //第一个添加是正确的添加
        addUser(new SysUser(1L,"a","b","c","d","e"));
        System.out.println("插入成功，但是是否提交看下面执行语句是否正确");

        //第二个添加是错误的，故意传参少参数，所以会抛异常，此时第一个添加会回滚，事务完成！
        addUser(new SysUser(2L,"a","b","c","d","e"));
    }

    @Override
    public void affairAddHbase() throws Exception {
        Configuration conf = HBaseConfiguration.create();
        Connection conn = null;
        conf.set("hbase.zookeeper.quorum", "192.168.146.200");
        //增加Hbase客户端连接池大小！
        conf.set("hbase.client.ipc.pool.size", "10");
        conn = ConnectionFactory.createConnection(conf);

        Put put = new Put(Bytes.toBytes("row4"));
        put.addColumn(Bytes.toBytes("f1"), Bytes.toBytes("name"), Bytes.toBytes("vccc"));
        Table table = conn.getTable(TableName.valueOf("t1"));
        table.put(put);

        System.out.println("第一个添加成功");

        //第二个故意写错列祖为f2，表名也故意写错为t123
        Put put2 = new Put(Bytes.toBytes("row4"));
        put2.addColumn(Bytes.toBytes("f2"), Bytes.toBytes("name"), Bytes.toBytes("aaa"));
        Table table2 = conn.getTable(TableName.valueOf("t123"));
        table2.put(put2);


    }

}


