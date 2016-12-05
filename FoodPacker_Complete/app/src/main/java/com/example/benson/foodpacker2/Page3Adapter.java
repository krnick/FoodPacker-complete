package com.example.benson.foodpacker2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.facebook.login.widget.ProfilePictureView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by nick on 2016/12/1.
 */
public class Page3Adapter extends SimpleAdapter {
    private Context ctx;
    private ArrayList<HashMap<String, String>> results;
    ArrayList<HashMap<String, String>> data;


    private String desg,id,sal,name,image;
    public Page3Adapter(Context context, ArrayList<HashMap<String, String>> data, int resource, String[] from, int[] to) {
        super(context, data, resource, from, to);
        this.results = data;
        this.ctx=context;
    }

    public View getView(int position, View view, ViewGroup parent){


        View v = view;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.page3_list_item, parent, false);
        }
        TextView desgview=(TextView)v.findViewById(R.id.desg);
        TextView salview=(TextView)v.findViewById(R.id.salary);
        TextView scoreview=(TextView)v.findViewById(R.id.score);
        TextView nameview=(TextView)v.findViewById(R.id.name);
        TextView feeview=(TextView)v.findViewById(R.id.fee);

        ProfilePictureView profilePictureView = (ProfilePictureView) v.findViewById(R.id.friendProfilePicture);



//        idview.setText(results.get(position).get(Config.TAG_ID));
//        desgview.setText(results.get(position).get(Config.TAG_DESG));
        salview.setText(results.get(position).get(Config.TAG_SAL));
        nameview.setText(results.get(position).get(Config.TAG_NAME));
        profilePictureView.setProfileId(results.get(position).get(Config.Tag_IMAGE));
        feeview.setText(results.get(position).get("fee"));
        scoreview.setText(results.get(position).get("scoredata"));







        return v;
    }
}