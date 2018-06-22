package com.example.ahmed.jms;

import android.content.Context;
import android.widget.Toast;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by on
 */
public class GetCompanies {
    // connection of company list with sql server data base
    Database db=new Database();
    public List<Company> GetCompanyData(Context cv)
    {
        List<Company> listcompdata=new ArrayList<Company>();
        Connection conn=db.ConnectDB(cv);
        if(conn==null)
            Toast.makeText(cv,"Check Internet Access",Toast.LENGTH_LONG).show();
        else
        {
            ResultSet rcompany=db.Search("Select * From Companies");
            try {
                while (rcompany.next())
                {
                    Company co=new Company();
                    co.setCompname(rcompany.getString(2));
                    co.setWebsite(rcompany.getString(10));
                    co.setLat(rcompany.getString(8));
                    co.setLang(rcompany.getString(9));
                    co.setLogopath(rcompany.getString(11));
                    listcompdata.add(co);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return listcompdata;
    }


    // connection of searching company list with sql server data base
    public List<Company> GetCompanyDataFilter(Context cv,String  search)
    {
        List<Company> listcompdata=new ArrayList<Company>();
        Connection conn=db.ConnectDB(cv);
        if(conn==null)
            Toast.makeText(cv,"Check Internet Access",Toast.LENGTH_LONG).show();
        else
        {
            ResultSet rcompany=db.Search("Select * From Companies where companyName like '%"+search+"%'");
            try {
                while (rcompany.next())
                {
                    Company co=new Company();
                    co.setCompname(rcompany.getString(2));
                    co.setWebsite(rcompany.getString(10));
                    co.setLat(rcompany.getString(8));
                    co.setLang(rcompany.getString(9));
                    co.setLogopath(rcompany.getString(11));
                    listcompdata.add(co);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return listcompdata;
    }
}
