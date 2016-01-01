package com.example.peng.apiparser.service;

import com.example.peng.apiparser.model.ContactModel;

import java.util.List;


import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by peng on 11/5/15.
 */
public interface IContact {

    //Using retrofit
    @GET("/contacts")
    public void getContacts(Callback<List<ContactModel>> response);
}
