package com.example.ahmed.jms;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
 // A list view of job ads
public class JopsActivity extends AppCompatActivity {
    ListView lst;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jops);
        lst=(ListView)findViewById(R.id.lstjobs);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,ExperFragment.joplist);
        lst.setAdapter(adapter);
    }
}
