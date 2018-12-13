package com.example.alfinda.modul;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.os.Bundle;
import android.util.Log;
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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.provider.CalendarContract.CalendarCache.URI;

public class LayarInsertMakanan extends OpsiMenu {

    Context mContext;
    ImageView mGambar;
    Button btAddGambar, btAddBack, btAddData, btAddCapt;
    EditText edtAddMenu, edtAddKategori, edtAddHarga, edtAddAlamat, edtAddReview, edtAddTanggal;
    TextView tvAddMessage;
    String imagePath = "";

//    Button mButtonPicture;
//    ImageView mImageView;
    File mFileURI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layar_insert_makanan);

        btAddCapt = (Button) findViewById(R.id.btAddCapt);
//        mImageView = (ImageView) findViewById(imgPreview);
        btAddCapt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                captureImage();
            }
        });

        // Checking camera availability
        if (!isDeviceSupportCamera()) {
            Toast.makeText(getApplicationContext(), "Camera di device Anda tidak tersedia",
                    Toast.LENGTH_LONG).show();
            finish();
        }

        mContext = getApplicationContext();
        mGambar= (ImageView) findViewById(R.id.imgAddGambar);
        btAddGambar = (Button)  findViewById(R.id.btAddGambar);
        edtAddMenu = (EditText) findViewById(R.id.edtAddMenu);
        edtAddKategori = (EditText) findViewById(R.id.edtAddKategori);
        edtAddHarga = (EditText) findViewById(R.id.edtAddHarga);
        edtAddAlamat = (EditText) findViewById(R.id.edtAddAlamat);
        edtAddReview = (EditText) findViewById(R.id.edtAddReview);
        edtAddTanggal = (EditText) findViewById(R.id.edtAddTanggal);

        btAddData = (Button) findViewById(R.id.btAddData);
        btAddBack = (Button) findViewById(R.id.btAddBack);
        tvAddMessage = (TextView) findViewById(R.id.tvAddMessage);

        btAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApiInterface mApiInterface = ApiClient.getClient().create(ApiInterface.class);

                MultipartBody.Part body = null;
                if (!imagePath.isEmpty()){
                    // Buat file dari image yang dipilih
                    File file = new File(imagePath);

                    // Buat RequestBody instance dari file
                    RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpg"), file);

                    // MultipartBody.Part digunakan untuk mendapatkan nama file
                    body = MultipartBody.Part.createFormData("gambar", file.getName(),
                            requestFile);
                }
                RequestBody reqMenu = MultipartBody.create(MediaType.parse("multipart/form-data"),
                        (edtAddMenu.getText().toString().isEmpty())?"":edtAddMenu.getText().toString());
                RequestBody reqKategori = MultipartBody.create(MediaType.parse("multipart/form-data"),
                        (edtAddMenu.getText().toString().isEmpty())?"":edtAddKategori.getText().toString());
                RequestBody reqHarga = MultipartBody.create(MediaType.parse("multipart/form-data"),
                        (edtAddMenu.getText().toString().isEmpty())?"":edtAddHarga.getText().toString());
                RequestBody reqAlamat = MultipartBody.create(MediaType.parse("multipart/form-data"),
                        (edtAddMenu.getText().toString().isEmpty())?"":edtAddAlamat.getText().toString());
                RequestBody reqReview = MultipartBody.create(MediaType.parse("multipart/form-data"),
                        (edtAddMenu.getText().toString().isEmpty())?"":edtAddReview.getText().toString());
                RequestBody reqTanggal = MultipartBody.create(MediaType.parse("multipart/form-data"),
                        (edtAddMenu.getText().toString().isEmpty())?"":edtAddTanggal.getText().toString());
                RequestBody reqSuka = MultipartBody.create(MediaType.parse("multipart/form-data"),
                        "0");
                RequestBody reqKomentar = MultipartBody.create(MediaType.parse("multipart/form-data"),
                        "0");
                RequestBody reqAction = MultipartBody.create(MediaType.parse("multipart/form-data"),
                        "insert");
                Call<GetMakanan> mMakananCall = mApiInterface.postMakanan(
                        body, reqMenu, reqKategori, reqHarga, reqAlamat, reqReview, reqTanggal, reqSuka, reqKomentar, reqAction);
                mMakananCall.enqueue(new Callback<GetMakanan>() {
                    @Override
                    public void onResponse(Call<GetMakanan> call, Response<GetMakanan> response) {
//                      Log.d("Insert Retrofit",response.body().getMessage());
                        if (response.body().getStatus().equals("failed")){
                            tvAddMessage.setText("Retrofit Insert \n Status = "+response.body()
                                    .getStatus()+"\n"+
                                    "Message = "+response.body().getMessage()+"\n");
                        }else{
                            String detail = "\n"+
                                    "menu = "+response.body().getResult().get(0).getMenu()+"\n"+
                                    "kategori = "+response.body().getResult().get(0).getKategori()+"\n"+
                                    "harga = "+response.body().getResult().get(0).getHarga()+"\n"+
                                    "alamat = "+response.body().getResult().get(0).getAlamat()+"\n"+
                                    "review = "+response.body().getResult().get(0).getReview()+"\n"+
                                    "tanggal = "+response.body().getResult().get(0).getTanggal()+"\n"+
                                    "gambar = "+response.body().getResult().get(0).getGambar()
                                    +"\n";
                            tvAddMessage.setText("Retrofit Insert \n Status = "+response.body().getStatus()+"\n"+
                                    "Message = "+response.body().getMessage()+detail);
                        }
                    }

                    @Override
                    public void onFailure(Call<GetMakanan> call, Throwable t) {
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
                Intent intent = new Intent(mContext, LayarListMakanan.class);
                startActivity(intent);
            }
        });
        btAddGambar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent galleryIntent = new Intent();
                galleryIntent.setType("image/*");
                galleryIntent.setAction(Intent.ACTION_PICK);
                Intent intentChoose = Intent.createChooser(
                        galleryIntent,
                        "Pilih foto untuk di-upload");
                startActivityForResult(intentChoose, 10);
            }
        });
    }

    private void captureImage() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
