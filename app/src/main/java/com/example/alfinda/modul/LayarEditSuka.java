package com.example.alfinda.modul;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.alfinda.modul.Model.GetSuka;
import com.example.alfinda.modul.Rest.ApiClient;
import com.example.alfinda.modul.Rest.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LayarEditSuka extends OpsiMenu {
    EditText edtIdUser, edtIdMakanan, edtIdSuka;
    TextView tvMessage;
    Context mContext;
    Button btUpdate, btDelete, btBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layar_edit_suka);

        mContext = getApplicationContext();

        edtIdUser = (EditText) findViewById(R.id.edtIdUser);
        edtIdSuka = (EditText) findViewById(R.id.edtIdSuka);
        edtIdMakanan = (EditText) findViewById(R.id.edtIdMakanan);

        tvMessage = (TextView) findViewById(R.id.tvMessage);

        btUpdate = (Button) findViewById(R.id.btUpdate);
        btDelete = (Button) findViewById(R.id.btDelete);
        btBack = (Button) findViewById(R.id.btBack);

        Intent mIntent = getIntent();

        edtIdUser.setText(mIntent.getStringExtra("idUser"));
        edtIdSuka.setText(mIntent.getStringExtra("idSuka"));
        edtIdMakanan.setText(mIntent.getStringExtra("idMakanan"));

        setListener();
    }

    private void setListener() {
        final ApiInterface mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<GetSuka> callUpdate = mApiInterface.putSuka(
//                        body, reqIdMakanan, reqMenu, reqKategori, reqHarga, reqAlamat, reqReview, reqTanggal, reqAction
                        edtIdSuka.getText().toString(),
                        edtIdUser.getText().toString(),
                        edtIdMakanan.getText().toString(),
                        "update"
                );


                callUpdate.enqueue(new Callback<GetSuka>() {
                    @Override
                    public void onResponse(Call<GetSuka> call, Response<GetSuka> response) {
                        //Log.d("Update Retrofit ", response.body().getStatus());
                        if (response.body().getStatus().equals("failed")){
                            tvMessage.setText("Retrofit Update \n Status = " + response.body()
                                    .getStatus()+"\n"+
                                    "Message = "+response.body().getMessage()+"\n");
                        }else{
                            String detail = "\n"+
                                    "idSuka = "+response.body().getResult().get(0).getIdSuka()+"\n"+
                                    "idUser = "+response.body().getResult().get(0).getIdUser()+"\n"+
                                    "idMakanan = "+response.body().getResult().get(0).getIdMakanan()
                                    +"\n";
                            tvMessage.setText("Retrofit Update \n Status = "+response.body().getStatus()+"\n"+
                                    "Message = "+response.body().getMessage()+detail);
                        }
                    }

                    @Override
                    public void onFailure(Call<GetSuka> call, Throwable t) {
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

                Call<GetSuka> callDelete = mApiInterface.deleteSuka(
//                            reqIdMakanan,reqAction
                        edtIdSuka.getText().toString(),
                        "delete" );
                callDelete.enqueue(new Callback<GetSuka>() {
                    @Override
                    public void onResponse(Call<GetSuka> call, Response<GetSuka> response) {
                        tvMessage.setText("Retrofit Delete \n Status = "+response.body()
                                .getStatus() +"\n"+
                                "Message = "+response.body().getMessage()+"\n");
                    }

                    @Override
                    public void onFailure(Call<GetSuka> call, Throwable t) {
                        tvMessage.setText("Retrofit Delete \n Status = "+ t.getMessage());
                    }
                });
            }
        });

        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tempIntent = new Intent(mContext, LayarListSuka.class);
                startActivity(tempIntent);
            }
        });

    }


}

