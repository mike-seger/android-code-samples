package com.example.peng.recycleviewcity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.peng.recycleviewcity.model.City;

import java.util.List;

/**
 * Created by peng on 11/3/15.
 */
public class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHolder>{

    private List<City> cities;
    private int rowLayout;
    private Context mContext;

    public CityAdapter(List<City> cities, int rowLayout, Context context) {
        this.cities = cities;
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
        final City city = cities.get(i);
        viewHolder.cityName.setText(city.name);
        viewHolder.cityImage.setImageDrawable(mContext.getResources().getDrawable(city.getImageResourceId(mContext)));
        // viewHolder.versionName=viewHolder.cityName.getText().toString();
        viewHolder.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                Intent i = new Intent(mContext, CityviewActivity.class);
                i.putExtra("city", city.name);
                i.putExtra("desc", city.description);
                i.putExtra("image", city.imageName);
                Log.i("hello", city.name);
                mContext.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return cities == null ? 0 : cities.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
        public TextView cityName;
        public ImageView cityImage;
        public String versionName;
        private ItemClickListener clickListener;
        public ViewHolder(View itemView) {
            super(itemView);
            cityName = (TextView) itemView.findViewById(R.id.city_name);
            cityImage = (ImageView)itemView.findViewById(R.id.city_image);
            itemView.setTag(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }
        public void setClickListener(ItemClickListener itemClickListener) {
            this.clickListener = itemClickListener;
        }

        @Override
        public void onClick(View view) {
            clickListener.onClick(view, getPosition(), false);
        }

        @Override
        public boolean onLongClick(View view) {
            clickListener.onClick(view, getPosition(), true);
            return true;
        }

    }
}
