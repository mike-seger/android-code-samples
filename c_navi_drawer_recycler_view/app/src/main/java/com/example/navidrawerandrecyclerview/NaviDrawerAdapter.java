package com.example.navidrawerandrecyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;


public class NaviDrawerAdapter extends RecyclerView.Adapter<NaviDrawerAdapter.DrawerViewHolder> {

    public final static int TYPE_HEADER = 0;
    public final static int TYPE_MENU = 1;

    private List<NaviMenuItem> drawerMenuList;
    private OnItemSelecteListener mListener;

    public NaviDrawerAdapter(List<NaviMenuItem> drawerMenuList) {
        this.drawerMenuList = drawerMenuList;
    }

    @Override
    public DrawerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == TYPE_HEADER) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.nav_header_main, parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.nav_menu_item, parent, false);
        }

        return new DrawerViewHolder(view, viewType);
    }


    @Override
    public void onBindViewHolder(DrawerViewHolder holder, int position) {
        if (position == 0) {
            holder.headerProfleImage.setImageResource(android.R.drawable.sym_def_app_icon);
            holder.headerTitle.setText("Android Developer");
            holder.headerEmail.setText("yours@example.com");
        } else {
            holder.title.setText(drawerMenuList.get(position - 1).getTitle());
            holder.icon.setImageResource(drawerMenuList.get(position - 1).getIcon());
        }
    }

    @Override
    public int getItemCount() {
        return drawerMenuList.size() + 1;
    }


    @Override
    public int getItemViewType(int position) {

        if (position == 0) {
            return TYPE_HEADER;
        }
        return TYPE_MENU;

    }

    class DrawerViewHolder extends RecyclerView.ViewHolder {
        ImageView headerProfleImage;
        TextView headerTitle;
        TextView headerEmail;

        TextView title;
        ImageView icon;

        public DrawerViewHolder(View itemView, int viewType) {
            super(itemView);

            if (viewType == 0) {
                headerProfleImage = (ImageView) itemView.findViewById(R.id.iv_profile_pic);
                headerTitle = (TextView) itemView.findViewById(R.id.tv_title);
                headerEmail = (TextView) itemView.findViewById(R.id.tv_email);
            } else {
                title = (TextView) itemView.findViewById(R.id.title);
                icon = (ImageView) itemView.findViewById(R.id.icon);
            }
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    if (pos > 0) mListener.onItemSelected(view, pos - 1);
                }
            });
        }

    }


    public void setOnItemClickLister(OnItemSelecteListener mListener) {
        this.mListener = mListener;
    }

    public interface OnItemSelecteListener {
        public void onItemSelected(View v, int position);
    }

}