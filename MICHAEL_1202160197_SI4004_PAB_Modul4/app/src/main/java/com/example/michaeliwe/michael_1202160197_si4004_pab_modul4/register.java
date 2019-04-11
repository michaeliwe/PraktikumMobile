package com.example.michaeliwe.michael_1202160197_si4004_pab_modul4;

import android.content.Intent;
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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class register extends AppCompatActivity {
    EditText name, email, password;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        mAuth = FirebaseAuth.getInstance();

    }

    public void regist(View view){
        if (check()){
            mAuth = FirebaseAuth.getInstance();
            mAuth.createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString())
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                FirebaseUser user = mAuth.getCurrentUser();
                                UserProfileChangeRequest userProfileChangeRequest = new UserProfileChangeRequest.Builder().setDisplayName(name.getText().toString()).build();

                                user.updateProfile(userProfileChangeRequest);
                                startActivity(new Intent(register.this,login.class));
                                finish();
                            } else {
                                buat_toast("Register gagal");
                            }
                        }
                    });
        }
    }

    public void goToLogin(View view){
        startActivity(new Intent(register.this,login.class));
        finish();
    }

    public boolean check(){
        if (name.getText().toString().equals("")){
            buat_toast("Tolong isi dahulu kolom name");
            password.setText("");
            return false;
        }
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
