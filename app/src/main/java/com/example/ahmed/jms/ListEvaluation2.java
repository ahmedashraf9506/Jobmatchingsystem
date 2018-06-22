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

public class ListEvaluation2 extends AppCompatActivity {
    ListView lst;
    List<String> data=new ArrayList<String>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_evaluation2);
        lst=(ListView)findViewById(R.id.lsteva2);

        ConnectDatabase db=new ConnectDatabase();
        java.sql.Connection conn=db.ConnectDB();
        if(conn==null)
            Toast.makeText(ListEvaluation2.this,"Check Internet Access",Toast.LENGTH_LONG).show();
        else {
            try {
                Statement st=conn.createStatement();
                ResultSet rs=st.executeQuery("Select * From [Evaluation2] where email='"+MainActivity.email+"'");
                while (rs.next()) {

                    data.add("Welcome : "+rs.getString(2)+"\n You applied for : "+rs.getString(3)
                            +"Evaluation Rating is : \n"+ "Presentation Skills : "+rs.getString(4)+"\n"
                            +"Techinical Skills : "+rs.getString(5)+"\n"
                            +"General Look : "+rs.getString(6));
                }
            } catch (SQLException e) {
                Toast.makeText(ListEvaluation2.this,""+e.getMessage(),Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
            adapter=new ArrayAdapter<String>(ListEvaluation2.this,android.R.layout.simple_list_item_1,data);
            lst.setAdapter(adapter);
        }


    }
}
