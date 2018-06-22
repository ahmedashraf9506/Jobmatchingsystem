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
public class CompanyFragment extends Fragment {
    //global attributes
    EditText txtsearch;
    dataAdapter adapter;
    Company datamodel;
    GetCompanies cont=new GetCompanies();
    GridView gv;

    public CompanyFragment() {
        // Required empty public constructor
    }
    // onCreate method : main method of activity
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {   // Inflate the layout for this fragment
        // gui integration with code
        // job seeker profile
        View vv= inflater.inflate(R.layout.fragment_fragment_company, container, false);
        gv=(GridView)vv.findViewById(R.id.gvcompany);
        txtsearch=(EditText) vv.findViewById(R.id.txtsearch);

        // Search module
        txtsearch.addTextChangedListener(new TextWatcher()
        {
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
                gv.setAdapter(adapter);
            }
        });
    //Array of companies list
        final ArrayList<Company> companylist;
        companylist = new ArrayList<>(cont.GetCompanyData(getActivity()));
        adapter = new dataAdapter(getActivity(), companylist);
        gv.setAdapter(adapter);
        return vv;
    }
}
