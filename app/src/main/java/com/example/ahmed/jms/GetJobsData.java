package com.example.ahmed.jms;

import android.content.Context;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */

public class GetJobsData {    // connection of job list with sql server data base
    Database db=new Database();
    public List<jobposts> GetjobData(Context cv)
    {
        List<jobposts> listcompdata=new ArrayList<jobposts>();
        Connection conn=db.ConnectDB(cv);
        if(conn==null)
            Toast.makeText(cv,"Check Internet Access",Toast.LENGTH_LONG).show();
        else
        {
            ResultSet rcompany=db.Search("Select * From ViewJobs order by postdate desc");
            try {
                while (rcompany.next())
                {
                    jobposts co=new jobposts();
                    co.setJobTitile(rcompany.getString(4));
                    co.setCompanyname(rcompany.getString(2));
                    co.setImageURL(rcompany.getString(7));
                    co.setExperience(rcompany.getString(13));
                    co.setExperienceend(rcompany.getString(14));
                    co.setPostID(rcompany.getString(3));
                    listcompdata.add(co);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return listcompdata;
    }

    public List<jobposts> GetjobDataSearch(Context cv,String search, String orderby)
    {
        List<jobposts> listcompdata=new ArrayList<jobposts>();
        Connection conn=db.ConnectDB(cv);
        if(conn==null)
            Toast.makeText(cv,"Check Internet Access",Toast.LENGTH_LONG).show();
        else
        {
            ResultSet rcompany=db.Search("Select * From ViewJobs where JobTitile like '%"+search+"%' order by "+orderby+" asc ");
            try {
                while (rcompany.next())
                {
                    jobposts co=new jobposts();
                    co.setJobTitile(rcompany.getString(4));
                    co.setCompanyname(rcompany.getString(2));
                    co.setImageURL(rcompany.getString(7));
                    co.setExperience(rcompany.getString(13));
                    co.setExperienceend(rcompany.getString(14));
                    listcompdata.add(co);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }


        }
        return listcompdata;
    }
}
