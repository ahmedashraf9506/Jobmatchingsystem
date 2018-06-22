package com.example.ahmed.jms;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ahmed.jms.R;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 * A simple {@link Fragment} subclass.
 show applicants who applied on job ads*/
public class ShowAppliedFragment extends Fragment {
ListView lst;
    List<String> data=new ArrayList<String>();
    ArrayAdapter<String> adapter;
    public ShowAppliedFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View vv= inflater.inflate(R.layout.fragment_show_applied, container, false);
        lst=(ListView)vv.findViewById(R.id.lstapply);


        ImageView fb=(ImageView) vv.findViewById(R.id.fb);
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),MainRatingActivity.class));
            }
        });

        ConnectDatabase db=new ConnectDatabase();
        java.sql.Connection conn=db.ConnectDB();
        if(conn==null)
            Toast.makeText(getActivity(),"Check Internet Access",Toast.LENGTH_LONG).show();
        else {
            try {
                Statement st=conn.createStatement();
                ResultSet rs=st.executeQuery("Select * From JobApplied");
                while (rs.next()) {
                 //   Toast.makeText(getActivity(),""+rs.getString(1),Toast.LENGTH_LONG).show();

                    data.add(rs.getString(1)+" at "+rs.getString(3));
                     }
            } catch (SQLException e) {
                Toast.makeText(getActivity(),""+e.getMessage(),Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
            adapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,data);

            lst.setAdapter(adapter);
        }
        return  vv;
    }
}
