package com.example.ahmed.jms;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Evaluation2Activity extends AppCompatActivity {
    Spinner txtpresent,txttech,txtgeneral,spnself;
    TextView txtemail,txtpost;
    Button btnsaveeval,btnconfirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluation2);


        txtpost=(TextView) findViewById(R.id.txt2post);
        txtemail=(TextView) findViewById(R.id.txt2email);

        txtpresent=(Spinner) findViewById(R.id.txtpresen);
        txttech=(Spinner) findViewById(R.id.txttech);
        txtgeneral=(Spinner) findViewById(R.id.txtloock);
        spnself=(Spinner) findViewById(R.id.spnself);

        txtemail.setText(MainRatingActivity.email.toString());
        txtpost.setText(MainRatingActivity.postname.toString());

        btnconfirm=(Button)findViewById(R.id.btncontract);
        btnconfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Evaluation2Activity.this,ConfirmInterviewActivity.class));
            }
        });

        ConnectDatabase db=new ConnectDatabase();
        java.sql.Connection conn=db.ConnectDB();
            ResultSet rs=db.Search("Select * from [JobSeeker] where email='"+MainRatingActivity.email+"'");
        try {
            if (rs.next()) {
            txtemail.setText(rs.getString(1)+" "+rs.getString(2));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }


            btnsaveeval=(Button)findViewById(R.id.btnsaveevaluation);

        btnsaveeval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectDatabase db=new ConnectDatabase();
                java.sql.Connection conn=db.ConnectDB();


                if(conn==null)
                    Toast.makeText(getApplication(),"Check Internet Access",Toast.LENGTH_LONG).show();
                else
                {

                    int result=db.RUNIUD("insert into Evaluation2 values('"+MainRatingActivity.email.toString()
                            +"','"+txtpost.getText() +"','"+txtpresent.getSelectedItem()+"','"+txttech.getSelectedItem()
                            +"','"+txtgeneral.getSelectedItem() +"','"+spnself.getSelectedItem()
                            +"')",Evaluation2Activity.this);
                    if(result==1)
                    {
                        AlertDialog.Builder msg=new AlertDialog.Builder(Evaluation2Activity.this);
                        msg.setTitle("JSM Registeration");
                        msg.setMessage("Evaluation result saved");
                        msg.setIcon(R.drawable.logo);
                        msg.setPositiveButton("Thanks",null);
                        msg.create();
                        msg.show();
                    }
                }
            }
        });


    }
}
