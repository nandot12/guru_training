package com.nandohusni.sayaguru.network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkClient {


    public static HttpLoggingInterceptor logging = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

    public static OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(logging)
            .build();


    public static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://172.168.10.36/server_guru/index.php/Api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build();

    public static ApiService service = retrofit.create(ApiService.class);
}
