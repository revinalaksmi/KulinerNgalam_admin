package com.example.alfinda.modul.Adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.alfinda.modul.LayarEditSuka;
import com.example.alfinda.modul.Model.Suka;
import com.example.alfinda.modul.R;

import java.util.List;

public class SukaAdapter extends RecyclerView.Adapter<SukaAdapter.SukaViewHolder> {
    List<Suka> listSuka;

    public SukaAdapter(List<Suka> listSuka) {
        this.listSuka = listSuka;
    }

    @Override
    public SukaAdapter.SukaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_suka, parent, false);
        SukaAdapter.SukaViewHolder mHolder = new SukaAdapter.SukaViewHolder(view);
        return mHolder;
    }

    @Override
    public void onBindViewHolder(SukaAdapter.SukaViewHolder holder, final int position) {

        holder.tvIdSuka.setText(listSuka.get(position).getIdSuka());
        holder.tvIdUser.setText(listSuka.get(position).getIdUser());
        holder.tvIdMakanan.setText(listSuka.get(position).getIdMakanan());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), LayarEditSuka.class);
                intent.putExtra("idSuka", listSuka.get(position).getIdSuka());
                intent.putExtra("idUser", listSuka.get(position).getIdUser());
                intent.putExtra("idMakanan", listSuka.get(position).getIdMakanan());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listSuka.size();
    }

    public class SukaViewHolder extends RecyclerView.ViewHolder {
        TextView tvIdUser, tvIdSuka, tvIdMakanan;

        public SukaViewHolder(View itemView) {
            super(itemView);
            tvIdUser = (TextView) itemView.findViewById(R.id.tvIdUser);
            tvIdSuka = (TextView) itemView.findViewById(R.id.tvIdSuka);
            tvIdMakanan = (TextView) itemView.findViewById(R.id.tvIdMakanan);
        }
    }
}
