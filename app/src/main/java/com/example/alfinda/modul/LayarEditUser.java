package com.example.alfinda.modul;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.alfinda.modul.Model.GetUser;
import com.example.alfinda.modul.Rest.ApiClient;
import com.example.alfinda.modul.Rest.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LayarEditUser extends OpsiMenu {
        EditText edtIdUser, edtUsername, edtPassword, edtNama, edtJk, edtEmail;
        TextView tvMessage;
        Context mContext;
        Button btUpdate, btDelete, btBack;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_layar_edit_user);

            mContext = getApplicationContext();

            edtIdUser = (EditText) findViewById(R.id.edtIdUser);
            edtUsername = (EditText) findViewById(R.id.edtUsername);
            edtPassword = (EditText) findViewById(R.id.edtPassword);
            edtNama = (EditText) findViewById(R.id.edtNama);
            edtJk = (EditText) findViewById(R.id.edtJk);
            edtEmail = (EditText) findViewById(R.id.edtEmail);

            tvMessage = (TextView) findViewById(R.id.tvMessage);

            btUpdate = (Button) findViewById(R.id.btUpdate);
            btDelete = (Button) findViewById(R.id.btDelete);
            btBack = (Button) findViewById(R.id.btBack);

            Intent mIntent = getIntent();

            edtIdUser.setText(mIntent.getStringExtra("idUser"));
            edtUsername.setText(mIntent.getStringExtra("username"));
            edtPassword.setText(mIntent.getStringExtra("password"));
            edtNama.setText(mIntent.getStringExtra("nama"));
            edtJk.setText(mIntent.getStringExtra("jk"));
            edtEmail.setText(mIntent.getStringExtra("email"));

