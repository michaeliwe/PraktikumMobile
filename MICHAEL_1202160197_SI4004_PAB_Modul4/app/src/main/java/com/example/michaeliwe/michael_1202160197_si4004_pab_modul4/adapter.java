package com.example.michaeliwe.michael_1202160197_si4004_pab_modul4;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

public class adapter extends RecyclerView.Adapter<adapter.ViewHolder>{
    private ArrayList<menu> daftarChat;
    private Context mContext;

    public adapter(ArrayList<menu> daftarChat, Context mContext) {
        this.daftarChat = daftarChat;
        this.mContext = mContext;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new adapter.ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.menu, parent, false)) {
        };
    }

    @Override
    public void onBindViewHolder(adapter.ViewHolder holder, int position) {
        menu card = daftarChat.get(position);
        holder.bindTo(card);

    }

    @Override
    public int getItemCount() {
        return daftarChat.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView nama, harga;
        private ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nama = itemView.findViewById(R.id.txJudulCard);
            harga = itemView.findViewById(R.id.txHarga);
            image = itemView.findViewById(R.id.cardImg);


            itemView.setOnClickListener(this);
        }

        @SuppressLint("StaticFieldLeak")
        public void bindTo(final menu card) {
            nama.setText(card.getNama());
            harga.setText(card.getHarga());

            final StorageReference islandRef = FirebaseStorage.getInstance().getReference().child("images/" + card.getImagePath());

            final long ONE_MEGABYTE = 10* 1024 * 1024;
            islandRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                @Override
                public void onSuccess(byte[] bytes) {
                    Drawable d = Drawable.createFromStream(new ByteArrayInputStream(bytes), null);
                    image.setImageDrawable(d);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    image.setImageResource(R.drawable.ic_launcher_background);
                }
            });
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(mContext, nama.getText().toString(), Toast.LENGTH_SHORT).show();
        }
    }
}
