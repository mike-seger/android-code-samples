package com.example.peng.apiparser.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.peng.apiparser.R;
import com.example.peng.apiparser.adapter.ContactAdapter;
import com.example.peng.apiparser.model.ContactModel;
import com.example.peng.apiparser.service.IContact;
import com.example.peng.apiparser.utilities.Constants;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ContactAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView)findViewById(R.id.list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        RestAdapter rest = new RestAdapter.Builder()
                .setEndpoint(Constants.BASE_URL)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        IContact iContact = rest.create(IContact.class);
        iContact.getContacts(new Callback<List<ContactModel>>() {
            @Override
            public void success(List<ContactModel> contactModels, Response response) {
                for (ContactModel cm : contactModels) {
                    Log.i("MainActivity", cm.getFirstName());
                    mAdapter = new ContactAdapter(contactModels, R.layout.row_contact, getApplicationContext());
                    mRecyclerView.setAdapter(mAdapter);
                }
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

}
