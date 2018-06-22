package com.example.ahmed.jms;
import android.content.Intent;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
public class ExperFragment extends Fragment {
    public  static
    String jobtitle,trainig,companyname,experience,level,coursename,courseyear,coursepereiod;
    EditText txtcompany,txtcoursename,txtcourseyear,txtcourseperiod,txttraining;
    Spinner txtjobtitle,spnexper,spnlevel;

    public static List<String> joplist=new ArrayList<String>();
    public ExperFragment() {
        // Required empty public constructor
    }
    // 2nd Cv Fragment experience details
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vv= inflater.inflate(R.layout.fragment_exper, container, false);
        txtjobtitle=(Spinner) vv.findViewById(R.id.txtjobtitle);
         txtcompany=(EditText)vv.findViewById(R.id.txtcomp);
        txtcoursename=(EditText)vv.findViewById(R.id.txtcoursename);
        txttraining=(EditText)vv.findViewById(R.id.txttraining);
        txtcourseyear=(EditText)vv.findViewById(R.id.txtyearcourse);
        txtcourseperiod=(EditText)vv.findViewById(R.id.txtperiodcourse);
        spnexper=(Spinner)vv.findViewById(R.id.spnexphr);
        spnlevel=(Spinner)vv.findViewById(R.id.spnlevelhr);
        Button btn=(Button)vv.findViewById(R.id.btnsavecvs);


        ConnectDatabase db=new ConnectDatabase();
        java.sql.Connection conn=db.ConnectDB();
        if(conn==null)
            Toast.makeText(getActivity(),"Check Internet Access",Toast.LENGTH_LONG).show();
        else {
            try {
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery("Select * From [CV] where cvemail='" + MainActivity.email + "'");
                if (rs.next()) {

                    txtjobtitle.setSelection(2);
                    txtcompany.setText(rs.getString(17));
                    txtcoursename.setText(rs.getString(25));
                    txttraining.setText(rs.getString(19));
                    txtcourseyear.setText(rs.getString(21));
                    txtcourseperiod.setText(rs.getString(20));
                    spnexper.setSelection(2);
                    spnlevel.setSelection(1);
                }
            } catch (SQLException e) {
                Toast.makeText(getActivity(), "" + e.getMessage(), Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }


        }

// saving data entry of experience frag..

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jobtitle=txtjobtitle.getSelectedItem().toString();
                companyname=txtcompany.getText().toString();
                coursename=txtcoursename.getText().toString();
                courseyear=txtcourseyear.getText().toString();
                coursepereiod=txtcourseperiod.getText().toString();
                experience=spnexper.getSelectedItem().toString();
                trainig=txttraining.getText().toString();
                level=spnlevel.getSelectedItem().toString();

                ConnectDatabase db=new ConnectDatabase();
                java.sql.Connection conn=db.ConnectDB();



                if(conn==null) // check internet connection
                    Toast.makeText(getActivity(),"Check Internet Access",Toast.LENGTH_LONG).show();
                else // save CV data in data base
                {
                    int result=db.RUNIUD("insert into CV values('"+MainActivity.email+"','"
                            +PersonalFragment.country+"','"+PersonalFragment.city+"','"+PersonalFragment.gender+"','"+PersonalFragment.militry+"','"
                            +PersonalFragment.nativelan+"','"+PersonalFragment.spoken+"','"+PersonalFragment.driving+"','"+EducationFragment.univeristy+"'," +
                            "'"+EducationFragment.gradeyear+"','"+EducationFragment.grade+"','"+EducationFragment.specintfic+"','"+EducationFragment.specliz+"'" +
                            ",'"+ExperFragment.jobtitle+"','"+ExperFragment.companyname+"','"+spnlevel.getSelectedItem()+"','"
                            +ExperFragment.trainig+"','"+ExperFragment.coursepereiod+"','"+ExperFragment.courseyear+"','"
                            +PersonalFragment.location+"','"+EducationFragment.salary+"','"+spnexper.getSelectedItem()+"','"+ExperFragment.coursename+"','"+PersonalFragment.age+"')",getActivity());
                    if(result==1)  // Alert dialog confirming saving cv
                    {
                        AlertDialog.Builder msg=new AlertDialog.Builder(getActivity());
                        msg.setTitle("JSM Registeration");
                        msg.setMessage("Thank you "+MainActivity.email+" Your CV has been Saved ");
                        msg.setIcon(R.drawable.logo);
                        msg.setPositiveButton("Thanks",null);
                        msg.create();
                        msg.show();
                        try {               // viewing matched jobs according to cv in JS Profile
                            int x=0;
                            Statement st=conn.createStatement();
                            ResultSet rs=st.executeQuery("Select * from jobads where jobexp ='"+ExperFragment.experience+"' and jobspecialization ='"
                                                         +EducationFragment.specliz +"' and jobsalary ='"+EducationFragment.salary+"'");
                            while (rs.next())
                            {
                                x++;
                                joplist.add(rs.getString(8)+ " in "+rs.getString(9));
                            }
                                startActivity(new Intent(getActivity(),MainUserActivity.class));
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }}}
            }
        });
        return vv;
    }}
