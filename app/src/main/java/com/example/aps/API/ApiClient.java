package com.example.aps.API;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private ApiService apiService;
    public ApiService getApiService() {
        retrofit2.Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.0.243:8082/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);
        return apiService;
    }

}
