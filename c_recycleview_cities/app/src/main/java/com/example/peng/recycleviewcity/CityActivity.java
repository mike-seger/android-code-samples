package com.example.peng.recycleviewcity;

/**
 * Created by peng on 11/3/15.
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.peng.recycleviewcity.model.City;
import com.example.peng.recycleviewcity.model.CityManager;

import java.util.List;


public class CityActivity extends Activity implements ItemClickListener {

    private RecyclerView mRecyclerView;
    private CityAdapter mAdapter;
    private List<City> cities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        cities = CityManager.getInstance(this.getApplicationContext()).getCites();
        mRecyclerView = (RecyclerView)findViewById(R.id.list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mAdapter = new CityAdapter(cities, R.layout.row_city, this);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setClickListener(this);
    }

    @Override
    public void onClick(View view, int position) {
        final City city = cities.get(position);
        Intent i = new Intent(this, CityviewActivity.class);
        i.putExtra("city", city.name);
        i.putExtra("desc", city.description);
        i.putExtra("image", city.imageName);
        Log.i("hello", city.name);
        startActivity(i);
    }
}
