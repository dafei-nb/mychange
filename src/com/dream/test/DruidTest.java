package com.dream.test;

import com.alibaba.druid.pool.DruidDataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DruidTest {
    private Connection conn;
    @BeforeEach
    public void c3p0test() throws SQLException, IOException {
        DruidDataSource dataSource=new DruidDataSource();
        Properties properties=new Properties();
        properties.load(DBCPTest.class.getClassLoader().getResourceAsStream("druid.properties"));
        dataSource.setDriverClassName(properties.getProperty("com.mysql.jdbc.Driver"));
        dataSource.setUrl(properties.getProperty("jdbc:mysql://localhost:3306/test"));
        dataSource.setUsername(properties.getProperty("root"));
        dataSource.setPassword(properties.getProperty("root"));
        conn=dataSource.getConnection();
    }
    @Test
    public void test(){
        PreparedStatement ps=null;
        ResultSet rs =null;
        try {
            String sql="select *from dept";
            ps = conn.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()){
                int deptno = rs.getInt(1);
                String dname = rs.getString(2);
                String loc = rs.getString(3);
                System.out.println(deptno+"---"+dname+"---"+loc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(conn!=null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
