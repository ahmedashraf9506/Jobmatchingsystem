package com.example.ahmed.jms;


import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ahmed.jms.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddJobsFragment extends Fragment {
EditText txtstartdate,txtenddate,txtbenfits,
         txtexper,txtdetails,txttoex;
    Button btn;
Spinner txtsalary,txtrequir,txttitle;
    public AddJobsFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vv= inflater.inflate(R.layout.fragment_add_jobs, container, false);
        txtbenfits=(EditText) vv.findViewById(R.id.txtmanbenfits);
        txtdetails=(EditText)vv.findViewById(R.id.txtmandetails);
        txtenddate=(EditText)vv.findViewById(R.id.txtmanend);
        txtstartdate=(EditText)vv.findViewById(R.id.txtmanstart);
        txtrequir=(Spinner) vv.findViewById(R.id.txtmanrequir);
        txtexper=(EditText)vv.findViewById(R.id.txtmanfrom);
       // txttoex=(EditText)vv.findViewById(R.id.txtmanto);
        txttitle=(Spinner)vv.findViewById(R.id.txtmatitile);
        txtsalary=(Spinner) vv.findViewById(R.id.txtmansalary);
        btn=(Button)vv.findViewById(R.id.btnmansave);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ConnectDatabase db=new ConnectDatabase();
                java.sql.Connection conn=db.ConnectDB();
                if(conn==null)
                    Toast.makeText(getActivity(),"Check Internet Access",Toast.LENGTH_LONG).show();
                else
                {
                    int result=db.RUNIUD("insert into [JobPosts] values('"+txttitle.getSelectedItem()
                                        +"','"+txtsalary.getSelectedItem()+"','"+txtdetails.getText()
                                        +"','','"+txtstartdate.getText()+"','"+txtenddate.getText()
                                        +"','"+txtrequir.getSelectedItem()+"','','"+txtbenfits.getText()
                                        +"','"+txtexper.getText()+"','1','"+"','','')",getActivity());
                    if(result==1)
                    {
                        AlertDialog.Builder msg=new AlertDialog.Builder(getActivity());
                        msg.setTitle("JSM Add Job");
                        msg.setMessage("Job Post Has Been Saved Done");
                        msg.setIcon(R.drawable.logo);
                        msg.setPositiveButton("Thanks",null);
                        msg.create();
                        msg.show();
                    }
                }

            }
        });
        return vv;
    }
}
