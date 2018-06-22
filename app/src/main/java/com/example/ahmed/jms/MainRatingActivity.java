package com.example.ahmed.jms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainRatingActivity extends AppCompatActivity {

    List<String> lstname=new ArrayList<String>();
    List<String> lstemail=new ArrayList<String>();
    List<String> lstjobpostno=new ArrayList<String>();
    List<String> lstid=new ArrayList<String>();
    List<String> lstrating=new ArrayList<String>();
    public static String email=null;
    public static String postno=null;
    public static String postname=null;
    ListView lstrate;
    Spinner spnjob;
ArrayAdapter adapter,adapterrate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_rating);

       lstrate=(ListView)findViewById(R.id.lstrating);

        spnjob=(Spinner)findViewById(R.id.spnpost);
        ConnectDatabase db=new ConnectDatabase();
        java.sql.Connection conn=db.ConnectDB();
        if(conn==null)
            Toast.makeText(getApplication(),"Check Internet Access",Toast.LENGTH_LONG).show();
        else {

            ResultSet rs = db.Search("Select * from ViewMatchingApplied");
            try {
                while (rs.next()) {
                    lstid.add(rs.getString(15));
                    lstname.add(rs.getString(14));
                    lstemail.add(rs.getString(1));
                    lstjobpostno.add(rs.getString(15));
                    adapter = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, lstname);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        spnjob.setAdapter(adapter);

        spnjob.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                ConnectDatabase db=new ConnectDatabase();
                java.sql.Connection conn=db.ConnectDB();
                if(conn==null)
                    Toast.makeText(getApplication(),"Check Internet Access",Toast.LENGTH_LONG).show();
                else {

                    int count=0;
                    String ex=null,speclization=null;
                    lstemail.clear();
                    lstjobpostno.clear();

                    ResultSet rsrate = db.Search("Select * from ViewMatchingApplied where JobTitile ='"+spnjob.getSelectedItem().toString()+"'");
                    lstrating.clear();
                    try {
                        while (rsrate.next()) {

                            lstname.add(rsrate.getString(14));
                            lstemail.add(rsrate.getString(1));
                            lstjobpostno.add(rsrate.getString(15));

                            // Compare experience of job ADS  with   experience of job seeker CV
                            if(rsrate.getString(7).toString().equals(rsrate.getString(11).toString()))
                               count+=5;
                            // Compare specialization of job ADS with specialization of job seeker CV
                            if(rsrate.getString(8).toString().equals(rsrate.getString(10).toString()))
                                count+=5;
                            // Compare Scientific degree of job ADS with Scientific degree of job seeker CV
                            if(rsrate.getString(16).toString().equals(rsrate.getString(17).toString()))
                                count+=5;


                            if(count>15)
                                count=15;
                            lstrating.add(rsrate.getString(2) + "\n" + rsrate.getString(1) + " \n" + rsrate.getString(3)
                                    +"    "+"\n"+"Rating : "+count+"/15");
                            showrating();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
       }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        lstrate.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                email=lstemail.get(position).toString();
                postno=lstjobpostno.get(position).toString();
                startActivity(new Intent(MainRatingActivity.this,ApprovedActivity.class));
            }
        });

        lstrate.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                email=lstemail.get(position).toString();
                postname=lstname.get(position).toString();
                startActivity(new Intent(MainRatingActivity.this,Evaluation2Activity.class));

                return true;
            }
        });
    }
    private void showrating()
    {
        adapterrate=new ArrayAdapter(this,android.R.layout.simple_list_item_1,lstrating);
        lstrate.setAdapter(adapterrate);
    }
}
