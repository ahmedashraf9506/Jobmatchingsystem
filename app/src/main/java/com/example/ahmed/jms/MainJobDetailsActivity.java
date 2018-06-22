package com.example.ahmed.jms;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

public class MainJobDetailsActivity extends AppCompatActivity {
    TextView txttitle,txtsalary,txtstartdate,txtenddate,txtbenfits,txtrequir,txtexper,txtdetails;
    public static   String lat,lang,titile;
    ImageView img;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_job_details2);

        txtbenfits=(TextView)findViewById(R.id.txtbenfits);
        txtdetails=(TextView)findViewById(R.id.txtdetails);
        txtenddate=(TextView)findViewById(R.id.txtenddate);
        txtstartdate=(TextView)findViewById(R.id.txtstartdate);
        txtrequir=(TextView)findViewById(R.id.txtrequir);
        txtexper=(TextView)findViewById(R.id.txtexp);
        txttitle=(TextView)findViewById(R.id.txtdtitle);
        txtsalary=(TextView)findViewById(R.id.txtdsalary);

        img=(ImageView)findViewById(R.id.imglocation);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
startActivity(new Intent(MainJobDetailsActivity.this,MapsJobActivity.class));
            }
        });
        btn=(Button)findViewById(R.id.btnapply);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder msg=new AlertDialog.Builder(MainJobDetailsActivity.this);
                msg.setTitle("Apply On Job");
                msg.setMessage("To confirm applying on job ADS press Apply ");
                msg.setIcon(R.drawable.logo);
                msg.setPositiveButton("Apply", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String cdate=String.valueOf(Calendar.getInstance().getTime());
                        Database db=new Database();
                        Connection conn=db.ConnectDB(MainJobDetailsActivity.this);
                        if(conn==null)
                            Toast.makeText(getApplication(),"Check Internet Access",Toast.LENGTH_LONG).show();
                        else {
                            int t = db.RunDML("insert into JobApplied (email,jobpostno,applydate) values('" + MainActivity.email + "','" +FragmentJobs.jobid + "','" + cdate + "')", MainJobDetailsActivity.this);
                            if (t >= 1)
                                Toast.makeText(getApplication(), "Congratulations Apply has been saved done", Toast.LENGTH_LONG).show();
                        }
                    }
                }).setNegativeButton("Edit CV", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(MainJobDetailsActivity.this,MainCVActivity.class));
                    }
                });
                msg.create();
                msg.show();
            }
        });
        Database db=new Database();
        Connection conn=db.ConnectDB(MainJobDetailsActivity.this);
        if(conn==null)
            Toast.makeText(getApplication(),"Check Internet Access",Toast.LENGTH_LONG).show();
        else
        {
            ResultSet rcompany=db.Search("Select * From [JobPosts] where PostID = '"+FragmentJobs.jobid+"'");
            try {
                if (rcompany.next())
                {


                    txttitle.setText("Title : "+rcompany.getString(2));
                    titile=rcompany.getString(2);
                  //  int sal= Integer.parseInt(rcompany.getString(3).toString())

                    txtsalary.setText("Salary : "+rcompany.getInt(3));
                    txtbenfits.setText("Speclization : "+rcompany.getString(10));
                    txtrequir.setText("Requirments : "+rcompany.getString(8));
                    txtexper.setText("Experience  : "+rcompany.getString(11) +" To "+rcompany.getString(13)+" Year");
                    txtdetails.setText("Details : "+rcompany.getString(4));
                    txtstartdate.setText("Starting Apply Date : "+rcompany.getString(6));
                    txtenddate.setText("Ending Apply Date : "+rcompany.getString(7));
                    lat=rcompany.getString(14);
                    lang=rcompany.getString(15);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
