package com.example.ahmed.jms;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;

import java.util.ArrayList;
/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentCompany extends Fragment { // Using adapter class to show company list with
    dataAdapter adapter;                        // with searching and sorting in company list list
    Company datamodel;
    GetCompanies cont=new GetCompanies();
    GridView gv;
    EditText txtsearch;
    public FragmentCompany() {// Required empty public constructor
    }@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vv= inflater.inflate(R.layout.fragment_fragment_company, container, false);
        txtsearch=(EditText) vv.findViewById(R.id.txtsearch);
        gv=(GridView)vv.findViewById(R.id.gvcompany);
        txtsearch=(EditText) vv.findViewById(R.id.txtsearch);
        txtsearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                final ArrayList<Company> companylist;
                companylist = new ArrayList<>(cont.GetCompanyDataFilter(getActivity(),txtsearch.getText().toString()));
                adapter = new dataAdapter(getActivity(), companylist);
                gv.setAdapter(adapter);}
        });
        final ArrayList<Company> companylist;
        companylist = new ArrayList<>(cont.GetCompanyData(getActivity()));
        adapter = new dataAdapter(getActivity(), companylist);
        gv.setAdapter(adapter);
        //  Toast.makeText(getApplication(),contacts.size()+"",Toast.LENGTH_SHORT).show();
return vv;}}
