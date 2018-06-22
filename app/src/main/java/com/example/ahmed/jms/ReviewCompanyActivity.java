package com.example.ahmed.jms;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ReviewCompanyActivity extends AppCompatActivity {

    TextView txtemail,txtcompany,txtcomment;
    Button btnrate;
    RatingBar rt;
    Spinner spn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_company);

        txtcompany=(TextView) findViewById(R.id.txtcompanyrivew);
        txtemail=(TextView) findViewById(R.id.txtemailrivew);
        txtcomment=(TextView) findViewById(R.id.txtcomment);

        txtemail.setText(MainActivity.email);
        rt=(RatingBar)findViewById(R.id.rate);
        btnrate=(Button)findViewById(R.id.btnsaverivew);
        btnrate.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        spn=(Spinner)findViewById(R.id.spinner);

        ConnectDatabase db=new ConnectDatabase();
        java.sql.Connection conn=db.ConnectDB();

        if(conn==null)
            Toast.makeText(getApplication(),"Check Internet Access",Toast.LENGTH_LONG).show();
        else
        {

            int result=db.RUNIUD("insert into ReviewCompany values('"+txtemail.getText()+"','"+spn.getSelectedItem()+"','"+rt.getRating()+"','"+txtcomment.getText()+"')",ReviewCompanyActivity.this);
            if(result==1)
            {
                AlertDialog.Builder msg=new AlertDialog.Builder(ReviewCompanyActivity.this);
                msg.setTitle("JSM Registeration");
                msg.setMessage("Evaluation  Saved");
                msg.setIcon(R.drawable.logo);
                msg.setPositiveButton("Thanks",null);
                msg.create();
                msg.show();
            }
        }
    }



});
    }

    public void action(View view) {
        Intent i = new Intent(ReviewCompanyActivity.this,Map.class);
        startActivity(i);
    }

    public void openmap(View view) {
        Intent ii=new Intent(Intent.ACTION_VIEW, Uri.parse("https://proj01.maps.arcgis.com/apps/webappviewer/index.html?id=13f1d3a6ea9d44d78d2c246415e3d9cc"));
        startActivity(ii);
    }

}
