package com.example.ahmed.jms;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class NotificationActivity extends AppCompatActivity {
    ListView lst;
    List<String> data=new ArrayList<String>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        lst=(ListView)findViewById(R.id.lstnotification);

        ConnectDatabase db=new ConnectDatabase();
        java.sql.Connection conn=db.ConnectDB();
        if(conn==null)
            Toast.makeText(NotificationActivity.this,"Check Internet Access",Toast.LENGTH_LONG).show();
        else {
            try {
                Statement st=conn.createStatement();
                ResultSet rs=st.executeQuery("Select * From ViewMatchingApplied where email='"+MainActivity.email+"' and  status is not null");
                while (rs.next()) {
                    //   Toast.makeText(getActivity(),""+rs.getString(1),Toast.LENGTH_LONG).show();

                    data.add("Welcome : "+rs.getString(2)+"\n You applied for : "+rs.getString(12)+" at job : "+rs.getString(14)+"\n"+"Company has been : "+rs.getString(18)+"\n"+"Interview Date : "+rs.getString(19)+"\n"+"At Time : "+rs.getString(20));
                }
            } catch (SQLException e) {
                Toast.makeText(NotificationActivity.this,""+e.getMessage(),Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
            adapter=new ArrayAdapter<String>(NotificationActivity.this,android.R.layout.simple_list_item_1,data);

            lst.setAdapter(adapter);
        }


    }
}
