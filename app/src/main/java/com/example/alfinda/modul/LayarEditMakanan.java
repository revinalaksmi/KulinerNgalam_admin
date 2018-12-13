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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.alfinda.modul.Model.GetMakanan;
import com.example.alfinda.modul.Rest.ApiClient;
import com.example.alfinda.modul.Rest.ApiInterface;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LayarEditMakanan extends OpsiMenu {
    ImageView mGambar;
    EditText edtIdMakanan, edtMenu, edtKategori, edtHarga, edtAlamat, edtReview, edtTanggal;
    TextView tvMessage;
    Context mContext;
    Button btUpdate, btDelete, btBack, btGambar;
    String pathImage="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layar_edit_makanan);

        mContext = getApplicationContext();

        mGambar = (ImageView) findViewById(R.id.imgGambar);
        edtIdMakanan = (EditText) findViewById(R.id.edtIdMakanan);
        edtMenu = (EditText) findViewById(R.id.edtMenu);
        edtKategori = (EditText) findViewById(R.id.edtKategori);
        edtHarga = (EditText) findViewById(R.id.edtHarga);
        edtAlamat = (EditText) findViewById(R.id.edtAlamat);
        edtReview = (EditText) findViewById(R.id.edtReview);
        edtTanggal = (EditText) findViewById(R.id.edtTanggal);

        tvMessage = (TextView) findViewById(R.id.tvMessage);

        btUpdate = (Button) findViewById(R.id.btUpdate);
        btDelete = (Button) findViewById(R.id.btDelete);
        btBack = (Button) findViewById(R.id.btBack);
        btGambar = (Button) findViewById(R.id.btGambar);

        Intent mIntent = getIntent();

        edtIdMakanan.setText(mIntent.getStringExtra("idMakanan"));
        edtMenu.setText(mIntent.getStringExtra("menu"));
        edtKategori.setText(mIntent.getStringExtra("kategori"));
        edtHarga.setText(mIntent.getStringExtra("harga"));
        edtAlamat.setText(mIntent.getStringExtra("alamat"));
        edtReview.setText(mIntent.getStringExtra("review"));
        edtTanggal.setText(mIntent.getStringExtra("tanggal"));

//        if (mIntent.getStringExtra("photo_url").length()>0) Picasso.with(mContext).load
// (ApiClient.BASE_URL + mIntent.getStringExtra("photo_url")).into(mPhotoUrl);
//        else Picasso.with(mContext).load(R.drawable.photoid).into(mPhotoUrl);
        if (mIntent.getStringExtra("gambar") != null)
        if (mIntent.getStringExtra("gambar") != null)

            //--tambahan--
            Glide.with(mContext).load(ApiClient.BASE_URL +
                    mIntent.getStringExtra("gambar")).into(mGambar);
        else
            Glide.with(mContext).load(R.drawable.default_user).into(mGambar);

        pathImage = mIntent.getStringExtra("gambar");
        setListener();
    }

    private void setListener() {
        final ApiInterface mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MultipartBody.Part body = null;
                //dicek apakah image sama dengan yang ada di server atau berubah
                //jika sama dikirim lagi jika berbeda akan dikirim ke server
//                if ((!pathImage.contains("upload/" + edtIdMakanan.getText().toString())) &&
//                        (pathImage.length()>0)){
                //----------Tambahan--------------------
                if ((!pathImage.contains("uploads/" )) && (!pathImage.isEmpty())){
                    //File creating from selected URL
                    File file = new File(pathImage);

                    // create RequestBody instance from file
                    RequestBody requestFile = RequestBody.create(
                            MediaType.parse("multipart/form-data"), file);

                    // MultipartBody.Part is used to send also the actual file name
                    body = MultipartBody.Part.createFormData("gambar", file.getName(),
                            requestFile);
                }

                RequestBody reqIdMakanan =
                        MultipartBody.create(MediaType.parse("multipart/form-data"),
                                (edtIdMakanan.getText().toString().isEmpty())?
                                        "" : edtIdMakanan.getText().toString());

                RequestBody reqMenu =
                        MultipartBody.create(MediaType.parse("multipart/form-data"),
                                (edtMenu.getText().toString().isEmpty())?
                                        "" : edtMenu.getText().toString());

                RequestBody reqKategori =
                        MultipartBody.create(MediaType.parse("multipart/form-data"),
                                (edtKategori.getText().toString().isEmpty())?
                                        "" : edtKategori.getText().toString());

                RequestBody reqHarga =
                        MultipartBody.create(MediaType.parse("multipart/form-data"),
                                (edtHarga.getText().toString().isEmpty())?
                                        "" : edtHarga.getText().toString());

                RequestBody reqAlamat =
                        MultipartBody.create(MediaType.parse("multipart/form-data"),
                                (edtAlamat.getText().toString().isEmpty())?
                                        "" : edtAlamat.getText().toString());

                RequestBody reqReview =
                        MultipartBody.create(MediaType.parse("multipart/form-data"),
                                (edtReview.getText().toString().isEmpty())?
                                        "" : edtReview.getText().toString());

                RequestBody reqTanggal =
                        MultipartBody.create(MediaType.parse("multipart/form-data"),
                                (edtTanggal.getText().toString().isEmpty())?
                                        "" : edtTanggal.getText().toString());

                RequestBody reqAction =
                        MultipartBody.create(MediaType.parse("multipart/form-data"), "update");

                Call<GetMakanan> callUpdate = mApiInterface.putMakanan(body, reqIdMakanan, reqMenu, reqKategori, reqHarga,
                        reqAlamat, reqReview, reqTanggal, reqAction);

                callUpdate.enqueue(new Callback<GetMakanan>() {
                    @Override
                    public void onResponse(Call<GetMakanan> call, Response<GetMakanan> response) {
                        //Log.d("Update Retrofit ", response.body().getStatus());
                        if (response.body().getStatus().equals("failed")){
                            tvMessage.setText("Retrofit Update \n Status = " + response.body()
                                    .getStatus()+"\n"+
                                    "Message = "+response.body().getMessage()+"\n");
                        }else{
                            String detail = "\n"+
                                    "idMakanan = "+response.body().getResult().get(0).getIdMakanan()+"\n"+
                                    "menu = "+response.body().getResult().get(0).getMenu()+"\n"+
                                    "kategori = "+response.body().getResult().get(0).getKategori()+"\n"+
                                    "harga = "+response.body().getResult().get(0).getHarga()+"\n"+
                                    "alamat = "+response.body().getResult().get(0).getAlamat()+"\n"+
                                    "review = "+response.body().getResult().get(0).getReview()+"\n"+
                                    "tanggal = "+response.body().getResult().get(0).getTanggal()+"\n"+
                                    "gambar = "+response.body().getResult().get(0).getGambar()
                                    +"\n";
                            tvMessage.setText("Retrofit Update \n Status = "+response.body().getStatus()+"\n"+
                                    "Message = "+response.body().getMessage()+detail);
                        }
                    }

                    @Override
                    public void onFailure(Call<GetMakanan> call, Throwable t) {
                        //Log.d("Update Retrofit ", t.getMessage());
                        tvMessage.setText("Retrofit Update \n Status = "+ t.getMessage());
                    }
                });

            }
        });
        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestBody reqIdMakanan =
                        MultipartBody.create(MediaType.parse("multipart/form-data"),
                                (edtIdMakanan.getText().toString().isEmpty())?
                                        "" : edtIdMakanan.getText().toString());
                RequestBody reqAction =
                        MultipartBody.create(MediaType.parse("multipart/form-data"), "delete");

                Call<GetMakanan> callDelete = mApiInterface.deleteMakanan(reqIdMakanan,reqAction);
                callDelete.enqueue(new Callback<GetMakanan>() {
                    @Override
                    public void onResponse(Call<GetMakanan> call, Response<GetMakanan> response) {
                        tvMessage.setText("Retrofit Delete \n Status = "+response.body()
                                .getStatus() +"\n"+
                                "Message = "+response.body().getMessage()+"\n");
                    }

                    @Override
                    public void onFailure(Call<GetMakanan> call, Throwable t) {
                        tvMessage.setText("Retrofit Delete \n Status = "+ t.getMessage());
                    }
                });
            }
        });

        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tempIntent = new Intent(mContext, LayarListMakanan.class);
                startActivity(tempIntent);
            }
        });

        btGambar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent galleryIntent = new Intent();
                galleryIntent.setType("image/*");
                galleryIntent.setAction(Intent.ACTION_PICK);
                Intent intentChoose = Intent.createChooser(galleryIntent, "Pilih foto untuk " +
                        "di-upload");
                startActivityForResult(intentChoose, 10);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode ==10) {
            if (data == null) {
                Toast.makeText(mContext, "Foto gagal di-load", Toast.LENGTH_LONG).show();
                return;
            }
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                pathImage = cursor.getString(columnIndex);

                //Picasso.with(mContext).load(new File(imagePath)).fit().into(mImageView);
                Glide.with(mContext).load(new File(pathImage)).into(mGambar);
                cursor.close();
            } else {
                Toast.makeText(mContext, "Foto gagal di-load", Toast.LENGTH_LONG).show();
            }
        }
    }
}
