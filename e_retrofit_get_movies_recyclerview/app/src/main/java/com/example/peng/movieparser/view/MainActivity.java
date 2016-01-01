package com.example.peng.movieparser.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.peng.movieparser.R;
import com.example.peng.movieparser.adapter.MovieAdapter;
import com.example.peng.movieparser.model.MovieModel;
import com.example.peng.movieparser.service.IMovie;
import com.example.peng.movieparser.utilities.Constants;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        // Getting movie from REST endpoint
        RestAdapter rest = new RestAdapter.Builder()
                .setEndpoint(Constants.MOVIE_LIST_URL)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
        IMovie iMovie = rest.create(IMovie.class);
        iMovie.getMovies(new Callback<List<MovieModel>>() {
            @Override
            public void success(List<MovieModel> movieModels, Response response) {
                for (MovieModel m : movieModels) {
                    Log.i("MainActivity:", m.getTitle());
                }
                movieAdapter = new MovieAdapter(movieModels, R.layout.row_movie, getBaseContext());
                recyclerView.setAdapter(movieAdapter);
            }
            @Override
            public void failure(RetrofitError error) {
                Log.e("MainActivity:", error.toString());
            }
        });
    }
}
