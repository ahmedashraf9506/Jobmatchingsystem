package com.example.ahmed.jms;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentAvailibile extends Fragment {
    // available jobs list
    ListView lst;
    ArrayAdapter<String> adapter;
     List<String> joplist=new ArrayList<String>();
    List<String> joplistid=new ArrayList<String>();

    public FragmentAvailibile() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vv= inflater.inflate(R.layout.fragment_fragment_availibile, container, false);
        lst=(ListView)vv.findViewById(R.id.lstjobsfrags);

        ConnectDatabase db=new ConnectDatabase();
        java.sql.Connection conn=db.ConnectDB();

        if(conn==null) // check internet connection
            Toast.makeText(getActivity(),"Check Internet Access",Toast.LENGTH_LONG).show();
        else // save CV data in data base
        {
            try {               // viewing matched jobs according to cv in JS Profile
                int x = 0;
                Statement st = conn.createStatement();

                ResultSet rs = st.executeQuery("Select * from CV where cvemail ='" + MainActivity.email + "'");
               rs.next();
                 rs = st.executeQuery("Select * from jobads where jobexp ='" + rs.getString(24).toString() + "' and jobspecialization ='"
                        + rs.getString(14).toString() + "' and jobsalary ='" + rs.getString(23).toString() + "'");
                while (rs.next()) {
                    x++;
                    joplist.add(rs.getString(9) + " in " + rs.getString(8));
                    joplistid.add(rs.getString(1).toString());
                     adapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,joplist);

                }
                lst.setAdapter(adapter);
             } catch (SQLException e) {
                Toast.makeText(getActivity(),e.getMessage(),Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }



        }
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                android.app.AlertDialog.Builder msg=new android.app.AlertDialog.Builder(getActivity());
                msg.setTitle("Apply On Job");
                msg.setMessage("Are you sure apply for this job ?");
                msg.setIcon(R.drawable.logo);
                msg.setPositiveButton("Apply Now", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String cdate=String.valueOf(Calendar.getInstance().getTime());
                        Database db=new Database();
                        Connection conn=db.ConnectDB(getActivity());
                        if(conn==null)
                            Toast.makeText(getActivity(),"Check Internet Access",Toast.LENGTH_LONG).show();
                        else {
                            int t = db.RunDML("insert into JobApplied (Email,JobPostNo,ApplyDate) values('" + MainActivity.email + "','" +joplistid.get(position) + "','" + cdate + "')", getActivity());
                            if (t >= 1)
                                Toast.makeText(getActivity(), "Congratulations Apply has been saved done", Toast.LENGTH_LONG).show();
                        }
                    }
                }).setNegativeButton("Edit CV", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(getActivity(),MainCVActivity.class));
                    }
                });
                msg.create();
                msg.show();
            }
        });
        return  vv;

    }

}
