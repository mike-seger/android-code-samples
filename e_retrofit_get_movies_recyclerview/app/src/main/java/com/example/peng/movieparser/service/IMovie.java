package com.example.peng.movieparser.service;

import com.example.peng.movieparser.model.MovieModel;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by peng on 11/5/15.
 */
public interface IMovie {
    @GET("/json/movies.json")
    public void getMovies(Callback<List<MovieModel>> response);
}
