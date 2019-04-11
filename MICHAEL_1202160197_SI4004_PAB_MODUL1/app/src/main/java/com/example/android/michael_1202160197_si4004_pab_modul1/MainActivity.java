package com.example.android.michael_1202160197_si4004_pab_modul1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText a, b;
    private TextView hasil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        a = (EditText) findViewById(R.id.editText);
        b = (EditText) findViewById(R.id.editText2);
        hasil = (TextView) findViewById(R.id.hasil);
    }

    public void hitung(View view){
        String Spanjang = a.getText().toString();
        String Slebar = b.getText().toString();

        int panjang = Integer.parseInt(Spanjang);
        int lebar = Integer.parseInt(Slebar);

        int luas = panjang*lebar;

        hasil.setText(Integer.toString(luas));


    }
}
