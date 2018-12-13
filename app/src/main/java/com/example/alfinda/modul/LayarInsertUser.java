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

public class LayarInsertUser extends OpsiMenu {

    Context mContext;
    Button btAddBack, btAddData;
    EditText edtAddUsername, edtAddPassword, edtAddNama, edtAddJk, edtAddEmail;
    TextView tvAddMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layar_insert_user);

        mContext = getApplicationContext();
        edtAddUsername = (EditText) findViewById(R.id.edtAddUsername);
        edtAddPassword = (EditText) findViewById(R.id.edtAddPassword);
        edtAddNama = (EditText) findViewById(R.id.edtAddNama);
        edtAddJk = (EditText) findViewById(R.id.edtAddJk);
        edtAddEmail = (EditText) findViewById(R.id.edtAddEmail);

        btAddData = (Button) findViewById(R.id.btAddData);
        btAddBack = (Button) findViewById(R.id.btAddBack);
        tvAddMessage = (TextView) findViewById(R.id.tvAddMessage);

        btAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApiInterface mApiInterface = ApiClient.getClient().create(ApiInterface.class);

//                Username = edtAddNamaKereta.getText().toString());
//                RequestBody reqKategori = MultipartBody.create(MediaType.parse("multipart/form-data"),
//                        (edtAddMenu.getText().toString().isEmpty())?"":edtAddKategori.getText().toString());
//                RequestBody reqHarga = MultipartBody.create(MediaType.parse("multipart/form-data"),
//                        (edtAddMenu.getText().toString().isEmpty())?"":edtAddHarga.getText().toString());
//                RequestBody reqAlamat = MultipartBody.create(MediaType.parse("multipart/form-data"),
//                        (edtAddMenu.getText().toString().isEmpty())?"":edtAddAlamat.getText().toString());
//                RequestBody reqReview = MultipartBody.create(MediaType.parse("multipart/form-data"),
//                        (edtAddMenu.getText().toString().isEmpty())?"":edtAddReview.getText().toString());
//                RequestBody reqTanggal = MultipartBody.create(MediaType.parse("multipart/form-data"),
//                        (edtAddMenu.getText().toString().isEmpty())?"":edtAddTanggal.getText().toString());
//                RequestBody reqSuka = MultipartBody.create(MediaType.parse("multipart/form-data"),
//                        "0");
//                RequestBody reqKomentar = MultipartBody.create(MediaType.parse("multipart/form-data"),
//                        "0");
//                RequestBody reqAction = MultipartBody.create(MediaType.parse("multipart/form-data"),
//                        "insert");
                Call<GetUser> mUserCall = mApiInterface.postUser(
                        edtAddUsername.getText().toString(),
                        edtAddPassword.getText().toString(),
                        edtAddNama.getText().toString(),
                        edtAddJk.getText().toString(),
                        edtAddEmail.getText().toString(),
                        "insert");
                        //body, reqMenu, reqKategori, reqHarga, reqAlamat, reqReview, reqTanggal, reqSuka, reqKomentar, reqAction);
                mUserCall.enqueue(new Callback<GetUser>() {
                    @Override
                    public void onResponse(Call<GetUser> call, Response<GetUser> response) {
//                      Log.d("Insert Retrofit",response.body().getMessage());
                        if (response.body().getStatus().equals("failed")){
                            tvAddMessage.setText("Retrofit Insert \n Status = "+response.body()
                                    .getStatus()+"\n"+
                                    "Message = "+response.body().getMessage()+"\n");
                        }else{
                            String detail = "\n"+
                                    "username = "+response.body().getResult().get(0).getUsername()+"\n"+
                                    "password = "+response.body().getResult().get(0).getPassword()+"\n"+
                                    "nama = "+response.body().getResult().get(0).getNama()+"\n"+
                                    "jk = "+response.body().getResult().get(0).getJk()+"\n"+
                                    "email = "+response.body().getResult().get(0).getEmail()
                                    +"\n";
                            tvAddMessage.setText("Retrofit Insert \n Status = "+response.body().getStatus()+"\n"+
                                    "Message = "+response.body().getMessage()+detail);
                        }
                    }

                    @Override
                    public void onFailure(Call<GetUser> call, Throwable t) {
//                      Log.d("Insert Retrofit", t.getMessage());
                        tvAddMessage.setText("Retrofit Insert Failure \n Status = "+ t.getMessage
                                ());
                    }
                });
            }
        });
        btAddBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, LayarListUser.class);
                startActivity(intent);
            }
        });
    }


}
