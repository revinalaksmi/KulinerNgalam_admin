package com.example.alfinda.modul.Adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.alfinda.modul.LayarEditMakanan;
import com.example.alfinda.modul.Model.Makanan;
import com.example.alfinda.modul.R;
import com.example.alfinda.modul.Rest.ApiClient;

import java.util.List;

public class MakananAdapter extends RecyclerView.Adapter<MakananAdapter.MakananViewHolder> {
    List<Makanan> listMakanan;

    public MakananAdapter(List<Makanan> listMakanan) {
        this.listMakanan = listMakanan;
    }

    @Override
    public MakananAdapter.MakananViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_makanan, parent, false);
        MakananViewHolder mHolder = new MakananViewHolder(view);
        return mHolder;
    }

    @Override
    public void onBindViewHolder(MakananAdapter.MakananViewHolder holder, final int position) {

        holder.tvIdMakanan.setText(listMakanan.get(position).getIdMakanan());
        holder.tvMenu.setText(listMakanan.get(position).getMenu());
        holder.tvKategori.setText(listMakanan.get(position).getKategori());
        holder.tvHarga.setText(listMakanan.get(position).getHarga());
        holder.tvAlamat.setText(listMakanan.get(position).getAlamat());
        holder.tvReview.setText(listMakanan.get(position).getReview());
        holder.tvTanggal.setText(listMakanan.get(position).getTanggal());
//        holder.tvTotalSuka.setText(String.valueOf(listMakanan.get(position).getSuka()));
//        holder.tvTotalKomentar.setText(String.valueOf(listMakanan.get(position).getKomentar()));
        if (listMakanan.get(position).getGambar() != null ){
//            Picasso.with(holder.itemView.getContext()).load(ApiClient.BASE_URL+listPembeli.get(position).getPhotoId())
//                    .into(holder.mPhotoURL);

            //--tambahan--
            Glide.with(holder.itemView.getContext()).load(ApiClient.BASE_URL +
                    listMakanan.get(position).getGambar()).into(holder.mImage);
        } else {
//          Picasso.with(holder.itemView.getCopembelintext()).load(R.drawable.photoid).into(holder
// .mPhotoURL);
            Glide.with(holder.itemView.getContext()).load(R.drawable.default_user).into(holder
                    .mImage);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), LayarEditMakanan.class);
                intent.putExtra("idMakanan", listMakanan.get(position).getIdMakanan());
                intent.putExtra("menu", listMakanan.get(position).getMenu());
                intent.putExtra("kategori", listMakanan.get(position).getKategori());
                intent.putExtra("harga", listMakanan.get(position).getHarga());
                intent.putExtra("alamat", listMakanan.get(position).getAlamat());
                intent.putExtra("review", listMakanan.get(position).getReview());
                intent.putExtra("tanggal", listMakanan.get(position).getTanggal());
                intent.putExtra("suka", String.valueOf(listMakanan.get(position).getSuka()));
                intent.putExtra("komentar", String.valueOf(listMakanan.get(position).getKomentar()));
                intent.putExtra("gambar", listMakanan.get(position).getGambar());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listMakanan.size();
    }

    public class MakananViewHolder extends RecyclerView.ViewHolder {
        ImageView mImage;
        TextView tvIdMakanan, tvMenu, tvKategori, tvHarga, tvAlamat, tvReview, tvTanggal, tvTotalSuka, tvTotalKomentar;

        public MakananViewHolder(View itemView) {
            super(itemView);
            tvIdMakanan = (TextView) itemView.findViewById(R.id.tvIdMakanan);
            tvKategori = (TextView) itemView.findViewById(R.id.tvKategori);
            tvMenu = (TextView) itemView.findViewById(R.id.tvMenu);
            mImage = (ImageView) itemView.findViewById(R.id.imgMakanan);
            tvHarga = (TextView) itemView.findViewById(R.id.tvHarga);
            tvAlamat = (TextView) itemView.findViewById(R.id.tvAlamat);
            tvReview = (TextView) itemView.findViewById(R.id.tvReview);
            tvTanggal = (TextView) itemView.findViewById(R.id.tvTanggal);
//            tvTotalSuka = (TextView) itemView.findViewById(R.id.tvTotalSuka);
//            tvTotalKomentar = (TextView) itemView.findViewById(R.id.tvTotalKomentar);
        }
    }
}