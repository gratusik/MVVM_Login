package com.agik.mvvm_live_retro_bind_login.repository.retrofit;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClass {
    private static Retrofit retrofit = null;

    public static Retrofit getInstance() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
//        OkHttpClient httpClient = new OkHttpClient.Builder()
//                .connectTimeout(1, TimeUnit.SECONDS)
//                .addInterceptor(logging)
//                .readTimeout(10000, TimeUnit.SECONDS)
//                .build();
     //   .client(httpClient)
       /* Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.3:80/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();*/
        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.2/")
                .addConverterFactory(GsonConverterFactory.create())

                .build();
        return retrofit;
    }
}
