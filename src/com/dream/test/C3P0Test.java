package com.dream.test;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class C3P0Test {
    private Connection conn;
    @BeforeEach
    public void c3p0test() throws SQLException {
    ComboPooledDataSource fa=new ComboPooledDataSource();
     conn = fa.getConnection();
}
@Test
    public void testit(){
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
}}
}
