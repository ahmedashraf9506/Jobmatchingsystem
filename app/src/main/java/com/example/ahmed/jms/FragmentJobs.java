package com.example.ahmed.jms;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
 import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.StringTokenizer;
/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentJobs extends Fragment {// view jobs fragment with its search and sort
    dataAdapterJobs data;
    jobposts datamodel;
    EditText txtsearch;
    Spinner spnorder;
    public  static  String jobid=null;
    GetJobsData job=new GetJobsData();
    ListView lst;
    public FragmentJobs() {// Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vv= inflater.inflate(R.layout.fragment_fragment_jobs, container, false);
        lst=(ListView)vv.findViewById(R.id.lstjobs);
        spnorder=(Spinner)vv.findViewById(R.id.spnorder);
        txtsearch=(EditText)vv.findViewById(R.id.txtsearchjobs);
        txtsearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                final ArrayList<jobposts> joblist;
                joblist = new ArrayList<>(job.GetjobDataSearch(getActivity(),txtsearch.getText().toString()
                        ,spnorder.getSelectedItem().toString()));
                data = new dataAdapterJobs(getActivity(), joblist);
                lst.setAdapter(data);}
        });


        // making an ordered list by selecting order factor from spinner list
        spnorder.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position!=0) {
                    final ArrayList<jobposts> joblist;
                    joblist = new ArrayList<>(job.GetjobDataSearch(getActivity(), txtsearch.getText().toString(),
                            spnorder.getSelectedItem().toString()));
                    data = new dataAdapterJobs(getActivity(), joblist);
                    lst.setAdapter(data);}}
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        final ArrayList<jobposts> joblist;
        joblist = new ArrayList<>(job.GetjobData(getActivity()));
        data = new dataAdapterJobs(getActivity(), joblist);
        lst.setAdapter(data);
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                datamodel = joblist.get(position);
                 jobid=datamodel.getPostID();
              startActivity(new Intent(getActivity(),MainJobDetailsActivity.class));

            }
        });
return vv;
    }}
