package com.example.michaeliwe.michael_1202160197_si4004_pab_modul3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Detail extends AppCompatActivity {

    private TextView nama, title;
    private ImageView foto;
    private int jk;

    private String sNama, sTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        nama = findViewById(R.id.detail_nama);
        title = findViewById(R.id.detail_title);
        foto = findViewById(R.id.detail_photo);

        sNama = getIntent().getStringExtra("nama");
        sTitle = getIntent().getStringExtra("title");
        jk = getIntent().getIntExtra("jk",1);

        nama.setText(sNama);
        title.setText(sTitle);

        if (jk == 1){
            foto.setImageResource(R.drawable.male);
        } else {
            foto.setImageResource(R.drawable.female);
        }
    }
}
