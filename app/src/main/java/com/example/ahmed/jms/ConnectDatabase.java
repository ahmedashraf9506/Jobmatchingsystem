package com.example.ahmed.jms;
import android.content.Context;
import android.os.StrictMode;
import android.widget.Toast;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectDatabase {
    Connection conn = null;
    // connecting sql server with android studio

    public Connection ConnectDB() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            conn= DriverManager.getConnection("jdbc:jtds:sqlserver://SQL7003.site4now.net/DB_A3CA3E_JobOffersDB"
                    ,"DB_A3CA3E_JobOffersDB_admin","MTI@2468");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }


    // Validation on data entry as Email ....

    public int RUNIUD(String s, Context c) {
        int x=0;
        try {
            Statement stmt = conn.createStatement();
              x = stmt.executeUpdate(s);
            return x;
        } catch (SQLException e) {
            if (e.getErrorCode() == 2627)
                Toast.makeText(c, "Email already exist try again !!! :(", Toast.LENGTH_LONG).show();
            else if (e.getMessage().contains("IX_Customers"))
                Toast.makeText(c, "Email already exist try again !!! :(", Toast.LENGTH_LONG).show();

            else
                Toast.makeText(c, "Sorry Error  : " + e.getMessage() + "\n"
                        + e.getErrorCode(), Toast.LENGTH_LONG).show();
            return x;
        }

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
