package com.example.ahmed.jms;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import java.util.Random;


import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

// registration activity
public class MainLoginActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    EditText txtfname,txtlname,txtemail,txtphone,txtcollege,txtbdate,txtspec,txtdept;
    int activecode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        txtfname=(EditText)findViewById(R.id.txtfname);
        txtlname=(EditText)findViewById(R.id.txtlname);
        txtphone=(EditText)findViewById(R.id.txtphone);
        txtemail=(EditText)findViewById(R.id.txtname);
        txtbdate=(EditText)findViewById(R.id.txtbdate);
        txtcollege=(EditText)findViewById(R.id.txtcollege);
        txtdept=(EditText)findViewById(R.id.txtdept);
        txtspec=(EditText)findViewById(R.id.txtspec);

        ImageView img=(ImageView)findViewById(R.id.imgregister);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectDatabase db=new ConnectDatabase();
                java.sql.Connection conn=db.ConnectDB();


                if(conn==null)
                    Toast.makeText(getApplication(),"Check Internet Access",Toast.LENGTH_LONG).show();
                else
                {
                    SendActiveCode();
                    int result=db.RUNIUD("insert into JobSeeker values('"+txtfname.getText()+"','"+txtlname.getText()
                            +"','"+txtemail.getText()+"','"+txtbdate.getText()+"','"+txtphone.getText()+"','"+txtcollege.getText()
                            +"','"+txtdept.getText()+"','"+txtspec.getText()+"','"+activecode+"')",MainLoginActivity.this);
                      if(result==1)
                      {
                          AlertDialog.Builder msg=new AlertDialog.Builder(MainLoginActivity.this);
                          msg.setTitle("JSM Registeration");
                          msg.setMessage("Welcome "+txtfname.getText()+" Your Account Has Been Created , Password On Your Email");
                          msg.setIcon(R.drawable.logo);
                          msg.setPositiveButton("Thanks",null);
                          msg.create();
                          msg.show();
                      }
                }
            }
        });
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

// send activation password
    public void SendActiveCode()
    {
        Random r=new Random();
         activecode=r.nextInt(1000000+123456-1)+123456;

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
                                InternetAddress.parse(txtemail.getText().toString()));
                        message.setSubject("JMS Activation Password");
                        message.setText("Welcome : "+txtfname.getText()
                                +"\n Thank you for Registeration JMS Application ,\n Your password  is : "+activecode);
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


    // main MENU
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_register) {
           startActivity(new Intent(this,MainLoginActivity.class));
        } else if (id == R.id.nav_login) {

            startActivity(new Intent(this,MainActivity.class));
        }
        else if (id == R.id.nav_loginhr) {
            startActivity(new Intent(this,MainLoginHRActivity.class));
        }

        else if (id == R.id.nav_Aboutus) {

        }
        else if (id == R.id.nav_Contact) {

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
