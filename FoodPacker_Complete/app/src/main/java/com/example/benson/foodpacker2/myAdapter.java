package com.example.benson.foodpacker2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Benson on 2016/11/21.
 */
public class myAdapter extends BaseAdapter {
    //    int images[]={R.drawable.icon_inbox,R.drawable.icon_outbox,R.drawable.icon_profile};

    LayoutInflater minflator;
    Context context;
    ArrayList<String> order;


    public  myAdapter(Context context, ArrayList<String> order){
        this.context=context;
        minflator=LayoutInflater.from(context);
        this.order=order;
    }



    //得到有幾列
    @Override
    public int getCount() {
        return order.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if(convertView==null){
//            實體化R.layout.mylayout
            convertView=minflator.inflate(R.layout.mylayout,null);

            holder=new ViewHolder();

            holder.text=(TextView)convertView.findViewById(R.id.numtext);
//            holder.icon=(ImageView)convertView.findViewById(R.id.numimage);
            holder.num=(NumberPicker)convertView.findViewById(R.id.numpick);


            convertView.setTag(holder);
        }else
        {
//            取出之前的三個元件
            holder=(ViewHolder)convertView.getTag();
        }


        //設定資料
        holder.text.setText(order.toArray()[position].toString());
//        holder.icon.setImageResource(images[position]);
        holder.num.setTag(position);
        NumberPicker picker=(NumberPicker)convertView.findViewById(R.id.numpick);
        picker.setMaxValue(10);
        picker.setMinValue(0);
        picker.setValue(1);

        return convertView;
    }






    static class ViewHolder{
        TextView text;
        ImageView icon;
        NumberPicker num;
    }
}
