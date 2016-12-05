package com.example.benson.foodpacker2;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.login.widget.ProfilePictureView;
import com.squareup.picasso.Picasso;

import org.java_websocket.util.Base64;

import java.net.URL;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Benson on 2016/11/1.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    Context context;

    List<GetDataAdapter> getDataAdapter;

    String menu;

    public RecyclerViewAdapter(List<GetDataAdapter> getDataAdapter, Context context){

        super();

        this.getDataAdapter = getDataAdapter;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        final GetDataAdapter getDataAdapter1 =  getDataAdapter.get(position);

        holder.NameTextView.setText(getDataAdapter1.getName());

        holder.IdTextView.setText(String.valueOf(getDataAdapter1.getId()));

        //holder.PhoneNumberTextView.setText(getDataAdapter1.getPhone_number());


        menu=getDataAdapter1.getMenu_page2();


        holder.profilePictureView.setProfileId(getDataAdapter1.getPhone_number());





        holder.SubjectTextView.setText(getDataAdapter1.getSubject());
        holder.bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                /// button click event



                AlertDialog.Builder dialog = new AlertDialog.Builder(v.getContext());
                dialog.setTitle(getDataAdapter1.getName()+"的訂單");
                // 承接傳過來的字串，顯示在對話框之中
                dialog.setMessage(menu);
                // 設定 PositiveButton 也就是一般 確定 或 OK 的按鈕

                //設定 NegativeButton 也就是一般 取消 或 Cancel 的按鈕
                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                        // 當使用者按下 取消鈕 後所執行的動作
                    }
                });
                dialog.show();


            }
        });

    }

    @Override
    public int getItemCount() {

        return getDataAdapter.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public TextView IdTextView;
        public TextView NameTextView;
        public TextView SubjectTextView;
        public Button bt;
        ProfilePictureView profilePictureView;


        public ViewHolder(View itemView) {

            super(itemView);

            IdTextView = (TextView) itemView.findViewById(R.id.tv_blah) ;
            NameTextView = (TextView) itemView.findViewById(R.id.friend_name) ;
            SubjectTextView = (TextView) itemView.findViewById(R.id.restaurant) ;
            bt = (Button) itemView.findViewById(R.id.moreinfo);
            profilePictureView=(ProfilePictureView)itemView.findViewById(R.id.friendProfilePicture);

        }
    }


}
