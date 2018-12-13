package com.example.alfinda.modul;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.alfinda.modul.Model.GetSuka;
import com.example.alfinda.modul.Model.GetUser;
import com.example.alfinda.modul.Rest.ApiClient;
import com.example.alfinda.modul.Rest.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LayarInsertSuka extends OpsiMenu {

    Context mContext;
    Button btAddBack, btAddData;
    EditText edtAddIdUser, edtAddIdReview;
    TextView tvAddMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layar_insert_suka);

        mContext = getApplicationContext();
        edtAddIdUser = (EditText) findViewById(R.id.edtAddIdUser);
        edtAddIdReview = (EditText) findViewById(R.id.edtAddIdReview);

        btAddData = (Button) findViewById(R.id.btAddData);
        btAddBack = (Button) findViewById(R.id.btAddBack);
        tvAddMessage = (TextView) findViewById(R.id.tvAddMessage);

        btAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApiInterface mApiInterface = ApiClient.getClient().create(ApiInterface.class);

                Call<GetSuka> mSukaCall = mApiInterface.postSuka(
                        edtAddIdUser.getText().toString(),
                        edtAddIdReview.getText().toString(),
                        "insert");
                //body, reqMenu, reqKategori, reqHarga, reqAlamat, reqReview, reqTanggal, reqSuka, reqKomentar, reqAction);
                mSukaCall.enqueue(new Callback<GetSuka>() {
                    @Override
                    public void onResponse(Call<GetSuka> call, Response<GetSuka> response) {
//                      Log.d("Insert Retrofit",response.body().getMessage());
                        if (response.body().getStatus().equals("failed")){
                            tvAddMessage.setText("Retrofit Insert \n Status = "+response.body()
                                    .getStatus()+"\n"+
                                    "Message = "+response.body().getMessage()+"\n");
                        }else{
                            String detail = "\n"+
                                    "idUser = "+response.body().getResult().get(0).getIdUser()+"\n"+
                                    "idMakanan = "+response.body().getResult().get(0).getIdMakanan()
                                    +"\n";
                            tvAddMessage.setText("Retrofit Insert \n Status = "+response.body().getStatus()+"\n"+
                                    "Message = "+response.body().getMessage()+detail);
                        }
                    }

                    @Override
                    public void onFailure(Call<GetSuka> call, Throwable t) {
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
                Intent intent = new Intent(mContext, LayarListSuka.class);
                startActivity(intent);
            }
        });
    }


}
