package com.example.exp5.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;

/**
 * 数据库连接池
 */

public class JDBCUtils {
    private static DataSource dataSource;
    // 使用Alibaba的Druid连接池，采用druid.properties文件配置
    // 该文件通常放在resource目录中
    static {
        try {
            Properties properties = new Properties();
            // 通过当前文件获取当前项目的resource目录中的内容
            InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties");
            properties.load(is);
            // 通过Druid的工厂类获取连接池对象
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            throw new RuntimeException("Druid 连接池初始化失败...");
        }
    }

    // 获取连接池对象 
    public static DataSource getDataSource(){
        return dataSource;
    }

    // 获取连接池当中的连接对象
    public static Connection getConnection() throws SQLException{
        return dataSource.getConnection();
    }

    // 释放连接对象，换个连接池，所以不需要关闭DataSource
    public static void release(ResultSet rs, Statement stmt, Connection conn){
        // 关闭结果集对象
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // 关闭语句对象
        if(stmt != null){
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        // 关闭连接对象
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}


