package com.example.ahmed.jms;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ApprovedActivity extends AppCompatActivity {

    CheckBox chk,chkreject;
    EditText txtdate,txtaddress;
    Button btn;
    int mYear;
    int mMonth;
    int mDay;

    int mHour;
    int mMinute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approved);
        chk=(CheckBox)findViewById(R.id.chkapp);
        chkreject=(CheckBox)findViewById(R.id.chkreject);

        txtaddress=(EditText)findViewById(R.id.txtappaddrress);
        txtdate=(EditText)findViewById(R.id.txtappdate);


        txtdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  datePicker();
            }
        });

        txtaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
tiemPicker();
            }
        });
        chk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chk.isChecked()) {
                    chkreject.setChecked(false);
                    txtaddress.setVisibility(View.VISIBLE);
                    txtdate.setVisibility(View.VISIBLE);
                }
            }
        });

        chkreject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkreject.isChecked()) {
                    chk.setChecked(false);
                    txtaddress.setVisibility(View.INVISIBLE);
                    txtdate.setVisibility(View.INVISIBLE);
                }
            }
        });
        btn=(Button)findViewById(R.id.btnconfirm);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectDatabase db=new ConnectDatabase();
                java.sql.Connection conn=db.ConnectDB();
                if(conn==null)
                    Toast.makeText(getApplication(),"Check Internet Access",Toast.LENGTH_LONG).show();
                else
                {
                    String con=null;
                    if(chk.isChecked()) {
                        con = "Approved";

                    }
                    if ((chkreject.isChecked())) {
                        con = "Reject";

                    }
                    int result=db.RUNIUD("update [JobApplied] set Status='"+con+"',Interviewdate='"+txtdate.getText()+"',Location='"+txtaddress.getText()+"' where email='"+MainRatingActivity.email+"' and JobPostNo='"+MainRatingActivity.postno+"'",ApprovedActivity.this);
                    if(result==1)
                    {
                        AlertDialog.Builder msg=new AlertDialog.Builder(ApprovedActivity.this);
                        msg.setTitle("Confirmation");
                        msg.setMessage("Confirm has been send to user");
                        msg.setIcon(R.drawable.logo);
                        msg.setPositiveButton("Thanks",null);
                        msg.create();
                        msg.show();
                    }
                }

            }
        });
    }
    private void datePicker(){

        // Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {


                        SimpleDateFormat simpledateformat = new SimpleDateFormat("EEEE");
                        Date date = new Date(year, monthOfYear, dayOfMonth-1);
                        String dayOfWeek = simpledateformat.format(date);
                        txtdate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year+" at "+dayOfWeek);
                        //*************Call Time Picker Here ********************

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }
    private void tiemPicker(){
        // Get Current Time
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        mHour = hourOfDay;
                        mMinute = minute;

                        txtaddress.setText(hourOfDay + ":" + minute);
                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();
    }
}
