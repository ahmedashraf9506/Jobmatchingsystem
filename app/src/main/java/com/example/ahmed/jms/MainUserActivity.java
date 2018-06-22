package com.example.ahmed.jms;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
// Main user activity
public class MainUserActivity extends AppCompatActivity {
  private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_user);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        tabLayout.addTab(tabLayout.newTab().setText("Company"));
        tabLayout.addTab(tabLayout.newTab().setText("Jobs "));
         //tabLayout.addTab(tabLayout.newTab().setText("Matching Jobs"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.container);
        final Pageradapter adapter = new Pageradapter(getSupportFragmentManager(), tabLayout.getTabCount());

        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_user, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            getSharedPreferences("MyPref",MODE_PRIVATE)
                    .edit()
                    .clear()
                     .commit();
            startActivity(new Intent(MainUserActivity.this,MainActivity.class));
            return true;
        }
       else if (id == R.id.action_CV) {

            startActivity(new Intent(MainUserActivity.this,MainCVActivity.class));
            return true;
        }
        else if (id == R.id.action_not) {

            startActivity(new Intent(MainUserActivity.this,NotificationActivity.class));
            return true;
        }
        else if (id == R.id.action_ev) {

            startActivity(new Intent(MainUserActivity.this,ListEvaluation2.class));
            return true;
        }
        else if (id == R.id.action_review) {

            startActivity(new Intent(MainUserActivity.this,ReviewCompanyActivity.class));
            return true;
        }
        else if (id == R.id.action_map) {

            startActivity(new Intent(MainUserActivity.this,Map.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