//            mFileURI = getPublicAlbumStorageDir("Camera");
            mFileURI = getMediaFileName();
//            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, URI.fromFile(mFileURI));
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, URI.fromFile(mFileURI));
            startActivityForResult(takePictureIntent, 100);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode ==10){
            if (data==null){
                Toast.makeText(mContext, "Foto gagal di-load", Toast.LENGTH_LONG).show();
                return;
            }
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);

            if (cursor != null) {
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                imagePath =cursor.getString(columnIndex);

//                Picasso.with(mContext).load(new File(imagePath)).fit().into(mImageView);
                Glide.with(mContext).load(new File(imagePath)).into(mGambar);
                cursor.close();
            }else{
                Toast.makeText(mContext, "Foto gagal di-load", Toast.LENGTH_LONG).show();
            }
        } else /*if(requestCode == 100 && resultCode == RESULT_OK)*/ {
//            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
//// rescale bitmap jika aplikasi force close
//// semakin besar ukuran rescale maka image/gambar yang ditampilkan semakin kecil
//            bmOptions.inSampleSize = 4;
//            Bitmap bitmap = BitmapFactory.decodeFile(mFileURI.getPath(), bmOptions);
//            mGambar.setVisibility(View.VISIBLE);
//            mGambar.setImageBitmap(bitmap);

            Glide.with(getApplicationContext()).load(new File(mFileURI.getPath())).into(mGambar);
            mGambar
                    .setVisibility(View.VISIBLE);
        }
    }




    private boolean isDeviceSupportCamera() {
        if (getApplicationContext().getPackageManager().hasSystemFeature(
                PackageManager.FEATURE_CAMERA)) {
// this device has a camera
            return true;
        } else {
// no camera on this device
            return false;
        }

    }

    public File getPublicAlbumStorageDir(String albumName) {
        // Get the directory for the user's public pictures directory.
        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), albumName);
        if (!file.mkdirs()) {
//            Log.e(LOG_TAG, "Directory not created");
        }
//        return file;
        String timeStamp =
                new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        File mediaFile = null;
        mediaFile = new File(file.getPath() + File.separator + "IMG_" + timeStamp +
                ".jpg");

        return mediaFile;
    }

    private static File getMediaFileName() {
// Lokasi External sdcard
//        File mediaStorageDir = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),"CameraDemo");
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "CameraDemo");
// Buat directori tidak direktori tidak eksis
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d("CameraDemo", "Gagal membuat directory" + "CameraDemo");
                return null;
            }
        }
        // Membuat nama file
        String timeStamp =
                new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        File mediaFile = null;
        mediaFile = new File(mediaStorageDir.getPath() + File.separator + "IMG_" + timeStamp +
                ".jpg");

        return mediaFile;
    }

}
