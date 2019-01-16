package com.nandohusni.sayaguru.network;

import com.nandohusni.sayaguru.ui.home.fragment.model.ResultChangeStatus;
import com.nandohusni.sayaguru.ui.home.model.ResultRequest;
import com.nandohusni.sayaguru.ui.signIn.model.ResponseLogin;
import com.nandohusni.sayaguru.ui.signUp.model.ResponsePacket;
import com.nandohusni.sayaguru.ui.signUp.model.ResponseSignUp;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiService {



    @FormUrlEncoded
    @POST("changeStatus")
    Call<ResultChangeStatus> actionChangeStatus(@Field("userID")String userid);

    @FormUrlEncoded
    @POST("list_request_guru")
    Call<ResultRequest> actionRequest(@Field("id") String id);



    @Multipart
    @POST("register_guru")
    Call<ResponseSignUp> actionSINGup(@Part MultipartBody.Part foto,
                                      @Part MultipartBody.Part foto2,
                                      @Part("name") RequestBody name,
                                      @Part("email") RequestBody email,
                                      @Part("password") RequestBody password,
                                      @Part("alamat") RequestBody alamat,
                                      @Part("kelurahan") RequestBody kelurahan,
                                      @Part("kec") RequestBody kec,
                                      @Part("kab") RequestBody kab,
                                      @Part("telpon") RequestBody telpon,
                                      @Part("jp") RequestBody jp);


    @FormUrlEncoded
    @POST("insert_request")
    Call<ResponseSignUp> actionRequest(@Field("iduser") String iduser,
                                       @Field("idjp") String idjp,
                                       @Field("lat") String lat,
                                       @Field("lon") String lon,
                                       @Field("alamat") String alamat,
                                       @Field("ket") String ket);

    @GET("packet_private")
    Call<ResponsePacket> actionGetPacket();

    @FormUrlEncoded
    @POST("register_siswa")
    Call<ResponseSignUp> actionSigUp(
            @Field("name") String name,
            @Field("password") String password,
            @Field("hp") String hp,
            @Field("email") String email
    );

    @FormUrlEncoded
    @POST("login_guru")
    Call<ResponseLogin> actionLogin(
            @Field("email") String hp,
            @Field("password") String password
    );

}
