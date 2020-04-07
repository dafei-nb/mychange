package com.dream.test;

import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
public class DBCPTest {
    private Connection conn;
    @BeforeEach
     void getconnection() throws Exception {
        BasicDataSourceFactory factory=new BasicDataSourceFactory();
        Properties properties=new Properties();
        properties.load(DBCPTest.class.getClassLoader().getResourceAsStream("dbcp.properties"));
        DataSource dataSource = factory.createDataSource(properties);
        conn=dataSource.getConnection();
    }
    @Test
    public void testgetconnection(){
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            String sql="select *from dept";
             ps = conn.prepareStatement(sql);
             rs = ps.executeQuery();
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
