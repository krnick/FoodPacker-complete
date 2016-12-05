package com.example.benson.foodpacker2;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.login.widget.ProfilePictureView;

import java.util.List;

/**
 * Created by Benson on 2016/11/1.
 */
public class Continus_OrderAdapter extends RecyclerView.Adapter<Continus_OrderAdapter.ViewHolder> {
    Context context;

    List<GetDataAdapter> getDataAdapter;

    public Continus_OrderAdapter(List<GetDataAdapter> getDataAdapter, Context context) {

        super();

        this.getDataAdapter = getDataAdapter;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.continus_order_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        final GetDataAdapter getDataAdapter1 = getDataAdapter.get(position);

        holder.NameTextView.setText(getDataAdapter1.getName());

        holder.IdTextView.setText(String.valueOf(getDataAdapter1.getId()));

        //holder.PhoneNumberTextView.setText(getDataAdapter1.getPhone_number());

        holder.SubjectTextView.setText(getDataAdapter1.getSubject());

        holder.profilePictureView.setProfileId(getDataAdapter1.getPhone_number());


    }

    @Override
    public int getItemCount() {

        return getDataAdapter.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView IdTextView;
        public TextView NameTextView;
        public TextView SubjectTextView;
        ProfilePictureView profilePictureView;

        public ViewHolder(View itemView) {

            super(itemView);

            IdTextView = (TextView) itemView.findViewById(R.id.tv_blah);
            NameTextView = (TextView) itemView.findViewById(R.id.friend_name);
            SubjectTextView = (TextView) itemView.findViewById(R.id.restaurant);

            profilePictureView = (ProfilePictureView) itemView.findViewById(R.id.friendProfilePicture);

        }
    }
}
