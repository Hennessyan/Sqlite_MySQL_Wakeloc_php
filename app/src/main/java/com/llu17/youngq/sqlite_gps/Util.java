package com.llu17.youngq.sqlite_gps;

import android.util.Log;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {
    public static Session session;
    public static void go() {
        String user = "smartpark";//SSH连接用户名
        String password = "Lab02282017";//SSH连接密码
        String host = "alpha.cs.binghamton.edu";//SSH服务器
        int lport = 33333;//本地端口（随便取）
        String rhost = "mysql.cs.binghamton.edu";//远程MySQL服务器
        int rport = 3306;//远程MySQL服务端口
        int port = 22;//SSH访问端口
        try {
            JSch jsch = new JSch();
            session = jsch.getSession(user, host, port);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();
            Log.e("=======>", "服务器连接成功");
//            System.out.println(session.getServerVersion());//这里打印SSH服务器版本信息
            int assinged_port = session.setPortForwardingL(lport, rhost, rport);//将服务器端口和本地端口绑定，这样就能通过访问本地端口来访问服务器
//            System.out.println("localhost:" + assinged_port + " -> " + rhost + ":" + rport);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection openConnection(String url, String user,
                                            String password) {
        Connection conn = null;
        try {
            final String DRIVER_NAME = "com.mysql.jdbc.Driver";
            Class.forName(DRIVER_NAME);
            conn = DriverManager.getConnection(url, user, password);
            Log.e("=====连接结果=======", "数据库连接成功");
        } catch (ClassNotFoundException e) {
            Log.e("=====连接结果=======", "报ClassNotFoundException异常");
            conn = null;
        } catch (SQLException e) {
            Log.e("=====连接结果=======", "报SQLException异常");
            Log.e("++++",e.getMessage());
            Log.e("++++",e.getSQLState());
            Log.e("++++",""+e.getErrorCode());
            conn = null;
        }

        return conn;
    }

    public static void query(Connection conn, String sql) {

        if (conn == null) {
            Log.e("=====连接前判断=======", "conn == null");
            return;
        }

        Statement statement = null;
        ResultSet result = null;

        try {
            statement = conn.createStatement();
            result = statement.executeQuery(sql);
            if (result != null && result.first()) {
                int timestamp = result.findColumn("time");
                int latitude = result.findColumn("latitude");
                int longitude = result.findColumn("longitude");
                Log.e("======结果======", "结果");
                while (!result.isAfterLast()) {
                    Log.e("======timestamp======", result.getString(timestamp));
                    Log.e("======latitude======", result.getString(latitude));
                    Log.e("======longitude======", result.getString(longitude));

//					System.out.print(result.getString(idColumnIndex) + "\t\t");
//					System.out.println(result.getString(nameColumnIndex));
                    result.next();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
//            try {
//                if (result != null) {
//                    result.close();
//                    result = null;
//                }
//                if (statement != null) {
//                    statement.close();
//                    statement = null;
//                }
//
//            } catch (SQLException sqle) {
//
//            }
        }
    }

    public static boolean execSQL(Connection conn, String sql) {
//        Log.e("============", "execute here!");
        boolean execResult = false;
        if (conn == null) {
            Log.e("============", "conn == null");
            return execResult;
        }

        Statement statement = null;

        try {
            statement = conn.createStatement();
            if (statement != null) {
                Log.e("============", "statement  != null");
//                execResult = statement.executeUpdate(sql);
                execResult = statement.execute(sql);
                Log.e("execResult: ", "------------------------"+execResult);
//                statement.close();
            }
            else{
                Log.e("*********", "statement  == null");
            }
        } catch (SQLException e) {
            e.getStackTrace();
            Log.e("$$$$",e.getMessage());
        }
        Log.e("============", "插入成功");
        return execResult;
    }
}
