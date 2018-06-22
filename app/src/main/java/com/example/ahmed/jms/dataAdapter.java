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
public class dataAdapter extends ArrayAdapter<Company> {
    Context context;
    ArrayList<Company> mcontact;

    public dataAdapter(Context context, ArrayList<Company> contact) {
        super(context, R.layout.companylayout, contact);
        this.context = context;
        this.mcontact = contact;
    }
    //Holder class to hold company name with its picture

    public class Holder {
        TextView TVname;
        ImageView pic;
    }
//if company pic not found put our System logo

    public View getView(int position, View convertView, ViewGroup parent) {

        // Get the data item for this position
        Company data = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        Holder viewHolder; // view lookup cache stored in tag

        if (convertView == null) {
            viewHolder = new Holder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.companylayout, parent, false);
            viewHolder.TVname = (TextView) convertView.findViewById(R.id.txtcompname);
            viewHolder.pic = (ImageView) convertView.findViewById(R.id.imgcompany);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (Holder) convertView.getTag();
         }

       PicassoClient.downloadImage(context, mcontact.get(position).getLogopath(), viewHolder.pic);

        viewHolder.TVname.setText(data.getCompname());

        return convertView;
    }
}