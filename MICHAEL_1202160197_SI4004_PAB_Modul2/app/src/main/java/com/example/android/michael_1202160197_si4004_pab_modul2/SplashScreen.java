package com.example.android.michael_1202160197_si4004_pab_modul2;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

public class SplashScreen extends AppCompatActivity {
    public static final String TAG = "SplashScreen";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //menghilangkan ActionBar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash_screen);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        }, 5000L); //3000 L = 3 detik
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