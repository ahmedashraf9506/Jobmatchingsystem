package com.example.ahmed.jms;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.example.ahmed.jms.R;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EducationFragment extends Fragment {
    public  static String univeristy,gradeyear,grade,specintfic,specliz,salary;
    EditText txtgradeyear,txtgrade,txtsalary;
    Spinner txtuniv,spnspentific,spnspec;

    public EducationFragment() {
        // Required empty public constructor
    }
    // 1st Cv Fragment Education details
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        // integration of gui with code
        View vv= inflater.inflate(R.layout.fragment_education, container, false);
        txtuniv=(Spinner)vv.findViewById(R.id.txtuniver);
        txtgradeyear=(EditText)vv.findViewById(R.id.txtgradyear);
        txtgrade=(EditText)vv.findViewById(R.id.txtGrade);
        txtsalary=(EditText)vv.findViewById(R.id.txtsalary);
        spnspentific=(Spinner)vv.findViewById(R.id.spnscentific);
        spnspec=(Spinner)vv.findViewById(R.id.spnspecliz);
        Button btn=(Button)vv.findViewById(R.id.btneducation);



        ConnectDatabase db=new ConnectDatabase();
        java.sql.Connection conn=db.ConnectDB();
        if(conn==null)
            Toast.makeText(getActivity(),"Check Internet Access",Toast.LENGTH_LONG).show();
        else {
            try {
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery("Select * From [CV] where cvemail='" + MainActivity.email + "'");
                if (rs.next()) {

                    txtuniv.setSelection(2);
                    txtgradeyear.setText(rs.getString(12));
                    txtgrade.setText(rs.getString(11));
                    txtsalary.setText(rs.getString(23));
                    spnspentific.setSelection(1);
                    spnspec.setSelection(2);

                }
            } catch (SQLException e) {
                Toast.makeText(getActivity(), "" + e.getMessage(), Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }


        }


        //saving data entry of education fragment
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                univeristy=txtuniv.getSelectedItem().toString();
                gradeyear=txtgradeyear.getText().toString();
                grade=txtgrade.getText().toString();
                specintfic=spnspentific.getSelectedItem().toString();
                specliz=spnspec.getSelectedItem().toString();
                salary=txtsalary.getText().toString();
            }
        });
        return  vv;
    }
}
