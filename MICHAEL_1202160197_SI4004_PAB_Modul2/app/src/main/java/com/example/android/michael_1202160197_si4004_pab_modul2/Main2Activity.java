package com.example.android.michael_1202160197_si4004_pab_modul2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    public static final String TAG = "Main2Activity";
    String sTujuan, sBerangkat, sPulang, sTiket, sHarga_total, sSaldo;
    TextView tujuan, berangkat, pulang, tiket, harga_total, label_pulang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent in = getIntent();
        sTujuan = in.getStringExtra("tujuan");
        sBerangkat = in.getStringExtra("berangkat");
        sPulang = in.getStringExtra("pulang");
        sTiket = in.getStringExtra("tiket");
        sHarga_total = in.getStringExtra("harga_total");
        sSaldo = in.getStringExtra("saldo");

        tujuan = (TextView) findViewById(R.id.tujuan);
        berangkat = (TextView) findViewById(R.id.berangkat);
        pulang = (TextView) findViewById(R.id.pulang);
        label_pulang = (TextView) findViewById(R.id.label_pulang);
        tiket = (TextView) findViewById(R.id.tiket);
        harga_total = (TextView) findViewById(R.id.harga_total);

        tujuan.setText(sTujuan);
        berangkat.setText(sBerangkat);
        pulang.setText(sPulang);
        tiket.setText(sTiket);
        harga_total.setText("Rp " + sHarga_total);

        if (sPulang.equals("Pilih Tanggal - Pilih Waktu")){
            pulang.setVisibility(View.GONE);
            label_pulang.setVisibility(View.GONE);
        }
    }

    public void onClick_konfirmasi(View view){
        Intent intent = new Intent(Main2Activity.this,MainActivity.class);
        Main2Activity.this.startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "ini onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "ini onRestart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "ini onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "ini onResume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "ini onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "ini onDestroy");
    }
}
