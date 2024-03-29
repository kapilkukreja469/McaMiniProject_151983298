package com.example.mcaminiproject_151983298;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class OtpActivity extends AppCompatActivity {
    TextView one;
    FirebaseAuth auth;
    String verifyCode;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        one = findViewById(R.id.one);
        progressBar = findViewById(R.id.probar2);
        auth = FirebaseAuth.getInstance();
        getSupportActionBar().hide();
        verifyCode = getIntent().getStringExtra("OTP");
    }
    public void verify(View view) {
        String inputCode = one.getText().toString();
        if (!inputCode.equals("")) {
            progressBar.setVisibility(View.VISIBLE);
            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verifyCode, inputCode);
            signInWithPhone(credential);
        } else Toast.makeText(this, "Enter otp", Toast.LENGTH_SHORT).show();
    }

    private void signInWithPhone(PhoneAuthCredential credential) {
        auth.signInWithCredential(credential)
                .addOnCompleteListener(OtpActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(OtpActivity.this, "sign in successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(OtpActivity.this, HomeActivity.class));
                            finish();
                        } else if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                            Toast.makeText(OtpActivity.this, "wrong Otp", Toast.LENGTH_SHORT).show();
                        } else
                            Toast.makeText(OtpActivity.this, "wrong Otp", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
