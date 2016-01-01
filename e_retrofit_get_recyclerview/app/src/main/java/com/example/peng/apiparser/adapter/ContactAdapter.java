package com.example.peng.apiparser.adapter;

/**
 * Created by peng on 11/5/15.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.peng.apiparser.R;
import com.example.peng.apiparser.model.ContactModel;

import java.util.List;

/**
 * Created by peng on 11/3/15.
 */
public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder>{

    private List<ContactModel> contacts;
    private int rowLayout;
    private Context mContext;

    public ContactAdapter(List<ContactModel> contacts, int rowLayout, Context context) {
        this.contacts = contacts;
        this.rowLayout = rowLayout;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        final ContactModel contact = contacts.get(i);
        viewHolder.tvId.setText(contact.getId().toString());
        viewHolder.tvName.setText(contact.getSurname() + ". " + contact.getFirstName());
        viewHolder.tvEmail.setText(contact.getEmail());
        viewHolder.tvPhone.setText(contact.getPhoneNumber());
        viewHolder.tvAddress.setText(contact.getAddress());
        viewHolder.tvCreatedAt.setText(contact.getCreatedAt());
        viewHolder.tvUpdatedAt.setText(contact.getUpdatedAt());
    }

    @Override
    public int getItemCount() {
        return contacts == null ? 0 : contacts.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tvId;
        public TextView tvName;
        public TextView tvEmail;
        public TextView tvPhone;
        public TextView tvAddress;
        public TextView tvCreatedAt;
        public TextView tvUpdatedAt;

        public ViewHolder(View itemView) {
            super(itemView);
            tvId = (TextView) itemView.findViewById(R.id.contact_id);
            tvName = (TextView) itemView.findViewById(R.id.contact_name);
            tvEmail = (TextView) itemView.findViewById(R.id.contact_email);
            tvPhone = (TextView) itemView.findViewById(R.id.contact_phone);
            tvAddress = (TextView) itemView.findViewById(R.id.contact_address);
            tvCreatedAt = (TextView) itemView.findViewById(R.id.contact_created_at);
            tvUpdatedAt = (TextView) itemView.findViewById(R.id.contact_updated_at);
        }
    }

}
