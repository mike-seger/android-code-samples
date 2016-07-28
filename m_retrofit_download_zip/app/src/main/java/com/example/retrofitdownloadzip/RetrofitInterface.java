package com.example.retrofitdownloadzip;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by pye on 7/28/16.
 */
public interface RetrofitInterface {

    @Streaming
    @GET
    Call<ResponseBody> downloadFileByUrl(@Url String fileUrl);
}
