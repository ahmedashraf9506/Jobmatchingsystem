package com.example.ahmed.jms;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
// Data Adapter Class to control Array list
public class dataAdapterJobs extends ArrayAdapter<jobposts> {
    Context context;
    ArrayList<jobposts> mcontact;

    public dataAdapterJobs(Context context, ArrayList<jobposts> contact) {
        super(context, R.layout.listjobscontents, contact);
        this.context = context;
        this.mcontact = contact;}

    public class Holder {
        TextView jobtitle,experience,companymname;
        ImageView pic;}

    //Holder class to view job ads

    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        jobposts data = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        Holder viewHolder; // view lookup cache stored in tag

        if (convertView == null) {
            viewHolder = new Holder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.listjobscontents, parent, false);
            viewHolder.jobtitle = (TextView) convertView.findViewById(R.id.txtjobtitle);
            viewHolder.experience = (TextView) convertView.findViewById(R.id.txtexper);
            viewHolder.companymname = (TextView) convertView.findViewById(R.id.txtcompname);
            viewHolder.pic = (ImageView) convertView.findViewById(R.id.imgjobs);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (Holder) convertView.getTag();}
        PicassoClient.downloadImage(context, mcontact.get(position).getImageURL(), viewHolder.pic);
        viewHolder.jobtitle.setText(data.getJobTitile());
        viewHolder.experience.setText("EX : "+data.getExperience()+" - "+data.getExperienceend()+" Year");
        viewHolder.companymname.setText(data.getCompanyname());
        return convertView;
    }
}
