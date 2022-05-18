package com.elm.util;


import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.ResourceBundle;

public class DBUtil {

  /*  private static final String URL = "jdbc:mysql://localhost:3306/elme?characterEncoding=utf-8&useSSL=false&allowPublicKeyRetrieval=true";
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456";
*/
  /*  static String URL =null;
    static String DRIVER =null;
    static String USERNAME =null;
    static String PASSWORD =null;*/
    private DBUtil(){}

    //类加载时绑定属性资源文件
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("db");

    //获取Connection
    public static Connection getConnection() {
        Connection con = null;
        try {
            String URL = resourceBundle.getString("url");
            String USERNAME = resourceBundle.getString("username");
            String PASSWORD = resourceBundle.getString("password");
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Class.forName(resourceBundle.getString("driver"));
            con = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }

    //关闭资源
    public static void close(ResultSet rs, PreparedStatement pst, Connection con) {
        if(rs!=null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            rs = null;
        }
        if(pst!=null) {
            try {
                pst.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            pst = null;
        }
        if(con!=null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            con = null;
        }
    }
}