package com.example.michaeliwe.michael_1202160197_si4004_pab_modul4;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {
    EditText email, password;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        mAuth = FirebaseAuth.getInstance();
    }

    @SuppressLint("StaticFieldLeak")
    public void login(View view){
        if (check()) {
            new AsyncTask<Void,Void,Boolean>(){
                @Override
                protected Boolean doInBackground(Void... voids) {
                    mAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        startActivity(new Intent(login.this, MainActivity.class));
                                        finish();
                                    } else {
                                        Toast.makeText(login.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                    return null;
                }

                @Override
                protected void onPreExecute() {
                    Toast.makeText(login.this, "Sign In...", Toast.LENGTH_SHORT).show();

                }

                @Override
                protected void onPostExecute(Boolean aBoolean) {
                    super.onPostExecute(aBoolean);
                }
            }.execute();
        }
    }

    public void goToRegister(View view){
        startActivity(new Intent(login.this,register.class));
        finish();
    }

    public boolean check(){
        if (email.getText().toString().equals("")){
            buat_toast("Tolong isi dahulu kolom email");
            email.setText("");
            return false;
        }
        if (password.getText().toString().equals("")){
            buat_toast("Tolong isi dahulu kolom password");
            password.setText("");
            return false;
        }
        return true;
    }

    public void buat_toast(String isi){
        Toast toast = Toast.makeText(getApplicationContext(),
                isi,
                Toast.LENGTH_SHORT);
        toast.show();
    }
}
