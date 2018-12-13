package com.example.alfinda.modul.Rest;

import com.example.alfinda.modul.Model.GetMakanan;
import com.example.alfinda.modul.Model.GetSuka;
import com.example.alfinda.modul.Model.GetUser;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;

public interface ApiInterface {

//    ==========MAKANAN============
    @GET("makanan/all")
    Call<GetMakanan> getMakanan();

    @Multipart
    @POST("makanan/all")
    Call<GetMakanan> postMakanan(
            @Part MultipartBody.Part file,
            @Part("menu") RequestBody menu,
            @Part("kategori") RequestBody kategori,
            @Part("harga") RequestBody harga,
            @Part("alamat") RequestBody alamat,
            @Part("review") RequestBody review,
            @Part("tanggal") RequestBody tanggal,
            @Part("suka") RequestBody suka,
            @Part("komentar") RequestBody komentar,
            @Part("action") RequestBody action
    );

    @Multipart
    @POST("makanan/all")
    Call<GetMakanan> putMakanan(
            @Part MultipartBody.Part file,
            @Part("idMakanan") RequestBody idMakanan,
            @Part("menu") RequestBody menu,
            @Part("kategori") RequestBody kategori,
            @Part("harga") RequestBody harga,
            @Part("alamat") RequestBody alamat,
            @Part("review") RequestBody review,
            @Part("tanggal") RequestBody tanggal,
//            @Part("suka") RequestBody suka,
//            @Part("komentar") RequestBody komentar,
            @Part("action") RequestBody action
    );

    @Multipart
    @POST("makanan/all")
    Call<GetMakanan> deleteMakanan(
            @Part("idMakanan") RequestBody idMakanan,
            @Part("action") RequestBody action);


    //    ==========USER============
    @GET("user/all")
    Call<GetUser> getUser();

    @FormUrlEncoded
    @POST("user/all")
    Call<GetUser> postUser(
            @Field("username") String username,
            @Field("password") String password,
            @Field("nama") String nama,
            @Field("jk") String jk,
            @Field("email") String email,
            @Field("action") String action
    );

    @FormUrlEncoded
    @POST("user/all")
    Call<GetUser> putUser(
            @Field("idUser") String idUser,
            @Field("username") String username,
            @Field("password") String password,
            @Field("nama") String nama,
            @Field("jk") String jk,
            @Field("email") String email,
            @Field("action") String action
    );

    @FormUrlEncoded
    @POST("user/all")
    Call<GetUser> deleteUser(
            @Field("idUser") String idUser,
            @Field("action") String action
    );

    //    ==========SUKA============
    @GET("suka/all")
    Call<GetSuka> getSuka();

    @FormUrlEncoded
    @POST("suka/all")
    Call<GetSuka> postSuka(
            @Field("idUser") String idUser,
            @Field("idMakanan") String idMakanan,
            @Field("action") String action
    );

    @FormUrlEncoded
    @POST("suka/all")
    Call<GetSuka> putSuka(
            @Field("idSuka") String idSuka,
            @Field("idUser") String idUser,
            @Field("idMakanan") String idMakanan,
            @Field("action") String action
    );

    @FormUrlEncoded
    @POST("suka/all")
    Call<GetSuka> deleteSuka(
            @Field("idSuka") String idSuka,
            @Field("action") String action
    );

}
