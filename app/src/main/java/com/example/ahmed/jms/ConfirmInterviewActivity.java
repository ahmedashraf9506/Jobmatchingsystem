package com.example.ahmed.jms;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ConfirmInterviewActivity extends AppCompatActivity {
    ListView lst;
    List<String> data=new ArrayList<String>();
    List<String> dataemail=new ArrayList<String>();

    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_interview);
        lst=(ListView)findViewById(R.id.lstconfirm);

        ConnectDatabase db=new ConnectDatabase();
        java.sql.Connection conn=db.ConnectDB();
        if(conn==null)
            Toast.makeText(ConfirmInterviewActivity.this,"Check Internet Access",Toast.LENGTH_LONG).show();
        else {
            try {
                Statement st=conn.createStatement();
                ResultSet rs=st.executeQuery("SELECT id ,Email ,JobTitile ,Presentation +Technical +GeberalLoock +SelfConfidenc  as Sum FROM [DB_A3CA3E_JobOffersDB].[dbo].[Evaluation2] order by sum desc");
                while (rs.next()) {
                    //   Toast.makeText(getActivity(),""+rs.getString(1),Toast.LENGTH_LONG).show();
                    dataemail.add(rs.getString(2));
                    data.add("Email : "+rs.getString(2)+"\n You applied for : "+rs.getString(3)+"Interview Rating is : \n"+
                            rs.getString(4));
                }
            } catch (SQLException e) {
                Toast.makeText(ConfirmInterviewActivity.this,""+e.getMessage(),Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
            adapter=new ArrayAdapter<String>(ConfirmInterviewActivity.this,android.R.layout.simple_list_item_1,data);
            lst.setAdapter(adapter);
        }
        lst.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String email=dataemail.get(position);
                SendActiveCode(email);
                AlertDialog.Builder msg=new AlertDialog.Builder(ConfirmInterviewActivity.this);
                msg.setTitle("JSM Registeration");
                msg.setMessage("Confirmation Email has been sent !!!");
                msg.setIcon(R.drawable.logo);
                msg.setPositiveButton("Thanks",null);
                msg.create();
                msg.show();
                return false;
            }
        });
    }

    public void SendActiveCode(final String email)
    {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    final String username = "YourMobileApp2017@gmail.com";
                    final String password = "okok2017";
                    Properties props = new Properties();
                    props.put("mail.smtp.auth", "true");
                    props.put("mail.smtp.starttls.enable", "true");
                    props.put("mail.smtp.host", "smtp.gmail.com");
                    props.put("mail.smtp.port", "587");

                    Session session = Session.getInstance(props,
                            new javax.mail.Authenticator() {

                                protected PasswordAuthentication getPasswordAuthentication() {
                                    return new PasswordAuthentication(username, password);
                                }
                            });
                    try {
                        Message message = new MimeMessage(session);
                        message.setFrom(new InternetAddress("YourMobileApp2017@gmail.com"));
                        message.setRecipients(Message.RecipientType.TO,
                                InternetAddress.parse(email));
                        message.setSubject("JMS Activation Password");
                        message.setText("Dear Job Seeker you are accepted in our job");
                        Transport.send(message);

                    } catch (MessagingException e) {
                        Toast.makeText(getApplication(), e.getMessage(), Toast.LENGTH_LONG).show();

                        throw new RuntimeException(e);
                    }
                } catch (Exception ex) {
                    Toast.makeText(getApplication(), ex.getMessage(), Toast.LENGTH_LONG).show();

                    ex.printStackTrace();
                }
            }
        }).start();
    }
}

