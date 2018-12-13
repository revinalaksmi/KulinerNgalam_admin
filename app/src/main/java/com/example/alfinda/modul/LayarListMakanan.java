package com.example.alfinda.modul;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.alfinda.modul.Adapter.MakananAdapter;
import com.example.alfinda.modul.Model.GetMakanan;
import com.example.alfinda.modul.Model.Makanan;
import com.example.alfinda.modul.Rest.ApiClient;
import com.example.alfinda.modul.Rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LayarListMakanan extends OpsiMenu {

    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    Context mContext;
    ApiInterface mApiInterface;
    Button btGet, btAddData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layar_list_makanan);

        mContext = getApplicationContext();
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(mLayoutManager);
//        btGet = (Button) findViewById(R.id.btGet);
        btAddData = (Button) findViewById(R.id.btAddData);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<GetMakanan> mGetMakananCall = mApiInterface.getMakanan();
        mGetMakananCall.enqueue(new Callback<GetMakanan>() {
            @Override
            public void onResponse(Call<GetMakanan> call, Response<GetMakanan> response) {
                Log.d("Get Makanan",response.body().getStatus());
                List<Makanan> listMakanan = response.body().getResult();
                mAdapter = new MakananAdapter(listMakanan);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<GetMakanan> call, Throwable t) {
                Log.d("Get Makanan",t.getMessage());
            }
        });

//        btGet.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //
//            }
//        });

        btAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, LayarInsertMakanan.class);
                startActivity(intent);
            }
        });
    }
}