//        if (mIntent.getStringExtra("photo_url").length()>0) Picasso.with(mContext).load
// (ApiClient.BASE_URL + mIntent.getStringExtra("photo_url")).into(mPhotoUrl);
//        else Picasso.with(mContext).load(R.drawable.photoid).into(mPhotoUrl);
            setListener();
        }

        private void setListener() {
            final ApiInterface mApiInterface = ApiClient.getClient().create(ApiInterface.class);

            btUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

//                    MultipartBody.Part body = null;
//                    //dicek apakah image sama dengan yang ada di server atau berubah
//                    //jika sama dikirim lagi jika berbeda akan dikirim ke server
////                if ((!pathImage.contains("upload/" + edtIdMakanan.getText().toString())) &&
////                        (pathImage.length()>0)){
//                    //----------Tambahan--------------------
//                    if ((!pathImage.contains("uploads/" )) && (!pathImage.isEmpty())){
//                        //File creating from selected URL
//                        File file = new File(pathImage);
//
//                        // create RequestBody instance from file
//                        RequestBody requestFile = RequestBody.create(
//                                MediaType.parse("multipart/form-data"), file);
//
//                        // MultipartBody.Part is used to send also the actual file name
//                        body = MultipartBody.Part.createFormData("gambar", file.getName(),
//                                requestFile);
//                    }
//
//                    RequestBody reqIdMakanan =
//                            MultipartBody.create(MediaType.parse("multipart/form-data"),
//                                    (edtIdMakanan.getText().toString().isEmpty())?
//                                            "" : edtIdMakanan.getText().toString());
//
//                    RequestBody reqMenu =
//                            MultipartBody.create(MediaType.parse("multipart/form-data"),
//                                    (edtMenu.getText().toString().isEmpty())?
//                                            "" : edtMenu.getText().toString());
//
//                    RequestBody reqKategori =
//                            MultipartBody.create(MediaType.parse("multipart/form-data"),
//                                    (edtKategori.getText().toString().isEmpty())?
//                                            "" : edtKategori.getText().toString());
//
//                    RequestBody reqHarga =
//                            MultipartBody.create(MediaType.parse("multipart/form-data"),
//                                    (edtHarga.getText().toString().isEmpty())?
//                                            "" : edtHarga.getText().toString());
//
//                    RequestBody reqAlamat =
//                            MultipartBody.create(MediaType.parse("multipart/form-data"),
//                                    (edtAlamat.getText().toString().isEmpty())?
//                                            "" : edtAlamat.getText().toString());
//
//                    RequestBody reqReview =
//                            MultipartBody.create(MediaType.parse("multipart/form-data"),
//                                    (edtReview.getText().toString().isEmpty())?
//                                            "" : edtReview.getText().toString());
//
//                    RequestBody reqTanggal =
//                            MultipartBody.create(MediaType.parse("multipart/form-data"),
//                                    (edtTanggal.getText().toString().isEmpty())?
//                                            "" : edtTanggal.getText().toString());
//
//                    RequestBody reqAction =
//                            MultipartBody.create(MediaType.parse("multipart/form-data"), "update");

                    Call<GetUser> callUpdate = mApiInterface.putUser(
//                        body, reqIdMakanan, reqMenu, reqKategori, reqHarga, reqAlamat, reqReview, reqTanggal, reqAction
                            edtIdUser.getText().toString(),
                            edtUsername.getText().toString(),
                            edtPassword.getText().toString(),
                            edtNama.getText().toString(),
                            edtJk.getText().toString(),
                            edtEmail.getText().toString(),
                            "update"
                    );


                    callUpdate.enqueue(new Callback<GetUser>() {
                        @Override
                        public void onResponse(Call<GetUser> call, Response<GetUser> response) {
                            //Log.d("Update Retrofit ", response.body().getStatus());
                            if (response.body().getStatus().equals("failed")){
                                tvMessage.setText("Retrofit Update \n Status = " + response.body()
                                        .getStatus()+"\n"+
                                        "Message = "+response.body().getMessage()+"\n");
                            }else{
                                String detail = "\n"+
                                        "idUser = "+response.body().getResult().get(0).getIdUser()+"\n"+
                                        "username = "+response.body().getResult().get(0).getUsername()+"\n"+
                                        "password = "+response.body().getResult().get(0).getPassword()+"\n"+
                                        "nama = "+response.body().getResult().get(0).getNama()+"\n"+
                                        "jk = "+response.body().getResult().get(0).getJk()+"\n"+
                                        "email = "+response.body().getResult().get(0).getEmail()
                                        +"\n";
                                tvMessage.setText("Retrofit Update \n Status = "+response.body().getStatus()+"\n"+
                                        "Message = "+response.body().getMessage()+detail);
                            }
                        }

                        @Override
                        public void onFailure(Call<GetUser> call, Throwable t) {
                            //Log.d("Update Retrofit ", t.getMessage());
                            tvMessage.setText("Retrofit Update \n Status = "+ t.getMessage());
                        }
                    });

                }
            });
            btDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    RequestBody reqIdMakanan =
//                            MultipartBody.create(MediaType.parse("multipart/form-data"),
//                                    (edtIdMakanan.getText().toString().isEmpty())?
//                                            "" : edtIdMakanan.getText().toString());
//                    RequestBody reqAction =
//                            MultipartBody.create(MediaType.parse("multipart/form-data"), "delete");

                    Call<GetUser> callDelete = mApiInterface.deleteUser(
//                            reqIdMakanan,reqAction
                            edtIdUser.getText().toString(),
                            "delete" );
                    callDelete.enqueue(new Callback<GetUser>() {
                        @Override
                        public void onResponse(Call<GetUser> call, Response<GetUser> response) {
                            tvMessage.setText("Retrofit Delete \n Status = "+response.body()
                                    .getStatus() +"\n"+
                                    "Message = "+response.body().getMessage()+"\n");
                        }

                        @Override
                        public void onFailure(Call<GetUser> call, Throwable t) {
                            tvMessage.setText("Retrofit Delete \n Status = "+ t.getMessage());
                        }
                    });
                }
            });

            btBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent tempIntent = new Intent(mContext, LayarListUser.class);
                    startActivity(tempIntent);
                }
            });

        }


    }


