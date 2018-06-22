package com.example.ahmed.jms;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class Map extends AppCompatActivity {

    private WebView mWebview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        mWebview = (WebView) findViewById(R.id.activity_map);
        WebSettings webSettings = mWebview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mWebview.loadUrl("https://proj01.maps.arcgis.com/apps/webappviewer/index.html?id=13f1d3a6ea9d44d78d2c246415e3d9cc");
    }
}
