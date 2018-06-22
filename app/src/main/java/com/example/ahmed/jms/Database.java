package com.example.ahmed.jms;
import android.content.Context;
import android.os.StrictMode;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    Connection conn;

    public Connection ConnectDB(Context cont)
    {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            conn= DriverManager.getConnection("jdbc:jtds:sqlserver://SQL7003.site4now.net/DB_A3CA3E_JobOffersDB"
                    ,"DB_A3CA3E_JobOffersDB_admin","MTI@2468");
        } catch (ClassNotFoundException e) {
            Toast.makeText(cont,e.getMessage(),Toast.LENGTH_LONG).show();
            e.printStackTrace();
        } catch (SQLException e) {
            Toast.makeText(cont,e.getMessage(),Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
        return conn;
    }

    public int RunDML(String st,Context cont)
    {
        int result=0;
        try {
            Statement mohamed=conn.createStatement();
              result=mohamed.executeUpdate(st);
        } catch (SQLException e) {

            if(e.getMessage().contains("PRIMARY KEY"))
                Toast.makeText(cont,"Sorry You are Apply before",Toast.LENGTH_LONG).show();
            e.printStackTrace();}
        return result;
    }

    public ResultSet Search(String st)
    {
        ResultSet r=null;
        try {
            Statement statement=conn.createStatement();
              r=statement.executeQuery(st);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return r;
    }
}