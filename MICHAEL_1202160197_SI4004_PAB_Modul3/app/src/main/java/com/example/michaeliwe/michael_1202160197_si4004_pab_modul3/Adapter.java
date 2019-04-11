package com.example.michaeliwe.michael_1202160197_si4004_pab_modul3;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private ArrayList<User> daftarUser;
    private Context mContext;

    public Adapter(ArrayList<User> daftarUser, Context mContext) {
        this.daftarUser = daftarUser;
        this.mContext = mContext;
    }

    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(mContext).
                inflate(R.layout.list_user, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(Adapter.ViewHolder viewHolder, int urutan) {
        User currentUser = daftarUser.get(urutan);
        viewHolder.setList(currentUser);
    }

    @Override
    public int getItemCount() {
        return daftarUser.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView nama, title;
        private ImageView foto;
        private int jk;

        public ViewHolder(View itemView) {
            super(itemView);

            nama = itemView.findViewById(R.id.nama);
            title = itemView.findViewById(R.id.title);
            foto = itemView.findViewById(R.id.photo);

            itemView.setOnClickListener(this);
        }

        void setList(User user){
            nama.setText(user.getNama());
            title.setText(user.getTitle());

            jk = user.getJk();
            if (jk == 1){
                foto.setImageResource(R.drawable.male);
            } else {
                foto.setImageResource(R.drawable.female);
            }
        }

        @Override
        public void onClick(View v) {
            Intent to_detail = new Intent(v.getContext(),Detail.class);
            to_detail.putExtra("nama",nama.getText().toString());
            to_detail.putExtra("jk",jk);
            to_detail.putExtra("title",title.getText().toString());
            v.getContext().startActivity(to_detail);
        }
    }

}

