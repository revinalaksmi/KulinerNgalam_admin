package com.example.alfinda.modul.Adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.alfinda.modul.LayarEditUser;
import com.example.alfinda.modul.Model.User;
import com.example.alfinda.modul.R;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    List<User> listUser;

    public UserAdapter(List<User> listUser) {
        this.listUser = listUser;
    }

    @Override
    public UserAdapter.UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_user, parent, false);
        UserAdapter.UserViewHolder mHolder = new UserAdapter.UserViewHolder(view);
        return mHolder;
    }

    @Override
    public void onBindViewHolder(UserAdapter.UserViewHolder holder, final int position) {

        holder.tvIdUser.setText(listUser.get(position).getIdUser());
        holder.tvUsername.setText(listUser.get(position).getUsername());
        holder.tvPassword.setText(listUser.get(position).getPassword());
        holder.tvNama.setText(listUser.get(position).getNama());
        holder.tvJk.setText(listUser.get(position).getJk());
        holder.tvEmail.setText(listUser.get(position).getEmail());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), LayarEditUser.class);
                intent.putExtra("idUser", listUser.get(position).getIdUser());
                intent.putExtra("username", listUser.get(position).getUsername());
                intent.putExtra("password", listUser.get(position).getPassword());
                intent.putExtra("nama", listUser.get(position).getNama());
                intent.putExtra("jk", listUser.get(position).getJk());
                intent.putExtra("email", listUser.get(position).getEmail());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listUser.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        TextView tvIdUser, tvUsername, tvPassword, tvNama, tvJk, tvEmail;

        public UserViewHolder(View itemView) {
            super(itemView);
            tvIdUser = (TextView) itemView.findViewById(R.id.tvIdUser);
            tvUsername = (TextView) itemView.findViewById(R.id.tvUsername);
            tvPassword = (TextView) itemView.findViewById(R.id.tvPassword);
            tvNama = (TextView) itemView.findViewById(R.id.tvNama);
            tvJk = (TextView) itemView.findViewById(R.id.tvJk);
            tvEmail = (TextView) itemView.findViewById(R.id.tvEmail);
        }
    }
}
