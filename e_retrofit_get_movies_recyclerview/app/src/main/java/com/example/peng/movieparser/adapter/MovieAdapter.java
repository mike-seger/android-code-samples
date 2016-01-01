package com.example.peng.movieparser.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.peng.movieparser.R;
import com.example.peng.movieparser.model.MovieModel;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by peng on 11/5/15.
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private List <MovieModel> movieList = new ArrayList<MovieModel>();
    private int rowLayout;
    private Context context;

    public MovieAdapter(List <MovieModel> list, int layout, Context context) {
        this.movieList  = list;
        this.rowLayout  = layout;
        this.context    = context;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MovieModel m = movieList.get(position);
        if (m != null) {
            try {
//                URL newurl = new URL(m.getImage());
//                holder.ivImage.setImageBitmap(BitmapFactory.decodeStream(newurl.openConnection().getInputStream()));

                Picasso.with(context).load(m.getImage()).into(holder.ivImage);
                holder.tvTitle.setText(m.getTitle());
                holder.tvRating.setText(m.getRating().toString());
                holder.tvGenre.setText(m.getGenre().toString());
                holder.tvReleaseYear.setText(m.getReleaseYear().toString());
            } catch (Exception e) {
                Log.e("MovieAdapter", e.toString());
            }
        }
    }

    @Override
    public int getItemCount() {
        return movieList == null ? 0 : movieList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView ivImage;
        public TextView  tvTitle;
        public TextView  tvRating;
        public TextView  tvGenre;
        public TextView  tvReleaseYear;

        public ViewHolder(View itemView) {
            super(itemView);
            this.ivImage        = (ImageView) itemView.findViewById(R.id.movie_image);
            this.tvTitle        = (TextView)  itemView.findViewById(R.id.movie_title);
            this.tvRating       = (TextView)  itemView.findViewById(R.id.movie_rating);
            this.tvGenre        = (TextView)  itemView.findViewById(R.id.movie_genre);
            this.tvReleaseYear  = (TextView)  itemView.findViewById(R.id.movie_release_year);
        }
    }
}
