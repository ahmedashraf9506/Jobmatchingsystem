package com.example.ahmed.jms;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
// HR login
public class MainLoginHRActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
        EditText txtemail,txtpass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login_hr);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        txtemail=(EditText)findViewById(R.id.txtemailloginhr);
        txtpass=(EditText)findViewById(R.id.txtpasslognhr);

        ImageView img=(ImageView)findViewById(R.id.imgloginhr);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ConnectDatabase db=new ConnectDatabase();
                java.sql.Connection conn=db.ConnectDB();
                if(conn==null)
                    Toast.makeText(getApplication(),"Check Internet Access",Toast.LENGTH_LONG).show();
                else {

                    try {
                        Statement st=conn.createStatement();
                        ResultSet rs=st.executeQuery("Select * From Companies where Username ='"+txtemail.getText()
                                +"' and password='"+txtpass.getText()+"'");
                        if(rs.next())
                            startActivity(new Intent(MainLoginHRActivity.this,MainHRManagerActivity.class));


                        else
                        {
                            AlertDialog.Builder msg=new AlertDialog.Builder(MainLoginHRActivity.this);
                            msg.setTitle("JSM Registeration");
                            msg.setMessage("Invaild username / password try again !!!");
                            msg.setIcon(R.drawable.logo);
                            msg.setPositiveButton("Thanks",null);
                            msg.create();
                            msg.show();

                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
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

        }   else if (id == R.id.nav_Contact) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
