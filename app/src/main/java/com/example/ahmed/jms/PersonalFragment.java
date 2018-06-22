package com.example.ahmed.jms;


 import android.os.Bundle;

 import android.support.v4.app.Fragment;
 import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
 import android.widget.ArrayAdapter;
 import android.widget.Button;
 import android.widget.EditText;
 import android.widget.RadioButton;
 import android.widget.Spinner;
 import android.widget.Switch;
 import android.widget.Toast;

 import com.example.ahmed.jms.R;

 import java.sql.ResultSet;
 import java.sql.SQLException;
 import java.sql.Statement;

/**
 * A simple {@link Fragment} subclass.
 */
public class PersonalFragment extends Fragment {

public  static String age,name,country,city,gender,militry,nativelan,spoken,location,driving;

    Button btnsavepersonal;
    Spinner spncountry,spncity,spnmilitry,spnnative,spnspoken,spnlocation;
    Switch swdriving;
    RadioButton rdmale,rdfemale;
    EditText txtname,txtage;
    public PersonalFragment() {
        // Required empty public constructor
    }
    // cv 3rd fragment Personal data
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vv= inflater.inflate(R.layout.fragment_personal, container, false);
        spncountry=(Spinner)vv.findViewById(R.id.spncountry);
        spncity=(Spinner)vv.findViewById(R.id.spncity);
        spnmilitry=(Spinner)vv.findViewById(R.id.spnmilitry);
        spnnative=(Spinner)vv.findViewById(R.id.spnnative);
        spnspoken=(Spinner)vv.findViewById(R.id.spnspoken);
        spnlocation=(Spinner)vv.findViewById(R.id.spnlocation);
        swdriving=(Switch)vv.findViewById(R.id.swderive);
       rdmale=(RadioButton)vv.findViewById(R.id.rdmale);
        rdfemale=(RadioButton)vv.findViewById(R.id.rdfemale);
        btnsavepersonal=(Button)vv.findViewById(R.id.btnsavepersonal);
        txtname=(EditText)vv.findViewById(R.id.txtname);
        txtage=(EditText)vv.findViewById(R.id.txtage);


        ConnectDatabase db=new ConnectDatabase();
        java.sql.Connection conn=db.ConnectDB();
        if(conn==null)
            Toast.makeText(getActivity(),"Check Internet Access",Toast.LENGTH_LONG).show();
        else {
            try {
                Statement st=conn.createStatement();
                ResultSet rs=st.executeQuery("Select * From [CV] where cvemail='"+MainActivity.email+"'");
                if (rs.next()) {

                    spncountry.setSelection(1);
                    spncity.setSelection(2);
                    spnmilitry.setSelection(1);
                    spnnative.setSelection(1);
                    spnspoken.setSelection(1);
                    spnlocation.setSelection(1);
                    swdriving.setChecked(true);
                    rdmale.setChecked(true);
                     txtname.setText(rs.getString(2));
                    txtage.setText(rs.getString(22));


                }
            } catch (SQLException e) {
                Toast.makeText(getActivity(),""+e.getMessage(),Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }

        }




        btnsavepersonal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                country=spncountry.getSelectedItem().toString();
                city=spncity.getSelectedItem().toString();
                militry=spnmilitry.getSelectedItem().toString();
                nativelan=spnnative.getSelectedItem().toString();
                spoken=spnspoken.getSelectedItem().toString();
                location=spnlocation.getSelectedItem().toString();
                name=txtname.getText().toString();
                age=txtage.getText().toString();
               if(swdriving.isChecked())
                driving="No";
                else
                    driving="Yes";

                if(rdfemale.isChecked())
                    gender="Female";
                else
                    gender="Male";
            }
        });
        return vv;
    }

}
