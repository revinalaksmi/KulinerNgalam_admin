package com.example.alfinda.modul;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.alfinda.modul.Adapter.SukaAdapter;
import com.example.alfinda.modul.Model.GetSuka;
import com.example.alfinda.modul.Model.Suka;
import com.example.alfinda.modul.Rest.ApiClient;
import com.example.alfinda.modul.Rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LayarListSuka extends OpsiMenu {

    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    Context mContext;
    ApiInterface mApiInterface;
    Button btGet, btAddData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layar_list_suka);

        mContext = getApplicationContext();
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(mLayoutManager);
//        btGet = (Button) findViewById(R.id.btGet);
        btAddData = (Button) findViewById(R.id.btAddData);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<GetSuka> mGetSukaCall = mApiInterface.getSuka();
        mGetSukaCall.enqueue(new Callback<GetSuka>() {
            @Override
            public void onResponse(Call<GetSuka> call, Response<GetSuka> response) {
                Log.d("Get Suka",response.body().getStatus());
                List<Suka> listSuka = response.body().getResult();
                mAdapter = new SukaAdapter(listSuka);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<GetSuka> call, Throwable t) {
                Log.d("Get Suka",t.getMessage());
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
                Intent intent = new Intent(mContext, LayarInsertSuka.class);
                startActivity(intent);
            }
        });
    }
}