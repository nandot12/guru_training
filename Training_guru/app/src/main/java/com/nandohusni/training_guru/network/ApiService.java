package com.nandohusni.training_guru.network;

import com.nandohusni.training_guru.ui.home.model.ResponsePaket;
import com.nandohusni.training_guru.ui.signIn.model.ResponseLogin;
import com.nandohusni.training_guru.ui.signUp.model.ResponseSignUp;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {


    @FormUrlEncoded
    @POST("insert_request")
    Call<ResponseSignUp> actionRequest(@Field("iduser") String iduser,
                                       @Field("idjp") String idjp,
                                       @Field("lat") String lat,
                                       @Field("lon") String lon,
                                       @Field("alamat") String alamat,
                                       @Field("ket") String ket);

    @GET("packet_private")
    Call<ResponsePaket> actionGetPacket();

    @FormUrlEncoded
    @POST("register_siswa")
    Call<ResponseSignUp> actionSigUp(
            @Field("name") String name,
            @Field("password") String password,
            @Field("hp") String hp,
            @Field("email") String email
    );

    @FormUrlEncoded
    @POST("login")
    Call<ResponseLogin> actionLogin(
            @Field("hp") String hp,
            @Field("pass") String password
    );

}
