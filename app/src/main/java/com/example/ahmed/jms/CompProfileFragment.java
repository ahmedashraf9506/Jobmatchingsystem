package com.example.ahmed.jms;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CompProfileFragment extends Fragment {

ListView lst;
    Spinner spnspec,spnexp;
    ArrayAdapter<String> adapter;
    List<String> joplist=new ArrayList<String>();

    public CompProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vv= inflater.inflate(R.layout.fragment_comp_profile, container, false);
        lst=(ListView)vv.findViewById(R.id.lsthr);
        spnexp=(Spinner)vv.findViewById(R.id.spnhrexpernew);
        spnspec=(Spinner)vv.findViewById(R.id.spnspechrnew);

        spnspec.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                joplist.clear();
                ConnectDatabase db=new ConnectDatabase();
                java.sql.Connection conn=db.ConnectDB();

                if(conn==null) // check internet connection
                    Toast.makeText(getActivity(),"Check Internet Access",Toast.LENGTH_LONG).show();
                else // save CV data in data base
                {
                    try {               // viewing matched jobs according to cv in JS Profile
                        int x = 0;
                        Statement st = conn.createStatement();

                        ResultSet rs = st.executeQuery("Select * from CV where cvscinentificdegree  ='" + spnspec.getSelectedItem().toString() + "' and Experience ='"+spnexp.getSelectedItem().toString()+"'");
                         while (rs.next()) {
                            x++;
                            joplist.add(rs.getString(2) + " : / : " + rs.getString(3));
                            adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, joplist);

                        }
                        lst.setAdapter(adapter);
                    } catch (SQLException e) {
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }

                }
                }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return  vv;
    }

}
